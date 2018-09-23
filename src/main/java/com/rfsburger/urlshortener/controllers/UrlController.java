package com.rfsburger.urlshortener.controllers;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rfsburger.urlshortener.services.UrlService;

@Controller
public class UrlController {

    public static final String SHORT = "/short";

    @Autowired
    private UrlService urlService;

    @RequestMapping(value = SHORT, method = RequestMethod.GET)
    public String getLongUrl(@RequestParam String shrt, final Model model) {
        Optional<String> longUrl = urlService.getLongUrl(shrt);
        if (longUrl.isPresent()) {
            return "redirect:" + longUrl.get();
        } else {
            model.addAttribute("shortUrl", shrt);
            return "error";
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getMainPage() {
        return "main";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createShortUrl(@RequestParam String longUrl, final Model model, final HttpServletRequest request) {
        if (validateUrl(longUrl)) {
            model.addAttribute("shortUrl", getShortenedUrl(longUrl, request));
            return "shorturl";
        } else {
            model.addAttribute("longUrl", longUrl);
            return "invalid";
        }
    }

    private String getShortenedUrl(@RequestParam String longUrl, HttpServletRequest request) {
        return getBaseUrl(request) + SHORT.substring(1) + "?shrt=" + urlService.getOrCreateShortUrl(longUrl);
    }

    private String getBaseUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
    }

    private boolean validateUrl(String longUrl) {
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return (urlValidator.isValid(longUrl));
    }
}

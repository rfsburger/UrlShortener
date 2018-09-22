package com.rfsburger.urlshortener.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rfsburger.urlshortener.services.UrlService;

@Controller
public class UrlController {

    @Autowired
    private UrlService urlService;

    @RequestMapping(value = "/shrt")
    public String getShortUrl(@RequestParam String l){
        return "redirect:" + urlService.getLongUrl(l);
    }
}

package com.rfsburger.urlshortener.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rfsburger.urlshortener.daos.UrlDao;
import com.rfsburger.urlshortener.services.UrlService;

@Service
public class DefaultUrlService implements UrlService {

    @Autowired
    private UrlDao urlDao;

    @Override
    public String getLongUrl(String shortUrl) {
        return urlDao.getLongUrl(shortUrl);
    }
}

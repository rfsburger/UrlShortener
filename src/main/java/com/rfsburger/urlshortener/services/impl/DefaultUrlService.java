package com.rfsburger.urlshortener.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rfsburger.urlshortener.daos.UrlDao;
import com.rfsburger.urlshortener.services.UrlService;

@Service
public class DefaultUrlService implements UrlService {

    @Autowired
    @Qualifier(value = "urlSqlDao")
    private UrlDao urlDao;

    @Override
    public Optional<String> getLongUrl(String shortUrl) {
        return urlDao.getLongUrl(shortUrl);
    }

    @Override
    public String getOrCreateShortUrl(String longUrl) {
        return urlDao.getOrCreateShortUrl(longUrl);
    }


}

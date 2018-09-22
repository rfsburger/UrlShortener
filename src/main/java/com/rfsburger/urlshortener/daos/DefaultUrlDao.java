package com.rfsburger.urlshortener.daos;

import org.springframework.stereotype.Repository;

@Repository
public class DefaultUrlDao implements UrlDao{

    @Override
    public String getLongUrl(String shortUrl) {
        return "http://www.google.com";
    }
}

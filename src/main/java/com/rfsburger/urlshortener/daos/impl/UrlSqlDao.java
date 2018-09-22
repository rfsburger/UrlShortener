package com.rfsburger.urlshortener.daos.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rfsburger.urlshortener.daos.UrlDao;

@Repository
public class UrlSqlDao implements UrlDao {

    @Override
    public Optional<String> getLongUrl(String shortUrl) {
        return null;
    }

    @Override
    public String getOrCreateShortUrl(String longUrl) {
        return null;
    }
}

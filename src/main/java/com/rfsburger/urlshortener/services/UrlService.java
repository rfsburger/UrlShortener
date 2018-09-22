package com.rfsburger.urlshortener.services;

import java.util.Optional;

public interface UrlService {

    Optional<String> getLongUrl(String shortUrl);

    String getOrCreateShortUrl(String longUrl);
}

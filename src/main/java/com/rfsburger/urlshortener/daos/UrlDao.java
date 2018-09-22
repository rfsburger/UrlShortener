package com.rfsburger.urlshortener.daos;

import java.util.Optional;

public interface UrlDao {

    Optional<String> getLongUrl(String shortUrl);

    String getOrCreateShortUrl(String longUrl);
}

package com.rfsburger.urlshortener.daos.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rfsburger.urlshortener.daos.UrlDao;

@Repository
public class UrlSqlDao implements UrlDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<String> getLongUrl(String shortUrl) {
        List<String> potentialLongUrl = jdbcTemplate.queryForList("SELECT longUrl FROM urls WHERE shortUrl = :shortUrl", new MapSqlParameterSource().addValue("shortUrl", shortUrl), String.class);
        if(!potentialLongUrl.isEmpty()){
            return Optional.of(potentialLongUrl.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional("tjtJTransactionManager")
    public String getOrCreateShortUrl(String longUrl) {
        List<String> potentialShort = jdbcTemplate.queryForList("SELECT shortUrl FROM urls WHERE longUrl = :longUrl", new MapSqlParameterSource().addValue("longUrl", longUrl), String.class);
        if (!potentialShort.isEmpty()) {
            return potentialShort.get(0);
        } else {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update("INSERT INTO urls (longUrl) VALUE (:longUrl)",  new MapSqlParameterSource().addValue("longUrl", longUrl), holder);
            return holder.getKey().toString();
        }
    }
}
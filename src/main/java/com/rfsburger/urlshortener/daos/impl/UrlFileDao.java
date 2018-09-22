package com.rfsburger.urlshortener.daos.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rfsburger.urlshortener.daos.UrlDao;

/**
 * FileDao that persists the hashmap to a file instead of a DB.
 */
@Repository
public class UrlFileDao implements UrlDao {

    /**
     * File relative to location of tomcat folder
     */
    public static final String FILE_LOCATION = "urls.txt";

    @Override
    public Optional<String> getLongUrl(String shortUrl) {
        return readFile().entrySet().stream().filter(entry -> entry.getKey().equals(shortUrl)).map(Map.Entry::getValue).findFirst();
    }

    @Override
    public String getOrCreateShortUrl(String longUrl) {
        HashMap<String, String> map = readFile();
        Optional optionalValue = map.entrySet().stream().filter(entry -> entry.getValue().equals(longUrl)).map(Map.Entry::getKey).findFirst();
        if (optionalValue.isPresent()) {
            return (String) optionalValue.get();
        } else {
            return createUrlPair(longUrl, map);
        }
    }

    private void writeFile(HashMap<String, String> urls) {
        Path path = Paths.get(FILE_LOCATION);
        try (ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(path))) {
            os.writeObject(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> readFile() {
        Path path = Paths.get(FILE_LOCATION);
        if (path.toFile().exists()) {
            try (ObjectInputStream os = new ObjectInputStream(Files.newInputStream(path))) {
                return (HashMap<String, String>) os.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new HashMap<String, String>();
    }

    private String createUrlPair(String longUrl, HashMap<String, String> map) {
        String key = Integer.toString(map.size() + 1);
        map.put(key, longUrl);
        writeFile(map);
        return key;
    }

}

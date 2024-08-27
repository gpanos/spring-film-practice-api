package com.example.spring_film_api.services;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String store(MultipartFile file) throws IOException;

    String store(String path, MultipartFile file) throws IOException;

    String getUrl(String filename) throws IOException;

    InputStream get(String filename) throws IOException;

    void delete(String filename) throws IOException;

    boolean exists(String filename);
}

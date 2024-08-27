package com.example.spring_film_api.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.spring_film_api.exception.StorageException;

public class LocalStorageService implements StorageService {

    private final Path rootLocation;

    public LocalStorageService(String uploadDir) {
        this.rootLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @Override
    public String store(MultipartFile file) throws IOException {
        String originalFilename = Optional.ofNullable(file.getOriginalFilename()).orElse("unnamed");
        String filename = UUID.randomUUID() + "-" + StringUtils.cleanPath(originalFilename);
        return store(filename, file);
    }

    @Override
    public String store(String path, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file " + path);
        }
        if (path.contains("..")) {
            // This is a security check
            throw new StorageException(
                    "Cannot store file with relative path outside current directory " + path);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Path destinationFile =
                    this.rootLocation.resolve(Paths.get(path)).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return path;
    }

    @Override
    public InputStream get(String filename) throws IOException {
        Path file = rootLocation.resolve(filename);
        return Files.newInputStream(file);
    }

    @Override
    public void delete(String filename) throws IOException {
        Path file = rootLocation.resolve(filename);
        Files.deleteIfExists(file);
    }

    @Override
    public String getUrl(String filename) {
        return "/storage/" + filename;
    }

    @Override
    public boolean exists(String filename) {
        Path file = rootLocation.resolve(filename);
        return Files.exists(file);
    }
}

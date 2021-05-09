package com.test.mykola.service.impl;

import com.test.mykola.dao.Goods;
import com.test.mykola.exception.FileNotFoundException;
import com.test.mykola.exception.FileSaveException;
import com.test.mykola.service.StorageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path fileStorageLocation;

    public StorageServiceImpl(@Value("${file.storage.path}") String filesStoragePath) {
        this.fileStorageLocation = Paths.get(filesStoragePath).toAbsolutePath().normalize();
    }

    /**
     * Save image to server.
     *
     * @param file the image file.
     * @return the response entity with absolute path to image.
     * @throws FileSaveException if image cannot to save.
     */
    @SneakyThrows
    public ResponseEntity<Goods> save(MultipartFile file) {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileName = System.currentTimeMillis() + originalFileName.substring(originalFileName.lastIndexOf("."));

        try {
            Files.copy(file.getInputStream(), fileStorageLocation.resolve(fileName));
        } catch (Exception e) {
            throw new FileSaveException(e.getMessage());
        }

        return ResponseEntity.ok(Goods.builder().image(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/image/" + fileName).build());
    }

    /**
     * Load image from server.
     *
     * @param filename the name of image.
     * @return the image.
     * @throws FileNotFoundException if image does not exist.
     * @throws ServerErrorException if image cannot load from server.
     */
    @SneakyThrows
    @Override
    public ResponseEntity<Resource> load(String filename) {
        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            } else {
                throw new FileNotFoundException(filename);
            }
        } catch (MalformedURLException e) {
            throw new ServerErrorException(e.getMessage(), e);
        }
    }
}

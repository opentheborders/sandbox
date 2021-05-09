package com.test.mykola.controller;

import com.test.mykola.dao.Goods;
import com.test.mykola.exception.FileNotFoundException;
import com.test.mykola.exception.FileSaveException;
import com.test.mykola.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerErrorException;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/image")
public class FileStorageController {

    @Autowired StorageService storageService;

    //1.1: return image
    /**
     * Load image from server.
     *
     * @param imageName the name of image.
     * @return the image.
     * @throws FileNotFoundException if image does not exist.
     * @throws ServerErrorException if image cannot load from server.
     */
    @GetMapping(value = "/{imageName:.+}")
    public ResponseEntity<Resource> getImageById(@PathVariable String imageName) {
        return storageService.load(imageName);
    }

    //1.2: save image
    /**
     * Save image to server.
     *
     * @param file the image file.
     * @return the response entity with absolute path to image.
     * @throws FileSaveException if image cannot to save.
     */
    @PostMapping(value = "/")
    public ResponseEntity<Goods> uploadGoodsImage(@RequestParam("image") @NotNull MultipartFile file) {
        return storageService.save(file);
    }
}

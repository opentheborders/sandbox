package com.test.mykola.service;

import com.test.mykola.dao.Goods;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    ResponseEntity<Goods> save(MultipartFile file);

    ResponseEntity<Resource> load(String filename);
}

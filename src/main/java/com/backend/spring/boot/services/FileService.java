package com.backend.spring.boot.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public interface FileService {

    String uploadImage(String path, MultipartFile file) throws IOException;

    InputStream getFile (String path, String file) throws FileNotFoundException;

}

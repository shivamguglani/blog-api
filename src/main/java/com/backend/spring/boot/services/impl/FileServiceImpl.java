package com.backend.spring.boot.services.impl;

import com.backend.spring.boot.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //full name
        String name=file.getOriginalFilename();


        //create random Id

        String randomId= UUID.randomUUID().toString();
        String extention=name.substring(name.lastIndexOf("."));
        System.out.println(extention);

        if (!extention.equalsIgnoreCase("."+"png")){
            System.out.println("Error throws");
        }

        String fileName1 = randomId.concat(extention);
//        System.out.println(randomId);
//        System.out.println(fileName1);

        //full path

        String filePath=path + File.separator+fileName1;



        // create folder if not created

        File f=new File(path);

        if (!f.exists()){
            f.mkdir();

        }

        //file copy

        Files.copy(file.getInputStream(), Paths.get(filePath));




        return fileName1;
    }

    @Override
    public InputStream getFile(String path, String file) throws FileNotFoundException {
        String fullPath=path+File.separator+file;
        InputStream is=new FileInputStream(fullPath);

        return is;
    }
}

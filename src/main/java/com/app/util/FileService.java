package com.app.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    @Value("${app.upload.dir}")
    public String uploadDir;

    @Value("${spring.api.baseUrl}")
    private String baseUrl;
    
    @Autowired
    ServletContext context;
    
    public String uploadFile(MultipartFile file, int counter) {
    	String prefix=baseUrl;
    	String allPath="";
        try {
      	  Date tim=new Date();
      	  String imageName=tim.getTime()+counter+".jpg";
//            Path copyLocation = Paths
//                .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
      	Path copyLocation = Paths.get(context.getRealPath("")+imageName) ;
        Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
       return prefix+imageName+",";
        
        } catch (Exception e) {
            e.printStackTrace();
           throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                + ". Please try again!");
        }
    }
}
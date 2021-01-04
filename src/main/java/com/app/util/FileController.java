package com.app.util;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("img/")
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/uploadFile")
    public String uploadFile( @RequestBody MultipartFile file) {

        fileService.uploadFile(file,0);


        return "success";
    }
    
    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestBody MultipartFile[] files, RedirectAttributes redirectAttributes) {

    	String imagePath="";
        
		for (MultipartFile file : files) {
			imagePath = fileService.uploadFile(file,0) + imagePath;
		}

//        redirectAttributes.addFlashAttribute("message",
//            "You successfully uploaded all files!");

        return imagePath;
    }
    
}
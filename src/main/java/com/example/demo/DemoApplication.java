
package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.FileDTO;
import com.example.demo.service.S3Services;
import com.example.demo.service.StorageService;


  @SpringBootApplication
  @RestController
  public class DemoApplication {
    
	  @Autowired
	  private StorageService storageService;
	  
	  @Autowired
	  S3Services s3Services;
      
      public static void main(String[] args) {
    	  SpringApplication.run(DemoApplication.class, args);
      }
      
      @PostMapping("/doUpload")
	  public FileDTO hello(@RequestBody MultipartFile file) {
    	  storageService.uploadFile(file);
    	  return s3Services.uploadFile(file.getOriginalFilename());
      }
    
  }

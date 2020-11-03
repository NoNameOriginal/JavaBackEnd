package com.example.demo.service.impl;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
 
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.dto.FileDTO;
import com.example.demo.service.S3Services;

@Service
public class S3ServicesImpl implements S3Services {
  
  
  @Autowired
  private AmazonS3 s3client;
 
  @Value("${jsa.s3.bucket}")
  private String bucketName;
 
  @Value("${upload.path}")
  private String path;
 
  @Override
  public FileDTO uploadFile(String keyName) {
	  
	  String uploadFilePath = path + keyName;
	  
      FileDTO fileDTO = new FileDTO();
    
    try {
      
      File file = new File(uploadFilePath);
      PutObjectRequest request = new PutObjectRequest(bucketName, keyName, uploadFilePath).withCannedAcl(CannedAccessControlList.PublicRead);
      s3client.putObject(request);
      URL s3Url = s3client.getUrl(bucketName, keyName);
      

      fileDTO.setFileName(keyName);
      fileDTO.setURLfile(s3Url.toString());
      
          
    } catch (Exception e) {
    	System.out.println(e);
    }
    return fileDTO;
  }
}
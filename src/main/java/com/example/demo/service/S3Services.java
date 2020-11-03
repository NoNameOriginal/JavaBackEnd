package com.example.demo.service;

import com.example.demo.dto.FileDTO;

public interface S3Services {
  public FileDTO uploadFile(String keyName);
}
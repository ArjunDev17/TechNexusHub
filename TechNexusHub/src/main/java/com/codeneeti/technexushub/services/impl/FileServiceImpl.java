package com.codeneeti.technexushub.services.impl;

import com.codeneeti.technexushub.exceptions.BadApiRequestException;
import com.codeneeti.technexushub.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        String orignalFileName=file.getOriginalFilename();
        logger.info("file name is :{}",orignalFileName);
        String fileName= UUID.randomUUID().toString();
        String extention=orignalFileName.substring(orignalFileName.lastIndexOf("."));
        String fileNameWithExtention=fileName+extention;
//        String fullPathWithFileName=path+ File.separator+fileNameWithExtention;
        String fullPathWithFileName=path+fileNameWithExtention;

        if(extention.equalsIgnoreCase(".pdf")||extention.equalsIgnoreCase(".png")||extention.equalsIgnoreCase(".jpeg")||extention.equalsIgnoreCase(".jpg")){
            //file save
            logger.info("full Path of folder :{}",fullPathWithFileName);
            File folder=new File(path);
            if (!folder.exists()){
                //make folder
                folder.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return  fileNameWithExtention;
        }else{
            throw new BadApiRequestException("file with this "+extention+"not allowed");
        }

    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath=path+File.separator+name;
        InputStream inputStream=new FileInputStream(fullPath);

        return inputStream;
    }
}

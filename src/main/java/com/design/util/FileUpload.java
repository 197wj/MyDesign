package com.design.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {  
	  
    //文件上传  
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {  
        String fileName = file.getOriginalFilename();  
        String path= PathUtil.getWebRootPath()+ File.separator + "images/";
        		//request.getSession().getServletContext().getRealPath("images/");  
        File tempFile = new File(path, new Date().getTime() + String.valueOf(fileName));  
        if (!tempFile.getParentFile().exists()) {  
            tempFile.getParentFile().mkdir();  
        }  
        if (!tempFile.exists()) {  
            tempFile.createNewFile();  
        }  
        file.transferTo(tempFile);  
        return "images/" + tempFile.getName();  
    }  
  
}  

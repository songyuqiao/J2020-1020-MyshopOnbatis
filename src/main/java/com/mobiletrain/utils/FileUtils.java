package com.mobiletrain.utils;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUtils implements ServletContextListener {
    private static ServletContext application;
    //获取扩展名
    public static String getExtName(String filename) {

        String[] split = filename.split("\\.");
        if(split.length == 1){
            return "";
        }else{
            int result = (split.length - 1);
            return "." + split[result];
        }
    }

    public static String storage(Part picture) throws IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        //获取当前时分秒
        String format = simpleDateFormat.format(date);
        //直接获取文件名
        String extName = FileUtils.getExtName(picture.getSubmittedFileName());
        //获取文件名
        String filename = UUID.randomUUID().toString() + extName;
        //获取相对路径
        String relative = "prodimg/"+format;
        //根据相对路径，获取绝对路径
        String realPath = application.getRealPath(relative);

        //创建目录
        File file = new File(realPath);
        file.mkdirs();

        FileOutputStream fis = new FileOutputStream(realPath+"/"+filename);
        IOUtils.copy(picture.getInputStream() , fis);
        fis.close();

        return relative+"/"+filename;


    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        application = event.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

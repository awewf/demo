package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName FileUploadController
 * @Description 实现文件上传
 * @Author GuanLS
 * @Date 2019/9/6 10:28
 * @Version 1.0
 **/
@Controller
public class FileUploadController {

    /**
     * @Author GuanLS
     * @Description //获取file页面
     * @Date 10:48 2019/9/6
     * @return java.lang.String
     **/
    @RequestMapping("/file")
    public String file(){
        return "/file";
    }

    /**
     * @Author GuanLS
     * @Description //实现文件上传
     * @Date 10:48 2019/9/6
     * @Param [file]
     * @return java.lang.String
     **/
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file")  MultipartFile file){
        if (file.isEmpty()){
            return "false";
        }
        //获取上传文件的名字
        String fileName = file.getOriginalFilename();
        //获取上传文件对应的字段名字 (file)
        //String name = file.getName();
        int size = (int)file.getSize();
        System.out.println(fileName + "-->" + size);

        //父目录
        String path = "F:\\testDownload";
        File dest = new File(path + "\\" + fileName);
        //判断父目录是否存在
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }

        try {
            //传送文件至dest下
            file.transferTo(dest);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    /**
     * @Author GuanLS
     * @Description //实现多文件上传
     * @Date 10:48 2019/9/6
     * @Param [file]
     * @return java.lang.String
     **/
    @RequestMapping("/multifile")
    public String multifile(){
        return "/multifile";
    }

    @PostMapping("/someFileUpload")
    @ResponseBody
    //public String someFileUpload(@RequestParam("fileName")List<MultipartFile> fileList){
    public String someFileUpload(MultipartHttpServletRequest request){
        List<MultipartFile> fileList = request.getFiles("fileName");

        if (null != fileList || fileList.size()>0){
            String path = "F:\\testDownload";
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }

            for (MultipartFile multipartFile : fileList){
                String fileName = multipartFile.getOriginalFilename();

                if (multipartFile.isEmpty()){
                    return "false";
                }else{
                    String filePath = path + "\\" + fileName;
                    File saveFile = new File(filePath);

                    try {
                        multipartFile.transferTo(saveFile);
                    }catch (Exception e){
                        e.printStackTrace();
                        return "false";
                    }
                }
            }
        }else{
            return "false";
        }

        return "true";
    }

    /**
     * @Author GuanLS
     * @Description //实现下载
     * @Date 14:48 2019/9/6
     * @Param [response]
     * @return java.lang.String
     **/
    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response){
        String filePath = "F:\\testDownload";
        String fileName = "创建JS.txt";

        File file = new File(filePath + "\\" + fileName);
        if (file.exists()){
            byte[] buffer = new byte[10240];
            //输入流
            FileInputStream fis = null;
            //缓存字节流
            BufferedInputStream bis = null;
            //输出流
            OutputStream os = null;

            try{
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName,"UTF-8"));

                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);

                while (i !=-1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("----------file download：" + fileName);

            try {
                os.close();
                bis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return "success";
    }
}

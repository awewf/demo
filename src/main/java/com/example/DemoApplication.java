package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

//@MapperScan("com.example.mapper")
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * @Author GuanLS
	 * @Description //设置上传文件的大小
	 * @Date 14:26 2019/9/6
	 * @return javax.servlet.MultipartConfigElement
	 **/
	@Bean
	public MultipartConfigElement multipartConfigElement(){
		MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
		//单个文件最大
		multipartConfigFactory.setMaxFileSize("10240KB");//KB、MB
		//设置总上传数据大小
		multipartConfigFactory.setMaxRequestSize("102400KB");

		return multipartConfigFactory.createMultipartConfig();
	}

}

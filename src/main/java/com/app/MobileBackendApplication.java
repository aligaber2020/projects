package com.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.app.service.categeory.DefaultCategeoryService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

@EnableSwagger2
@SpringBootApplication
//public class MobileBackendApplication extends WebMvcConfigurerAdapter
public class MobileBackendApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MobileBackendApplication.class);
    }
	 

    public static void main(String[] args)
    {
//    	ApplicationContext ctx=new ClassPathXmlApplicationContext("Spring.xml");
//    	HibernatedaoImpl obj=ctx.getBean("hibernatedaoImpl",HibernatedaoImpl.class);
    	
    
    	
        SpringApplication.run(MobileBackendApplication.class, args);

    }
    
    @Bean
    public ModelMapper modelMapper() {
      return new ModelMapper();
    }


//    @Override
//    public void addInterceptors(InterceptorRegistry registry)
//    {
//        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
//    }


    @Bean
    public Docket docket()
    {
       return new Docket(DocumentationType.SWAGGER_2)
           .select()
           .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
           .paths(PathSelectors.any())
           .build()
           .apiInfo(generateApiInfo());
   }


    private ApiInfo generateApiInfo()
    {
        return new ApiInfo("Mobile App Server Applicant  Service", "This service is to test mobile app services.", "Version 1.0 - mw",
            "urn:tos", "career@mobileApp.com", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    }
}

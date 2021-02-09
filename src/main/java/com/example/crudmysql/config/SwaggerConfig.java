package com.example.crudmysql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        //return a prepared docket instance
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.crudmysql.api"))
                .paths(PathSelectors.ant("/api/*"))
                .build()
                .apiInfo(apiInfo());  }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My REST API_Custmer_Mangement",
                "Sample API for Springboot_Mysql_Crud",
                "API TOS",
                "Free to use ",
                new Contact("Merwa MANSSAR", "www.example.com", "merwamanssar@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}

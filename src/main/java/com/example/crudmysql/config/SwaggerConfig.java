package com.example.crudmysql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
//@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket api(){
        //return a prepared docket instance
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.crudmysql.api"))
                .paths(PathSelectors.ant("/api/*"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes (Arrays.asList (securityScheme ()))
                .securityContexts (Arrays.asList (securityContext ()));  }



    // definir quelque valeurs par defaut
    @Bean
    public SecurityConfiguration security () {
        return SecurityConfigurationBuilder.builder ()
                .clientId ("Merwa")
                .clientSecret ("1234")
                .scopeSeparator ("")
                .useBasicAuthenticationWithAccessCodeGrant (true)
                .build ();
    }

    // decrire comment schema OAUTH2

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpoint("" + "/token", "oauthtoken"))
                .tokenRequestEndpoint(
                        new TokenRequestEndpoint("" + "/authorize", "Merwa", "1234"))
                .build();

        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
                .grantTypes(Arrays.asList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
        return oauth;
    }


    //les portées que nous devons avoir définies
    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations"),
                new AuthorizationScope("api", "Access api API") };
        return scopes;
    }


    // définir un contexte de sécurité de l'API
    private SecurityContext securityContext () {
        return SecurityContext.builder ()
                .securityReferences (
                        Arrays.asList (new SecurityReference ("spring_oauth", scopes ())))
                .forPaths (PathSelectors.regex ("/ api. *"))
                .build ( );
    }

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

package com.obinna.bucketlist.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.obinna.bucketlist.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(new ArrayList<>(Arrays
                        .asList(new ApiKey("Bearer %token", "Authorization", "Header"))));//
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Bucket List API",
                "Create users and assign bucket lists to them",
                "version 1.0.0",
                "",
                new Contact("Obinna Ogbonna", "",
                "obinnaogbonnajoseph@gmail.com"),
                "MIT Licence","http://opensource.org/licenses/MIT",
                Collections.emptyList());
    }
}

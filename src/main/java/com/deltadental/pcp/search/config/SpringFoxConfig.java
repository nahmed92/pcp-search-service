package com.deltadental.pcp.search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.deltadental.pcp.search.controller"))              
          .paths(PathSelectors.any())  
          .build().apiInfo(apiInfo());                                           
    }
    
    private ApiInfo apiInfo() {
        String description = "Delta Dental Ins -  PCP Search Service";
        return new ApiInfoBuilder()
                .title("REST API")
                .description(description)
                .version("1.0")
                .build();
    }
}
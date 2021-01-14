package com.education.rest.webservices.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
* This is a class used by Swagger that initializes the mapping of the endpoints for creating documentation and so on.
*
* The dependencies added for this are :
*
*   <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-boot-starter</artifactId>
        <version>3.0.0</version>
    </dependency>

    <dependency>
		<groupId>io.springfox</groupId>
		<artifactId>springfox-swagger2</artifactId>
		<!--<version>2.4.0</version>-->
	</dependency>

    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
    </dependency>
    *
    * After adding the dependencies and setting this Config Class. You can hit
    * both the Json version and the UI HTml version of swagger by hitting this URL's:
    *
    * UI : http://localhost:8080/swagger-ui/
    * JSON : http://localhost:8080/v2/api-docs
    *
    * More information : https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
*
* */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("Alan Sosa", "url link here", "alan.sosa.mejia@gmail.com");
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Awesome Api Title",
            "Awesome API Description",
            "1.0",
            "urn:tos",
           "DEFAULT_CONTACT",
            "Apache 2.0",
            "Apache Link Here"
    );
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json", "application/xml"));

    // Docket is the object that allows to add more characteristics to our documentation.
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}

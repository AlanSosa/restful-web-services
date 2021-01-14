package com.education.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	//TODO: COMMENT ABOUT THIS :
	//CHECK LESSON : STEP 18 INTERNATIONALIZATION FOR RESTFUL SERVICES
	@Bean
	public LocaleResolver localeResolver(){
		//There's a library automatically handles the "accept-locale" header.
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	//TODO: COMMENT ABOUT THIS :
	//CHECK LESSON : STEP 18 INTERNATIONALIZATION FOR RESTFUL SERVICES for more information
	//Honestly this doesn't seem interesting.
	@Bean
	public ResourceBundleMessageSource messageSource(){ //rename to messageSource()
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}

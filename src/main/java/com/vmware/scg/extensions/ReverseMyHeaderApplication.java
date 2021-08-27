package com.vmware.scg.extensions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@EnableAutoConfiguration()
@PropertySource("classpath:reverse-application.properties")
@ComponentScan("com.vmware.scg.extensions")
public class ReverseMyHeaderApplication {

	public static void main(String[]args) {
		SpringApplication.run(ReverseMyHeaderApplication.class, args);
	}

}

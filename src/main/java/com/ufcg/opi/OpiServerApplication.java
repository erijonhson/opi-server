package com.ufcg.opi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = { "com.ufcg" })
public class OpiServerApplication extends SpringBootServletInitializer {

//	@Bean
//	InitializingBean databaseSeeder() {
//		return () -> {
//			SomethingRepository.save(new Something());
//		};
//	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OpiServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(OpiServerApplication.class, args);
	}

}

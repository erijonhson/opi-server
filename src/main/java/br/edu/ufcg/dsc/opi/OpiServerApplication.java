package br.edu.ufcg.dsc.opi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = { "br.edu.ufcg.dsc.opi" })
public class OpiServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OpiServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(OpiServerApplication.class, args);
	}

}

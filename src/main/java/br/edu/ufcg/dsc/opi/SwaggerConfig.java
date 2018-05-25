package br.edu.ufcg.dsc.opi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact(
			"Eri Jonhson", 
			"https://github.com/erijonhson",
			"eri.silva@ccc.ufcg.edu.br" );

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
			.title("OPI Server Swagger API")
			.description("OPI Server Swagger API")
			.version("0.0.1")
			.termsOfServiceUrl("http://www.termsofservice.url")
			.contact(DEFAULT_CONTACT)
			.license("License")
			.licenseUrl("http://www.license.url")
			.build();

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json"));

	@Bean
	public Docket api() {
		final String REJECT_SLASH_AT_END = "^.*[^//]$";
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex(REJECT_SLASH_AT_END))
				.build();
	}

}

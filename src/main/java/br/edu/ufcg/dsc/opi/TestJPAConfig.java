package br.edu.ufcg.dsc.opi;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = { "br.edu.ufcg.dsc.opi" })
@PropertySource("test.properties")
@EnableTransactionManagement
public class TestJPAConfig {

}

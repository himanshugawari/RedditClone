package in.himanshugawari.reddit.config;

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
public class SwaggerConfiguration {
	@Bean
	public Docket redditCloneApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Reddit Clone API").version("1.0")
				.description("API for Reddit Clone Application")
				.contact(new Contact("Himanshu Gawari", "https://himanshu-gawari.in", "himanshu25031991@gmail.com"))
				.license("Apache License Version 2.0").build();
	}

	/*
	 * 
	 * @Bean public LinkDiscoverers discoverers() { List<LinkDiscoverer> plugins =
	 * new ArrayList<>(); plugins.add(new HalLinkDiscoverer()); return new
	 * LinkDiscoverers(SimplePluginRegistry.create(plugins)); }
	 * 
	 * @Bean public LinkRelationProvider provider() { return new
	 * EvoInflectorLinkRelationProvider(); }
	 * 
	 */
}

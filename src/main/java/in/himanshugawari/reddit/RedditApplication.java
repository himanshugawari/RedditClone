package in.himanshugawari.reddit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import in.himanshugawari.reddit.config.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@Import(value = { SwaggerConfiguration.class })
public class RedditApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditApplication.class, args);
	}

}

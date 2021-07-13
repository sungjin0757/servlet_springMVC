package study.servlettospringmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ServletToSpringmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletToSpringmvcApplication.class, args);
	}

}

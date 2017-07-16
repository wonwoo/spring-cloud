package me.wonwoo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}

@RestController
class UserRestController{

	private final RestTemplate restTemplate;

	UserRestController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("/users")
	@HystrixCommand(fallbackMethod = "usersFallback")
	public List<User> getUsers() {
		return restTemplate.exchange("http://user-service/users", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {}).getBody();
	}

	@GetMapping("/users/{name}")
	@HystrixCommand(fallbackMethod = "userFallback")
	public User getUser (@PathVariable String name) {
		return restTemplate.exchange("http://user-service/users/" + name, HttpMethod.GET, null,
				User.class).getBody();
	}

	public List<User> usersFallback() {
		return Collections.emptyList();
	}

	public User userFallback(String name) {
		return new User();
	}
}

class User {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
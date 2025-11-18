package com.github.mediumclone;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class MediumcloneApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MediumcloneApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		RestClient restClient = RestClient.builder().baseUrl("https://jsonplaceholder.typicode.com").build();

		final Todo body = restClient.get().uri("/todos/1").retrieve().body(Todo.class);

		System.out.println(body);


		final List<Todo> todos = restClient.get().uri("todos").retrieve().body(new ParameterizedTypeReference<List<Todo>>() {
		});

		System.out.println(todos);
		

	}

	@Bean
	ApplicationRunner applicationRunner(TodoClient todoClient) {
		return _ -> {
			System.out.println("feign call");
			final Todo todo = todoClient.getTodo(1);
			System.out.println(todo);
			final List<Todo> todos = todoClient.getTodos();
			System.out.println(todos);
			//todoClient.deleteTodo(1);
		};
	}

}
record Todo(Integer userId, Integer id, String title, boolean completed) {}

@FeignClient(name = "todoClient",url = "https://jsonplaceholder.typicode.com")
interface TodoClient {

	@GetMapping("/todos/{id}")
	Todo getTodo(@PathVariable Integer id);

	@GetMapping("/todos")
	List<Todo> getTodos();

	@DeleteMapping("/todos/{id}")
	void deleteTodo(Integer id);
}


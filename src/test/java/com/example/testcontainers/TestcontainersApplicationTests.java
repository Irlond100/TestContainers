package com.example.testcontainers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestcontainersApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
//	@Container
	private static final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
			.withExposedPorts(8080);
//	@Container
	private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
			.withExposedPorts(8081);

	@BeforeAll
	public static void setUp() {
		devApp.start();
		prodApp.start();
	}

	@Test
	void ContextLoads() {
		ResponseEntity<String> devEntity = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8080) + "/profile",
				String.class);
		ResponseEntity<String> prodEntity = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8081) + "/profile",
				String.class);
//		System.out.println(devEntity.getBody() + "Dev");
//		System.out.println(prodEntity.getBody() + "Prod");
		assertEquals(devEntity.getBody(), "Current profile is dev");
		assertEquals(prodEntity.getBody(), "Current profile is production");
	}

}

package com.skcodify;

import com.skcodify.controllers.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.LinkedHashSet;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private ProductController productController;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws Exception{
		assert(productController != null);
	}

	@Test
	void findAllShouldReturnList(){
		List list = this.restTemplate.getForObject("http://localhost:"
						+ port + "/products", List.class);
		assert (!list.isEmpty());
	}

}

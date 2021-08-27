package com.vmware.scg.extensions;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = { ReverseMyHeaderApplication.class }
)
class ReverseMyHeaderGatewayFilterFactoryTest {

	@LocalServerPort
	String port;

	@Autowired
	private WebTestClient client;

	@BeforeEach
	public void clearLogList() {
		client = WebTestClient.bindToServer()
							  .baseUrl("http://localhost:" + port)
							  .build();
	}

	@Test
	public void whenCallServiceThroughGatewayAndXReverseHeaderIsPresent_thenXReversedHeaderIsAdded() {
		String toReverse = "to-reverse", reversed = "esrever-ot";
		ResponseSpec response = client.get()
									  .uri("/api/reverse")
									  .header("X-Reverse-Me", toReverse)
									  .exchange();

		response.expectStatus()
				.isOk()
				.expectHeader()
				.valueEquals("X-Reversed", reversed);
	}

}
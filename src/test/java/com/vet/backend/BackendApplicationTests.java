package com.vet.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Activates the test profile
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

}

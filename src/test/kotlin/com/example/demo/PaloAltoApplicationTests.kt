package com.example.demo

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertNotNull

@SpringBootTest
class PaloAltoApplicationTests {

	@Test
	fun `test synchronization`(): Unit = runBlocking {
		val sharedResource = SharedResource()

		launch { sharedResource.put("key-1", "value-1") }
		launch { assertNotNull(sharedResource.get("key-1")) }
		launch { sharedResource.put("key-2", "value-2") }
		launch { sharedResource.put("key-3", "value-3") }
	}

}

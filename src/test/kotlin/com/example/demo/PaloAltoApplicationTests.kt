package com.example.demo

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertNotNull

@SpringBootTest
class PaloAltoApplicationTests {

	@Test
	fun `test mutex blocks shared resource access`() = runBlocking {
		val sharedResource = SharedResource()

		val firstJob = launch {
			sharedResource.put("key1", "initialValue")
			sharedResource.generateValue("key1")
		}

		val secondJob = launch {
			delay(100) // Ensure job1 gets the lock first
			val value = sharedResource.get("key1")
			println("Concurrent access: key1 value [$value]")
		}

		joinAll(firstJob, secondJob)

		val finalValue = sharedResource.get("key1")
		println("Final value: key1 value [$finalValue]")
		// Ensure the final value is set by job1 and job2 waited for the lock release
		assertEquals("initialValue", finalValue)
	}

}

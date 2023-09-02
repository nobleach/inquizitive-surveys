package com.ww.assessmentservice.service

import java.time.ZonedDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import com.ww.assessmentservice.entity.ResponseTypeEntity
import com.ww.assessmentservice.repository.ResponseTypesRepository

class ResponseTypesServiceTest {
  companion object {
    private val responseTypes = mutableListOf<ResponseTypeEntity>(
      ResponseTypeEntity(),
      ResponseTypeEntity()
    )

    @MockK
    private lateinit var responseTypesRepository: ResponseTypesRepository

    @BeforeAll
    @JvmStatic
    fun setup() {
      MockKAnnotations.init(this, relaxed = true)

      every { responseTypesRepository.listAll() } returns responseTypes
    }
  }

  @Test
  fun testShouldReturnResponseTypes() {
    val service = ResponseTypesService(
      responseTypesRepository,
    )
    val result = service.getAllResponseTypes()
    assertThat(result).hasSize(2)
  }
}

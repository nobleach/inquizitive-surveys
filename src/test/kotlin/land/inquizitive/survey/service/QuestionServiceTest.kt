package com.ww.assessmentservice.service

import java.time.ZonedDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import java.util.UUID
import com.ww.assessmentservice.entity.ResponseTypeEntity
import com.ww.assessmentservice.entity.QuestionEntity
import com.ww.assessmentservice.entity.ResponseEntity
import com.ww.assessmentservice.repository.QuestionRepository

class QuestionServiceTest {
  companion object {
    private var responseType = ResponseTypeEntity()

    private var question1 = QuestionEntity()

    private var response1 = ResponseEntity()

    @MockK
    private lateinit var questionRepository: QuestionRepository

    @BeforeAll
    @JvmStatic
    fun setup() {
      MockKAnnotations.init(this, relaxed = true)

      every { questionRepository.findByQuestionId(UUID.fromString("67e7ff1c-c125-4d3c-9309-d913432d1393")) } returns question1
    }
  }


  @Test
  fun testShouldReturnOneQuestion() {
    responseType.id = UUID.fromString("422104de-9fe1-4775-9848-34534a896329")
    responseType.name = "SINGLE_OPTION"
    question1.id = UUID.fromString("67e7ff1c-c125-4d3c-9309-d913432d1393")
    question1.questionText = "How does your garden grow?"
    question1.responseType = responseType
    question1.questionAidType = null
    question1.questionAidUrl = ""
    question1.responses = mutableListOf(response1)
    response1.question = question1
    val service = QuestionService(
      questionRepository,
    )

    val result = service.getQuestionById(UUID.fromString("67e7ff1c-c125-4d3c-9309-d913432d1393"))

    assertThat(result.questionText).isEqualTo("How does your garden grow?")
  }

}


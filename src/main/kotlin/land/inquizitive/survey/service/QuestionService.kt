package com.ww.assessmentservice.service

import javax.inject.Inject
import javax.enterprise.context.ApplicationScoped
import com.ww.assessmentservice.repository.QuestionRepository
import com.ww.assessmentservice.dto.QuestionDto
import com.ww.assessmentservice.dto.ResponseTypesDto
import com.ww.assessmentservice.dto.ResponseDto
import com.ww.assessmentservice.dto.QuestionAidTypeDto
import java.util.UUID

@ApplicationScoped
class QuestionService(
  @Inject
  private val questionRepository: QuestionRepository,
) {
  fun getQuestionById(questionId: UUID): QuestionDto {
    val question = questionRepository.findByQuestionId(questionId)

    return QuestionDto(
      id = question.id,
      questionText = question.questionText,
      responseType = ResponseTypesDto(
        id = question.responseType.id,
        name = question.responseType.name,
      ),
      questionAidType = if (!question.questionAidUrl.isNullOrBlank()) {
        QuestionAidTypeDto(
          id = question.questionAidType?.id,
          label = question.questionAidType!!.label,
        )
      } else {
        null
      },
      questionAidUrl = question.questionAidUrl,
      responses = question.responses.map {
        ResponseDto(
          id = it.id,
          questionId = it.question.id!!,
          label = it.label,
        )
      },
    )
  }
}

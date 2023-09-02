package land.inquizitive.survey.service

import jakarta.inject.Inject
import jakarta.enterprise.context.ApplicationScoped
import land.inquizitive.survey.repository.SurveyQuestionRepository
import land.inquizitive.survey.dto.QuestionDto
import land.inquizitive.survey.dto.SurveyResponseTypesDto
import land.inquizitive.survey.dto.SurveyResponseDto
import land.inquizitive.survey.dto.QuestionAidTypeDto
import java.util.UUID

@ApplicationScoped
class SurveyQuestionService(
  @Inject
  private val questionRepository: SurveyQuestionRepository,
) {
  fun getQuestionById(questionId: UUID): QuestionDto {
    val question = questionRepository.findByQuestionId(questionId)

    return QuestionDto(
      id = question.id,
      questionText = question.questionText,
      responseType = SurveyResponseTypesDto(
        id = question.surveyResponseType.id,
        name = question.surveyResponseType.name,
      ),
      questionAidType = if (!question.questionAidUrl.isNullOrBlank()) {
        QuestionAidTypeDto(
          id = question.surveyQuestionAidType?.id,
          label = question.surveyQuestionAidType!!.label,
        )
      } else {
        null
      },
      questionAidUrl = question.questionAidUrl,
      responses = question.surveyResponses.map {
        SurveyResponseDto(
          id = it.id,
          questionId = it.surveyQuestion.id!!,
          label = it.label,
        )
      },
    )
  }
}

package land.inquizitive.survey.service

import jakarta.inject.Inject
import jakarta.enterprise.context.ApplicationScoped
import land.inquizitive.survey.dto.SurveyDto
import land.inquizitive.survey.dto.QuestionDto
import land.inquizitive.survey.dto.SurveyResponseTypesDto
import land.inquizitive.survey.dto.QuestionAidTypeDto
import land.inquizitive.survey.dto.SurveyResponseDto
import land.inquizitive.survey.dto.QuestionPlanDto
import land.inquizitive.survey.entity.SurveyEntity
import land.inquizitive.survey.repository.SurveyRepository
import java.util.UUID

@ApplicationScoped
class SurveyService(
  @Inject
  private val surveyRepository: SurveyRepository,
) {
  fun getSurveyById(surveyId: UUID): SurveyDto {
    val survey = surveyRepository.list("id", surveyId).firstOrNull()

    // TODO check for null response
    val questionsMap = getAdditionalQuestions(survey!!)

    // Add first question to map
    questionsMap.set(survey.startingQuestion.id!!, QuestionDto(
      id = survey.startingQuestion.id,
      questionText = survey.startingQuestion.questionText,
      responseType = SurveyResponseTypesDto(
        id = survey.startingQuestion.surveyResponseType.id,
        name = survey.startingQuestion.surveyResponseType.name,
      ),
      questionAidType = if (!survey.startingQuestion.questionAidUrl.isNullOrBlank()) {
        QuestionAidTypeDto(
          id = survey.startingQuestion.surveyQuestionAidType?.id,
          label = survey.startingQuestion.surveyQuestionAidType!!.label,
        )
      } else {
        null
      },
      questionAidUrl = survey.startingQuestion.questionAidUrl,
      responses = survey.startingQuestion.surveyResponses.map {
        SurveyResponseDto(
          id = it.id,
          questionId = it.surveyQuestion.id!!,
          label = it.label,
        )
      },
    ))

    return SurveyDto(
      id = survey.id,
      name = survey.name,
      startDate = survey.startDate!!,
      endDate = survey.endDate!!,
      isActive = survey.isActive,
      startingQuestionId = survey.startingQuestion.id!!,
      questionPlan = buildQuestionsPlan(survey),
      questions = questionsMap,
    )
  }

  fun createSurvey(): SurveyDto {

  }

  fun buildQuestionsPlan(survey: SurveyEntity): MutableMap<UUID, UUID> {
    val questionsPlanMap = mutableMapOf<UUID, UUID>()

    survey.questionPlanList.forEach {
      questionsPlanMap.set(it.responseId!!, it.nextQuestion.id!!)
    }

    return questionsPlanMap
  }

  fun getAdditionalQuestions(survey: SurveyEntity): MutableMap<UUID, QuestionDto> {
    return survey.questionPlanList.fold(mutableMapOf<UUID, QuestionDto>()) {
      acc, plan -> acc.set(plan.nextQuestion.id!!, QuestionDto(
        id = plan.nextQuestion.id,
        questionText = plan.nextQuestion.questionText,
        responseType = SurveyResponseTypesDto(
          id = plan.nextQuestion.surveyResponseType.id,
          name = plan.nextQuestion.surveyResponseType.name,
        ),
        questionAidType = if (!plan.nextQuestion.questionAidUrl.isNullOrBlank()) {
          QuestionAidTypeDto(
            id = plan.nextQuestion.surveyQuestionAidType?.id,
            label = plan.nextQuestion.surveyQuestionAidType!!.label,
          )
        } else {
          null
        },
        questionAidUrl = plan.nextQuestion.questionAidUrl,
        responses = plan.nextQuestion.surveyResponses.map {
          SurveyResponseDto(
            id = it.id,
            questionId = it.surveyQuestion.id!!,
            label = it.label,
          )
        }
      ))

      return acc
    }
  }
}

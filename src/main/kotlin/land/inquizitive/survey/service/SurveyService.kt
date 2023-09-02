package land.inquizitive.survey.service

import jakarta.inject.Inject
import jakarta.enterprise.context.ApplicationScoped
import land.inquizitive.survey.dto.SurveyDto
import land.inquizitive.survey.dto.QuestionDto
import land.inquizitive.survey.dto.ResponseTypesDto
import land.inquizitive.survey.dto.QuestionAidTypeDto
import land.inquizitive.survey.dto.ResponseDto
import land.inquizitive.survey.dto.QuestionPlanDto
import land.inquizitive.survey.entity.AssessmentEntity
import java.util.UUID

@ApplicationScoped
class AssessmentService(
  @Inject
  private val assessmentRepository: AssessmentRepository,
) {
  fun getAssessmentById(assessmentId: UUID): AssessmentDto {
    val assessment = assessmentRepository.list("id", assessmentId).firstOrNull()

    val questionsMap = getAdditionalQuestions(assessment!!)

    // Add first question to map
    questionsMap.set(assessment.startingQuestion.id!!, QuestionDto(
      id = assessment.startingQuestion.id,
      questionText = assessment.startingQuestion.questionText,
      responseType = ResponseTypesDto(
        id = assessment.startingQuestion.responseType.id,
        name = assessment.startingQuestion.responseType.name,
      ),
      questionAidType = if (!assessment.startingQuestion.questionAidUrl.isNullOrBlank()) {
        QuestionAidTypeDto(
          id = assessment.startingQuestion.questionAidType?.id,
          label = assessment.startingQuestion.questionAidType!!.label,
        )
      } else {
        null
      },
      questionAidUrl = assessment.startingQuestion.questionAidUrl,
      responses = assessment.startingQuestion.responses.map {
        ResponseDto(
          id = it.id,
          questionId = it.question.id!!,
          label = it.label,
        )
      },
    ))

    return AssessmentDto(
      id = assessment.id,
      name = assessment.name,
      startDate = assessment.startDate!!,
      endDate = assessment.endDate!!,
      isActive = assessment.isActive,
      startingQuestionId = assessment.startingQuestion.id!!,
      questionPlan = buildQuestionsPlan(assessment),
      questions = questionsMap,
    )
  }

  fun createAssessment(): AssessmentDto {

  }

  fun buildQuestionsPlan(assessment: AssessmentEntity): MutableMap<UUID, UUID> {
    val questionsPlanMap = mutableMapOf<UUID, UUID>()

    assessment.questionPlanList.forEach {
      questionsPlanMap.set(it.responseId!!, it.nextQuestion.id!!)
    }

    return questionsPlanMap
  }

  fun getAdditionalQuestions(assessment: AssessmentEntity): MutableMap<UUID, QuestionDto> {
    return assessment.questionPlanList.fold(mutableMapOf<UUID, QuestionDto>()) {
      acc, plan -> acc.set(plan.nextQuestion.id!!, QuestionDto(
        id = plan.nextQuestion.id,
        questionText = plan.nextQuestion.questionText,
        responseType = ResponseTypesDto(
          id = plan.nextQuestion.responseType.id,
          name = plan.nextQuestion.responseType.name,
        ),
        questionAidType = if (!plan.nextQuestion.questionAidUrl.isNullOrBlank()) {
          QuestionAidTypeDto(
            id = plan.nextQuestion.questionAidType?.id,
            label = plan.nextQuestion.questionAidType!!.label,
          )
        } else {
          null
        },
        questionAidUrl = plan.nextQuestion.questionAidUrl,
        responses = plan.nextQuestion.responses.map {
          ResponseDto(
            id = it.id,
            questionId = it.question.id!!,
            label = it.label,
          )
        }
      ))

      return acc
    }
  }
}

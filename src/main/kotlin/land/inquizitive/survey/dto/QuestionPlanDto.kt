package land.inquizitive.survey.dto

import java.util.UUID

data class QuestionPlanDto(
  val id: UUID?,
  val responseId: UUID,
  val nextQuestionId: UUID,
)


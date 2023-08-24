package land.inquizitive.survey.dto

import java.time.ZonedDateTime
import java.util.UUID

data class SurveyDto(
  val id: UUID?,
  val name: String,
  val startDate: ZonedDateTime,
  val endDate: ZonedDateTime,
  val isActive: Boolean,
  val startingQuestionId: UUID?,
  val questions: Map<UUID, QuestionDto>?,
  val questionPlan: Map<UUID, UUID>?,
)

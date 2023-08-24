package land.inquizitive.survey.dto

import java.util.UUID

data class ResponseDto(
  val id: UUID?,
  val questionId: UUID,
  val label: String,
)


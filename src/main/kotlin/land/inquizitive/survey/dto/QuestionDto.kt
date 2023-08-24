package land.inquizitive.survey.dto

import java.util.UUID

data class QuestionDto(
  val id: UUID?,
  val questionText: String,
  val responseType: ResponseTypesDto,
  var questionAidType: QuestionAidTypeDto?,
  val questionAidUrl: String?,
  val responses: List<ResponseDto>
)

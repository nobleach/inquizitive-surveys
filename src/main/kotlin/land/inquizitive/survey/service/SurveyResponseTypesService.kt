package land.inquizitive.survey.service

import jakarta.inject.Inject
import jakarta.enterprise.context.ApplicationScoped

import land.inquizitive.survey.repository.SurveyResponseTypesRepository
import land.inquizitive.survey.dto.SurveyResponseTypesDto

@ApplicationScoped
class SurveyResponseTypesService(
  @Inject
  private val responseTypesRepository: SurveyResponseTypesRepository,
) {
  fun getAllResponseTypes(): List<SurveyResponseTypesDto> {
    val responseTypes = responseTypesRepository.listAll();

    return responseTypes.map {
      it -> SurveyResponseTypesDto(
        id = it.id,
        name = it.name,
      )
    }
  }
}

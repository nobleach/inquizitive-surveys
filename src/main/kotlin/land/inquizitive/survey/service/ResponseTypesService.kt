package com.ww.assessmentservice.service

import javax.inject.Inject
import javax.enterprise.context.ApplicationScoped

import com.ww.assessmentservice.repository.ResponseTypesRepository
import com.ww.assessmentservice.dto.ResponseTypesDto

@ApplicationScoped
class ResponseTypesService(
  @Inject
  private val responseTypesRepository: ResponseTypesRepository,
) {
  fun getAllResponseTypes(): List<ResponseTypesDto> {
    val responseTypes = responseTypesRepository.listAll();

    return responseTypes.map {
      it -> ResponseTypesDto(
        id = it.id,
        name = it.name,
      )
    }
  }
}

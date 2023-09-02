package land.inquizitive.survey.repository

import jakarta.enterprise.context.ApplicationScoped
import java.util.UUID
import io.quarkus.hibernate.orm.panache.PanacheRepository

import land.inquizitive.survey.entity.SurveyResponseTypeEntity

@ApplicationScoped
class ResponseTypesRepository : PanacheRepository<SurveyResponseTypeEntity> {
  fun findById(responseTypeId: UUID): SurveyResponseTypeEntity {
    return find("id", responseTypeId).firstResult()
  }
}


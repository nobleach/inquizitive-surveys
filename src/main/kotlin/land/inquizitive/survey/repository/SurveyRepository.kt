package land.inquizitive.survey.repository

import jakarta.enterprise.context.ApplicationScoped
import java.util.UUID
import io.quarkus.hibernate.orm.panache.PanacheRepository
import land.inquizitive.survey.entity.SurveyEntity

@ApplicationScoped
class SurveyRepository : PanacheRepository<SurveyEntity> {
}

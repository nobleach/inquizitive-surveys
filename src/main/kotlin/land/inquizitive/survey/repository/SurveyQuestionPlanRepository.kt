package land.inquizitive.survey.repository

import jakarta.enterprise.context.ApplicationScoped
import java.util.UUID
import io.quarkus.hibernate.orm.panache.PanacheRepository
import land.inquizitive.survey.entity.SurveyQuestionPlanEntity

@ApplicationScoped
class SurveyQuestionPlanRepository : PanacheRepository<SurveyQuestionPlanEntity> {}

package land.inquizitive.survey.repository

import jakarta.enterprise.context.ApplicationScoped
import java.util.UUID
import io.quarkus.hibernate.orm.panache.PanacheRepository
import land.inquizitive.survey.entity.SurveyQuestionEntity

@ApplicationScoped
class QuestionRepository : PanacheRepository<SurveyQuestionEntity> {
  fun findByQuestionId(questionId: UUID): SurveyQuestionEntity {
    return list("id", questionId).first()
  }
}


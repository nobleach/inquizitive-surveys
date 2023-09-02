package land.inquizitive.survey.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import java.time.ZonedDateTime
import java.util.UUID
import org.hibernate.annotations.Type
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "question_plans")
class SurveyQuestionPlanEntity {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  @GeneratedValue(generator = "UUIDGenerator")
  var id: UUID? = null

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "survey_id")
  lateinit var survey: SurveyEntity

  @Column(name = "response_id")
  val responseId: UUID? = null

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "next_question_id")
  lateinit var nextQuestion: SurveyQuestionEntity

  @Column(name = "created_at")
  val createdTimestamp: ZonedDateTime? = ZonedDateTime.now()

  @Column(name = "updated_at")
  val updatedTimestamp: ZonedDateTime? = ZonedDateTime.now()

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val that = other as QuestionPlanEntity
    if (id != that.id) return false
    return true
  }

  override fun hashCode(): Int {
    return if (id != null) id.hashCode() else 0
  }
}

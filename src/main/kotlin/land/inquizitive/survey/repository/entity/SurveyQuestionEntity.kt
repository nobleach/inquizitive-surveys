package land.inquizitive.survey.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.OneToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.ZonedDateTime
import java.util.UUID
import org.hibernate.annotations.Type
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "questions")
class SurveyQuestionEntity {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  @GeneratedValue(generator = "UUIDGenerator")
  var id: UUID? = null

  @Column(name = "question_text")
  var questionText: String = ""

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "response_type_id", nullable = false)
  lateinit var surveyResponseType: SurveyResponseTypeEntity

  @OneToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "question_aid_types_id", nullable = true)
  var surveyQuestionAidType: SurveyQuestionAidTypeEntity? = null

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
  lateinit var surveyResponses: MutableList<SurveyResponseEntity>

  @Column(name = "question_aid_url")
  var questionAidUrl: String = ""

  @Column(name = "created_at")
  val createdTimestamp: ZonedDateTime? = ZonedDateTime.now()

  @Column(name = "updated_at")
  var updatedTimestamp: ZonedDateTime? = ZonedDateTime.now()

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val that = other as SurveyQuestionEntity
    if (id != that.id) return false
    return true
  }

  override fun hashCode(): Int {
    return if (id != null) id.hashCode() else 0
  }
}


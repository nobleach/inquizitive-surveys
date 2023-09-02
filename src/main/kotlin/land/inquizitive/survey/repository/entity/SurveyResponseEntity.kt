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
import java.time.ZonedDateTime
import java.util.UUID
import org.hibernate.annotations.Type
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "responses")
class SurveyResponseEntity {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  @GeneratedValue(generator = "UUIDGenerator")
  var id: UUID? = null

  val label: String = ""

  @ManyToOne(fetch = FetchType.LAZY)
  lateinit var surveyQuestion: SurveyQuestionEntity

  @Column(name = "created_at")
  val createdTimestamp: ZonedDateTime? = ZonedDateTime.now()

  @Column(name = "updated_at")
  val updatedTimestamp: ZonedDateTime? = ZonedDateTime.now()

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val that = other as SurveyResponseEntity
    if (id != that.id) return false
    return true
  }

  override fun hashCode(): Int {
    return if (id != null) id.hashCode() else 0
  }
}


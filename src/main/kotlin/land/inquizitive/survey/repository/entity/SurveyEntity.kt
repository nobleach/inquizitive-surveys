package land.inquizitive.survey.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.JoinColumn
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import jakarta.persistence.OneToMany
import java.time.ZonedDateTime
import java.util.UUID
import org.hibernate.annotations.Type
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "surveys")
class SurveyEntity {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  @GeneratedValue(generator = "UUIDGenerator")
  var id: UUID? = null

  var name: String = ""

  @Column(name = "start_date")
  var startDate: ZonedDateTime? = null

  @Column(name = "end_date")
  var endDate: ZonedDateTime? = null

  @Column(name = "is_active")
  var isActive: Boolean = true

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "starting_question_id", nullable = false)
  lateinit var startingQuestion: QuestionEntity

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "assessment")
  lateinit var questionPlanList: MutableList<QuestionPlanEntity>

  @Column(name = "created_at")
  val createdTimestamp: ZonedDateTime? = ZonedDateTime.now()

  @Column(name = "updated_at")
  val updatedTimestamp: ZonedDateTime? = ZonedDateTime.now()

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val that = other as SurveyEntity
    if (id != that.id) return false
    return true
  }

  override fun hashCode(): Int {
    return if (id != null) id.hashCode() else 0
  }
}


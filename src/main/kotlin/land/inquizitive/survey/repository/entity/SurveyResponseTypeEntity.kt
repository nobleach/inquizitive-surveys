package land.inquizitive.survey.entity

import java.time.ZonedDateTime
import java.util.UUID
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "response_types")
class SurveyResponseTypeEntity {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  @GeneratedValue(generator = "UUIDGenerator")
  var id: UUID? = null

  var name: String = ""

  @Column(name = "created_at") val createdTimestamp: ZonedDateTime? = ZonedDateTime.now()

  @Column(name = "updated_at") val updatedTimestamp: ZonedDateTime? = ZonedDateTime.now()

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val that = other as SurveyResponseTypeEntity
    if (id != that.id) return false
    return true
  }

  override fun hashCode(): Int {
    return if (id != null) id.hashCode() else 0
  }
}

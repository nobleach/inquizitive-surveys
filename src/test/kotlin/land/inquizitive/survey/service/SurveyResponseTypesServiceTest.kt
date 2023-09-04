package land.inquizitive.survey.service

import java.time.ZonedDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import land.inquizitive.survey.entity.SurveyResponseTypeEntity
import land.inquizitive.survey.repository.SurveyResponseTypesRepository

class SurveyResponseTypesServiceTest {
  companion object {
    private val responseTypes = mutableListOf<SurveyResponseTypeEntity>(
      SurveyResponseTypeEntity(),
      SurveyResponseTypeEntity()
    )

    @MockK
    private lateinit var responseTypesRepository: SurveyResponseTypesRepository

    @BeforeAll
    @JvmStatic
    fun setup() {
      MockKAnnotations.init(this, relaxed = true)

      every { responseTypesRepository.listAll() } returns responseTypes
    }
  }

  @Test
  fun testShouldReturnResponseTypes() {
    val service = SurveyResponseTypesService(
      responseTypesRepository,
    )
    val result = service.getAllResponseTypes()
    assertThat(result).hasSize(2)
  }
}

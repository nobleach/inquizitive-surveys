package land.inquizitive.survey.controller

import jakarta.ws.rs.Path
import jakarta.ws.rs.GET
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/health")
class HealthController {
  @GET 
  @Produces(MediaType.APPLICATION_JSON)
  fun getHealth(): Map<String, String> {
    return mapOf("status" to "UP")
  }
}

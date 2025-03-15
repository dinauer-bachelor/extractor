package extractor.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Path("dev")
public class Extractor {

    private final ProjectExtractor projectExtractor;

    @Inject
    public Extractor(extractor.core.ProjectExtractor projectExtractor) {
        this.projectExtractor = projectExtractor;

    }

    @POST
    @Consumes
    @Produces
    public void run() throws ExecutionException, InterruptedException, IOException {
        ZonedDateTime from = null; //Pull timestamp from last execution.
        ZonedDateTime now = ZonedDateTime.now(); // Pull last run's datetime here to extract all changes from source system.
        Map<String, List<String>> projectConfigs = new ObjectMapper().readValue(new File("src/main/resources/config.json"), new TypeReference<Map<String, List<String>>>() {});
        projectExtractor.run(projectConfigs);
    }

}
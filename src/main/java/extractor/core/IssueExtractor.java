package extractor.core;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;

import extractor.core.jql.JqlBuilder;
import extractor.repo.source.SourceClient;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IssueExtractor {


    public void run(String projectKey, List<String> issuetypes) throws IOException {
        try(JiraRestClient client = SourceClient.build()) {
            Iterable<Issue> issues = client.getSearchClient().searchJql(JqlBuilder.build(projectKey, issuetypes, null)).get().getIssues();
            for(Issue issue : issues) {
                run(issue);
                //commentExtractor.run(issue);
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(Issue issue) {
        //Handle Issues here

    }

}
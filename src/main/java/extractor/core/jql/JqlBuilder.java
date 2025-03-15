package extractor.core.jql;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JqlBuilder {

    public static String build(String projectKey, List<String> issuetypes, List<String> conditions) {
        List<String> parts = new LinkedList<>();
        if(projectKey != null && !projectKey.isBlank()) {
            parts.add("project = '" + projectKey + "'");
            if(issuetypes != null && issuetypes.size() > 0) {
                parts.add(buildIssuetypes(issuetypes));
            }
            if(conditions != null) {
                for(String condition : conditions) {
                    parts.add("(" + condition + ")");
                }
            }
            return String.join(" AND ", parts);
        }
        throw new RuntimeException();
    }

    private static String buildIssuetypes(List<String> issuetypes) {
        return "(" + issuetypes.stream().map(item -> "issuetype = '" + item + "'").collect(Collectors.joining(" OR ")) + ")";
    }
}
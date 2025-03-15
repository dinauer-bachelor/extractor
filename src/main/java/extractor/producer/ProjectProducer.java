package extractor.producer;

import extractor.persistance.target.ProjectTarget;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ProjectProducer
{
    @Inject
    @Channel("projects")
    Emitter<ProjectTarget> projectEmitter;

    private final static Logger LOG = LoggerFactory.getLogger(ProjectProducer.class);


    public void produce(ProjectTarget project)
    {
        LOG.info("Sending project '{}' to loader", project.getKey());
        projectEmitter.send(project);
    }

}

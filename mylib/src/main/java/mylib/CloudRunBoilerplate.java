package mylib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.annotation.Requires;
import io.micronaut.discovery.event.ServiceStartedEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Async;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

// dumb singleton to spit out some Cloud Run info at application start
@Requires(property = CloudRunConfiguration.PREFIX)
@Singleton
public class CloudRunBoilerplate {
  private static final Logger LOG = LoggerFactory.getLogger(CloudRunBoilerplate.class);

  private final ObjectMapper objectMapper;

  private final CloudRunConfiguration cloudRunConfiguration;

  public CloudRunBoilerplate(ObjectMapper objectMapper, CloudRunConfiguration cloudRunConfiguration) {
    this.objectMapper = objectMapper;
    this.cloudRunConfiguration = cloudRunConfiguration;
  }

  @EventListener
  @Async
  public void startup(final ServiceStartedEvent event) {
    try {
      LOG.info("Cloud Run environment: {}", objectMapper.writer().writeValueAsString(cloudRunConfiguration));
    } catch (JsonProcessingException e) {
      LOG.warn("Unable to convert CloudRunConfiguration to JSON");
    }
  }
}

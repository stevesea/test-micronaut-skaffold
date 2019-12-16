package mylib;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;

import javax.validation.constraints.NotBlank;

/**
 * typical metrics config:
 *
myapp:
       cloud-run-env:
         # see https://cloud.google.com/run/docs/reference/container-contract#env-vars
         # name of cloud run service being run
         service: ${K_SERVICE:UNKNOWN}
         # name of the cloud run revision being run
         revision: ${K_REVISION:UNKNOWN}
         # name of the cloud run configuration that created the revision
         configuration: ${K_CONFIGURATION:UNKNOWN}
 */
@Requires(property = CloudRunConfiguration.PREFIX)
@ConfigurationProperties(CloudRunConfiguration.PREFIX)
interface CloudRunConfiguration {
  String PREFIX = "com.tzero.mulligan.cloud-run-env";

  @NotBlank
  String getService();
  @NotBlank
  String getRevision();
  @NotBlank
  String getConfiguration();
}

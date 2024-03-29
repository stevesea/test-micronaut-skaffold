package myapp.a;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

@Controller("/ready")
public class ReadyController {
    @Value("${micronaut.application.name}")
    protected String appName;

    @Value("${micronaut.application.instance.id}")
    protected String instanceId;

    /**
     * A no-op controller that'll return 200 OK after app startup. Not tied to the health of other systems.
     *
     * @return 200 OK if app is ready.
     */
    @Get(uri = "/", produces = MediaType.TEXT_PLAIN)
    public Single<HttpResponse<String>> ready() {

        return Single.just(HttpResponse.ok("ok - " + appName + " - " + instanceId));
    }
}

package app.routes;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

    private DoctorMockUpRoutes doctorMockUpRoutes = new DoctorMockUpRoutes();

    public EndpointGroup getApiRoutes() {
        return () ->
        {
            path("/doctor", doctorMockUpRoutes.getDoctorRoutes());
        };
    }
}

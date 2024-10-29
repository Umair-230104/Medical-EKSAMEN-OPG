package app.routes;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

    private DoctorMockUpRoutes doctorMockUpRoutes = new DoctorMockUpRoutes();
    private DoctorRoutes doctorRoutes = new DoctorRoutes();

    public EndpointGroup getApiRoutes() {
        return () ->
        {
//            path("/doctor", doctorMockUpRoutes.getDoctorRoutes());
            path("/doctor", doctorRoutes.getDoctorRoutes());
        };
    }
}

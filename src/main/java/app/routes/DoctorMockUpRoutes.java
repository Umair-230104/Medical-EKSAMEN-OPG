//package app.routes;
//
//import app.controllers.MockUp.DoctorMockController;
//import io.javalin.apibuilder.EndpointGroup;
//
//import static io.javalin.apibuilder.ApiBuilder.*;
//
//public class DoctorMockUpRoutes
//{
//    DoctorMockController doctorMockController = new DoctorMockController();
//
//    public EndpointGroup getDoctorRoutes()
//    {
//        return () ->
//        {
//            get("/", doctorMockController::getAll);
//            get("/{id}", doctorMockController::getById);
//            get("/speciality/{speciality}", doctorMockController::getBySpeciality);
//            get("/birthdate-range/{from}/{to}", doctorMockController::getByBirthDateRange);
//            post("/", doctorMockController::createDoctor);
//            put("/{id}", doctorMockController::update);
//        };
//    }
//}

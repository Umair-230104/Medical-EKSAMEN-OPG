package app.routes;

import app.config.HibernateConfig;
import app.controllers.DoctorControllerDB;
//import app.controllers.MockUp.DoctorMockController;
import app.daos.DoctorDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class DoctorRoutes
{
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    DoctorDAO doctorDAO = new DoctorDAO(emf);
    DoctorControllerDB doctorControllerDB = new DoctorControllerDB(doctorDAO);

    public EndpointGroup getDoctorRoutes()
    {
        return () ->
        {
            get("/", doctorControllerDB::getAll);
            get("/{id}", doctorControllerDB::getById);
            get("/speciality/{speciality}", doctorControllerDB::getBySpeciality);
            get("/birthdate-range/{from}/{to}", doctorControllerDB::getByBirthDateRange);
            post("/", doctorControllerDB::createDoctor);
            put("/{id}", doctorControllerDB::update);
        };
    }
}

package app.config;

import app.daos.DoctorDAO;
import app.dtos.DoctorDTO;
import app.entities.Appointment;
import app.entities.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static app.entities.Speciality.*;

public class Populator
{

    private static EntityManagerFactory emf;
    private static DoctorDAO doctorDAO;

    public Populator(EntityManagerFactory emf, DoctorDAO doctorDAO)
    {
        Populator.emf = emf;
        Populator.doctorDAO = doctorDAO;
    }

    public List<DoctorDTO> populateDoctors()
    {
        if (emf == null)
        {
            throw new IllegalStateException("EntityManagerFactory is not initialized");
        }

        try (var em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            Doctor d1 = new Doctor("Dr. John Doe", LocalDate.of(1975, 3, 15), 2000, "City Health Clinic", FAMILY_MEDICINE);
            Doctor d2 = new Doctor("Dr. Jane Smith", LocalDate.of(1980, 7, 22), 2005, "Downtown Medical Center", PEDIATRICS);
            Doctor d3 = new Doctor("Dr. Sam Brown", LocalDate.of(1969, 11, 5), 1995, "General Hospital", PSYCHIATRY);

            // Adding appointments to each doctor
//            addAppointmentsToDoctor(d1, em);
//            addAppointmentsToDoctor(d2, em);
//            addAppointmentsToDoctor(d3, em);

            // Persisting countries to the database
            em.persist(d1);
            em.persist(d2);
            em.persist(d3);

            em.getTransaction().commit();

            return new ArrayList<>(List.of(new DoctorDTO(d1), new DoctorDTO(d2), new DoctorDTO(d3)));
        }
    }

    // Helper method to add two appointments to a doctor
//    private void addAppointmentsToDoctor(Doctor doctor, EntityManager em)
//    {
//        Appointment appointment1 = new Appointment("Client A", LocalDate.now().plusDays(1), LocalTime.of(10, 30), "Check-up", doctor);
//        Appointment appointment2 = new Appointment("Client B", LocalDate.now().plusDays(2), LocalTime.of(14, 0), "Consultation", doctor);
//
//        doctor.getAppointments().add(appointment1);
//        doctor.getAppointments().add(appointment2);
//
//        em.persist(appointment1);
//        em.persist(appointment2);
//    }

    public void cleanUp() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory is not initialized");
        }

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Delete all appointments before doctors
            em.createQuery("DELETE FROM Appointment").executeUpdate();
            em.createQuery("DELETE FROM Doctor").executeUpdate();

            em.createNativeQuery("ALTER SEQUENCE doctor_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE appointment_id_seq RESTART WITH 1").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

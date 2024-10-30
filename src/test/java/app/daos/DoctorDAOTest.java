package app.daos;

import app.config.HibernateConfig;
import app.dtos.DoctorDTO;
import app.entities.Doctor;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static app.entities.Speciality.*;
import static org.junit.jupiter.api.Assertions.*;

class DoctorDAOTest {

    static EntityManagerFactory emf;
    static DoctorDAO doctorDAO;
    static DoctorDTO d1, d2, d3;

    @BeforeAll
    static void setUpAll() {
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactoryConfigTest();
        doctorDAO = new DoctorDAO(emf);
    }

    @BeforeEach
    void setUp() {
        d1 = new DoctorDTO("Dr. John Doe", LocalDate.of(1975, 3, 15), 2000, "City Health Clinic", FAMILY_MEDICINE);
        d2 = new DoctorDTO("Dr. Jane Smith", LocalDate.of(1980, 7, 22), 2005, "Downtown Medical Center", PEDIATRICS);
        d3 = new DoctorDTO("Dr. Sam Brown", LocalDate.of(1969, 11, 5), 1995, "General Hospital", PSYCHIATRY);

        doctorDAO.createDoctor(d1);
        doctorDAO.createDoctor(d2);
        doctorDAO.createDoctor(d3);
    }

    @AfterEach
    void tearDown() {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Doctor").executeUpdate();
            em.createQuery("DELETE FROM Appointment").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE doctor_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE appointment_id_seq RESTART WITH 1").executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Test
    void getAll() {
        List<Doctor> doctors = doctorDAO.readAll();
        assertEquals(3, doctors.size());
    }

    @Test
    void getById() {
        Doctor doctor = doctorDAO.read(1);
        assertNotNull(doctor);
        assertEquals(1, doctor.getId());
    }

    @Test
    void doctorBySpeciality() {
        List<Doctor> doctors = doctorDAO.doctorBySpeciality(FAMILY_MEDICINE);
        assertEquals(1, doctors.size());
        assertEquals("Dr. John Doe", doctors.get(0).getName());
    }

    @Test
    void doctorByBirthdateRange() {
        List<Doctor> doctors = doctorDAO.doctorByBirthdateRange(LocalDate.of(1970, 1, 1), LocalDate.of(1979, 12, 31));
        assertEquals(1, doctors.size());
    }

    @Test
    void createDoctor() {
        DoctorDTO newDoctorDTO = new DoctorDTO("Dr. Alex Johnson", LocalDate.of(1985, 5, 10), 2010, "City Health Clinic", FAMILY_MEDICINE);
        Doctor newDoctor = doctorDAO.createDoctor(newDoctorDTO);
        assertNotNull(newDoctor.getId());
    }

    @Test
    void update() {
        d1.setNameOfClinic("City Hospital");
        doctorDAO.update(1, d1);
        Doctor updatedDoctor = doctorDAO.read(1);
        assertNotNull(updatedDoctor);
        assertEquals("City Hospital", updatedDoctor.getNameOfClinic());
    }
}

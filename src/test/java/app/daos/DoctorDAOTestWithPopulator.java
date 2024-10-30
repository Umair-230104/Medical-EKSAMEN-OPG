package app.daos;

import app.config.HibernateConfig;
import app.config.Populator;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DoctorDAOTestWithPopulator
{

    static EntityManagerFactory emf;
    static DoctorDAO doctorDAO;
    static DoctorDTO d1, d2, d3;
    private static Populator populator;
    private static List<DoctorDTO> doctorsDTOS;


    @BeforeAll
    static void setUpAll() {
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactoryConfigTest();
        doctorDAO = new DoctorDAO(emf);
        populator = new Populator(emf, doctorDAO);
    }

    @BeforeEach
    void setUp() {
        doctorsDTOS = populator.populateDoctors();
        d1 = doctorsDTOS.get(0);
        d2 = doctorsDTOS.get(1);
        d3 = doctorsDTOS.get(2);
    }

    @AfterEach
    void tearDown() {
        populator.cleanUp();
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

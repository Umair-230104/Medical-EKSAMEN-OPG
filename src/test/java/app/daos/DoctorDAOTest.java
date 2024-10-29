package app.daos;

import app.config.HibernateConfig;
import app.dtos.AppointmentDTO;
import app.dtos.DoctorDTO;
import app.entities.Doctor;
import app.entities.Speciality;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoctorDAOTest {
    private static EntityManagerFactory emf;
    private static DoctorDAO doctorDAO;
    static DoctorDTO d1, d2, d3;
    private static int doctorId1, doctorId2, doctorId3;

    @BeforeAll
    static void setup() {
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactoryConfigTest();
        doctorDAO = new DoctorDAO(emf);
    }

    @BeforeEach
    void init() {
        // Initialize test data
        d1 = new DoctorDTO("Dr. John Doe", LocalDate.of(1980, 1, 1), 2000, "City Hospital", Speciality.GERIATRICS);
        d2 = new DoctorDTO("Dr. Jane Doe", LocalDate.of(1985, 1, 1), 2005, "City Clinic", Speciality.FAMILY_MEDICINE);
        d3 = new DoctorDTO("Dr. James Doe", LocalDate.of(1990, 1, 1), 2010, "City Clinic", Speciality.PEDIATRICS);

        doctorId1 = doctorDAO.createDoctor(d1).getId();
        doctorId2 = doctorDAO.createDoctor(d2).getId();
        doctorId3 = doctorDAO.createDoctor(d3).getId();
    }

    @AfterEach
    void tearDown() {
        // Clear data from the database after each test
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Appointment").executeUpdate();
            em.createQuery("DELETE FROM Doctor").executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Test
    void testCreateDoctor() {
        DoctorDTO doctorDTO = new DoctorDTO("Dr. Smith", LocalDate.of(1980, 1, 1), 2005, "City Clinic", Speciality.FAMILY_MEDICINE);
        Doctor createdDoctor = doctorDAO.createDoctor(doctorDTO);

        assertNotNull(createdDoctor);
        assertEquals("Dr. Smith", createdDoctor.getName());
    }

    @Test
    void testReadAllDoctors() {
        List<Doctor> doctors = doctorDAO.readAll();
        assertNotNull(doctors);
        assertTrue(doctors.size() >= 3); // Checking for at least the 3 initialized doctors
    }

    @Test
    void testReadDoctor() {
        Doctor doctor = doctorDAO.read(doctorId1);
        assertNotNull(doctor);
        assertEquals("Dr. John Doe", doctor.getName());
    }

    @Test
    void testDoctorBySpeciality() {
        List<Doctor> doctors = doctorDAO.doctorBySpeciality(Speciality.GERIATRICS);
        assertNotNull(doctors);
        assertFalse(doctors.isEmpty());
        assertEquals(Speciality.GERIATRICS, doctors.get(0).getSpeciality());
    }

    @Test
    void testDoctorByBirthdateRange() {
        LocalDate from = LocalDate.of(1970, 1, 1);
        LocalDate to = LocalDate.of(1990, 12, 31);
        List<Doctor> doctors = doctorDAO.doctorByBirthdateRange(from, to);

        assertNotNull(doctors);
        assertTrue(doctors.stream().allMatch(doctor ->
                doctor.getDateOfBirth().isAfter(from) && doctor.getDateOfBirth().isBefore(to) ||
                        doctor.getDateOfBirth().isEqual(from) || doctor.getDateOfBirth().isEqual(to)
        ));
    }

    @Test
    void testUpdateDoctor() {
        DoctorDTO updatedDTO = new DoctorDTO("Dr. Updated", LocalDate.of(1995, 1, 1), 2015, "Updated Clinic", Speciality.PEDIATRICS);
        Doctor updatedDoctor = doctorDAO.update(doctorId1, updatedDTO);

        assertNotNull(updatedDoctor);
        assertEquals("Dr. John Doe", updatedDoctor.getName());  // Expect the original name to remain
        assertEquals("Updated Clinic", updatedDoctor.getNameOfClinic());
        assertEquals(Speciality.PEDIATRICS, updatedDoctor.getSpeciality());
    }


    @Test
    void testCreateDoctorWithAppointments() {
        // Create an AppointmentDTO to add to the DoctorDTO
        AppointmentDTO appointmentDTO = new AppointmentDTO(
                "Client One", LocalDate.of(2024, 1, 1), LocalTime.of(10, 30), "Routine check-up"
        );

        // Create a DoctorDTO with the appointment
        DoctorDTO doctorDTO = new DoctorDTO(
                "Dr. Smith", LocalDate.of(1980, 1, 1), 2005, "City Clinic", Speciality.FAMILY_MEDICINE
        );
        doctorDTO.setAppointments(List.of(appointmentDTO)); // Set appointments in the DTO

        // Call createDoctor and verify results
        Doctor createdDoctor = doctorDAO.createDoctor(doctorDTO);

        // Assertions to ensure the doctor and appointments are created as expected
        assertNotNull(createdDoctor, "Created doctor should not be null");
        assertEquals("Dr. Smith", createdDoctor.getName());
        assertEquals(1, createdDoctor.getAppointments().size(), "Doctor should have 1 appointment");
        assertEquals("Client One", createdDoctor.getAppointments().get(0).getClientName(), "Appointment client name should match");
        assertEquals(LocalDate.of(2024, 1, 1), createdDoctor.getAppointments().get(0).getDate(), "Appointment date should match");
        assertEquals(LocalTime.of(10, 30), createdDoctor.getAppointments().get(0).getTime(), "Appointment time should match");
        assertEquals("Routine check-up", createdDoctor.getAppointments().get(0).getComment(), "Appointment comment should match");
    }

}

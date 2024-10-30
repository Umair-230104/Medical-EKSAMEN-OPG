package app.config;

import app.daos.MockUp.DoctorMockDAO;
import app.dtos.DoctorDTO;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static app.entities.Speciality.*;

public class DoctorPopulator {

    // Method to populate the doctor list with sample data
    public static void populateDoctorList(DoctorMockDAO doctorMockDAO) {
        // Creating a list of sample DoctorDTO objects
        List<DoctorDTO> doctors = Arrays.asList(
                new DoctorDTO(1, "Dr. John Doe", LocalDate.of(1975, 3, 15), 2000, "City Health Clinic", FAMILY_MEDICINE),
                new DoctorDTO(2, "Dr. Jane Smith", LocalDate.of(1980, 7, 22), 2005, "Downtown Medical Center", PEDIATRICS),
                new DoctorDTO(3, "Dr. Sam Brown", LocalDate.of(1969, 11, 5), 1995, "General Hospital", PSYCHIATRY),
                new DoctorDTO(4, "Dr. Emily White", LocalDate.of(1985, 2, 28), 2010, "Westside Clinic", GERIATRICS),
                new DoctorDTO(5, "Dr. Michael Green", LocalDate.of(1978, 9, 12), 2003, "Eastside Health Services", SURGERY)
        );

        // Adding all doctors to the DAO list
        doctors.forEach(doctorMockDAO::createDoctorWithAppointments);
    }
}
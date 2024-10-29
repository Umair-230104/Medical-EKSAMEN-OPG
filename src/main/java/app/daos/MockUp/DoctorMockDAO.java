package app.daos.MockUp;

import app.daos.IDAO;
import app.dtos.DoctorDTO;
import app.entities.Speciality;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorMockDAO implements IDAO<DoctorDTO>
{
    private static List<DoctorDTO> doctorList = new ArrayList<>();

    @Override
    public List<DoctorDTO> readAll()
    {
        return doctorList;
    }

    //use streams
    @Override
    public DoctorDTO read(int id)
    {
        return doctorList.stream().filter(docter -> docter.getId() == id).findFirst().orElse(null);
    }

    //use streams
    @Override
    public List<DoctorDTO> doctorBySpeciality(Speciality speciality)
    {
        return doctorList.stream().filter(docter -> docter.getSpeciality().equals(speciality)).collect(Collectors.toList());
    }

    //use streams
    @Override
    public List<DoctorDTO> doctorByBirthdateRange(LocalDate from, LocalDate to)
    {
        return doctorList.stream().filter(doctor -> !doctor.getBirthDate().isBefore(from) && !doctor.getBirthDate().isAfter(to)).collect(Collectors.toList());
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctor)
    {
        doctorList.add(doctor);
        return doctor;
    }

    @Override
    public DoctorDTO update(int id, DoctorDTO doctor)
    {
        // Find the doctor with the matching ID
        DoctorDTO existingDoctor = doctorList.stream().filter(d -> d.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + id));

        // Update the fields of the existing doctor with new data from the passed doctor object
        existingDoctor.setName(doctor.getName());
        existingDoctor.setBirthDate(doctor.getBirthDate());
        existingDoctor.setYearOfGraduation(doctor.getYearOfGraduation());
        existingDoctor.setNameOfClinic(doctor.getNameOfClinic());
        existingDoctor.setSpeciality(doctor.getSpeciality());

        // Return the updated doctor
        return existingDoctor;
    }

}

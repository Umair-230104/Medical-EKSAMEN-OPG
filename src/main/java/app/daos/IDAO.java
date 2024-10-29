package app.daos;

import app.dtos.DoctorDTO;
import app.entities.Speciality;

import java.time.LocalDate;
import java.util.List;

public interface IDAO<T>
{

    List<T> readAll();

    T read(int id);

    List<T> doctorBySpeciality(Speciality speciality);

    List<T> doctorByBirthdateRange(LocalDate from, LocalDate to);

    T createDoctor(DoctorDTO doctorDTO);

    T update(int id, DoctorDTO doctorDTO);


}

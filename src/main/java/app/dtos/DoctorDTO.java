package app.dtos;

import app.entities.Doctor;
import app.entities.Speciality;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDTO
{
    private int id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private int yearOfGraduation;
    private String nameOfClinic;
    private Speciality speciality;
    private List<AppointmentDTO> appointments;  // Ny liste for appointments


    public DoctorDTO(int id, String name, LocalDate dateOfBirth, int yearOfGraduation, String nameOfClinic, Speciality speciality)
    {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.yearOfGraduation = yearOfGraduation;
        this.nameOfClinic = nameOfClinic;
        this.speciality = speciality;
    }

    public DoctorDTO(String name, LocalDate dateOfBirth, int yearOfGraduation, String nameOfClinic, Speciality speciality)
    {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.yearOfGraduation = yearOfGraduation;
        this.nameOfClinic = nameOfClinic;
        this.speciality = speciality;
    }

    public DoctorDTO(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.dateOfBirth = doctor.getDateOfBirth();
        this.yearOfGraduation = doctor.getYearOfGraduation();
        this.nameOfClinic = doctor.getNameOfClinic();
        this.speciality = doctor.getSpeciality();
        this.appointments = doctor.getAppointments().stream()
                .map(AppointmentDTO::new)  // Convert each Appointment to AppointmentDTO
                .collect(Collectors.toList());
    }


    public static List<DoctorDTO> toDoctorDTOList(List<Doctor> doctors)
    {
        return doctors.stream().map(DoctorDTO::new).collect(Collectors.toList());
    }
}

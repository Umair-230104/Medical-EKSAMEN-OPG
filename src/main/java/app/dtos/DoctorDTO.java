package app.dtos;

import app.entities.Speciality;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDTO
{
    private int id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private int yearOfGraduation;
    private String nameOfClinic;
    private Speciality speciality;

    public DoctorDTO(int id, String name, LocalDate birthDate, int yearOfGraduation, String nameOfClinic, Speciality speciality)
    {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.yearOfGraduation = yearOfGraduation;
        this.nameOfClinic = nameOfClinic;
        this.speciality = speciality;
    }
}

package app.entities;

import app.dtos.DoctorDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor

public class Doctor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Setter
    private LocalDate dateOfBirth;
    @Setter
    private int yearOfGraduation;
    @Setter
    private String nameOfClinic;
    @Setter
    private Speciality speciality;
    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Appointment> appointments = new ArrayList<>(); // Initialize appointments

//    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
//    @JsonManagedReference  // Allows appointments to be serialized
//    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(String name, LocalDate dateOfBirth, int yearOfGraduation, String nameOfClinic, Speciality speciality, List<Appointment> appointments)
    {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.yearOfGraduation = yearOfGraduation;
        this.nameOfClinic = nameOfClinic;
        this.speciality = speciality;
        this.appointments = appointments;
    }

    public Doctor(String name, LocalDate dateOfBirth, int yearOfGraduation, String nameOfClinic, Speciality speciality)
    {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.yearOfGraduation = yearOfGraduation;
        this.nameOfClinic = nameOfClinic;
        this.speciality = speciality;
    }

    @PrePersist
    protected void onCreate()
    {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate()
    {
        updatedAt = LocalDateTime.now();
    }

    public Doctor(DoctorDTO doctorDTO)
    {
        this.name = doctorDTO.getName();
        this.dateOfBirth = doctorDTO.getDateOfBirth();
        this.yearOfGraduation = doctorDTO.getYearOfGraduation();
        this.nameOfClinic = doctorDTO.getNameOfClinic();
        this.speciality = doctorDTO.getSpeciality();
    }

    public void updateToDoctor(DoctorDTO doctorDTO)
    {
        this.dateOfBirth = doctorDTO.getDateOfBirth();
        this.yearOfGraduation = doctorDTO.getYearOfGraduation();
        this.nameOfClinic = doctorDTO.getNameOfClinic();
        this.speciality = doctorDTO.getSpeciality();
    }

}

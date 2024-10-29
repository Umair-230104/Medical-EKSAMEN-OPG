package app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor

public class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String clientName;
    private LocalDate date;
    private LocalTime time;
    private String comment;

    @ManyToOne
    private Doctor doctor;

    public Appointment(int id, String clientName, LocalDate date, LocalTime time, String comment, Doctor doctor)
    {
        this.id = id;
        this.clientName = clientName;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.doctor = doctor;
    }
}

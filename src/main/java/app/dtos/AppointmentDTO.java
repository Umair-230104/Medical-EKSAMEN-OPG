package app.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentDTO
{
    private int id;
    private String clientName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime time;
    private String comment;

    public AppointmentDTO(int id, String clientName, LocalDate date, LocalTime time, String comment)
    {
        this.id = id;
        this.clientName = clientName;
        this.date = date;
        this.time = time;
        this.comment = comment;
    }
}

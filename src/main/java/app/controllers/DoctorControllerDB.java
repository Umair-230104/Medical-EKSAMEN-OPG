package app.controllers;

import app.daos.DoctorDAO;
import app.dtos.DoctorDTO;
import app.entities.Doctor;
import app.entities.Message;
import app.entities.Speciality;
import app.exceptions.ApiException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DoctorControllerDB
{
    private final DoctorDAO doctorDAO;
    private final Logger log = LoggerFactory.getLogger(DoctorControllerDB.class);

    public DoctorControllerDB(DoctorDAO doctorDAO)
    {
        this.doctorDAO = doctorDAO;
    }

    public void getAll(Context ctx)
    {
        try
        {
            List<Doctor> doctors = doctorDAO.readAll();
            List<DoctorDTO> doctorDTOList = DoctorDTO.toDoctorDTOList(doctors);
            ctx.res().setStatus(200);
            ctx.json(doctorDTOList, DoctorDTO.class);
        } catch (Exception e)
        {
            log.error("500 - {}", e.getMessage(), e);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            ctx.status(500).json(new Message(500, e.getMessage(),timestamp));
        }
    }

    public void getById(Context ctx)
    {
        try
        {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Doctor doctor = doctorDAO.read(id);
            ctx.res().setStatus(200);
            ctx.json(doctor, DoctorDTO.class);

        } catch (ApiException e)
        {
            ctx.status(500).json(new Message(500, e.getMessage(), e.getTimestamp()));
        } catch (Exception e)
        {
            log.error("500 - {}", e.getMessage(), e);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            ctx.status(500).json(new Message(500, e.getMessage(), timestamp));
        }
    }

    public void getBySpeciality(Context ctx)
    {
        try
        {
            String speciality = ctx.pathParam("speciality");
            List<Doctor> doctors = doctorDAO.doctorBySpeciality(Speciality.valueOf(speciality));
            ctx.res().setStatus(200);
            ctx.json(doctors, DoctorDTO.class);
        } catch (Exception e)
        {
            log.error("500 - {}", e.getMessage(), e);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            ctx.status(500).json(new Message(500, e.getMessage(), timestamp));
        }
    }

    public void getByBirthDateRange(Context ctx)
    {
        try
        {
            LocalDate from = LocalDate.parse(ctx.pathParam("from"));
            LocalDate to = LocalDate.parse(ctx.pathParam("to"));

            List<Doctor> doctors = doctorDAO.doctorByBirthdateRange(from, to);
            ctx.res().setStatus(200);
            ctx.json(doctors, DoctorDTO.class);
        } catch (Exception e)
        {
            log.error("500 - {}", e.getMessage(), e);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            ctx.status(500).json(new Message(500, e.getMessage(), timestamp));
        }
    }

    public void createDoctor(Context ctx)
    {
        try
        {
            DoctorDTO doctorDTO = ctx.bodyAsClass(DoctorDTO.class);
            Doctor newDoctor = doctorDAO.createDoctor(doctorDTO);

            ctx.res().setStatus(201);
            ctx.json(newDoctor, DoctorDTO.class);
        } catch (Exception e)
        {
            log.error("500 - {}", e.getMessage(), e);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            ctx.status(500).json(new Message(500, e.getMessage(), timestamp));
        }
    }

    public void update(Context ctx)
    {
        try
        {
            int id = Integer.parseInt(ctx.pathParam("id"));
            DoctorDTO doctorDTO = ctx.bodyAsClass(DoctorDTO.class);
            Doctor updatedDoctor = doctorDAO.update(id, doctorDTO);

            ctx.res().setStatus(200);
            ctx.json(updatedDoctor, DoctorDTO.class);
        } catch (Exception e)
        {
            log.error("500 - {}", e.getMessage(), e);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            ctx.status(500).json(new Message(500, e.getMessage(), timestamp));
        }
    }

}

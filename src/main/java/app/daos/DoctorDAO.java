package app.daos;

import app.dtos.DoctorDTO;
import app.entities.Appointment;
import app.entities.Doctor;
import app.entities.Speciality;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;

public class DoctorDAO implements IDAO<Doctor>
{
    private final EntityManagerFactory emf;

    public DoctorDAO(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public List<Doctor> readAll()
    {
        try (EntityManager em = emf.createEntityManager())
        {
            return em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
        }
    }

    @Override
    public Doctor read(int id)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            return em.find(Doctor.class, id);
        }
    }

    @Override
    public List<Doctor> doctorBySpeciality(Speciality speciality)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            return em.createQuery("SELECT d FROM Doctor d WHERE d.speciality = :speciality", Doctor.class)
                    .setParameter("speciality", speciality)
                    .getResultList();
        }
    }

    @Override
    public List<Doctor> doctorByBirthdateRange(LocalDate from, LocalDate to)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            return em.createQuery("SELECT d FROM Doctor d WHERE d.dateOfBirth BETWEEN :from AND :to", Doctor.class)
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getResultList();
        }
    }

    public Doctor createDoctor(DoctorDTO doctorDTO)
    {
        Doctor doctor = new Doctor(doctorDTO);

        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(doctor);
            em.getTransaction().commit();
            return doctor;
        }
    }

    public Doctor createDoctorWithAppointments(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor(doctorDTO);

        // Convert each AppointmentDTO to an Appointment and add to Doctor
        if (doctorDTO.getAppointments() != null) {
            doctorDTO.getAppointments().forEach(appointmentDTO -> {
                Appointment appointment = new Appointment(
                        appointmentDTO.getClientName(),
                        appointmentDTO.getDate(),
                        appointmentDTO.getTime(),
                        appointmentDTO.getComment(),
                        doctor
                );
                doctor.getAppointments().add(appointment);
            });
        }

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(doctor);
            em.getTransaction().commit();
            return doctor;
        }
    }

    @Override
    public Doctor update(int id, DoctorDTO doctorDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Doctor updatedDoctor = em.find(Doctor.class, id);
            if (updatedDoctor != null) {
                updatedDoctor.updateToDoctor(doctorDTO);  // Ensure the method handles non-null values appropriately
                em.merge(updatedDoctor);
            }
            em.getTransaction().commit();
            return updatedDoctor;  // May return null if not found
        }
    }

}

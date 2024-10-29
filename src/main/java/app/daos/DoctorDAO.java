package app.daos;

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

    @Override
    public Doctor createDoctor(Doctor doctor)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(doctor);
            em.getTransaction().commit();
            return doctor;
        }
    }

    @Override
    public Doctor update(int id, Doctor doctor)
    {
        return null;
    }
}

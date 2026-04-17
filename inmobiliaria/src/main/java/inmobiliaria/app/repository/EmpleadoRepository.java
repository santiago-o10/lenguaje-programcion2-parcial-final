package inmobiliaria.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inmobiliaria.app.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {}

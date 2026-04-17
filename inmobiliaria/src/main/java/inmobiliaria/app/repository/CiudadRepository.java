package inmobiliaria.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import inmobiliaria.app.model.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    List<Ciudad> findByDepartamento_IdDepartamento(Long idDepartamento);
}

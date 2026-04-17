package inmobiliaria.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import inmobiliaria.app.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    List<Departamento> findByPais_IdPais(Long idPais);
}

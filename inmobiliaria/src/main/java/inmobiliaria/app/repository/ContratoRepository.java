package inmobiliaria.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import inmobiliaria.app.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findByFechaInicioBetween(Date desde, Date hasta);
}

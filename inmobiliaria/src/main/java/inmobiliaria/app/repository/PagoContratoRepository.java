package inmobiliaria.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import inmobiliaria.app.model.PagoContrato;

public interface PagoContratoRepository extends JpaRepository<PagoContrato, Long> {
    List<PagoContrato> findByFechaPagoBetween(Date desde, Date hasta);
}

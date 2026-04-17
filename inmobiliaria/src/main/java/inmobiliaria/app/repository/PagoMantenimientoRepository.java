package inmobiliaria.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import inmobiliaria.app.model.PagoMantenimiento;

public interface PagoMantenimientoRepository extends JpaRepository<PagoMantenimiento, Long> {
    List<PagoMantenimiento> findByFechaPagoBetween(Date desde, Date hasta);
}

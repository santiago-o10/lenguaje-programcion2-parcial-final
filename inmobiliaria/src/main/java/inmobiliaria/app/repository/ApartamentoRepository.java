package inmobiliaria.app.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import inmobiliaria.app.model.Apartamento;
 
public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {
    List<Apartamento> findByEstado(Integer estado);
}
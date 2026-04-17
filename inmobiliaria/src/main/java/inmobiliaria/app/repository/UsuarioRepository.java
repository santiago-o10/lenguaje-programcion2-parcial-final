package inmobiliaria.app.repository;
 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import inmobiliaria.app.model.Usuario;
 
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
 
    @Query(value = "SELECT COUNT(*) FROM USUARIO WHERE NOMBRE_USUARIO = :nombreUsuario", nativeQuery = true)
    int countByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
}
 
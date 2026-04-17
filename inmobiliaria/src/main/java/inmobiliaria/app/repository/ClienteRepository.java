package inmobiliaria.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inmobiliaria.app.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}

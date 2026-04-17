package inmobiliaria.app.service;

import java.util.List;

import inmobiliaria.app.model.Apartamento;

public interface ApartamentoService {
    List<Apartamento> listar();
    List<Apartamento> porEstado(String estado);
    Apartamento buscarPorId(Long id);
    Apartamento crear(Apartamento a);
    Apartamento editar(Long id, Apartamento a);
    void eliminar(Long id);
}

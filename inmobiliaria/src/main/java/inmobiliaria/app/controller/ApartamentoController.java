package inmobiliaria.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inmobiliaria.app.model.Apartamento;
import inmobiliaria.app.service.ApartamentoService;

@RestController
@RequestMapping("/api/apartamentos")
@CrossOrigin(origins = "*")
public class ApartamentoController {

    private final ApartamentoService service;
    public ApartamentoController(ApartamentoService service) { this.service = service; }

    @GetMapping
    public List<Apartamento> listar() { return service.listar(); }

    @GetMapping("/por-estado/{estado}")
    public List<Apartamento> porEstado(@PathVariable String estado) {
        return service.porEstado(estado);
    }

    @GetMapping("/{id}")
    public Apartamento buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Apartamento crear(@RequestBody Apartamento a) { return service.crear(a); }

    @PutMapping("/{id}")
    public Apartamento editar(@PathVariable Long id, @RequestBody Apartamento a) {
        return service.editar(id, a);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}

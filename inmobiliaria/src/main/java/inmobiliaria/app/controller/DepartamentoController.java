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

import inmobiliaria.app.model.Departamento;
import inmobiliaria.app.repository.DepartamentoRepository;

@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin(origins = "*")
public class DepartamentoController {

    private final DepartamentoRepository repo;
    public DepartamentoController(DepartamentoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Departamento> listar() { return repo.findAll(); }

    @GetMapping("/por-pais/{idPais}")
    public List<Departamento> porPais(@PathVariable Long idPais) {
        return repo.findByPais_IdPais(idPais);
    }

    @GetMapping("/{id}")
    public Departamento buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public Departamento crear(@RequestBody Departamento d) { return repo.save(d); }

    @PutMapping("/{id}")
    public Departamento editar(@PathVariable Long id, @RequestBody Departamento d) {
        return repo.findById(id).map(dep -> {
            dep.setNombre(d.getNombre());
            dep.setPais(d.getPais());
            return repo.save(dep);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
}

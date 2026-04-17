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

import inmobiliaria.app.model.Ciudad;
import inmobiliaria.app.repository.CiudadRepository;

@RestController
@RequestMapping("/api/ciudades")
@CrossOrigin(origins = "*")
public class CiudadController {

    private final CiudadRepository repo;
    public CiudadController(CiudadRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Ciudad> listar() { return repo.findAll(); }

    @GetMapping("/por-departamento/{idDep}")
    public List<Ciudad> porDepartamento(@PathVariable Long idDep) {
        return repo.findByDepartamento_IdDepartamento(idDep);
    }

    @GetMapping("/{id}")
    public Ciudad buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public Ciudad crear(@RequestBody Ciudad c) { return repo.save(c); }

    @PutMapping("/{id}")
    public Ciudad editar(@PathVariable Long id, @RequestBody Ciudad c) {
        return repo.findById(id).map(ciu -> {
            ciu.setNombre(c.getNombre());
            ciu.setDepartamento(c.getDepartamento());
            return repo.save(ciu);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
}

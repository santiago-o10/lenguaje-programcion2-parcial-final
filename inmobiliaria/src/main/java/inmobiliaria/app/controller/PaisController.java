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

import inmobiliaria.app.model.Pais;
import inmobiliaria.app.repository.PaisRepository;

@RestController
@RequestMapping("/api/paises")
@CrossOrigin(origins = "*")
public class PaisController {

    private final PaisRepository repo;
    public PaisController(PaisRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Pais> listar() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Pais buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public Pais crear(@RequestBody Pais p) { return repo.save(p); }

    @PutMapping("/{id}")
    public Pais editar(@PathVariable Long id, @RequestBody Pais p) {
        return repo.findById(id).map(pais -> {
            pais.setNombre(p.getNombre());
            return repo.save(pais);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
}

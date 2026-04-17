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

import inmobiliaria.app.model.Propietario;
import inmobiliaria.app.repository.PropietarioRepository;

@RestController
@RequestMapping("/api/propietarios")
@CrossOrigin(origins = "*")
public class PropietarioController {

    private final PropietarioRepository repo;
    public PropietarioController(PropietarioRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Propietario> listar() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Propietario buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public Propietario crear(@RequestBody Propietario p) { return repo.save(p); }

    @PutMapping("/{id}")
    public Propietario editar(@PathVariable Long id, @RequestBody Propietario p) {
        return repo.findById(id).map(prop -> {
            prop.setPersona(p.getPersona());
            prop.setObservaciones(p.getObservaciones());
            return repo.save(prop);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
}

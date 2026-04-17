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

import inmobiliaria.app.model.Persona;
import inmobiliaria.app.repository.PersonaRepository;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "*")
public class PersonaController {

    private final PersonaRepository repo;
    public PersonaController(PersonaRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Persona> listar() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Persona buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public Persona crear(@RequestBody Persona p) { return repo.save(p); }

    @PutMapping("/{id}")
    public Persona editar(@PathVariable Long id, @RequestBody Persona p) {
        return repo.findById(id).map(per -> {
            per.setNombre(p.getNombre());
            per.setApellido(p.getApellido());
            per.setTipoPersona(p.getTipoPersona());
            per.setFechaNacimiento(p.getFechaNacimiento());
            per.setDomicilio(p.getDomicilio());
            per.setTelefono(p.getTelefono());
            per.setCorreo(p.getCorreo());
            return repo.save(per);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
}

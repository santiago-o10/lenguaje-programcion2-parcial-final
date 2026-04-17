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

import inmobiliaria.app.model.Empleado;
import inmobiliaria.app.repository.EmpleadoRepository;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    private final EmpleadoRepository repo;
    public EmpleadoController(EmpleadoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Empleado> listar() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Empleado buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public Empleado crear(@RequestBody Empleado e) { return repo.save(e); }

    @PutMapping("/{id}")
    public Empleado editar(@PathVariable Long id, @RequestBody Empleado e) {
        return repo.findById(id).map(emp -> {
            emp.setPersona(e.getPersona());
            emp.setCargo(e.getCargo());
            emp.setSalario(e.getSalario());
            return repo.save(emp);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
}

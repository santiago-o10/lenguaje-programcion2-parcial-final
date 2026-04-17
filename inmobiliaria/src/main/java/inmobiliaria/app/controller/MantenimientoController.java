package inmobiliaria.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import inmobiliaria.app.model.Mantenimiento;
import inmobiliaria.app.repository.MantenimientoRepository;

@RestController
@RequestMapping("/api/mantenimientos")
@CrossOrigin(origins = "*")
public class MantenimientoController {

    private final MantenimientoRepository repo;
    public MantenimientoController(MantenimientoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Mantenimiento> listar() { return repo.findAll(); }

    @GetMapping("/informe")
    public List<Mantenimiento> informe(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hasta) {
        return repo.findByFechaBetween(desde, hasta);
    }

    @GetMapping("/{id}")
    public Mantenimiento buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public Mantenimiento crear(@RequestBody Mantenimiento m) {
        if (m.getApartamento() == null || m.getApartamento().getIdApartamento() == null)
            throw new RuntimeException("Apartamento requerido");
        if (m.getEmpleado() == null || m.getEmpleado().getIdEmpleado() == null)
            throw new RuntimeException("Empleado requerido");
        if (m.getFecha() == null)
            throw new RuntimeException("Fecha requerida");
        if (m.getValor() == null)
            throw new RuntimeException("Valor requerido");
        return repo.save(m);
    }

    @PutMapping("/{id}")
    public Mantenimiento editar(@PathVariable Long id, @RequestBody Mantenimiento m) {
        return repo.findById(id).map(man -> {
            if (m.getApartamento() != null && m.getApartamento().getIdApartamento() != null)
                man.setApartamento(m.getApartamento());
            if (m.getEmpleado() != null && m.getEmpleado().getIdEmpleado() != null)
                man.setEmpleado(m.getEmpleado());
            if (m.getFecha() != null)
                man.setFecha(m.getFecha());
            if (m.getDescripcion() != null)
                man.setDescripcion(m.getDescripcion());
            if (m.getValor() != null)
                man.setValor(m.getValor());
            return repo.save(man);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
}

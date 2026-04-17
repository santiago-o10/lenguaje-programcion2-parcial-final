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

import inmobiliaria.app.model.PagoMantenimiento;
import inmobiliaria.app.repository.PagoMantenimientoRepository;

@RestController
@RequestMapping("/api/pagos-mantenimiento")
@CrossOrigin(origins = "*")
public class PagoMantenimientoController {

    private final PagoMantenimientoRepository repo;
    public PagoMantenimientoController(PagoMantenimientoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<PagoMantenimiento> listar() { return repo.findAll(); }

    @GetMapping("/informe")
    public List<PagoMantenimiento> informe(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hasta) {
        return repo.findByFechaPagoBetween(desde, hasta);
    }

    @GetMapping("/{id}")
    public PagoMantenimiento buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public PagoMantenimiento crear(@RequestBody PagoMantenimiento p) { return repo.save(p); }

    @PutMapping("/{id}")
    public PagoMantenimiento editar(@PathVariable Long id, @RequestBody PagoMantenimiento p) {
        return repo.findById(id).map(pm -> {
            pm.setMantenimiento(p.getMantenimiento());
            pm.setFormaPago(p.getFormaPago());
            pm.setFechaPago(p.getFechaPago());
            pm.setValor(p.getValor());
            pm.setObservaciones(p.getObservaciones());
            return repo.save(pm);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
}

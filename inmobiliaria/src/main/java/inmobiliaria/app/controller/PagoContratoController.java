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

import inmobiliaria.app.model.PagoContrato;
import inmobiliaria.app.repository.PagoContratoRepository;

@RestController
@RequestMapping("/api/pagos-contrato")
@CrossOrigin(origins = "*")
public class PagoContratoController {

    private final PagoContratoRepository repo;
    public PagoContratoController(PagoContratoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<PagoContrato> listar() { return repo.findAll(); }

    @GetMapping("/informe")
    public List<PagoContrato> informe(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hasta) {
        return repo.findByFechaPagoBetween(desde, hasta);
    }

    @GetMapping("/{id}")
    public PagoContrato buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public PagoContrato crear(@RequestBody PagoContrato p) { return repo.save(p); }

    @PutMapping("/{id}")
    public PagoContrato editar(@PathVariable Long id, @RequestBody PagoContrato p) {
        return repo.findById(id).map(pc -> {
            pc.setContrato(p.getContrato());
            pc.setFormaPago(p.getFormaPago());
            pc.setFechaPago(p.getFechaPago());
            pc.setValor(p.getValor());
            pc.setObservaciones(p.getObservaciones());
            return repo.save(pc);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
}

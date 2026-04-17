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

import inmobiliaria.app.model.FormaPago;
import inmobiliaria.app.repository.FormaPagoRepository;

@RestController
@RequestMapping("/api/formas-pago")
@CrossOrigin(origins = "*")
public class FormaPagoController {

    private final FormaPagoRepository repo;
    public FormaPagoController(FormaPagoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<FormaPago> listar() { return repo.findAll(); }

    @GetMapping("/{id}")
    public FormaPago buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public FormaPago crear(@RequestBody FormaPago f) { return repo.save(f); }

    @PutMapping("/{id}")
    public FormaPago editar(@PathVariable Long id, @RequestBody FormaPago f) {
        return repo.findById(id).map(fp -> {
            fp.setDescripcion(f.getDescripcion());
            return repo.save(fp);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
}

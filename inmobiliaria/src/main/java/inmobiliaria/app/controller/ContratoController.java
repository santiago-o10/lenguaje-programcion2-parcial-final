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

import inmobiliaria.app.model.Contrato;
import inmobiliaria.app.repository.ApartamentoRepository;
import inmobiliaria.app.repository.ContratoRepository;

@RestController
@RequestMapping("/api/contratos")
@CrossOrigin(origins = "*")
public class ContratoController {

    private final ContratoRepository repo;
    private final ApartamentoRepository apartamentoRepo;

    public ContratoController(ContratoRepository repo, ApartamentoRepository apartamentoRepo) {
        this.repo = repo;
        this.apartamentoRepo = apartamentoRepo;
    }

    @GetMapping
    public List<Contrato> listar() { return repo.findAll(); }

    @GetMapping("/informe")
    public List<Contrato> informe(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hasta) {
        return repo.findByFechaInicioBetween(desde, hasta);
    }

    @GetMapping("/{id}")
    public Contrato buscar(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public Contrato crear(@RequestBody Contrato c) {
        Contrato saved = repo.save(c);
        // Marcar apartamento como OCUPADO
        apartamentoRepo.findById(c.getApartamento().getIdApartamento()).ifPresent(ap -> {
            ap.setEstado(1);
            apartamentoRepo.save(ap);
        });
        return saved;
    }

    @PutMapping("/{id}")
    public Contrato editar(@PathVariable Long id, @RequestBody Contrato c) {
        return repo.findById(id).map(con -> {
            con.setApartamento(c.getApartamento());
            con.setCliente(c.getCliente());
            con.setEmpleado(c.getEmpleado());
            con.setFechaInicio(c.getFechaInicio());
            con.setFechaFin(c.getFechaFin());
            con.setValorMensual(c.getValorMensual());
            con.setEstado(c.getEstado());
            // Si se finaliza el contrato, liberar apartamento
            if ("FINALIZADO".equalsIgnoreCase(c.getEstado())) {
                apartamentoRepo.findById(c.getApartamento().getIdApartamento()).ifPresent(ap -> {
                    ap.setEstado(0);
                    apartamentoRepo.save(ap);
                });
            }
            return repo.save(con);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
}

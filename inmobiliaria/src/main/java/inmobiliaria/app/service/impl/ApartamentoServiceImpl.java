package inmobiliaria.app.service.impl;
 
import java.util.List;
 
import org.springframework.stereotype.Service;
 
import inmobiliaria.app.model.Apartamento;
import inmobiliaria.app.model.Ciudad;
import inmobiliaria.app.model.Propietario;
import inmobiliaria.app.repository.ApartamentoRepository;
import inmobiliaria.app.repository.CiudadRepository;
import inmobiliaria.app.repository.PropietarioRepository;
import inmobiliaria.app.service.ApartamentoService;
 
@Service
public class ApartamentoServiceImpl implements ApartamentoService {
 
    private final ApartamentoRepository apartamentoRepo;
    private final PropietarioRepository propietarioRepo;
    private final CiudadRepository ciudadRepo;
 
    public ApartamentoServiceImpl(ApartamentoRepository apartamentoRepo,
                                  PropietarioRepository propietarioRepo,
                                  CiudadRepository ciudadRepo) {
        this.apartamentoRepo = apartamentoRepo;
        this.propietarioRepo = propietarioRepo;
        this.ciudadRepo = ciudadRepo;
    }
 
    @Override
    public List<Apartamento> listar() {
        return apartamentoRepo.findAll();
    }
 
    @Override
    public List<Apartamento> porEstado(String estado) {
        return apartamentoRepo.findByEstado(Integer.parseInt(estado));
    }
 
    @Override
    public Apartamento buscarPorId(Long id) {
        return apartamentoRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Apartamento no encontrado"));
    }
 
    @Override
    public Apartamento crear(Apartamento a) {
        if (a.getPropietario() == null || a.getPropietario().getIdPropietario() == null)
            throw new RuntimeException("Propietario requerido");
        if (a.getCiudad() == null || a.getCiudad().getIdCiudad() == null)
            throw new RuntimeException("Ciudad requerida");
        if (a.getDireccion() == null || a.getDireccion().trim().isEmpty())
            throw new RuntimeException("Dirección requerida");
        if (a.getNumeroHabitacion() == null)
            throw new RuntimeException("Número de habitaciones requerido");
        if (a.getPagoMensual() == null)
            throw new RuntimeException("Pago mensual requerido");
 
        Propietario propietario = propietarioRepo.findById(a.getPropietario().getIdPropietario())
            .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        Ciudad ciudad = ciudadRepo.findById(a.getCiudad().getIdCiudad())
            .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
 
        a.setPropietario(propietario);
        a.setCiudad(ciudad);
        if (a.getEstado() == null) a.setEstado(1); // 1=LIBRE
 
        return apartamentoRepo.save(a);
    }
 
    @Override
    public Apartamento editar(Long id, Apartamento a) {
        Apartamento ap = apartamentoRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Apartamento no encontrado"));
 
        if (a.getPropietario() != null && a.getPropietario().getIdPropietario() != null) {
            Propietario p = propietarioRepo.findById(a.getPropietario().getIdPropietario())
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
            ap.setPropietario(p);
        }
        if (a.getCiudad() != null && a.getCiudad().getIdCiudad() != null) {
            Ciudad c = ciudadRepo.findById(a.getCiudad().getIdCiudad())
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
            ap.setCiudad(c);
        }
        if (a.getDireccion() != null && !a.getDireccion().trim().isEmpty())
            ap.setDireccion(a.getDireccion());
        if (a.getNumeroHabitacion() != null)
            ap.setNumeroHabitacion(a.getNumeroHabitacion());
        if (a.getPagoMensual() != null)
            ap.setPagoMensual(a.getPagoMensual());
        if (a.getEstado() != null)
            ap.setEstado(a.getEstado());
 
        return apartamentoRepo.save(ap);
    }
 
    @Override
    public void eliminar(Long id) {
        if (!apartamentoRepo.existsById(id))
            throw new RuntimeException("Apartamento no encontrado");
        apartamentoRepo.deleteById(id);
    }
}
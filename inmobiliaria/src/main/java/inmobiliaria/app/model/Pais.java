package inmobiliaria.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "PAIS")
public class Pais {

    @Id
    @SequenceGenerator(name = "seq_pais", sequenceName = "SEQ_PAIS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pais")
    @Column(name = "ID_PAIS")
    private Long idPais;

    @Column(name = "NOMBRE", nullable = false, length = 60)
    private String nombre;

    public Pais() {}

    public Long getIdPais() { return idPais; }
    public void setIdPais(Long idPais) { this.idPais = idPais; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}

package inmobiliaria.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "FORMA_PAGO")
public class FormaPago {

    @Id
    @SequenceGenerator(name = "seq_forma_pago", sequenceName = "SEQ_FORMA_PAGO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_forma_pago")
    @Column(name = "ID_FORMA_PAGO")
    private Long idFormaPago;

    @Column(name = "DESCRIPCION", nullable = false, length = 50)
    private String descripcion;

    public FormaPago() {}

    public Long getIdFormaPago() { return idFormaPago; }
    public void setIdFormaPago(Long idFormaPago) { this.idFormaPago = idFormaPago; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}

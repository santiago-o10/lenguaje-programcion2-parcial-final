package inmobiliaria.app.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "PAGO_MANTENIMIENTO")
public class PagoMantenimiento {

    @Id
    @SequenceGenerator(name = "seq_pago_mant", sequenceName = "SEQ_PAGO_MANT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pago_mant")
    @Column(name = "ID_PAGO_MANTENIMIENTO")
    private Long idPagoMantenimiento;

    @ManyToOne
    @JoinColumn(name = "ID_MANTENIMIENTO", nullable = false)
    private Mantenimiento mantenimiento;

    @ManyToOne
    @JoinColumn(name = "ID_FORMA_PAGO", nullable = false)
    private FormaPago formaPago;

    @Column(name = "FECHA_PAGO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPago;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "OBSERVACIONES", length = 200)
    private String observaciones;

    public PagoMantenimiento() {}

    public Long getIdPagoMantenimiento() { return idPagoMantenimiento; }
    public void setIdPagoMantenimiento(Long idPagoMantenimiento) { this.idPagoMantenimiento = idPagoMantenimiento; }
    public Mantenimiento getMantenimiento() { return mantenimiento; }
    public void setMantenimiento(Mantenimiento mantenimiento) { this.mantenimiento = mantenimiento; }
    public FormaPago getFormaPago() { return formaPago; }
    public void setFormaPago(FormaPago formaPago) { this.formaPago = formaPago; }
    public Date getFechaPago() { return fechaPago; }
    public void setFechaPago(Date fechaPago) { this.fechaPago = fechaPago; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}

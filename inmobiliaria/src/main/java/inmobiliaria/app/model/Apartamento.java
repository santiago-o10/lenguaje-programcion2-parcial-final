package inmobiliaria.app.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "APARTAMENTO")
public class Apartamento {
 
    @Id
    @SequenceGenerator(name = "seq_apartamento", sequenceName = "SEQ_APARTAMENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_apartamento")
    @Column(name = "ID_APARTAMENTO")
    private Long idApartamento;
 
    @ManyToOne
    @JoinColumn(name = "ID_PROPIETARIO", nullable = false)
    private Propietario propietario;
 
    @ManyToOne
    @JoinColumn(name = "ID_CIUDAD", nullable = false)
    private Ciudad ciudad;
 
    @Column(name = "DIRECCION", nullable = false, length = 30)
    private String direccion;
 
    @Column(name = "NUMERO_HABITACION", nullable = false)
    private Integer numeroHabitacion;
 
    // 1 = LIBRE, 0 = OCUPADO  (NUMBER(1) en Oracle)
    @Column(name = "ESTADO", nullable = false)
    private Integer estado;
 
    @Column(name = "PAGO_MENSUAL", nullable = false, precision = 10, scale = 2)
    private BigDecimal pagoMensual;
 
    public Apartamento() {}
 
    public Long getIdApartamento() { return idApartamento; }
    public void setIdApartamento(Long idApartamento) { this.idApartamento = idApartamento; }
 
    public Propietario getPropietario() { return propietario; }
    public void setPropietario(Propietario propietario) { this.propietario = propietario; }
 
    public Ciudad getCiudad() { return ciudad; }
    public void setCiudad(Ciudad ciudad) { this.ciudad = ciudad; }
 
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
 
    public Integer getNumeroHabitacion() { return numeroHabitacion; }
    public void setNumeroHabitacion(Integer numeroHabitacion) { this.numeroHabitacion = numeroHabitacion; }
 
    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }
 
    public BigDecimal getPagoMensual() { return pagoMensual; }
    public void setPagoMensual(BigDecimal pagoMensual) { this.pagoMensual = pagoMensual; }
}
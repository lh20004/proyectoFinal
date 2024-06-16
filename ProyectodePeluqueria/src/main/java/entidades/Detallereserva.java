/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author javier
 */
@Entity
@Table(name = "detallereserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallereserva.findAll", query = "SELECT d FROM Detallereserva d"),
    @NamedQuery(name = "Detallereserva.findByIddetalle", query = "SELECT d FROM Detallereserva d WHERE d.iddetalle = :iddetalle")})
public class Detallereserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "iddetalle")
    private Integer iddetalle;
    @JoinColumn(name = "idreserva", referencedColumnName = "idreserva")
    @ManyToOne
    private Reserva idreserva;
    @JoinColumn(name = "idservicio", referencedColumnName = "idservicio")
    @ManyToOne
    private Servicio idservicio;

    public Detallereserva() {
    }

    public Detallereserva(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Integer getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Integer iddetalle) {
        this.iddetalle = iddetalle;
    }

    public Reserva getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(Reserva idreserva) {
        this.idreserva = idreserva;
    }

    public Servicio getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(Servicio idservicio) {
        this.idservicio = idservicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalle != null ? iddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallereserva)) {
            return false;
        }
        Detallereserva other = (Detallereserva) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detallereserva[ iddetalle=" + iddetalle + " ]";
    }
    
}

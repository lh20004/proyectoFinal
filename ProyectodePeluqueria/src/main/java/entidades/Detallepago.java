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
@Table(name = "detallepago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallepago.findAll", query = "SELECT d FROM Detallepago d"),
    @NamedQuery(name = "Detallepago.findByIddetallepago", query = "SELECT d FROM Detallepago d WHERE d.iddetallepago = :iddetallepago")})
public class Detallepago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "iddetallepago")
    private Integer iddetallepago;
    @JoinColumn(name = "idpago", referencedColumnName = "idpago")
    @ManyToOne
    private Pago idpago;
    @JoinColumn(name = "idservicio", referencedColumnName = "idservicio")
    @ManyToOne
    private Servicio idservicio;

    public Detallepago() {
    }

    public Detallepago(Integer iddetallepago) {
        this.iddetallepago = iddetallepago;
    }

    public Integer getIddetallepago() {
        return iddetallepago;
    }

    public void setIddetallepago(Integer iddetallepago) {
        this.iddetallepago = iddetallepago;
    }

    public Pago getIdpago() {
        return idpago;
    }

    public void setIdpago(Pago idpago) {
        this.idpago = idpago;
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
        hash += (iddetallepago != null ? iddetallepago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallepago)) {
            return false;
        }
        Detallepago other = (Detallepago) object;
        if ((this.iddetallepago == null && other.iddetallepago != null) || (this.iddetallepago != null && !this.iddetallepago.equals(other.iddetallepago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detallepago[ iddetallepago=" + iddetallepago + " ]";
    }
    
}

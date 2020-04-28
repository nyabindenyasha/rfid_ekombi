package com.minimum.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GustTech
 */
@Entity
@Table(name = "rfid_ekombi_kombis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RfidEkombiKombis.findAll", query = "SELECT r FROM RfidEkombiKombis r")
    , @NamedQuery(name = "RfidEkombiKombis.findById", query = "SELECT r FROM RfidEkombiKombis r WHERE r.id = :id")
    , @NamedQuery(name = "RfidEkombiKombis.findByFromSource", query = "SELECT r FROM RfidEkombiKombis r WHERE r.fromSource = :fromSource")
    , @NamedQuery(name = "RfidEkombiKombis.findByToDestination", query = "SELECT r FROM RfidEkombiKombis r WHERE r.toDestination = :toDestination")})
public class RfidEkombiKombis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "from_source")
    private String fromSource;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "to_destination")
    private String toDestination;
    
    private int kombiFareDistanceId;
    
    private String fleetNumber;

    public RfidEkombiKombis() {
    }

    public RfidEkombiKombis(Integer id) {
        this.id = id;
    }

    public RfidEkombiKombis(Integer id, String fromSource, String toDestination) {
        this.id = id;
        this.fromSource = fromSource;
        this.toDestination = toDestination;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromSource() {
        return fromSource;
    }

    public void setFromSource(String fromSource) {
        this.fromSource = fromSource;
    }

    public String getToDestination() {
        return toDestination;
    }

    public void setToDestination(String toDestination) {
        this.toDestination = toDestination;
    }

    public int getKombiFareDistanceId() {
		return kombiFareDistanceId;
	}

	public void setKombiFareDistanceId(int kombiFareDistanceId) {
		this.kombiFareDistanceId = kombiFareDistanceId;
	}

	public String getFleetNumber() {
		return fleetNumber;
	}

	public void setFleetNumber(String fleetNumber) {
		this.fleetNumber = fleetNumber;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RfidEkombiKombis)) {
            return false;
        }
        RfidEkombiKombis other = (RfidEkombiKombis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zw.co.afrosoft.hospitalmanagement.tee_5_all.RfidEkombiKombis[ id=" + id + " ]";
    }
    
}

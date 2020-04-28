package com.minimum.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author GustTech
 */
@Entity
@Table(name = "rfid_ekombi_kombi_fare_distance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RfidEkombiKombiFareDistance.findAll", query = "SELECT r FROM RfidEkombiKombiFareDistance r")
    , @NamedQuery(name = "RfidEkombiKombiFareDistance.findById", query = "SELECT r FROM RfidEkombiKombiFareDistance r WHERE r.id = :id")
    , @NamedQuery(name = "RfidEkombiKombiFareDistance.findByKombiFare", query = "SELECT r FROM RfidEkombiKombiFareDistance r WHERE r.kombiFare = :kombiFare")
    , @NamedQuery(name = "RfidEkombiKombiFareDistance.findByDistance", query = "SELECT r FROM RfidEkombiKombiFareDistance r WHERE r.distance = :distance")})
public class RfidEkombiKombiFareDistance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "kombi_fare")
    private double kombiFare;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "distance")
    private double distance;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kombiFareDistanceId")
    private List<RfidEkombiKombis> rfidEkombiKombisList;

    public RfidEkombiKombiFareDistance() {
    }

    public RfidEkombiKombiFareDistance(Integer id) {
        this.id = id;
    }

    public RfidEkombiKombiFareDistance(Integer id, int kombiFare, int distance) {
        this.id = id;
        this.kombiFare = kombiFare;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getKombiFare() {
        return kombiFare;
    }

    public void setKombiFare(double kombiFare) {
        this.kombiFare = kombiFare;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @XmlTransient
    public List<RfidEkombiKombis> getRfidEkombiKombisList() {
        return rfidEkombiKombisList;
    }

    public void setRfidEkombiKombisList(List<RfidEkombiKombis> rfidEkombiKombisList) {
        this.rfidEkombiKombisList = rfidEkombiKombisList;
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
        if (!(object instanceof RfidEkombiKombiFareDistance)) {
            return false;
        }
        RfidEkombiKombiFareDistance other = (RfidEkombiKombiFareDistance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zw.co.afrosoft.hospitalmanagement.tee_5_all.RfidEkombiKombiFareDistance[ id=" + id + " ]";
    }
    
}

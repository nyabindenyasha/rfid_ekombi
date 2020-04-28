package com.minimum.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GustTech
 */
@Entity
@Table(name = "rfid_ekombi_kombi_tracker")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RfidEkombiKombiTracker.findAll", query = "SELECT r FROM RfidEkombiKombiTracker r")
    , @NamedQuery(name = "RfidEkombiKombiTracker.findById", query = "SELECT r FROM RfidEkombiKombiTracker r WHERE r.id = :id")
    , @NamedQuery(name = "RfidEkombiKombiTracker.findByKombiId", query = "SELECT r FROM RfidEkombiKombiTracker r WHERE r.kombiId = :kombiId")
    , @NamedQuery(name = "RfidEkombiKombiTracker.findByCurrentLocationLatitude", query = "SELECT r FROM RfidEkombiKombiTracker r WHERE r.currentLocationLatitude = :currentLocationLatitude")
    , @NamedQuery(name = "RfidEkombiKombiTracker.findByCurrentLocationLongitude", query = "SELECT r FROM RfidEkombiKombiTracker r WHERE r.currentLocationLongitude = :currentLocationLongitude")
    , @NamedQuery(name = "RfidEkombiKombiTracker.findByTime", query = "SELECT r FROM RfidEkombiKombiTracker r WHERE r.time = :time")})
public class RfidEkombiKombiTracker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "kombi_id")
    private int kombiId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "current_location_latitude")
    private double currentLocationLatitude;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "current_location_longitude")
    private double currentLocationLongitude;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public RfidEkombiKombiTracker() {
    }

    public RfidEkombiKombiTracker(Integer id) {
        this.id = id;
    }

    public RfidEkombiKombiTracker(Integer id, int kombiId, int currentLocationLatitude, int currentLocationLongitude, Date time) {
        this.id = id;
        this.kombiId = kombiId;
        this.currentLocationLatitude = currentLocationLatitude;
        this.currentLocationLongitude = currentLocationLongitude;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getKombiId() {
        return kombiId;
    }

    public void setKombiId(int kombiId) {
        this.kombiId = kombiId;
    }

    public double getCurrentLocationLatitude() {
        return currentLocationLatitude;
    }

    public void setCurrentLocationLatitude(double currentLocationLatitude) {
        this.currentLocationLatitude = currentLocationLatitude;
    }

    public double getCurrentLocationLongitude() {
        return currentLocationLongitude;
    }

    public void setCurrentLocationLongitude(double currentLocationLongitude) {
        this.currentLocationLongitude = currentLocationLongitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
        if (!(object instanceof RfidEkombiKombiTracker)) {
            return false;
        }
        RfidEkombiKombiTracker other = (RfidEkombiKombiTracker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zw.co.afrosoft.hospitalmanagement.tee_5_all.RfidEkombiKombiTracker[ id=" + id + " ]";
    }
    
}

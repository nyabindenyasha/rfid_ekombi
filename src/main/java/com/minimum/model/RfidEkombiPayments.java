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
@Table(name = "rfid_ekombi_payments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RfidEkombiPayments.findAll", query = "SELECT r FROM RfidEkombiPayments r")
    , @NamedQuery(name = "RfidEkombiPayments.findById", query = "SELECT r FROM RfidEkombiPayments r WHERE r.id = :id")
    , @NamedQuery(name = "RfidEkombiPayments.findByDateCreated", query = "SELECT r FROM RfidEkombiPayments r WHERE r.dateCreated = :dateCreated")})
public class RfidEkombiPayments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    
    private int kombiId;
    
    private int userId;
    
    private double amountPaid;

    public RfidEkombiPayments() {
    }

    public RfidEkombiPayments(Integer id) {
        this.id = id;
    }

    public RfidEkombiPayments(Integer id, Date dateCreated) {
        this.id = id;
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getKombiId() {
		return kombiId;
	}

	public void setKombiId(int kombiId) {
		this.kombiId = kombiId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
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
        if (!(object instanceof RfidEkombiPayments)) {
            return false;
        }
        RfidEkombiPayments other = (RfidEkombiPayments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zw.co.afrosoft.hospitalmanagement.tee_5_all.RfidEkombiPayments[ id=" + id + " ]";
    }
    
}

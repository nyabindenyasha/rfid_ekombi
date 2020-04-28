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
@Table(name = "rfid_ekombi_account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RfidEkombiAccount.findAll", query = "SELECT r FROM RfidEkombiAccount r")
    , @NamedQuery(name = "RfidEkombiAccount.findById", query = "SELECT r FROM RfidEkombiAccount r WHERE r.id = :id")
    , @NamedQuery(name = "RfidEkombiAccount.findByCardBalance", query = "SELECT r FROM RfidEkombiAccount r WHERE r.cardBalance = :cardBalance")})
public class RfidEkombiAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "card_balance")
    private double cardBalance;
    
    private double bankBalance;
    
    private double ecocashBalance;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private List<RfidEkombiUsers> rfidEkombiUsersList;

    public RfidEkombiAccount() {
    }

    public RfidEkombiAccount(Integer id) {
        this.id = id;
    }

    public RfidEkombiAccount(Integer id, int cardBalance) {
        this.id = id;
        this.cardBalance = cardBalance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(double cardBalance) {
		this.cardBalance = cardBalance;
	}

	public double getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}

	public double getEcocashBalance() {
		return ecocashBalance;
	}

	public void setEcocashBalance(double ecocashBalance) {
		this.ecocashBalance = ecocashBalance;
	}

	@XmlTransient
    public List<RfidEkombiUsers> getRfidEkombiUsersList() {
        return rfidEkombiUsersList;
    }

    public void setRfidEkombiUsersList(List<RfidEkombiUsers> rfidEkombiUsersList) {
        this.rfidEkombiUsersList = rfidEkombiUsersList;
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
        if (!(object instanceof RfidEkombiAccount)) {
            return false;
        }
        RfidEkombiAccount other = (RfidEkombiAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zw.co.afrosoft.hospitalmanagement.tee_5_all.RfidEkombiAccount[ id=" + id + " ]";
    }
    
}

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author GustTech
 */
@Entity
@Table(name = "rfid_ekombi_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RfidEkombiUsers.findAll", query = "SELECT r FROM RfidEkombiUsers r")
    , @NamedQuery(name = "RfidEkombiUsers.findById", query = "SELECT r FROM RfidEkombiUsers r WHERE r.id = :id")
    , @NamedQuery(name = "RfidEkombiUsers.findByMobileNumber", query = "SELECT r FROM RfidEkombiUsers r WHERE r.mobileNumber = :mobileNumber")
    , @NamedQuery(name = "RfidEkombiUsers.findByNationalId", query = "SELECT r FROM RfidEkombiUsers r WHERE r.nationalId = :nationalId")
    , @NamedQuery(name = "RfidEkombiUsers.findByRfidUniqueId", query = "SELECT r FROM RfidEkombiUsers r WHERE r.rfidUniqueId = :rfidUniqueId")
    , @NamedQuery(name = "RfidEkombiUsers.findByName", query = "SELECT r FROM RfidEkombiUsers r WHERE r.name = :name")
    , @NamedQuery(name = "RfidEkombiUsers.findBySurname", query = "SELECT r FROM RfidEkombiUsers r WHERE r.surname = :surname")
    , @NamedQuery(name = "RfidEkombiUsers.findByHarshedPassword", query = "SELECT r FROM RfidEkombiUsers r WHERE r.harshedPassword = :harshedPassword")
    , @NamedQuery(name = "RfidEkombiUsers.findBySecurityStamp", query = "SELECT r FROM RfidEkombiUsers r WHERE r.securityStamp = :securityStamp")
    , @NamedQuery(name = "RfidEkombiUsers.findByUsername", query = "SELECT r FROM RfidEkombiUsers r WHERE r.username = :username")})
public class RfidEkombiUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "mobile_number")
    private String mobileNumber;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "national_id")
    private String nationalId;
    
    @JsonIgnore
    @Size(max = 250)
    @Column(name = "rfid_unique_id")
    private String rfidUniqueId;
    
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    
    @Size(max = 50)
    @Column(name = "surname")
    private String surname;
    
    @JsonIgnore
    @Size(max = 250)
    @Column(name = "harshed_password")
    private String harshedPassword;
    
    @JsonIgnore
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "security_stamp")
    private String securityStamp;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    
    private int accountId;
    
    private boolean isActive;
    
    @Transient
    private String token;

    public RfidEkombiUsers() {
    }

    public RfidEkombiUsers(Integer id) {
        this.id = id;
    }

    public RfidEkombiUsers(Integer id, String mobileNumber, String nationalId, String securityStamp, String username) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.nationalId = nationalId;
        this.securityStamp = securityStamp;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getRfidUniqueId() {
        return rfidUniqueId;
    }

    public void setRfidUniqueId(String rfidUniqueId) {
        this.rfidUniqueId = rfidUniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getHarshedPassword() {
        return harshedPassword;
    }

    public void setHarshedPassword(String harshedPassword) {
        this.harshedPassword = harshedPassword;
    }

    public String getSecurityStamp() {
        return securityStamp;
    }

    public void setSecurityStamp(String securityStamp) {
        this.securityStamp = securityStamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
        if (!(object instanceof RfidEkombiUsers)) {
            return false;
        }
        RfidEkombiUsers other = (RfidEkombiUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "zw.co.afrosoft.hospitalmanagement.tee_5_all.RfidEkombiUsers[ id=" + id + " ]";
    }
    
}

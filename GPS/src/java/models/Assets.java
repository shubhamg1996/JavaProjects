/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 1389026
 */
@Entity
@Table(name = "assets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assets.findAll", query = "SELECT a FROM Assets a"),
    @NamedQuery(name = "Assets.findByIdassets", query = "SELECT a FROM Assets a WHERE a.idassets = :idassets"),
    @NamedQuery(name = "Assets.findByCategory", query = "SELECT a FROM Assets a WHERE a.category = :category"),
    @NamedQuery(name = "Assets.findByRequestor", query = "SELECT a FROM Assets a WHERE a.requestor = :requestor"),
    @NamedQuery(name = "Assets.findByVendor", query = "SELECT a FROM Assets a WHERE a.vendor = :vendor"),
    @NamedQuery(name = "Assets.findByAmount", query = "SELECT a FROM Assets a WHERE a.amount = :amount"),
    @NamedQuery(name = "Assets.findByGpsNumber", query = "SELECT a FROM Assets a WHERE a.gpsNumber = :gpsNumber"),
    @NamedQuery(name = "Assets.findByRequestDate", query = "SELECT a FROM Assets a WHERE a.requestDate = :requestDate"),
    @NamedQuery(name = "Assets.findByAssetStatus", query = "SELECT a FROM Assets a WHERE a.assetStatus = :assetStatus"),
    @NamedQuery(name = "Assets.findByPoNumber", query = "SELECT a FROM Assets a WHERE a.poNumber = :poNumber"),
    @NamedQuery(name = "Assets.findByAssetId", query = "SELECT a FROM Assets a WHERE a.assetId = :assetId"),
    @NamedQuery(name = "Assets.findByRemarks", query = "SELECT a FROM Assets a WHERE a.remarks = :remarks")})
public class Assets implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idassets")
    private Integer idassets;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "category")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "requestor")
    private String requestor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "vendor")
    private String vendor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "amount")
    private String amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "gps_number")
    private String gpsNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "request_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "asset_status")
    private String assetStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "po_number")
    private String poNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "asset_id")
    private String assetId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "remarks")
    private String remarks;

    public Assets() {
    }

    public Assets(Integer idassets) {
        this.idassets = idassets;
    }

    public Assets(Integer idassets, String category, String requestor, String vendor, String amount, String gpsNumber, Date requestDate, String assetStatus, String poNumber, String assetId, String remarks) {
        this.idassets = idassets;
        this.category = category;
        this.requestor = requestor;
        this.vendor = vendor;
        this.amount = amount;
        this.gpsNumber = gpsNumber;
        this.requestDate = requestDate;
        this.assetStatus = assetStatus;
        this.poNumber = poNumber;
        this.assetId = assetId;
        this.remarks = remarks;
    }

    public Integer getIdassets() {
        return idassets;
    }

    public void setIdassets(Integer idassets) {
        this.idassets = idassets;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getGpsNumber() {
        return gpsNumber;
    }

    public void setGpsNumber(String gpsNumber) {
        this.gpsNumber = gpsNumber;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idassets != null ? idassets.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assets)) {
            return false;
        }
        Assets other = (Assets) object;
        if ((this.idassets == null && other.idassets != null) || (this.idassets != null && !this.idassets.equals(other.idassets))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Assets[ idassets=" + idassets + " ]";
    }
    
}

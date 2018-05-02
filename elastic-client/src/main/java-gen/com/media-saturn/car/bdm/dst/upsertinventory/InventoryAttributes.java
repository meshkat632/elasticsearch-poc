package com.media-saturn.car.bdm.dst.upsertinventory;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class InventoryAttributes 
{
    private Integer stockLocationId;
    private Integer stockForSale;
    private String stockCategoryName;
    private Integer quantity;
    private Integer quantityReserved;
    private Integer unavailableToSell;
    private Integer reservedReturnToVendor;
    private Integer stockOnHand;
    private Integer availableToSell;
    private java.time.LocalDateTime updateTime;
    private String changeType;
    private String locationType;
    private java.util.Map<String,String> extension = new java.util.HashMap<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("InventoryAttributes[");
        toString(sb);
        return sb.append("]").toString();
    }
    protected void toString(StringBuilder sb) {
        sb.append("stockLocationId=").append(stockLocationId);
        sb.append(", stockForSale=").append(stockForSale);
        sb.append(", stockCategoryName=").append(stockCategoryName);
        sb.append(", quantity=").append(quantity);
        sb.append(", quantityReserved=").append(quantityReserved);
        sb.append(", unavailableToSell=").append(unavailableToSell);
        sb.append(", reservedReturnToVendor=").append(reservedReturnToVendor);
        sb.append(", stockOnHand=").append(stockOnHand);
        sb.append(", availableToSell=").append(availableToSell);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", changeType=").append(changeType);
        sb.append(", locationType=").append(locationType);
        sb.append(", extension=").append(extension);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryAttributes other = (InventoryAttributes)o;
        if (stockLocationId != null ? !stockLocationId.equals(other.stockLocationId) : other.stockLocationId != null) return false;
        if (stockForSale != null ? !stockForSale.equals(other.stockForSale) : other.stockForSale != null) return false;
        if (stockCategoryName != null ? !stockCategoryName.equals(other.stockCategoryName) : other.stockCategoryName != null) return false;
        if (quantity != null ? !quantity.equals(other.quantity) : other.quantity != null) return false;
        if (quantityReserved != null ? !quantityReserved.equals(other.quantityReserved) : other.quantityReserved != null) return false;
        if (unavailableToSell != null ? !unavailableToSell.equals(other.unavailableToSell) : other.unavailableToSell != null) return false;
        if (reservedReturnToVendor != null ? !reservedReturnToVendor.equals(other.reservedReturnToVendor) : other.reservedReturnToVendor != null) return false;
        if (stockOnHand != null ? !stockOnHand.equals(other.stockOnHand) : other.stockOnHand != null) return false;
        if (availableToSell != null ? !availableToSell.equals(other.availableToSell) : other.availableToSell != null) return false;
        if (updateTime != null ? !updateTime.equals(other.updateTime) : other.updateTime != null) return false;
        if (changeType != null ? !changeType.equals(other.changeType) : other.changeType != null) return false;
        if (locationType != null ? !locationType.equals(other.locationType) : other.locationType != null) return false;
        if (extension != null ? !extension.equals(other.extension) : other.extension != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (stockLocationId != null ? stockLocationId.hashCode() : 0);
        result = 31 * result + (stockForSale != null ? stockForSale.hashCode() : 0);
        result = 31 * result + (stockCategoryName != null ? stockCategoryName.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (quantityReserved != null ? quantityReserved.hashCode() : 0);
        result = 31 * result + (unavailableToSell != null ? unavailableToSell.hashCode() : 0);
        result = 31 * result + (reservedReturnToVendor != null ? reservedReturnToVendor.hashCode() : 0);
        result = 31 * result + (stockOnHand != null ? stockOnHand.hashCode() : 0);
        result = 31 * result + (availableToSell != null ? availableToSell.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (changeType != null ? changeType.hashCode() : 0);
        result = 31 * result + (locationType != null ? locationType.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }
    public Integer getStockLocationId() {
        return stockLocationId;
    }
    public void setStockLocationId(Integer stockLocationId) {
        this.stockLocationId = stockLocationId;
    }
    public Integer getStockForSale() {
        return stockForSale;
    }
    public void setStockForSale(Integer stockForSale) {
        this.stockForSale = stockForSale;
    }
    public String getStockCategoryName() {
        return stockCategoryName;
    }
    public void setStockCategoryName(String stockCategoryName) {
        this.stockCategoryName = stockCategoryName;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getQuantityReserved() {
        return quantityReserved;
    }
    public void setQuantityReserved(Integer quantityReserved) {
        this.quantityReserved = quantityReserved;
    }
    public Integer getUnavailableToSell() {
        return unavailableToSell;
    }
    public void setUnavailableToSell(Integer unavailableToSell) {
        this.unavailableToSell = unavailableToSell;
    }
    public Integer getReservedReturnToVendor() {
        return reservedReturnToVendor;
    }
    public void setReservedReturnToVendor(Integer reservedReturnToVendor) {
        this.reservedReturnToVendor = reservedReturnToVendor;
    }
    public Integer getStockOnHand() {
        return stockOnHand;
    }
    public void setStockOnHand(Integer stockOnHand) {
        this.stockOnHand = stockOnHand;
    }
    public Integer getAvailableToSell() {
        return availableToSell;
    }
    public void setAvailableToSell(Integer availableToSell) {
        this.availableToSell = availableToSell;
    }
    public java.time.LocalDateTime getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(java.time.LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getChangeType() {
        return changeType;
    }
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }
    public String getLocationType() {
        return locationType;
    }
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
    @JsonAnyGetter
    public java.util.Map<String,String> getExtension() {
        return extension;
    }
    public void setExtension(java.util.Map<String,String> extension) {
        this.extension = extension;
    }
    @JsonAnySetter
    public void putExtension(String key, String value) {
        this.extension.put(key, value);
    }
}
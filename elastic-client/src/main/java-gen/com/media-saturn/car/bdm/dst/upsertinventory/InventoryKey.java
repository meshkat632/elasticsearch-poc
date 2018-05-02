package com.media-saturn.car.bdm.dst.upsertinventory;

import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class InventoryKey 
{
    private String sourceSystem;
    private Integer outletId;
    private int productId;
    private Boolean ttFlag;
    private Boolean onlineFlag;
    @Pattern(regexp="^[A-Z]{2}$")
    private String subsidiary;
    private String brand;
    @JacksonXmlElementWrapper(localName="stockLocationIds", useWrapping=false)
    @JacksonXmlProperty(localName="stockLocationId")
    private java.util.List<Integer> stockLocationIds;
    @JacksonXmlElementWrapper(localName="stockCategoryNames", useWrapping=false)
    @JacksonXmlProperty(localName="stockCategoryName")
    private java.util.List<String> stockCategoryNames;
    private Integer atpStock;
    private Integer atsStock;
    private Integer backlogStock;
    private java.util.Map<String,String> extension = new java.util.HashMap<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("InventoryKey[");
        toString(sb);
        return sb.append("]").toString();
    }
    protected void toString(StringBuilder sb) {
        sb.append("sourceSystem=").append(sourceSystem);
        sb.append(", outletId=").append(outletId);
        sb.append(", productId=").append(productId);
        sb.append(", ttFlag=").append(ttFlag);
        sb.append(", onlineFlag=").append(onlineFlag);
        sb.append(", subsidiary=").append(subsidiary);
        sb.append(", brand=").append(brand);
        sb.append(", stockLocationIds=").append(stockLocationIds);
        sb.append(", stockCategoryNames=").append(stockCategoryNames);
        sb.append(", atpStock=").append(atpStock);
        sb.append(", atsStock=").append(atsStock);
        sb.append(", backlogStock=").append(backlogStock);
        sb.append(", extension=").append(extension);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryKey other = (InventoryKey)o;
        if (sourceSystem != null ? !sourceSystem.equals(other.sourceSystem) : other.sourceSystem != null) return false;
        if (outletId != null ? !outletId.equals(other.outletId) : other.outletId != null) return false;
        if (productId != other.productId) return false;
        if (ttFlag != null ? !ttFlag.equals(other.ttFlag) : other.ttFlag != null) return false;
        if (onlineFlag != null ? !onlineFlag.equals(other.onlineFlag) : other.onlineFlag != null) return false;
        if (subsidiary != null ? !subsidiary.equals(other.subsidiary) : other.subsidiary != null) return false;
        if (brand != null ? !brand.equals(other.brand) : other.brand != null) return false;
        if (stockLocationIds != null ? !stockLocationIds.equals(other.stockLocationIds) : other.stockLocationIds != null) return false;
        if (stockCategoryNames != null ? !stockCategoryNames.equals(other.stockCategoryNames) : other.stockCategoryNames != null) return false;
        if (atpStock != null ? !atpStock.equals(other.atpStock) : other.atpStock != null) return false;
        if (atsStock != null ? !atsStock.equals(other.atsStock) : other.atsStock != null) return false;
        if (backlogStock != null ? !backlogStock.equals(other.backlogStock) : other.backlogStock != null) return false;
        if (extension != null ? !extension.equals(other.extension) : other.extension != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (sourceSystem != null ? sourceSystem.hashCode() : 0);
        result = 31 * result + (outletId != null ? outletId.hashCode() : 0);
        result = 31 * result + productId;
        result = 31 * result + (ttFlag != null ? ttFlag.hashCode() : 0);
        result = 31 * result + (onlineFlag != null ? onlineFlag.hashCode() : 0);
        result = 31 * result + (subsidiary != null ? subsidiary.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (stockLocationIds != null ? stockLocationIds.hashCode() : 0);
        result = 31 * result + (stockCategoryNames != null ? stockCategoryNames.hashCode() : 0);
        result = 31 * result + (atpStock != null ? atpStock.hashCode() : 0);
        result = 31 * result + (atsStock != null ? atsStock.hashCode() : 0);
        result = 31 * result + (backlogStock != null ? backlogStock.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }
    public String getSourceSystem() {
        return sourceSystem;
    }
    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
    public Integer getOutletId() {
        return outletId;
    }
    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public Boolean isTtFlag() {
        return ttFlag;
    }
    public void setTtFlag(Boolean ttFlag) {
        this.ttFlag = ttFlag;
    }
    public Boolean isOnlineFlag() {
        return onlineFlag;
    }
    public void setOnlineFlag(Boolean onlineFlag) {
        this.onlineFlag = onlineFlag;
    }
    public String getSubsidiary() {
        return subsidiary;
    }
    public void setSubsidiary(String subsidiary) {
        this.subsidiary = subsidiary;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public java.util.List<Integer> getStockLocationIds() {
        return stockLocationIds;
    }
    public void setStockLocationIds(java.util.List<Integer> stockLocationIds) {
        this.stockLocationIds = stockLocationIds;
    }
    public void addStockLocationId(Integer stockLocationId) {
        if (this.stockLocationIds == null)
            this.stockLocationIds = new java.util.ArrayList<>();
        this.stockLocationIds.add(stockLocationId);
    }
    public java.util.List<String> getStockCategoryNames() {
        return stockCategoryNames;
    }
    public void setStockCategoryNames(java.util.List<String> stockCategoryNames) {
        this.stockCategoryNames = stockCategoryNames;
    }
    public void addStockCategoryName(String stockCategoryName) {
        if (this.stockCategoryNames == null)
            this.stockCategoryNames = new java.util.ArrayList<>();
        this.stockCategoryNames.add(stockCategoryName);
    }
    public Integer getAtpStock() {
        return atpStock;
    }
    public void setAtpStock(Integer atpStock) {
        this.atpStock = atpStock;
    }
    public Integer getAtsStock() {
        return atsStock;
    }
    public void setAtsStock(Integer atsStock) {
        this.atsStock = atsStock;
    }
    public Integer getBacklogStock() {
        return backlogStock;
    }
    public void setBacklogStock(Integer backlogStock) {
        this.backlogStock = backlogStock;
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
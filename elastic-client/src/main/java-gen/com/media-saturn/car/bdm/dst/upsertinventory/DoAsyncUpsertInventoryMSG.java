package com.media-saturn.car.bdm.dst.upsertinventory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"schemaLocation"})
public class DoAsyncUpsertInventoryMSG 
{
    @JacksonXmlElementWrapper(localName="inventoryList")
    @JacksonXmlProperty(localName="inventoryList")
    @Valid
    private java.util.List<Inventory> inventoryList;
    private java.util.Map<String,String> extension = new java.util.HashMap<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DoAsyncUpsertInventoryMSG[");
        toString(sb);
        return sb.append("]").toString();
    }
    protected void toString(StringBuilder sb) {
        sb.append("inventoryList=").append(inventoryList);
        sb.append(", extension=").append(extension);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoAsyncUpsertInventoryMSG other = (DoAsyncUpsertInventoryMSG)o;
        if (inventoryList != null ? !inventoryList.equals(other.inventoryList) : other.inventoryList != null) return false;
        if (extension != null ? !extension.equals(other.extension) : other.extension != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (inventoryList != null ? inventoryList.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }
    public java.util.List<Inventory> getInventoryList() {
        return inventoryList;
    }
    public void setInventoryList(java.util.List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
    public void addInventoryList(Inventory inventoryList) {
        if (this.inventoryList == null)
            this.inventoryList = new java.util.ArrayList<>();
        this.inventoryList.add(inventoryList);
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
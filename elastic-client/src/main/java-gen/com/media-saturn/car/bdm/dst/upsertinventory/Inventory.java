package com.media-saturn.car.bdm.dst.upsertinventory;

import javax.validation.constraints.NotNull;
import javax.validation.Valid;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class Inventory 
{
    @NotNull
    @Valid
    private InventoryKey keys;
    @JacksonXmlElementWrapper(localName="attributeses", useWrapping=false)
    @JacksonXmlProperty(localName="attributes")
    @Valid
    private java.util.List<InventoryAttributes> attributeses;
    private java.util.Map<String,String> extension = new java.util.HashMap<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Inventory[");
        toString(sb);
        return sb.append("]").toString();
    }
    protected void toString(StringBuilder sb) {
        sb.append("keys=").append(keys);
        sb.append(", attributeses=").append(attributeses);
        sb.append(", extension=").append(extension);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory other = (Inventory)o;
        if (keys != null ? !keys.equals(other.keys) : other.keys != null) return false;
        if (attributeses != null ? !attributeses.equals(other.attributeses) : other.attributeses != null) return false;
        if (extension != null ? !extension.equals(other.extension) : other.extension != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (keys != null ? keys.hashCode() : 0);
        result = 31 * result + (attributeses != null ? attributeses.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }
    public InventoryKey getKeys() {
        return keys;
    }
    public void setKeys(InventoryKey keys) {
        this.keys = keys;
    }
    public java.util.List<InventoryAttributes> getAttributeses() {
        return attributeses;
    }
    public void setAttributeses(java.util.List<InventoryAttributes> attributeses) {
        this.attributeses = attributeses;
    }
    public void addAttributes(InventoryAttributes attributes) {
        if (this.attributeses == null)
            this.attributeses = new java.util.ArrayList<>();
        this.attributeses.add(attributes);
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
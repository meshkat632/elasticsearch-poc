package com.media-saturn.car.bdm.dst.upsertinventory;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAnyGetter;

public class SynchronousInventory 
{
    @Valid
    private InventoryKey input;
    @Valid
    private Inventory output;
    @Valid
    private Error error;
    private java.util.Map<String,String> extension = new java.util.HashMap<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SynchronousInventory[");
        toString(sb);
        return sb.append("]").toString();
    }
    protected void toString(StringBuilder sb) {
        sb.append("input=").append(input);
        sb.append(", output=").append(output);
        sb.append(", error=").append(error);
        sb.append(", extension=").append(extension);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SynchronousInventory other = (SynchronousInventory)o;
        if (input != null ? !input.equals(other.input) : other.input != null) return false;
        if (output != null ? !output.equals(other.output) : other.output != null) return false;
        if (error != null ? !error.equals(other.error) : other.error != null) return false;
        if (extension != null ? !extension.equals(other.extension) : other.extension != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (input != null ? input.hashCode() : 0);
        result = 31 * result + (output != null ? output.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }
    public InventoryKey getInput() {
        return input;
    }
    public void setInput(InventoryKey input) {
        this.input = input;
    }
    public Inventory getOutput() {
        return output;
    }
    public void setOutput(Inventory output) {
        this.output = output;
    }
    public Error getError() {
        return error;
    }
    public void setError(Error error) {
        this.error = error;
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
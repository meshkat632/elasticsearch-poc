package com.media-saturn.car.bdm.dst.upsertinventory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

public class Anydata 
{
    @JacksonXmlText
    @JacksonXmlCData
    private String text;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Anydata[");
        toString(sb);
        return sb.append("]").toString();
    }
    protected void toString(StringBuilder sb) {
        sb.append("text=").append(text);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anydata other = (Anydata)o;
        if (text != null ? !text.equals(other.text) : other.text != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
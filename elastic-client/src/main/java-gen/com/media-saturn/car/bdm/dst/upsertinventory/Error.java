package com.media-saturn.car.bdm.dst.upsertinventory;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import javax.validation.Valid;

public class Error 
{
    @NotNull
    private String messageCode;
    @NotNull
    private String message;
    @JacksonXmlProperty(localName="ErrorReport")
    @Valid
    private ErrorReport errorReport;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Error[");
        toString(sb);
        return sb.append("]").toString();
    }
    protected void toString(StringBuilder sb) {
        sb.append("messageCode=").append(messageCode);
        sb.append(", message=").append(message);
        sb.append(", errorReport=").append(errorReport);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error other = (Error)o;
        if (messageCode != null ? !messageCode.equals(other.messageCode) : other.messageCode != null) return false;
        if (message != null ? !message.equals(other.message) : other.message != null) return false;
        if (errorReport != null ? !errorReport.equals(other.errorReport) : other.errorReport != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (messageCode != null ? messageCode.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (errorReport != null ? errorReport.hashCode() : 0);
        return result;
    }
    public String getMessageCode() {
        return messageCode;
    }
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public ErrorReport getErrorReport() {
        return errorReport;
    }
    public void setErrorReport(ErrorReport errorReport) {
        this.errorReport = errorReport;
    }
}
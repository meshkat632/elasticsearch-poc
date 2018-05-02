package com.media-saturn.car.bdm.dst.upsertinventory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"schemaLocation"})
public class ErrorReport 
{
    @JacksonXmlProperty(localName="StackTrace")
    @NotNull
    private String stackTrace;
    @JacksonXmlProperty(localName="Msg")
    @NotNull
    private String msg;
    @JacksonXmlProperty(localName="FullClass")
    @NotNull
    private String fullClass;
    @JacksonXmlProperty(localName="Class")
    @NotNull
    private String class_;
    @JacksonXmlProperty(localName="ProcessStack")
    @NotNull
    private String processStack;
    @JacksonXmlProperty(localName="MsgCode")
    private String msgCode;
    @JacksonXmlProperty(localName="Data")
    @Valid
    private Anydata data;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ErrorReport[");
        toString(sb);
        return sb.append("]").toString();
    }
    protected void toString(StringBuilder sb) {
        sb.append("stackTrace=").append(stackTrace);
        sb.append(", msg=").append(msg);
        sb.append(", fullClass=").append(fullClass);
        sb.append(", class_=").append(class_);
        sb.append(", processStack=").append(processStack);
        sb.append(", msgCode=").append(msgCode);
        sb.append(", data=").append(data);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorReport other = (ErrorReport)o;
        if (stackTrace != null ? !stackTrace.equals(other.stackTrace) : other.stackTrace != null) return false;
        if (msg != null ? !msg.equals(other.msg) : other.msg != null) return false;
        if (fullClass != null ? !fullClass.equals(other.fullClass) : other.fullClass != null) return false;
        if (class_ != null ? !class_.equals(other.class_) : other.class_ != null) return false;
        if (processStack != null ? !processStack.equals(other.processStack) : other.processStack != null) return false;
        if (msgCode != null ? !msgCode.equals(other.msgCode) : other.msgCode != null) return false;
        if (data != null ? !data.equals(other.data) : other.data != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (stackTrace != null ? stackTrace.hashCode() : 0);
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + (fullClass != null ? fullClass.hashCode() : 0);
        result = 31 * result + (class_ != null ? class_.hashCode() : 0);
        result = 31 * result + (processStack != null ? processStack.hashCode() : 0);
        result = 31 * result + (msgCode != null ? msgCode.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
    public String getStackTrace() {
        return stackTrace;
    }
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getFullClass() {
        return fullClass;
    }
    public void setFullClass(String fullClass) {
        this.fullClass = fullClass;
    }
    public String getClass_() {
        return class_;
    }
    public void setClass_(String class_) {
        this.class_ = class_;
    }
    public String getProcessStack() {
        return processStack;
    }
    public void setProcessStack(String processStack) {
        this.processStack = processStack;
    }
    public String getMsgCode() {
        return msgCode;
    }
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
    public Anydata getData() {
        return data;
    }
    public void setData(Anydata data) {
        this.data = data;
    }
}
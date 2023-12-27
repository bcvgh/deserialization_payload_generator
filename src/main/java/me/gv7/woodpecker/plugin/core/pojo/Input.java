package me.gv7.woodpecker.plugin.core.pojo;

import me.gv7.woodpecker.plugin.utils.FileUtil;

import java.net.MalformedURLException;
import java.net.URL;

public class Input {
    private String Command;
    private String Gadget;
    private String Type;
    private String Zip;
    private String Encode;
    private String ResourcePath;
    private String Output;
    private String DnsLog;
    private String className;
    private String factory;
    private String factoryLocation;
    private byte[] bytecode;
    private String bcel;
    private String UrlClassLocation;
    private String Ip;
    private String Port;
    private String LDAP_or_RMI;


    public Input(String Gadget, String Type, String Command, String Zip, String Encode, String ResourcePath, String Output, String DnsLog, String UrlClassLocation ,String Ip,String Port,String LDAP_or_RMI) {
        this.Command = Command;
        this.Gadget = Gadget;
        this.Type = Type;
        this.Zip = Zip;
        this.Encode = Encode;
        this.ResourcePath = ResourcePath;
        this.Output = Output;
        this.DnsLog = DnsLog;
        this.UrlClassLocation = UrlClassLocation;
        this.Ip = Ip;
        this.Port = Port;
        this.LDAP_or_RMI = LDAP_or_RMI;

        if (this.UrlClassLocation!=null){
            URL url = null;
            try {
                url = new URL(UrlClassLocation);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            this.factory = url.getPath().substring(1).replace(".class","");
            this.className = (Character.toUpperCase(this.factory.charAt(0)) + this.factory.substring(1)).replace(".class","");
            this.factoryLocation = UrlClassLocation.replace(this.factory+".class","");
        }
        if (this.getResourcePath()!=null) {
            this.bytecode = FileUtil.FileRead(this.getResourcePath());
        }
    }

    public String getLDAP_or_RMI() {
        return LDAP_or_RMI;
    }

    public void setLDAP_or_RMI(String LDAP_or_RMI) {
        LDAP_or_RMI = LDAP_or_RMI;
    }

    public String getUrlClassLocation() {
        return UrlClassLocation;
    }

    public void setUrlClassLocation(String urlClassLocation) {
        UrlClassLocation = urlClassLocation;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getPort() {
        return Port;
    }

    public void setPort(String port) {
        Port = port;
    }

    public byte[] getBytecode() {
        return bytecode;
    }

    public void setBytecode(byte[] bytecode) {
        this.bytecode = bytecode;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String Command) {
        this.Command = Command;
    }


    public String getGadget() {
        return Gadget;
    }

    public void setGadget(String Gadget) {
        this.Gadget = Gadget;
    }

    public String getBcel() {
        return bcel;
    }

    public void setBcel(String bcel) {
        this.bcel = bcel;
    }


    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String Zip) {
        this.Zip = Zip;
    }

    public String getEncode() {
        return Encode;
    }

    public void setEncode(String Encode) {
        this.Encode = Encode;
    }

    public String getResourcePath() {
        return ResourcePath;
    }

    public void setResourcePath(String ResourcePath) {
        this.ResourcePath = ResourcePath;
    }

    public String getOutput() {
        return Output;
    }

    public void setOutput(String Output) {
        this.Output = Output;
    }

    public String getDnsLog() {
        return DnsLog;
    }

    public void setDnsLog(String DnsLog) {
        this.DnsLog = DnsLog;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getFactoryLocation() {
        return factoryLocation;
    }

    public void setFactoryLocation(String factoryLocation) {
        this.factoryLocation = factoryLocation;
    }
}

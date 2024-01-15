package me.gv7.woodpecker.plugin.core;

import me.gv7.woodpecker.plugin.core.pojo.Input;
import me.gv7.woodpecker.plugin.gadgets.ObjectPayload;
import me.gv7.woodpecker.plugin.utils.FileUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Payload {
//    private String bytecode;
    public Class<? extends ObjectPayload> gadgetClass;

    public Payload(Input input) {
        this.gadgetClass = ObjectPayload.getPayloadClass(input.getGadget(),input.getType());
    }



    public <T> Object GadgetPayloadGenerate(Input input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String className = input.getGadget();

//        switch (input.getType()){
//            case "exec":
////                input.setGadget(className+"Exec");
//                className = className+"Exec";
//            break;
//            case "bytecode":
////                input.setGadget(className+"Bytecode");
//                className = className+"Bytecode";
//            break;
//            case "bcel":
////                input.setGadget(className+"Bcel");
//                className = className+"Bcel";
//            break;
//            default:break;
//        }
//        this.gadgetClass = ObjectPayload.getPayloadClass(className,input.getType());
        Object object = ObjectPayload.getObjectPayload(className ,input);
        return object;
    }


    public Object PayloadGenerate(String methods ,String tag ,byte[] payloadObject){
        Object object =  payloadObject;
        Class clz = null;
        try {
            clz = Class.forName("me.gv7.woodpecker.plugin.utils."+tag+"Util");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method method = null;
        try {
            method = clz.getDeclaredMethod(methods,byte[].class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            object = method.invoke(null,object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }

//    public <T> T EncodePayloadGenerate(String encode){
//        byte[] bytes = (byte[]) this.payload;
//        Class clz = null;
//        try {
//            clz = Class.forName("me.gv7.woodpecker.plugin.utils.EncodeUtil");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Method method = null;
//        try {
//            method = clz.getDeclaredMethod(other,Byte[].class);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        try {
//            bytes =(byte[]) method.invoke(null,bytes);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        this.payload = bytes;
//        return (T) bytes;
//    }

    public <T> void PayloadOutPut(String outpath ,T content){
        FileUtil.FileWrite(outpath , content);
    }


}

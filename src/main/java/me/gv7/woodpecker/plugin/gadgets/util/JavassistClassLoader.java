package me.gv7.woodpecker.plugin.gadgets.util;

public class JavassistClassLoader extends ClassLoader {
    public JavassistClassLoader(){
        super(Thread.currentThread().getContextClassLoader());
    }
}

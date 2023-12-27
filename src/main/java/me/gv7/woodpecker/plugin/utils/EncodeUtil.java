package me.gv7.woodpecker.plugin.utils;

import java.util.Base64;

public class EncodeUtil {
    public static byte[] Base64(byte[] bytes){
        byte[] payload =  Base64.getEncoder().encode(bytes);
        return payload;
    }
}

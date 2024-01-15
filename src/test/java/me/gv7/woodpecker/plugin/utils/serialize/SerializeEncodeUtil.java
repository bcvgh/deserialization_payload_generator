package me.gv7.woodpecker.plugin.utils.serialize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class SerializeEncodeUtil {

    public static byte[] GZIPEncode(byte[] bytes){
        // 构造一个GZIP格式的字节数组，将字节数组存储在GZIP数据块中
        byte[] maliciousBytes = bytes;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = null;
        try {
            gzipOutputStream = new GZIPOutputStream(baos);
            gzipOutputStream.write(maliciousBytes);
            gzipOutputStream.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] gzipBytes = baos.toByteArray();
        return gzipBytes;
    }
}

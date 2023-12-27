package me.gv7.woodpecker.plugin.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    public static byte[] FileRead(String filePath){
         try {
            Path FilePath = Paths.get(filePath);
            byte[] fileBytes = Files.readAllBytes(FilePath);
            return fileBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
         return null;
    }

    public static <T> void FileWrite(String filePath,T content ){
        Path FilePath = Paths.get(filePath);
        try {
            if (content instanceof String){
                Files.write(FilePath,((String) content).getBytes());
            }
            if (content instanceof byte[]){
                Files.write(FilePath, (byte[]) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String filename = filePath.split(File.separator)[-1];
//        if (content instanceof String){
//            FileWriter fileWriter = null;
//            try {
//                fileWriter = new FileWriter(filePath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            try {
//                bufferedWriter.write((String) content);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                bufferedWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (content instanceof Byte[]){
//            FileOutputStream fos =null;
//            try {
//                fos = new FileOutputStream(filePath);
//                fos.write((byte[]) content);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }

//        try {
//            FileWriter fileWriter = new FileWriter(filePath);
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            bufferedWriter.write(content);
//            bufferedWriter.close();
//        } catch (Exception e) {
//            return false;
//        }

//        return true;


}

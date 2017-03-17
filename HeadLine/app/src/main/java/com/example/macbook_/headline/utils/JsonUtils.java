package com.example.macbook_.headline.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by MacBook- on 2017/3/13.
 */
public class JsonUtils {
    public static String jsonToString(InputStream inputStream){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        byte b[]=new byte[1024];
        int len;
        try {
            while ((len=inputStream.read(b))!=-1){
             bos.write(b,0,len);
            }
            inputStream.close();
            bos.close();
            return bos.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

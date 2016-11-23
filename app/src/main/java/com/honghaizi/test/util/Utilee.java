package com.honghaizi.test.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by asus on 2016/11/22.
 */
public class Utilee {
    public static String stream2String(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length = 0 ;
        byte[] buffer = new byte[2048];
        while ((length = in.read(buffer))!=-1) {
            baos.write(buffer, 0, length);
        }
        return baos.toString();
    }

}

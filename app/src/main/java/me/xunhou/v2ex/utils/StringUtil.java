package me.xunhou.v2ex.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ihgoo on 2015/5/20.
 */
public class StringUtil {

    /**
     * @param in
     * @return
     * @throws IOException
     */
    public static String  inputStream2String(InputStream in) {
        String string = "";
        try {
            byte[] buf = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (int i; (i = in.read(buf)) != -1; ) {
                baos.write(buf, 0, i);
            }
            string = baos.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }
}

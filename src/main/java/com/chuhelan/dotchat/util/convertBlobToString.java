package com.chuhelan.dotchat.util;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * @author chuhelan
 * @version 1.0
 * @date 2022/5/10 19:28
 */
public class convertBlobToString {
    public String convertBlobToString(Blob blob) {
        String result = "";
        try {
            ByteArrayInputStream msgContent = (ByteArrayInputStream) blob.getBinaryStream();
            byte[] byte_data = new byte[msgContent.available()];
            msgContent.read(byte_data, 0, byte_data.length);
            result = new String(byte_data);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}

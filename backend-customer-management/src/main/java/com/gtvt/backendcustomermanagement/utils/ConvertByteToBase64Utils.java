package com.gtvt.backendcustomermanagement.utils;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class ConvertByteToBase64Utils {


    public static String convertBase64(InputStream object) throws IOException {


            byte[] bytes = IOUtils.toByteArray(object);
            String encode = Base64.getEncoder().encodeToString(bytes);
            return encode;

    }
}

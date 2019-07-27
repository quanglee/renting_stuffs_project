package com.quangle.rentingutilities.utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MulitPartFormHelper {

    public static String MULTIPART_FORM_DATA = "text/plain";

    public static RequestBody createRequestBody(Object data) {
       return RequestBody.create(MediaType.parse(MULTIPART_FORM_DATA), data.toString());
    }

}

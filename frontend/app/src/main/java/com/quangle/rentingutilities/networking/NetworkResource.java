package com.quangle.rentingutilities.networking;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.ResponseBody;

public class NetworkResource<T> {
    public T data = null;
    public int code = 0;
    public String errorMessage;

    public NetworkResource(T data) {
        this.data = data;
    }

    public NetworkResource(int code) {
        this.code = code;
    }

    // used to get error response
    public NetworkResource(T data, int code, ResponseBody responseBody) {
        this.data = data;
        this.code = code;

        // parse JSON error message from backend
        JsonParser parser = new JsonParser();
        JsonElement mJson = null;
        try {
            mJson = parser.parse(responseBody.string());
            JsonElement element= mJson.getAsJsonObject();
            String errorMessage= ((JsonObject) element).get("message").toString();
            this.errorMessage = errorMessage;
        } catch (IOException ex) {}
    }
}

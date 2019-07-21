package com.quangle.rentingutilities.networking;

public class NetworkResource<T> {
    public T data = null;
    public int code = 0;

    public NetworkResource(T data) {
        this.data = data;
    }

    public NetworkResource(int code) {
        this.code = code;
    }
}

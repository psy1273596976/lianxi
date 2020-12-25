package com.http.myshop.interfaces;

public interface CallBack<T> {

    void success(T data);

    void fail(String err);

}

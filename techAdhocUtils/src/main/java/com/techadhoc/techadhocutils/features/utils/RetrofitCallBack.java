package com.techadhoc.techadhocutils.features.utils;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitCallBack<T> implements Callback<T> {
    private static final String TAG = LogUtil.makeLogTag(RetrofitCallBack.class);
    private final Callback<T> mCallback;

    public RetrofitCallBack(Context context, Callback<T> callback) {
        this.mCallback = callback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        mCallback.onResponse(call, response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        mCallback.onFailure(call, t);
    }
}
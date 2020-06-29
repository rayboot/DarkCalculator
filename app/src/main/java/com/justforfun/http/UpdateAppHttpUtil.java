package com.justforfun.http;

import androidx.annotation.NonNull;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class UpdateAppHttpUtil {

    public interface Callback {
        /**
         * 结果回调
         *
         * @param result 结果
         */
        void onResponse(String result);

        /**
         * 错误回调
         *
         * @param error 错误提示
         */
        void onError(String error);
    }

    /**
     * 异步get
     *
     * @param url      get请求地址
     * @param callBack 回调
     */
    public void asyncGet(@NonNull String url, @NonNull final Callback callBack) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e, int id) {
                        callBack.onError(validateError(e, response));
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callBack.onResponse(response);
                    }
                });
    }

}
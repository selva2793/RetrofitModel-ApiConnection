package com.selvaandroid.retrofitmodel.Remote.Managers;

import com.selvaandroid.retrofitmodel.Remote.Datamodel.Token;
import com.selvaandroid.retrofitmodel.Remote.VariableAPi;
import com.google.gson.JsonObject;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import rx.Observable;

/**
 * Created by ${Selva} on 09/08/19.
 */


public class DataManager {

    //    VariableApi variableApi;
    ApiManager apiManager;
    RetrofitManager retrofitManager;

    public DataManager(){
        retrofitManager = new RetrofitManager(okHttpClient());
        apiManager = new ApiManager(retrofitManager.getApiService().create(VariableAPi.class));
    }


    public Observable<Token> getResponseToken(JsonObject object) {
        return  apiManager.getResponseToken(object);
    }

    public Observable<Token> sendTokens(String  tok) {
        return  apiManager.sendTokens(tok);
    }

    public Observable<Object> getUpdateVersion() {
        return apiManager.getUpdateVersion();
    }


    OkHttpClient okHttpClient() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//        logging.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder() .cookieJar(new JavaNetCookieJar(cookieManager)).retryOnConnectionFailure(true).connectTimeout(100000, TimeUnit.MILLISECONDS) .writeTimeout(10, TimeUnit.SECONDS).readTimeout(25000, TimeUnit.MILLISECONDS).addInterceptor(logging).build();
//        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().retryOnConnectionFailure(true).connectTimeout(20000, TimeUnit.MILLISECONDS) .writeTimeout(10, TimeUnit.SECONDS).readTimeout(25000, TimeUnit.MILLISECONDS).addInterceptor(logging).build();

        return okHttpClient;
    }
}

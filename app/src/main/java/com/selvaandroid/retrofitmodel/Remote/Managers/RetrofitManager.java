package com.selvaandroid.retrofitmodel.Remote.Managers;

import com.selvaandroid.retrofitmodel.Remote.ApiConstants;
import com.selvaandroid.retrofitmodel.Remote.Datamodel.Token;
import com.selvaandroid.retrofitmodel.Remote.Deserializers.TokenDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${Selva} on 17/06/19.
 */


public class RetrofitManager {

    private OkHttpClient okHttpClient;
    private Retrofit apiService;
//    private Retrofit apiService1;

    public RetrofitManager(OkHttpClient okHttpClient){
        this.okHttpClient = okHttpClient;
        this.apiService = createAPIService();
//        this.apiService1 = createAPIService1();
    }

    private Retrofit createAPIService(){
        Gson gson = new GsonBuilder()

                .registerTypeAdapter(Token.class, new TokenDeserializer())
//                .registerTypeAdapter(CategorywiseData.class, new CategorywiseDataDeserializer())
                .create();

        return (new Retrofit.Builder()).baseUrl(ApiConstants.BASE_ENDPOINT_Local).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public Retrofit getApiService(){
        return this.apiService;
    }
}

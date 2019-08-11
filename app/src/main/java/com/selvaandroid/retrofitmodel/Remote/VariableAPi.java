package com.selvaandroid.retrofitmodel.Remote;

import com.selvaandroid.retrofitmodel.Remote.Datamodel.Token;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface VariableAPi {

    @FormUrlEncoded
    @POST("api/tokenlog")  //Login
    Observable<Token> getResponseToken(@Field ("sending") JsonObject object);

    @FormUrlEncoded
    @POST("api/post")  //Login
    Observable<Token> sendTokens(@Field ("Bearer") String tok);

    @GET("qt_api/getVersion")
    Observable<Object> getUpdateVersion();
}

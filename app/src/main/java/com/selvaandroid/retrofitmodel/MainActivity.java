package com.selvaandroid.retrofitmodel;

import android.os.Bundle;
import android.util.Log;

import com.selvaandroid.retrofitmodel.Remote.Datamodel.Token;
import com.selvaandroid.retrofitmodel.Remote.Managers.DataManager;
import com.selvaandroid.retrofitmodel.Remote.RxJava.RxTransformerUtil;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by ${Selva} on 09/08/19.
 */

public class MainActivity extends AppCompatActivity {

    DataManager dataManager;
    CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dataManager = new DataManager();

        JsonObject loginJson = new JsonObject();
        loginJson.addProperty("UserName", "xxxx");
        loginJson.addProperty("Password", "yyyy");

        ApiConnect();
    }


    private void ApiConnect() {
        /*   progressDialog.show(MainActivity.this, "Loading...", "Please wait...", true);*/
        Subscription subscription = dataManager.getUpdateVersion().compose(RxTransformerUtil.androidComputation())
                .subscribe(this::handleRetrofitManagerResponse, this::handleRetrofitManagerError);
        compositeSubscription.add(subscription);
    }


    private void handleRetrofitManagerResponse(Object o) {
        Log.d("fmfkfjkb", "handleLoginResponse: "+o);
//        SendToken(token.getTokens());
    }

    private void handleRetrofitManagerError(Throwable throwable) {
        Log.d("dhhfdfhj", "handleLoginError: "+throwable.getMessage());
//        Toast.makeText(this, "Server under maintenance Please try after sometime" , Toast.LENGTH_SHORT).show();

    }

    private void SendToken(String object) {
        /*   progressDialog.show(MainActivity.this, "Loading...", "Please wait...", true);*/
        Subscription subscription = dataManager.sendTokens(object).compose(RxTransformerUtil.androidComputation())
                .subscribe(this::handleRetrofitSendTokenResponse, this::handleRetrofitSendTokenError);
        compositeSubscription.add(subscription);
    }

    private void handleRetrofitSendTokenResponse(Token token) {
        Log.d("efefvxzx", "handleLoginResponse: "+token.getTokens());
    }

    private void handleRetrofitSendTokenError(Throwable throwable) {
        Log.d("dhhfdfhj", "handleLoginError: "+throwable.getMessage());
//        Toast.makeText(this, "Server under maintenance Please try after sometime" , Toast.LENGTH_SHORT).show();
    }

}
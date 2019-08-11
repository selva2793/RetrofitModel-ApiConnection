package com.selvaandroid.retrofitmodel.Remote.Managers;

import com.selvaandroid.retrofitmodel.Remote.Datamodel.Token;
import com.selvaandroid.retrofitmodel.Remote.VariableAPi;
import com.google.gson.JsonObject;

import java.util.List;

import rx.Observable;

/**
 * Created by ${Selva} on 09/08/19.
 */

public class ApiManager {

    VariableAPi api;
//
    public ApiManager(VariableAPi api){
        this.api = api;
    }

    public Observable<Token> getResponseToken(JsonObject object) {
        return  api.getResponseToken(object);
    }

    public Observable<Token> sendTokens(String  tok) {
        return  api.sendTokens(tok);
    }

    public Observable<Object> getUpdateVersion() {
        return api.getUpdateVersion();
    }
//
//    public Observable<List<LoginModel>> getLoginresponse() {
//        return api.getLoginresponse();
//    }
//    public Observable<List<DealerListModel>> getDealerList(){
//        return api.getDealerList();
//    }

//    public Observable<List<QuestionBankModel>> getQuestionBank() {
//        return api.getQuestionBank();
//    }
}
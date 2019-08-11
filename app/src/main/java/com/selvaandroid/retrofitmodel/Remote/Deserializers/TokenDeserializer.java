package com.selvaandroid.retrofitmodel.Remote.Deserializers;

import com.selvaandroid.retrofitmodel.Remote.Datamodel.Token;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class TokenDeserializer implements JsonDeserializer<Token> {

    @Override
    public Token deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject=json.getAsJsonObject();
        Token token=new Token();


        if (jsonObject.get("token") != null) {
            if(!jsonObject.get("token").isJsonNull()) {
                token.setTokens(jsonObject.get("token").getAsString());
            }else {
                token.setTokens("");
            }
        }
        else {
            token.setTokens("");
        }

        return token;
    }
}


package com.rgabs.omise;
import android.util.Log;

import co.omise.android.Client;
import co.omise.android.TokenRequestListener;
import co.omise.android.TokenRequest;
import co.omise.android.models.Token;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import org.json.JSONObject;

/**
 * Created by rahulgaba on 10/8/17.
 */


public class Omise extends ReactContextBaseJavaModule {

    public Omise(ReactApplicationContext reactContext) {
        super(reactContext);
    }
    @Override
    public String getName() {
        return "OmiseModule";
    }
    @ReactMethod
    public void getToken(String pubKey, ReadableMap creditCard, final Promise promise) {
        TokenRequest request = new TokenRequest();
        try {
//            Client client = new Client("pkey_test_59hv29c33aajtz65mgo");
            Client client = new Client(pubKey);
            request.number = String.valueOf(creditCard.getString("number"));
            request.name = String.valueOf(creditCard.getString("name"));
            request.expirationMonth = Integer.parseInt(creditCard.getString("expirationMonth"));
            request.expirationYear = Integer.parseInt(creditCard.getString("expirationYear"));
            request.securityCode = String.valueOf(creditCard.getString("securityCode"));
            client.send(request, new TokenRequestListener() {
                @Override
                public void onTokenRequestSucceed(TokenRequest request, Token token) {
                    promise.resolve(token.id);
                    // you've got Token!
                }

                @Override
                public void onTokenRequestFailed(TokenRequest request, Throwable throwable) {
                    promise.reject(throwable);
                    // something bad happened
                }
            });
        }
        catch (Exception e) {
            promise.reject(e);
        }
    }
}

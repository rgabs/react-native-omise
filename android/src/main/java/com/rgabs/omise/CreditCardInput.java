package com.rgabs.omise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import co.omise.android.models.Token;

public class CreditCardInput extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, CreditCardActivity.class);
        intent.putExtra(CreditCardActivity.EXTRA_PKEY, Omise.PUB_KEY);
        startActivityForResult(intent, REQUEST_CC);
        Log.d("NOTETAKER","YO YO YO22");

    }

    private static final int REQUEST_CC = 100;

    @Override
    public void onBackPressed() {
        Log.d("NOTETAKER","YO YO YO");
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CC: {
                if (resultCode == CreditCardActivity.RESULT_CANCEL) {
                    return;
                }
                Token token = data.getParcelableExtra(CreditCardActivity.EXTRA_TOKEN_OBJECT);
//                data.getStringExtra(CreditCardActivity.EXTRA_TOKEN) - The string ID of the token. Use this if you only needs the ID and not the card data.
//                data.getParcelableExtra(CreditCardActivity.EXTRA_TOKEN_OBJECT) - The full Token object returned from the Omise API.
//                data.getParcelableExtra(CreditCardActivity.EXTRA_CARD_OBJECT) - The Card object which is part of the Token object returned from the Omise API.
                Omise.promiseInst.resolve(token.id);
                break;
                // process your token here.
            }

            default: {
                super.onActivityResult(requestCode, resultCode, data);
                Omise.promiseInst.reject(String.valueOf(resultCode));
            }
        }
        finish();
    }
}

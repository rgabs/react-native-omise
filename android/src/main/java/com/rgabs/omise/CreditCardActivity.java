package com.rgabs.omise;

/**
 * Created by rahulgaba on 10/9/17.
 */

public class CreditCardActivity extends co.omise.android.ui.CreditCardActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.finish();
        finish();
    }
}

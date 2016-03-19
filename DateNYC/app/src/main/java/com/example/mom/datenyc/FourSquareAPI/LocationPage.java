package com.example.mom.datenyc.FourSquareAPI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mom.datenyc.R;
import com.foursquare.android.nativeoauth.FoursquareCancelException;
import com.foursquare.android.nativeoauth.FoursquareDenyException;
import com.foursquare.android.nativeoauth.FoursquareInvalidRequestException;
import com.foursquare.android.nativeoauth.FoursquareOAuth;
import com.foursquare.android.nativeoauth.FoursquareOAuthException;
import com.foursquare.android.nativeoauth.FoursquareUnsupportedVersionException;
import com.foursquare.android.nativeoauth.model.AccessTokenResponse;
import com.foursquare.android.nativeoauth.model.AuthCodeResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationPage extends AppCompatActivity {

    FloatingActionButton mBx;


    private static final int REQUEST_CODE_FSQ_CONNECT = 200;
    private static final int REQUEST_CODE_FSQ_TOKEN_EXCHANGE = 201;
    String CLIENT_ID= "I304IKBD3PKC0HMR0QAHODBI3KRWGHCYHO4G0LJOU5HROYL2";
    String CLIENT_SECRET= "A2YTKVC5HOCJ4NPXWAP5PZML2N1AEPDQX5SLG53MVLMV4HNP";

    FloatingActionButton mBk,mQu, mMa, mSi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView backgroundTwo= (ImageView)findViewById(R.id.backgroundTwo);

        Picasso.with(LocationPage.this).load("https://s-media-cache-ak0.pinimg.com/564x/b6/f1/34/b6f1340783278be2c97535f2674f6f49.jpg").fit().into(backgroundTwo);

        mBk= (FloatingActionButton)findViewById(R.id.brooklyn);
        mBk.setImageResource(R.drawable.bk);

        mBx = (FloatingActionButton) findViewById(R.id.fab);
        mBx.setImageResource(R.drawable.bx);

        mQu=(FloatingActionButton)findViewById(R.id.queens);
        mQu.setImageResource(R.drawable.qu);

        mMa=(FloatingActionButton)findViewById(R.id.manhattan);
        mMa.setImageResource(R.drawable.ma);

        mSi=(FloatingActionButton)findViewById(R.id.staten);
        mSi.setImageResource(R.drawable.si);



        Intent intent = FoursquareOAuth.getConnectIntent(getApplicationContext(), CLIENT_ID);
        startActivityForResult(intent, REQUEST_CODE_FSQ_CONNECT);
    }

    public static final String BASE_URL = "http://api.myservice.com/";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_FSQ_TOKEN_EXCHANGE:
                AccessTokenResponse tokenResponse = FoursquareOAuth.getTokenFromResult(resultCode, data);
            /* ... */
                break;
        }
    }

    private void ensureUi() {
        boolean isAuthorized = !TextUtils.isEmpty(ExampleTokenStore.get().getToken());

        TextView tvMessage = (TextView) findViewById(R.id.tvMessage);
        tvMessage.setVisibility(isAuthorized ? View.VISIBLE : View.GONE);


        mBx.setVisibility(isAuthorized ? View.GONE : View.VISIBLE);
        mBx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the native auth flow.
                Intent intent = FoursquareOAuth.getConnectIntent(LocationPage.this, CLIENT_ID);

                // If the device does not have the Foursquare app installed, we'd
                // get an intent back that would open the Play Store for download.
                // Otherwise we start the auth flow.
                if (FoursquareOAuth.isPlayStoreIntent(intent)) {
                    Toast.makeText(LocationPage.this, "Doesn't Work", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    startActivityForResult(intent, REQUEST_CODE_FSQ_CONNECT);
                }
            }
        });
    }

    private void onCompleteConnect(int resultCode, Intent data) {
        AuthCodeResponse codeResponse = FoursquareOAuth.getAuthCodeFromResult(resultCode, data);
        Exception exception = codeResponse.getException();

        if (exception == null) {
            // Success.
            String code = codeResponse.getCode();
            performTokenExchange(code);

        } else {
            if (exception instanceof FoursquareCancelException) {
                // Cancel.
                Toast.makeText(LocationPage.this, "Canceled", Toast.LENGTH_SHORT).show();

            } else if (exception instanceof FoursquareDenyException) {
                // Deny.
                Toast.makeText(LocationPage.this, "Denied", Toast.LENGTH_SHORT).show();

            } else if (exception instanceof FoursquareOAuthException) {
                // OAuth error.
                String errorMessage = exception.getMessage();
                String errorCode = ((FoursquareOAuthException) exception).getErrorCode();
                Toast.makeText(LocationPage.this, "errorMessage + \" [\" + errorCode + \"]\"", Toast.LENGTH_SHORT).show();

            } else if (exception instanceof FoursquareUnsupportedVersionException) {
                // Unsupported Fourquare app version on the device.
                toastError(this, exception);

            } else if (exception instanceof FoursquareInvalidRequestException) {
                // Invalid request.
                toastError(this, exception);

            } else {
                // Error.
                toastError(this, exception);
            }
        }
    }

    private void onCompleteTokenExchange(int resultCode, Intent data) {
        AccessTokenResponse tokenResponse = FoursquareOAuth.getTokenFromResult(resultCode, data);
        Exception exception = tokenResponse.getException();

        if (exception == null) {
            String accessToken = tokenResponse.getAccessToken();
            // Success.
            toastMessage(this, "Access token: " + accessToken);

            // Persist the token for later use. In this example, we save
            // it to shared prefs.
            ExampleTokenStore.get().setToken(accessToken);

            // Refresh UI.
            ensureUi();

        } else {
            if (exception instanceof FoursquareOAuthException) {
                // OAuth error.
                String errorMessage = ((FoursquareOAuthException) exception).getMessage();
                String errorCode = ((FoursquareOAuthException) exception).getErrorCode();
                toastMessage(this, errorMessage + " [" + errorCode + "]");

            } else {
                // Other exception type.
                toastError(this, exception);
            }
        }
    }
    private void performTokenExchange(String code) {
        Intent intent = FoursquareOAuth.getTokenExchangeIntent(this, CLIENT_ID, CLIENT_SECRET, code);
        startActivityForResult(intent, REQUEST_CODE_FSQ_TOKEN_EXCHANGE);
    }

    public static void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toastError(Context context, Throwable t) {
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}


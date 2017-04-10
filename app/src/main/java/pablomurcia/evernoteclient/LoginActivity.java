package pablomurcia.evernoteclient;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.evernote.client.android.EvernoteSession;

public class LoginActivity extends AppCompatActivity {

    private static final int SUCCESS_REQUEST_CODE_LOGIN = 66394;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new EvernoteSession.Builder(this)
                .setEvernoteService(EvernoteSession.EvernoteService.SANDBOX)
                .setSupportAppLinkedNotebooks(true)
                .build(BuildConfig.EVERNOTE_CONSUMER_KEY, BuildConfig.EVERNOTE_CONSUMER_SECRET)
                .asSingleton();

        EvernoteSession.getInstance().authenticate(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(getString(R.string.LOG) + "isLoggedIn()=", String.valueOf(EvernoteSession.getInstance().isLoggedIn()));
        Log.e(getString(R.string.LOG) + "onActivityResult", "requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (!EvernoteSession.getInstance().isLoggedIn()) {
            return;
        }

        switch (requestCode) {
            case SUCCESS_REQUEST_CODE_LOGIN:
                if (resultCode == Activity.RESULT_OK) {
                    Log.e(getString(R.string.LOG), "LOGIN OK");
                    //new activity
                } else {
                    Snackbar.make(null, "El proceso de login falló", Snackbar.LENGTH_LONG).show();
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}

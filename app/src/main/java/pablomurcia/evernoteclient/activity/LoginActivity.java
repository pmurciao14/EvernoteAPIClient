package pablomurcia.evernoteclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.login.EvernoteLoginFragment;

import pablomurcia.evernoteclient.R;

public class LoginActivity extends AppCompatActivity implements EvernoteLoginFragment.ResultCallback{

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EvernoteSession.getInstance().authenticate(LoginActivity.this);
                view.setEnabled(false);
            }
        });

        EvernoteSession.getInstance().authenticate(this);
    }

    @Override
    public void onLoginFinished(boolean successful) {
        if (successful){
            Log.e(getString(R.string.LOG), "LOGIN OK");
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        } else {
            Log.e(getString(R.string.LOG), "LOGIN FAIL");
            Snackbar.make(null, getString(R.string.snackbar_login_fail), Snackbar.LENGTH_LONG).show();
            loginButton.setEnabled(true);
        }
        Log.e(getString(R.string.LOG) + "isLoggedIn()=", String.valueOf(EvernoteSession.getInstance().isLoggedIn()));
    }
}

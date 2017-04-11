package pablomurcia.evernoteclient;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.evernote.client.android.EvernoteOAuthActivity;
import com.evernote.client.android.EvernoteSession;
import com.evernote.client.android.login.EvernoteLoginActivity;

import java.util.Arrays;
import java.util.List;

import pablomurcia.evernoteclient.activity.LoginActivity;
import pablomurcia.evernoteclient.activity.MainActivity;

/**
 * Created by ifbpmurcia.externos on 11/04/2017.
 */

public class LoginManager implements Application.ActivityLifecycleCallbacks {
    private static final List<Class<? extends Activity>> IGNORED_ACTIVITIES = Arrays.asList(LoginActivity.class, EvernoteLoginActivity.class, EvernoteOAuthActivity.class);

    private Intent intentCache;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (!EvernoteSession.getInstance().isLoggedIn() && !IGNORED_ACTIVITIES.contains(activity.getClass())) {
            intentCache = activity.getIntent();
            LoginActivity.launch(activity);

            activity.finish();
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (activity instanceof LoginActivity && EvernoteSession.getInstance().isLoggedIn()) {
            if (intentCache != null) {
                activity.startActivity(intentCache);
                intentCache = null;
            } else {
                activity.startActivity(new Intent(activity, MainActivity.class));
            }
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}

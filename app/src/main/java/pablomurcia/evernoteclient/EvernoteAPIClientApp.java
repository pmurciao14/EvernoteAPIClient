package pablomurcia.evernoteclient;

import android.app.Application;

import com.evernote.client.android.EvernoteSession;

/**
 * Created by ifbpmurcia.externos on 11/04/2017.
 */

public class EvernoteAPIClientApp extends Application {
    private static final String CONSUMER_KEY = BuildConfig.EVERNOTE_CONSUMER_KEY;
    private static final String CONSUMER_SECRET = BuildConfig.EVERNOTE_CONSUMER_SECRET;
    private static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;
    private static final boolean SUPPORT_APP_LINKED_NOTEBOOKS = true;

    @Override
    public void onCreate() {
        super.onCreate();

        new EvernoteSession.Builder(this)
                .setEvernoteService(EVERNOTE_SERVICE)
                .setSupportAppLinkedNotebooks(SUPPORT_APP_LINKED_NOTEBOOKS)
                .setForceAuthenticationInThirdPartyApp(true)
                .build(CONSUMER_KEY, CONSUMER_SECRET)
                .asSingleton();

        registerActivityLifecycleCallbacks(new LoginManager());
    }
}

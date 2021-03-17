package delivery.innovation.techology.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import delivery.innovation.techology.R;
import delivery.innovation.techology.helper.Constant;
import delivery.innovation.techology.helper.Session;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_splash);

        final Session session = new Session (getApplicationContext ());

        int SPLASH_TIME_OUT = 2000;

        new Handler ().postDelayed (new Runnable () {

            @Override
            public void run ( ) {
                if (session.isUserLoggedIn ()) {
                    session.setData (Constant.OFFSET,"" + 0);
                    startActivity (new Intent (SplashActivity.this,MainActivity.class).addFlags (Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags (Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
                    startActivity (new Intent (SplashActivity.this,LoginActivity.class).addFlags (Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags (Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            }
        },SPLASH_TIME_OUT);
    }
}
package in.tvac.akshayejh.fingerprintauthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
    }

    public void openSecondScreen(View view) {

        startActivity(new Intent(SplashScreen.this,PasswordAuth.class));
        finish();

    }
}

package in.tvac.akshayejh.fingerprintauthapp;

import android.app.KeyguardManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PasswordAuth extends AppCompatActivity {

    private static int CODE_AUTHENTICATION_VERIFICATION=241;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_auth);


        KeyguardManager km = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
        if(km.isKeyguardSecure()) {



            Intent i = km.createConfirmDeviceCredentialIntent("Routes App Authentication", "Confirm Fingerprint or Password to continue");
            startActivityForResult(i, CODE_AUTHENTICATION_VERIFICATION);




        }
        else
            Toast.makeText(this, "No any security setup done by user(pattern or password or pin or fingerprint", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==CODE_AUTHENTICATION_VERIFICATION)
        {
            Toast.makeText(this, "Success: Verified user's identity", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Failure: Unable to verify user's identity", Toast.LENGTH_SHORT).show();
        }
    }
}
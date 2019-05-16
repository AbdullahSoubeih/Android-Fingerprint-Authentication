package in.tvac.akshayejh.fingerprintauthapp;

import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordAuth extends AppCompatActivity {

    private static int CODE_AUTHENTICATION_VERIFICATION=241;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_auth);


        KeyguardManager km = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
        if(!km.isKeyguardSecure()) {

            //Toast.makeText(this, "No any security setup done by user(pattern or password or pin or fingerprint", Toast.LENGTH_SHORT).show();

            buildDialog(PasswordAuth.this).show();



        }
        else {
            Intent i = km.createConfirmDeviceCredentialIntent("Routes App Authentication", "Confirm Fingerprint or Password to continue");
            startActivityForResult(i, CODE_AUTHENTICATION_VERIFICATION);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==CODE_AUTHENTICATION_VERIFICATION)
        {
            Toast.makeText(this, "Success: Verified user's identity", Toast.LENGTH_SHORT).show();


            //open app here...
        }
        else
        {
            Toast.makeText(this, "Failure: Unable to verify user's identity", Toast.LENGTH_SHORT).show();
        }
    }


    //if no Internet Connected,then App show a Message to User [ No Internet Connection ]
    public AlertDialog.Builder buildDialog(Context c) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogDanger));
        builder.setCancelable(false);
        View view = LayoutInflater.from(PasswordAuth.this).inflate(R.layout.custom_no_fingerprint_layout, null);
        TextView messageTitle = (TextView) view.findViewById(R.id.titlemessage);
        TextView messageBody = (TextView) view.findViewById(R.id.bodymessage);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);
        messageTitle.setText("رسالة خطأ");
        messageBody.setText("لا يوجد إتصال بالإنترنت !");
        imageButton.setImageResource(R.drawable.ic_fingerprint_black_24dp);

        builder.setInverseBackgroundForced(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivityForResult(new Intent(Settings.ACTION_SECURITY_SETTINGS), 0);
            }
        });
        builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               // finish();
                dialogInterface.dismiss();
            }
        });

        builder.setView(view);

        return builder;
    }


}
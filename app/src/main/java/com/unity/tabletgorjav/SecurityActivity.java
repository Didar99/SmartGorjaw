package com.unity.tabletgorjav;

import androidx.annotation.NonNull;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import static com.unity.tabletgorjav.Commands.fingerPrintAfterLock;
import static com.unity.tabletgorjav.Commands.fingerPrintLock;
import static com.unity.tabletgorjav.Commands.fingerPrintUnLock;

public class SecurityActivity extends Activity {
    MaterialButton unlock_door, police, fire, panic, usual, unusual;
    int state_security, state_no_security;
    Intent mIntent, mIntent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        unlock_door = findViewById(R.id.btn_unlock);
        police = findViewById(R.id.btn_police);
        fire =findViewById(R.id.btn_fire);
        panic = findViewById(R.id.btn_panik);
        usual = findViewById(R.id.btn_usual);
        unusual = findViewById(R.id.btn_unusual);

        // load data from PrefConfig
        state_security = PrefConfig.loadSecurity(this);
        state_no_security = PrefConfig.loadNoSecurity(this);

        System.out.println("state security" + state_security);
        System.out.println("state no security" + state_no_security);

        if (state_security == 0) {
            unusual_off();
        } else if (state_security == 1){
            unusual_on();
        }

        // Give Personal PERMISSION for Call Phone
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        }

        // Only one onClick Listener for all Material buttons
        @SuppressLint("NonConstantResourceId") View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.btn_unlock:
                    fingerPrintAfterLock(getApplicationContext());
                    break;
                case R.id.btn_police:
                    String number = ("tel:002");
                    mIntent = new Intent(Intent.ACTION_CALL);
                    mIntent.setData(Uri.parse(number));
                    //You already have permission
                    try {
                        startActivity(mIntent);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btn_fire:

                    String number2 = ("tel:003");
                    mIntent2 = new Intent(Intent.ACTION_CALL);
                    mIntent2.setData(Uri.parse(number2));
                    //You already have permission
                    try {
                        startActivity(mIntent2);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btn_panik:

                    break;
                case R.id.btn_usual:
                    if (state_security == 0) {
                        unusual_off();
                        state_security = 1;
                    } else {
                        unusual_on();
                        state_security = 0;
                    }
                    break;
                case R.id.btn_unusual:
                    if (state_security == 0) {
                        unusual_on();
                        state_security = 1;
                    } else {
                        unusual_off();
                        state_security = 0;
                    }
                    break;
            }
        };
        unlock_door.setOnClickListener(onClickListener);
        police.setOnClickListener(onClickListener);
        fire.setOnClickListener(onClickListener);
        panic.setOnClickListener(onClickListener);
        usual.setOnClickListener(onClickListener);
        unusual.setOnClickListener(onClickListener);
    }

    private void unusual_on(){
        unusual.setTextColor(getResources().getColor(R.color.white));
//        unusual.setBackgroundTintList(getColorStateList(R.color.green));
        unusual.setBackgroundColor(getResources().getColor(R.color.green));
        PrefConfig.saveSecurity(getApplicationContext(), 1);

        usual.setTextColor(getResources().getColor(R.color.bold_text));
//        usual.setBackgroundTintList(getColorStateList(R.color.white));
        usual.setBackgroundColor(getResources().getColor(R.color.green));
        PrefConfig.saveNoSecurity(getApplicationContext(), 0);

        fingerPrintLock(getApplicationContext());
        System.out.println("goragly => 1 adaty => 0");

    }
    private void unusual_off(){
        unusual.setTextColor(getResources().getColor(R.color.bold_text));
//        unusual.setBackgroundTintList(getColorStateList(R.color.white));
        unusual.setBackgroundColor(getResources().getColor(R.color.white));
        PrefConfig.saveSecurity(getApplicationContext(), 0);

        usual.setTextColor(getResources().getColor(R.color.white));
//        usual.setBackgroundTintList(getColorStateList(R.color.light_blue_600));
        usual.setBackgroundColor(getResources().getColor(R.color.light_blue_600));
        PrefConfig.saveNoSecurity(getApplicationContext(), 1);

        fingerPrintUnLock(getApplicationContext());
        System.out.println("goragly => 0 adaty => 1");

    }
    // Check if PERMISSION is GRANTED or NOT GRANTED
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.granted, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.notGranted, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}

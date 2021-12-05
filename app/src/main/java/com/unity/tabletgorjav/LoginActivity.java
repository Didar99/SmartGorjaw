package com.unity.tabletgorjav;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LoginActivity extends Activity {
    String log_data;
    int change;
    String ActCode = "kjn098hjnfd92h3fd2iojhnbc79h23jfc24uf024fhn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // status which know (user registered or not)
        change = PrefConfig.loadChange(this);
        // Start to scanning QR Code
        IntentIntegrator intentIntegrator = new IntentIntegrator(LoginActivity.this);
        intentIntegrator.setPrompt(String.valueOf(R.string.flashLed));
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setCaptureActivity(Capture.class);
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle(R.string.result);
            builder.setMessage(intentResult.getContents());
            log_data = intentResult.getContents();
            PrefConfig.saveSecretKey(getApplicationContext(), log_data);
//            byte[] encodeValue = Base64.encode(s.getBytes(), Base64.DEFAULT);
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>> " + s);
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>> " + encodeValue);
            builder.setPositiveButton(R.string.OK, (dialog, which) -> {
                if (log_data.equals(ActCode)) {
                    Toast.makeText(LoginActivity.this, R.string.userAdded, Toast.LENGTH_SHORT).show();
                    change = 1;
                    PrefConfig.saveChange(getApplicationContext(), change);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, R.string.notQRCode, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
                finish();
            });
            builder.show();
        } else {
            Toast.makeText(this, R.string.notCheckQRCode, Toast.LENGTH_SHORT).show();
        }
    }
}
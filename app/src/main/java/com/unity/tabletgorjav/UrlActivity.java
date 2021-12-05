package com.unity.tabletgorjav;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UrlActivity extends Activity {
    EditText ip, port, zeroParam, firstParam, secondParam, http;
    private String strIp, strPort, strZeroParam, strFirstParam, strSecondParam, strHttp;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        // declare variables
        ip = findViewById(R.id.editIP);
        port = findViewById(R.id.editPort);
        zeroParam = findViewById(R.id.editZeroParam);
        firstParam = findViewById(R.id.editFirstParam);
        secondParam = findViewById(R.id.editSecondParam);
        http = findViewById(R.id.editHttp);
        save = findViewById(R.id.save);

        // load data from PrefConfig
        strIp = PrefConfig.loadIpPref(getApplicationContext());
        strPort = PrefConfig.loadPORTPref(getApplicationContext());
        strZeroParam = PrefConfig.loadZeroParam(getApplicationContext());
        strFirstParam = PrefConfig.loadFirstParam(getApplicationContext());
        strSecondParam = PrefConfig.loadSecondParam(getApplicationContext());
        strHttp = PrefConfig.loadHttpPref(getApplicationContext());

        ip.setText(strIp);
        port.setText(strPort);
        zeroParam.setText(strZeroParam);
        firstParam.setText(strFirstParam);
        secondParam.setText(strSecondParam);
        http.setText(strHttp);

        save.setOnClickListener(v -> {
            // if place doesn't exist any characters
            if (ip.getText().toString().length() <= 0) {
                ip.setError("Boşlugy dolduryň!");
            } else if (port.getText().toString().length() <= 0) {
                port.setError("Boşlugy dolduryň!");
            } else if (firstParam.getText().toString().length() <= 0) {
                firstParam.setError("Boşlugy dolduryň!");
            } else if (secondParam.getText().toString().length() <= 0) {
                secondParam.setError("Boşlugy dolduryň!");
            } else if (http.getText().toString().length() <= 0) {
                http.setError("Boşlugy dolduryň!");
            } else {
                saveData();
            }
        });
    }
    private void saveData() {
        // get user data and save into mobile phone
        strIp = ip.getText().toString();
        strPort = port.getText().toString();
        strZeroParam = zeroParam.getText().toString();
        strFirstParam = firstParam.getText().toString();
        strSecondParam = secondParam.getText().toString();
        strHttp = http.getText().toString();
        PrefConfig.saveIpPref(getApplicationContext(), strIp);
        PrefConfig.savePORTPref(getApplicationContext(), strPort);
        PrefConfig.saveZeroParam(getApplicationContext(), strZeroParam);
        PrefConfig.saveFirstParam(getApplicationContext(), strFirstParam);
        PrefConfig.saveSecondParam(getApplicationContext(), strSecondParam);
        PrefConfig.saveHttpPref(getApplicationContext(), strHttp);
        // show text for successfully create a new user
        Toast.makeText(UrlActivity.this, "Üstünlikli", Toast.LENGTH_SHORT).show();

        System.out.println(strHttp + "://" + strIp + ":" + strPort + "/" + strZeroParam + strFirstParam + "/" + strSecondParam + "/");  // after strZeroParam you should put "/"
    }

}
package com.unity.tabletgorjav;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.material.button.MaterialButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TvActivity extends Activity {
    MaterialButton plus, minus, power, mute,  exit, enter, menu, btn_ch_up, btn_ch_down, btn_home, btn_source;
    MaterialButton up, down, left, right;
    String ip_address, strPort, strZeroParam, strFirstParam, strSecondParam, strHttp;
    JSONObject jsonParam = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv);
        // declare variables
        // ImageView (Round Remote Control button directions)
        up = findViewById(R.id.r_up);
        down = findViewById(R.id.r_down);
        left = findViewById(R.id.r_left);
        right = findViewById(R.id.r_right);
        // Material Button (remote control actions)
        plus = findViewById(R.id.vol_up_btn);
        minus = findViewById(R.id.vol_down_btn);
        power = findViewById(R.id.power_btn);
        mute = findViewById(R.id.mute_btn);
        enter = findViewById(R.id.enter);
        exit = findViewById(R.id.exit_btn);
        menu = findViewById(R.id.menu_btn);
        btn_ch_up = findViewById(R.id.ch_up_btn);
        btn_ch_down = findViewById(R.id.ch_down_btn);
        btn_home = findViewById(R.id.home_btn);
        btn_source = findViewById(R.id.access_btn);
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(this);
        // load URL data from PrefConfig
        strPort = PrefConfig.loadPORTPref(this);
        strZeroParam = PrefConfig.loadZeroParam(this);
        strFirstParam = PrefConfig.loadFirstParam(this);
        strSecondParam = PrefConfig.loadSecondParam(this);
        strHttp = PrefConfig.loadHttpPref(this);

        // Only one onClick Listener for all Material buttons
        @SuppressLint("NonConstantResourceId") View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.power_btn:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "power");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.enter:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "enter");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.menu_btn:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "settings");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.mute_btn:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "mute");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.exit_btn:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "exitt");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.vol_up_btn:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "volumeup");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.vol_down_btn:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "volumedown");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.ch_up_btn:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "Chplus");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.ch_down_btn:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "Chminus");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.home_btn:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "homee");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.access_btn:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "source");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
            }
        };
        power.setOnClickListener(onClickListener);
        enter.setOnClickListener(onClickListener);
        menu.setOnClickListener(onClickListener);
        mute.setOnClickListener(onClickListener);
        exit.setOnClickListener(onClickListener);
        plus.setOnClickListener(onClickListener);
        minus.setOnClickListener(onClickListener);
        btn_ch_up.setOnClickListener(onClickListener);
        btn_ch_down.setOnClickListener(onClickListener);
        btn_home.setOnClickListener(onClickListener);
        btn_source.setOnClickListener(onClickListener);

        // Only one onClick Listener for all ImageView control buttons
        @SuppressLint("NonConstantResourceId") View.OnClickListener imageClickListener = direction -> {
            switch (direction.getId()) {
                case R.id.r_up:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "Chplus");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.r_down:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "Chminus");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.r_left:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "volumedown");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
                case R.id.r_right:
                    try {
                        jsonParam.put("command", "command");
                        jsonParam.put("action", "volumeup");
                        sendData(); } catch (JSONException e) { e.printStackTrace(); }
                    break;
            }
        };
        up.setOnClickListener(imageClickListener);
        down.setOnClickListener(imageClickListener);
        left.setOnClickListener(imageClickListener);
        right.setOnClickListener(imageClickListener);
    }
    public void sendData() {
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/" + strSecondParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                pin_array.put(jsonParam);
                json.put("command", "tv_remote");
                json.put("pins", pin_array);
                Log.i("JSON", json.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(json.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
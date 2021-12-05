package com.unity.tabletgorjav;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import static com.unity.tabletgorjav.Commands.airSpeed1;
import static com.unity.tabletgorjav.Commands.airSpeed2;
import static com.unity.tabletgorjav.Commands.airSpeed3;
import static com.unity.tabletgorjav.Commands.air_power_off;
import static com.unity.tabletgorjav.Commands.air_power_on;

public class AirActivity extends Activity {
    SeekBar seekBar_air;
    int air_speed;
    String ip_address, strPort, strZeroParam, strFirstParam, strSecondParam, strHttp;
    TextView temp;
    int pro;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch air_switch;
    MaterialButton btn_speed1, btn_speed2, btn_speed3;
    int air_state, air_speed1, air_speed2, air_speed3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air);
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(this);
        // load URL data from PrefConfig
        strPort = PrefConfig.loadPORTPref(this);
        strZeroParam = PrefConfig.loadZeroParam(this);
        strFirstParam = PrefConfig.loadFirstParam(this);
        strSecondParam = PrefConfig.loadSecondParam(this);
        strHttp = PrefConfig.loadHttpPref(this);
        // load air_state => on/off
        air_state = PrefConfig.loadAir(this);
        air_speed1 = PrefConfig.loadAirSpeed1(this);
        air_speed2 = PrefConfig.loadAirSpeed2(this);
        air_speed3 = PrefConfig.loadAirSpeed3(this);
        // set Temperature to TextView
        temp = findViewById(R.id.tmp);

        // declare variables
        air_switch = findViewById(R.id.sw_air);
        btn_speed1 = findViewById(R.id.b_speed1);
        btn_speed2 = findViewById(R.id.b_speed2);
        btn_speed3 = findViewById(R.id.b_speed3);
        seekBar_air = findViewById(R.id.air_seek);

        // set air_state
        if (air_state == 1) {
            air_switch.setChecked(true);
            btn_speed1.setEnabled(true);
            btn_speed2.setEnabled(true);
            btn_speed3.setEnabled(true);
        } else if (air_state == 0) {
            air_switch.setChecked(false);
            btn_speed1.setEnabled(false);
            btn_speed2.setEnabled(false);
            btn_speed3.setEnabled(false);
        } else if (air_speed1 == 1) {
            speed1_On();
        } else if (air_speed2 == 1) {
            speed2_On();
        } else if (air_speed3 == 1) {
            speed3_On();
        }

        // Only one onClick Listener for all Material buttons
        @SuppressLint("NonConstantResourceId") View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.b_speed1:
                    speed1_On();
                    airSpeed1(getApplicationContext());
                    break;
                case R.id.b_speed2:
                    speed2_On();
                    airSpeed2(getApplicationContext());
                    break;
                case R.id.b_speed3:
                    speed3_On();
                    airSpeed3(getApplicationContext());
                    break;
            }
        };
        btn_speed1.setOnClickListener(onClickListener);
        btn_speed2.setOnClickListener(onClickListener);
        btn_speed3.setOnClickListener(onClickListener);

        // Seek_bar (for changing mode of lights)
        seekBar_air.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int degree, boolean b) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                air_speed = seekBar.getProgress();
                Thread thread = new Thread(() -> {
                    try {
                        URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/" + strSecondParam + "/");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                        conn.setRequestProperty("Accept", "application/json");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        JSONObject jsonParam = new JSONObject();
                        JSONObject jsonParam2 = new JSONObject();
                        JSONObject jsonParam3 = new JSONObject();
                        JSONObject jsonSend = new JSONObject();
                        JSONArray pin_array = new JSONArray();
                        if (air_speed == 0) {
                            // pin2 and pin3 -> HIGH or LOW both (for turn on all lights)
                            jsonParam.put("command", "mode_low");
                            jsonParam.put("action", "0");
                            jsonParam2.put("command", "mode_med");
                            jsonParam2.put("action", "0");
                            jsonParam3.put("command", "mode_high");
                            jsonParam3.put("action", "0");
                        } else if (air_speed == 1) {
                            jsonParam.put("command", "mode_low");
                            jsonParam.put("action", "1");
                            jsonParam2.put("command", "mode_med");
                            jsonParam2.put("action", "0");
                            jsonParam3.put("command", "mode_high");
                            jsonParam3.put("action", "0");
                        } else if (air_speed == 2) {
                            jsonParam.put("command", "mode_low");
                            jsonParam.put("action", "0");
                            jsonParam2.put("command", "mode_med");
                            jsonParam2.put("action", "1");
                            jsonParam3.put("command", "mode_high");
                            jsonParam3.put("action", "0");
                        } else if (air_speed == 3) {
                            jsonParam.put("command", "mode_low");
                            jsonParam.put("action", "0");
                            jsonParam2.put("command", "mode_med");
                            jsonParam2.put("action", "0");
                            jsonParam3.put("command", "mode_high");
                            jsonParam3.put("action", "1");
                        }
                        pin_array.put(jsonParam);
                        pin_array.put(jsonParam2);
                        pin_array.put(jsonParam3);
                        jsonSend.put("command", "conditioner_main");
                        jsonSend.put("pins", pin_array);
                        Log.i("JSON", jsonSend.toString());
                        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                        os.writeBytes(jsonSend.toString());
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
        });

        Croller croller = findViewById(R.id.recycle);
        croller.setOnCrollerChangeListener(new com.unity.tabletgorjav.OnCrollerChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                pro = progress + 15;
                temp.setText(pro + " \u2103");
            }
            @Override
            public void onStartTrackingTouch(Croller croller) {
            }
            @Override
            public void onStopTrackingTouch(Croller croller) {
            }
        });

        air_switch.setOnClickListener(v -> {
            if (air_switch.isChecked()) {
//                air_on(getApplicationContext());  // this is working with FAN COIL
                air_power_on(getApplicationContext());   // this is working with BEKO A/C
                btn_speed1.setEnabled(true);
                btn_speed2.setEnabled(true);
                btn_speed3.setEnabled(true);
            } else {
//                air_off(getApplicationContext()); // this is working with FAN COIL
                air_power_off(getApplicationContext());   // this is working with BEKO A/C
                btn_speed1.setEnabled(false);
                btn_speed2.setEnabled(false);
                btn_speed3.setEnabled(false);
            }
        });
    }

    public void speed1_On() {
        btn_speed1.setTextColor(getResources().getColor(R.color.white));
//        btn_speed1.setBackgroundTintList(getColorStateList(R.color.light_blue_600));
        btn_speed1.setBackgroundColor(getResources().getColor(R.color.light_blue_600));

        btn_speed2.setTextColor(getResources().getColor(R.color.light_text));
//        btn_speed2.setBackgroundTintList(getColorStateList(R.color.transparent));
        btn_speed2.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_speed3.setTextColor(getResources().getColor(R.color.light_text));
//        btn_speed3.setBackgroundTintList(getColorStateList(R.color.transparent));
        btn_speed3.setBackgroundColor(getResources().getColor(R.color.transparent));
    }
    public void speed2_On() {
        btn_speed1.setTextColor(getResources().getColor(R.color.light_text));
//        btn_speed1.setBackgroundTintList(getColorStateList(R.color.transparent));

        btn_speed2.setTextColor(getResources().getColor(R.color.white));
//        btn_speed2.setBackgroundTintList(getColorStateList(R.color.light_blue_600));

        btn_speed3.setTextColor(getResources().getColor(R.color.light_text));
//        btn_speed3.setBackgroundTintList(getColorStateList(R.color.transparent));
        btn_speed1.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_speed2.setBackgroundColor(getResources().getColor(R.color.light_blue_600));
        btn_speed3.setBackgroundColor(getResources().getColor(R.color.transparent));
    }
    public void speed3_On() {
        btn_speed1.setTextColor(getResources().getColor(R.color.light_text));
//        btn_speed1.setBackgroundTintList(getColorStateList(R.color.transparent));

        btn_speed2.setTextColor(getResources().getColor(R.color.light_text));
//        btn_speed2.setBackgroundTintList(getColorStateList(R.color.transparent));

        btn_speed3.setTextColor(getResources().getColor(R.color.white));
//        btn_speed3.setBackgroundTintList(getColorStateList(R.color.light_blue_600));

        btn_speed1.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_speed2.setBackgroundColor(getResources().getColor(R.color.transparent));
        btn_speed3.setBackgroundColor(getResources().getColor(R.color.light_blue_600));
    }
}
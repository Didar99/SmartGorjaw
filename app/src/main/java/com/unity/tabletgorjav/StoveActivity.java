package com.unity.tabletgorjav;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import static com.unity.tabletgorjav.Commands.stove1off;
import static com.unity.tabletgorjav.Commands.stove2off;
import static com.unity.tabletgorjav.Commands.stoveActive;
import static com.unity.tabletgorjav.Commands.stoveInactive;

public class StoveActivity extends Activity {
    Croller analog, analog2;
    TextView txt_analog, txt_analog2;
    private int get_pro, get_pro2;
    private String ip_address, strPort, strZeroParam, strFirstParam, strSecondParam, strHttp;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch stove_state;
    int stove_int, stove1_pro, stove2_pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stove);
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(this);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(this);
        strZeroParam = PrefConfig.loadZeroParam(this);
        strFirstParam = PrefConfig.loadFirstParam(this);
        strSecondParam = PrefConfig.loadSecondParam(this);
        strHttp = PrefConfig.loadHttpPref(this);
        // load stove data from PrefConfig
        stove_int = PrefConfig.loadStateStove(this);
        stove1_pro = PrefConfig.loadProST1(this);
        stove2_pro = PrefConfig.loadProST2(this);

        //declare variables
        // stove control analogs
        analog = findViewById(R.id.cooker_analog);
        analog2 = findViewById(R.id.cooker2_analog);
        // TextView (analog names)
        txt_analog = findViewById(R.id.pro_cook1);
        txt_analog2 = findViewById(R.id.pro_cook2);
        stove_state = findViewById(R.id.sw_stove);

        if (stove_int == 1) {
            stove_state.setChecked(true);
            analog.setEnabled(true);
            analog2.setEnabled(true);
        } else if (stove_int == 0) {
            stove_state.setChecked(false);
            analog.setEnabled(false);
            analog2.setEnabled(false);
        }

        analog.setProgress(stove1_pro);
        analog2.setProgress(stove2_pro);

        stove_state.setOnClickListener(v -> {
            if (stove_state.isChecked()) {
                stoveActive(getApplicationContext());
                analog.setEnabled(true);
                analog2.setEnabled(true);
                PrefConfig.saveStateStove(getApplicationContext(), 1);

            } else {
                stoveInactive(getApplicationContext());
                analog.setEnabled(false);
                analog2.setEnabled(false);
                PrefConfig.saveStateStove(getApplicationContext(), 0);

                PrefConfig.saveProST1(getApplicationContext(), 1);
                PrefConfig.saveProST2(getApplicationContext(), 1);
                stove1off(getApplicationContext());
                stove2off(getApplicationContext());
            }
        });

        analog.setOnCrollerChangeListener(new com.unity.tabletgorjav.OnCrollerChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                get_pro = progress-1;
                txt_analog.setText(String.valueOf(get_pro));
            }
            @Override
            public void onStartTrackingTouch(Croller croller) { }
            @Override
            public void onStopTrackingTouch(Croller croller) {
                Thread thread = new Thread(() -> {
                    try {
                        URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/" + strSecondParam + "/");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                        conn.setRequestProperty("Accept","application/json");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        JSONObject jsonParam = new JSONObject();
                        JSONObject json2 = new JSONObject();
                        JSONArray pin_array2 = new JSONArray();
                        jsonParam.put("command", "cooker1");
                        if (get_pro == 0) {
                            jsonParam.put("action", "0");
                        } else if (get_pro == 1) {
                            jsonParam.put("action", "1");
                        } else if (get_pro == 2) {
                            jsonParam.put("action", "2");
                        } else if (get_pro == 3) {
                            jsonParam.put("action", "3");
                        } else if (get_pro == 4) {
                            jsonParam.put("action", "4");
                        } else if (get_pro == 5) {
                            jsonParam.put("action", "5");
                        } else if (get_pro == 6) {
                            jsonParam.put("action", "6");
                        } else if (get_pro == 7) {
                            jsonParam.put("action", "7");
                        } else if (get_pro == 8) {
                            jsonParam.put("action", "8");
                        }
                        pin_array2.put(jsonParam);
                        json2.put("command", "smart_stove");
                        json2.put("pins", pin_array2);
                        Log.i("JSON", json2.toString());
                        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                        os.writeBytes(json2.toString());
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
                PrefConfig.saveProST1(getApplicationContext(), get_pro +1);
            }
        });
        analog2.setOnCrollerChangeListener(new com.unity.tabletgorjav.OnCrollerChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                get_pro2 = progress-1;
                txt_analog2.setText(String.valueOf(get_pro2));
            }
            @Override
            public void onStartTrackingTouch(Croller croller) { }
            @Override
            public void onStopTrackingTouch(Croller croller) {
                Thread thread = new Thread(() -> {
                    try {
                        URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/" + strSecondParam + "/");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                        conn.setRequestProperty("Accept","application/json");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);
                        JSONObject jsonParam = new JSONObject();
                        JSONObject json2 = new JSONObject();
                        JSONArray pin_array2 = new JSONArray();
                        jsonParam.put("command", "cooker2");
                        if (get_pro2 == 0) {
                            jsonParam.put("action", "0");
                        } else if (get_pro2 == 1) {
                            jsonParam.put("action", "1");
                        } else if (get_pro2 == 2) {
                            jsonParam.put("action", "2");
                        } else if (get_pro2 == 3) {
                            jsonParam.put("action", "3");
                        } else if (get_pro2 == 4) {
                            jsonParam.put("action", "4");
                        } else if (get_pro2 == 5) {
                            jsonParam.put("action", "5");
                        } else if (get_pro2 == 6) {
                            jsonParam.put("action", "6");
                        } else if (get_pro2 == 7) {
                            jsonParam.put("action", "7");
                        } else if (get_pro2 == 8) {
                            jsonParam.put("action", "8");
                        }
                        pin_array2.put(jsonParam);
                        json2.put("command", "smart_stove");
                        json2.put("pins", pin_array2);
                        Log.i("JSON", json2.toString());
                        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                        os.writeBytes(json2.toString());
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
                PrefConfig.saveProST2(getApplicationContext(), get_pro2 +1);
            }
        });
    }
}
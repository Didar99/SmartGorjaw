package com.unity.tabletgorjav;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class LightActivity extends Activity {

//    SeekBar seekBar_lamp;
    TextView one, two, three, four;
//    int light_degree;
    String ip_address, strPort, strZeroParam, strFirstParam, strSecondParam, strHttp;
    int light1, light2, light3;
    TextView txtLightD;
    TextView per0, per25, per50, per75, per100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //remove status bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_light);
        // Seek_bar (change mode of lights)
//        seekBar_lamp = findViewById(R.id.lamp_seek);
        // TextView (lights mode indicator)
        one = findViewById(R.id.one_txt);
        two = findViewById(R.id.two_txt);
        three = findViewById(R.id.three_txt);
        four = findViewById(R.id.four_txt);
        txtLightD = findViewById(R.id.txt_LDegree);
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(this);
        // load URL data from PrefConfig
        strPort = PrefConfig.loadPORTPref(this);
        strZeroParam = PrefConfig.loadZeroParam(this);
        strFirstParam = PrefConfig.loadFirstParam(this);
        strSecondParam = PrefConfig.loadSecondParam(this);
        strHttp = PrefConfig.loadHttpPref(this);
        // load light mode data from PrefConfig
        light1 = PrefConfig.loadLight1(this);
        light2 = PrefConfig.loadLight2(this);
        light3 = PrefConfig.loadLight3(this);

//        if (light1 == 1) {
//            seekBar_lamp.setProgress(1);
//            txtLightD.setText(R.string.percent25);
//        } else if (light2 == 1) {
//            seekBar_lamp.setProgress(2);
//            txtLightD.setText(R.string.percent50);
//        } else if (light3 == 1) {
//            seekBar_lamp.setProgress(3);
//            txtLightD.setText(R.string.percent75);
//        } else if (light2 == 1 && light3 == 1) {
//            seekBar_lamp.setProgress(4);
//            txtLightD.setText(R.string.percent100);
//        } else if (light1 == 0 && light2 == 0 && light3 == 0) {
//            seekBar_lamp.setProgress(0);
//            txtLightD.setText(R.string.percent0);
//        }


        per0 = findViewById(R.id.per0);
        per25 = findViewById(R.id.per25);
        per50 = findViewById(R.id.per50);
        per75 = findViewById(R.id.per75);
        per100 = findViewById(R.id.per100);

        per0.setOnClickListener(v -> {
            per0.setTextColor(getResources().getColor(R.color.fourth_effect));
            per25.setTextColor(getResources().getColor(R.color.black));
            per50.setTextColor(getResources().getColor(R.color.black));
            per75.setTextColor(getResources().getColor(R.color.black));
            per100.setTextColor(getResources().getColor(R.color.black));
            txtLightD.setText(R.string.percent0);
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
                    jsonParam.put("command", "pin2");
                    jsonParam.put("action", "0");
                    jsonParam2.put("command", "pin3");
                    jsonParam2.put("action", "0");
                    jsonParam3.put("command", "pin1");
                    jsonParam3.put("action", "0");
                    pin_array.put(jsonParam);
                    pin_array.put(jsonParam2);
                    pin_array.put(jsonParam3);
                    jsonSend.put("command", "three_mode_switch");
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
        });

        per25.setOnClickListener(v -> {
            per0.setTextColor(getResources().getColor(R.color.fourth_effect));
            per25.setTextColor(getResources().getColor(R.color.fourth_effect));
            per50.setTextColor(getResources().getColor(R.color.black));
            per75.setTextColor(getResources().getColor(R.color.black));
            per100.setTextColor(getResources().getColor(R.color.black));
            txtLightD.setText(R.string.percent25);
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
                    jsonParam.put("command", "pin2");
                    jsonParam.put("action", "0");
                    jsonParam2.put("command", "pin3");
                    jsonParam2.put("action", "0");
                    jsonParam3.put("command", "pin1");
                    jsonParam3.put("action", "1");
                    pin_array.put(jsonParam);
                    pin_array.put(jsonParam2);
                    pin_array.put(jsonParam3);
                    jsonSend.put("command", "three_mode_switch");
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
        });
        per50.setOnClickListener(v -> {
            per0.setTextColor(getResources().getColor(R.color.fourth_effect));
            per25.setTextColor(getResources().getColor(R.color.fourth_effect));
            per50.setTextColor(getResources().getColor(R.color.fourth_effect));
            per75.setTextColor(getResources().getColor(R.color.black));
            per100.setTextColor(getResources().getColor(R.color.black));
            txtLightD.setText(R.string.percent50);

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
                    jsonParam.put("command", "pin2");
                    jsonParam.put("action", "1");
                    jsonParam2.put("command", "pin3");
                    jsonParam2.put("action", "0");
                    jsonParam3.put("command", "pin1");
                    jsonParam3.put("action", "0");
                    pin_array.put(jsonParam);
                    pin_array.put(jsonParam2);
                    pin_array.put(jsonParam3);
                    jsonSend.put("command", "three_mode_switch");
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
        });

        per75.setOnClickListener(v -> {
            per0.setTextColor(getResources().getColor(R.color.fourth_effect));
            per25.setTextColor(getResources().getColor(R.color.fourth_effect));
            per50.setTextColor(getResources().getColor(R.color.fourth_effect));
            per75.setTextColor(getResources().getColor(R.color.fourth_effect));
            per100.setTextColor(getResources().getColor(R.color.black));
            txtLightD.setText(R.string.percent75);

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
                    jsonParam.put("command", "pin2");
                    jsonParam.put("action", "0");
                    jsonParam2.put("command", "pin3");
                    jsonParam2.put("action", "1");
                    jsonParam3.put("command", "pin1");
                    jsonParam3.put("action", "0");
                    pin_array.put(jsonParam);
                    pin_array.put(jsonParam2);
                    pin_array.put(jsonParam3);
                    jsonSend.put("command", "three_mode_switch");
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
        });
        per100.setOnClickListener(v -> {
            per0.setTextColor(getResources().getColor(R.color.fourth_effect));
            per25.setTextColor(getResources().getColor(R.color.fourth_effect));
            per50.setTextColor(getResources().getColor(R.color.fourth_effect));
            per75.setTextColor(getResources().getColor(R.color.fourth_effect));
            per100.setTextColor(getResources().getColor(R.color.fourth_effect));
            txtLightD.setText(R.string.percent100);


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
                    jsonParam.put("command", "pin2");
                    jsonParam.put("action", "1");
                    jsonParam2.put("command", "pin3");
                    jsonParam2.put("action", "1");
                    jsonParam3.put("command", "pin1");
                    jsonParam3.put("action", "0");
                    pin_array.put(jsonParam);
                    pin_array.put(jsonParam2);
                    pin_array.put(jsonParam3);
                    jsonSend.put("command", "three_mode_switch");
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
        });

        // Seek_bar (for changing mode of lights)
//        seekBar_lamp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int degree, boolean b) {}
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {}
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                light_degree = seekBar.getProgress();
//                // Send json data to ESP 8266 module  -> in Browser you can type like [ip_address/control/pin1=1 or pin2=1 or pin3=1 <-> pin1=0 or pin2=0 or pin3=0]
//                Thread thread = new Thread(() -> {
//                    try {
//                        URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/" + strSecondParam + "/");
//                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                        conn.setRequestMethod("POST");
//                        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//                        conn.setRequestProperty("Accept", "application/json");
//                        conn.setDoOutput(true);
//                        conn.setDoInput(true);
//                        JSONObject jsonParam = new JSONObject();
//                        JSONObject jsonParam2 = new JSONObject();
//                        JSONObject jsonParam3 = new JSONObject();
//                        JSONObject jsonSend = new JSONObject();
//                        JSONArray pin_array = new JSONArray();
//                        if (light_degree == 0) {
//                            // pin2 and pin3 -> HIGH or LOW both (for turn on all lights)
//                            jsonParam.put("command", "pin2");
//                            jsonParam.put("action", "0");
//                            jsonParam2.put("command", "pin3");
//                            jsonParam2.put("action", "0");
//                            jsonParam3.put("command", "pin1");
//                            jsonParam3.put("action", "0");
//                            txtLightD.setText(R.string.percent0);
//                        } else if (light_degree == 1) {
//                            jsonParam.put("command", "pin2");
//                            jsonParam.put("action", "0");
//                            jsonParam2.put("command", "pin3");
//                            jsonParam2.put("action", "0");
//                            jsonParam3.put("command", "pin1");
//                            jsonParam3.put("action", "1");
//                            txtLightD.setText(R.string.percent25);
//                        } else if (light_degree == 2) {
//                            jsonParam.put("command", "pin2");
//                            jsonParam.put("action", "1");
//                            jsonParam2.put("command", "pin3");
//                            jsonParam2.put("action", "0");
//                            jsonParam3.put("command", "pin1");
//                            jsonParam3.put("action", "0");
//                            txtLightD.setText(R.string.percent50);
//                        } else if (light_degree == 3) {
//                            jsonParam.put("command", "pin2");
//                            jsonParam.put("action", "0");
//                            jsonParam2.put("command", "pin3");
//                            jsonParam2.put("action", "1");
//                            jsonParam3.put("command", "pin1");
//                            jsonParam3.put("action", "0");
//                            txtLightD.setText(R.string.percent75);
//                        } else if (light_degree == 4) {
//                            jsonParam.put("command", "pin2");
//                            jsonParam.put("action", "1");
//                            jsonParam2.put("command", "pin3");
//                            jsonParam2.put("action", "1");
//                            jsonParam3.put("command", "pin1");
//                            jsonParam3.put("action", "0");
//                            txtLightD.setText(R.string.percent100);
//                        }
//                        pin_array.put(jsonParam);
//                        pin_array.put(jsonParam2);
//                        pin_array.put(jsonParam3);
//                        jsonSend.put("command", "three_mode_switch");
//                        jsonSend.put("pins", pin_array);
//                        Log.i("JSON", jsonSend.toString());
//                        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
//                        os.writeBytes(jsonSend.toString());
//                        os.flush();
//                        os.close();
//                        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                            conn.disconnect();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                });
//                thread.start();
//            }
//        });
    }
}
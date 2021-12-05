package com.unity.tabletgorjav;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.unity.tabletgorjav.Commands.gasValve_on;
import static com.unity.tabletgorjav.Commands.light_off;
import static com.unity.tabletgorjav.Commands.light_on;
import static com.unity.tabletgorjav.Commands.socket1_off;
import static com.unity.tabletgorjav.Commands.socket1_on;
import static com.unity.tabletgorjav.Commands.socket2_off;
import static com.unity.tabletgorjav.Commands.socket2_on;
import static com.unity.tabletgorjav.Commands.socket3_off;
import static com.unity.tabletgorjav.Commands.socket3_on;
import static com.unity.tabletgorjav.Commands.water_off;

public class MainActivity extends Activity {
    NetworkInfo mWifi, mEth;

    EditText editTextField;
    String editTextInput;
    MaterialButton btn_panel;
    TextView txt_DevName, txt_SubName, txt_DevPx;
    TextView txt_wattValue, txt_waterValue, txt_gasValue;
    GridView gridView;
    String [] numberWord = {"Çyra", "Kondisioner", "Rozetka", "Perde", "Telewizor", "Rozetka Aşhana", "Gorag Ulgamy", "Rozetka Otag 1", "Elektrik peç", "Bellikler", "Kamera"};

    String [] numberSubWord = {"Ýagtylandyryş, Görnüş", "Temperatura, Tizlik", "Ýagdaý", "Dereje", "Görnüş", "Ýagdaý",
            "Tertip, Funksiýa", "Ýagdaý", "Ojak A, Ojak B", "Ýagdaý", "Görnüş"};

    int[] numberImage = {R.drawable.ic_lamp, R.drawable.conditioner, R.drawable.ic_socket, R.drawable.ic_blind,
            R.drawable.ic_tv, R.drawable.ic_socket, R.drawable.secure, R.drawable.ic_socket, R.drawable.ic_oven, R.drawable.ic_info, R.drawable.ic_camera};

    // All device states 0 or 1
    int light_state1, light_state2, light_state3, air_state, SpeedAir1_state, SpeedAir2_state, SpeedAir3_state, socket1_state, socket2_state, socket3_state, water_valve, water_leak_sensor, gerKon_sensor, door_sensor, watt_value, water_value, gas_leak_sensor, gas_valve, pir_sensor;   // tv_state
    // water, gas, PIR, gerKon => [BOOLEAN] (FOR -> call only one SMS or NOTIFICATION)
    boolean bool_water, bool_gas, bool_pir, bool_gerKon = false;
    // activate Sending SMS, NOTIFICATION or PIR object detecting
    int sms_activator, note_activator, pir_activator;
    // SMS Manager for sending SMS
    SmsManager smsManager;  int phoneNumber;
    //AsyncTask class variables
    int loop_time;
    // Notification settings
    static final String CHANNEL_ID = "channel_id";
    NotificationManagerCompat notificationManagerCompat;
    // URL Settings
    private String final_result, ip_address, strPort, strZeroParam, strFirstParam, strHttp; // strSecondParam
    // Scan Network
    TextView txtScan;
    // Recreate app when change settings
    int recreateAct;
//    SensorManager sm;
//    ShakeDetector sd;
    boolean changeHost = false;
    Handler handler;
    Runnable runnable;
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton netChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*  for recreate without animation effect
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);     */

        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(this);   
        // load URL data from PrefConfig
        strPort = PrefConfig.loadPORTPref(this);
        strZeroParam = PrefConfig.loadZeroParam(this);
        strFirstParam = PrefConfig.loadFirstParam(this);
//        strSecondParam = PrefConfig.loadSecondParam(this);
        strHttp = PrefConfig.loadHttpPref(this);
        // load phone number, sms, note VALUES
        phoneNumber = PrefConfig.loadPhone(this);
        sms_activator = PrefConfig.loadStateSms(this);
        note_activator = PrefConfig.loadStateNote(this);
        // load PIR sensor state
        pir_activator = PrefConfig.loadPirLight(this);
        // delay for loop -> fetch data from Server
        loop_time = PrefConfig.loadTime(this);
        // Surfing URL (ScanNetwork => for updating ip address of wifi modules)
        txtScan = findViewById(R.id.t_ScanNetwork);
        // Set SMS sender
        smsManager = SmsManager.getDefault();
        // Set Notification sender
        notificationManagerCompat = NotificationManagerCompat.from(this);

        // declare variables
        coordinatorLayout = findViewById(R.id.coordinator);
        btn_panel = findViewById(R.id.b_Panel);
        txt_DevName = findViewById(R.id.t_DeviceName);
        txt_SubName = findViewById(R.id.t_SubName);
        txt_DevPx = findViewById(R.id.t_DevicePx);
        gridView = findViewById(R.id.grid_view);
        txt_wattValue = findViewById(R.id.t_wattValue);
        txt_waterValue = findViewById(R.id.t_waterValue);
        txt_gasValue = findViewById(R.id.t_gasValue);
        netChange = findViewById(R.id.fabNet);

        // Give Personal PERMISSION for Receive and Send SMS
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, 1000);
            }
        }

        // GIVE ATTENTION !!! This is get data from Raspberry Pi4 4GB every 5 seconds and it can take from PHONE more energy and network (see: Android Profiler)
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, loop_time); // this delay repeat CLASS every GIVEN seconds
                new GetJsonData().execute();
                Log.e("Main Response => ", "updating data...");
            }
        }, 3000); // this delay run first
//        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
//        sd = new ShakeDetector(() -> {
//            if (!changeHost) {
//                PrefConfig.saveIpPref(getApplicationContext(), "192.168.7.252");
//                changeHost = true;
//                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Ýerli tor birikmesi", Snackbar.LENGTH_LONG);
//                snackbar.show();
//            } else {
//                PrefConfig.saveIpPref(getApplicationContext(), "95.85.112.74");
//                changeHost = false;
//                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Internet birikmesi", Snackbar.LENGTH_LONG);
//                snackbar.show();
//            }
//            Log.e("SHAKE DETECTOR => ", "DETECTED"); });

        // check wifi or internet connection
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null) {
            mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            mEth = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        }

//        if (mWifi.isConnected()) {
//            PrefConfig.saveIpPref(getApplicationContext(), "192.168.7.252");
//            Log.e("LOCALHOST => ", "CONNECTED");
//            changeHost = true;
//            netChange.setImageResource(R.drawable.cloud_off);
//            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Ýerli tor birikmesi", Snackbar.LENGTH_LONG);
//            snackbar.show();
//        } else if (mEth.isConnected()) {
////            PrefConfig.saveIpPref(getApplicationContext(), "95.85.112.74");
//            Log.e("INTERNET => ", "CONNECTED");
//            changeHost = false;
//            netChange.setImageResource(R.drawable.cloud);
//            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Internet birikmesi", Snackbar.LENGTH_LONG);
//            snackbar.show();
//        } else {
//            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Internet baglanyşygy ýok", Snackbar.LENGTH_LONG);
//            snackbar.show();
//        }
        if (mEth.isConnected() || mWifi.isConnected()) {
            Log.e("INTERNET => ", "CONNECTED");
        } else {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Internet baglanyşygy ýok", Snackbar.LENGTH_LONG);
            snackbar.show();
            handler.removeCallbacks(runnable);
        }

        if (ip_address.contains("112")) {
            netChange.setImageResource(R.drawable.cloud);
            changeHost = false;
        } else {
            netChange.setImageResource(R.drawable.cloud_off);
            changeHost = true;
        }


        // Get All View items from MainAdapter
        MainAdapter adapter = new MainAdapter(MainActivity.this, numberWord, numberSubWord, numberImage);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
//            Toast.makeText(MainActivity.this, "you clicked" + numberWord[+position], Toast.LENGTH_SHORT).show();
            switch (position) {
                case 0:
                    startActivity(new Intent(getApplicationContext(), LightActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getApplicationContext(), AirActivity.class));
                    break;
                case 2:
                    if (socket1_state == 0) {
                        socket1_on(getApplicationContext());
                        view.setBackgroundColor(Color.BLUE);
                    } else if (socket1_state == 1){
                        socket1_off(getApplicationContext());
                        view.setBackgroundColor(Color.TRANSPARENT);
                    }
                    break;
                case 3:
                    startActivity(new Intent(getApplicationContext(), CurtainActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(getApplicationContext(), TvActivity.class));
                    break;
                case 5:
                    if (socket2_state == 0) {
                        socket2_on(getApplicationContext());
                        view.setBackgroundColor(Color.BLUE);
                    } else if (socket2_state == 1){
                        socket2_off(getApplicationContext());
                        view.setBackgroundColor(Color.TRANSPARENT);
                    }
                    break;
                case 6:
                    editTextField = new EditText(MainActivity.this);
                    AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.securitySys)
                            .setMessage(R.string.password)
                            .setView(editTextField)
                            .setPositiveButton(R.string.confirm, (dialogInterface, i) -> {
                                editTextInput = editTextField.getText().toString();
                                // Log.d("onclick","editText value is: "+ editTextInput);
                                if (editTextInput.equals("123")) {
                                    startActivity(new Intent(getApplicationContext(), SecurityActivity.class));
                                } else {
                                    Toast.makeText(MainActivity.this, R.string.tryAgain, Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton(R.string.cancel, null)
                            .create();
                    dialog.show();
                    break;
                case 7:
                    if (socket3_state == 0) {
                        socket3_on(getApplicationContext());
                        view.setBackgroundColor(Color.BLUE);
                    } else if (socket3_state == 1){
                        socket3_off(getApplicationContext());
                        view.setBackgroundColor(Color.TRANSPARENT);
                    }
                    break;
                case 8:
                    startActivity(new Intent(getApplicationContext(), StoveActivity.class));
                    break;
                case 9:
                    startActivity(new Intent(getApplicationContext(), TimerActivity.class));
                    break;
                case  10:
                    startActivity(new Intent(getApplicationContext(), StreamActivity.class));
                    break;
            }
        });

        txtScan.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ScanActivity.class)));

        netChange.setOnClickListener(v -> {
            if (!changeHost) {
                PrefConfig.saveIpPref(getApplicationContext(), "192.168.7.252");
                changeHost = true;
                netChange.setImageResource(R.drawable.cloud_off);
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Ýerli tor birikmesi", Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                PrefConfig.saveIpPref(getApplicationContext(), "95.85.112.74");
                changeHost = false;
                netChange.setImageResource(R.drawable.cloud);
                Snackbar snackbar = Snackbar.make(coordinatorLayout, "Internet birikmesi", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
            handler.removeCallbacks(runnable);
            recreate();
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            recreateAct = 1;
        });
        floatingActionButton.setOnLongClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), UrlActivity.class));
            return false;
        });
    }

    // Check if PERMISSION is GRANTED or NOT GRANTED
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.granted, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.notGranted, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private class GetJsonData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            String getUrl = strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/" + "checkState/";
            try {
                    URL url;
                    HttpURLConnection urlConnection;
                    url = new URL(getUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
                    StringBuilder response = new StringBuilder();   //  this is also declared like => StringBuffer response = new StringBuffer();
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) { //success
                        BufferedReader inUrl = new BufferedReader(new InputStreamReader(
                                urlConnection.getInputStream()));
                        String inputLine;
                        while ((inputLine = inUrl.readLine()) != null) {
                            response.append(inputLine);
                        }
                        inUrl.close();
                    } else {
                        Log.i("test", "POST request not worked.");
                    }
                    final_result = response.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {
                if (final_result != null) {
                    parseJson(final_result);
                } else {
                    Log.e("NO INTERNET => ", "NO RESPONSE");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            if(result!=null){  Log.e("INTERNET => ", "RESPONSE"); }   // Do you work here on success
//            else{ Log.e("NO INTERNET => ", "NO RESPONSE");  }   // null response or Exception occur
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        void parseJson(String json) throws JSONException {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jArr = jsonObject.getJSONArray("data");

            // lights (getting devices data from (/esp/checkState) array[])
            JSONObject obj10 = jArr.getJSONObject(10);
            JSONArray pinLight1 = obj10.getJSONArray("pins");
            JSONArray pinLight2 = obj10.getJSONArray("pins");
            JSONArray pinLight3 = obj10.getJSONArray("pins");

            JSONObject light1 = pinLight1.getJSONObject(0);
            light_state1 = Integer.parseInt(light1.getString("action"));
            PrefConfig.saveLight1(getApplicationContext(), light_state1);

            JSONObject light2 = pinLight2.getJSONObject(1);
            light_state2 = Integer.parseInt(light2.getString("action"));
            PrefConfig.saveLight2(getApplicationContext(), light_state2);

            JSONObject light3 = pinLight3.getJSONObject(2);
            light_state3 = Integer.parseInt(light3.getString("action"));
            PrefConfig.saveLight3(getApplicationContext(), light_state3);

            // watt (electric current) measurer sensor (value)
            JSONObject obj8 = jArr.getJSONObject(8);
            JSONArray pins8 = obj8.getJSONArray("sensors");
            JSONObject watt_val = pins8.getJSONObject(0);
            watt_value = watt_val.getInt("value");
//            watt_value = watt_value * 220;
//            PrefConfig.saveWattValue(getApplicationContext(), watt_value);
            txt_wattValue.setText(String.valueOf(watt_value));

            // water measurer sensor value
            JSONObject obj9 = jArr.getJSONObject(9);
            JSONArray pins9 = obj9.getJSONArray("sensors");
            JSONObject water_val = pins9.getJSONObject(0);
            water_value = water_val.getInt("value");
            water_value = water_value / 1000;
//            PrefConfig.saveWaterValue(getApplicationContext(), water_value);
            txt_waterValue.setText(String.valueOf(water_value));

            // smart socket
            JSONObject obj2 = jArr.getJSONObject(2);
            JSONArray pinSocket1 = obj2.getJSONArray("pins");
            JSONArray pinSocket2 = obj2.getJSONArray("pins");
            JSONArray pinSocket3 = obj2.getJSONArray("pins");
            // => socket1 state 1 or 0
            JSONObject socket1 = pinSocket1.getJSONObject(0);
            socket1_state = socket1.getInt("action");
            // => socket2 state 1 or 0
            JSONObject socket2 = pinSocket2.getJSONObject(1);
            socket2_state = socket2.getInt("action");
            // => socket3 state 1 or 0
            JSONObject socket3 = pinSocket3.getJSONObject(2);
            socket3_state = socket3.getInt("action");

            // air conditioner speed
            JSONObject obj6 = jArr.getJSONObject(1);
            JSONArray AIRSpeed1 = obj6.getJSONArray("pins");
            JSONArray AIRSpeed2 = obj6.getJSONArray("pins");
            JSONArray AIRSpeed3 = obj6.getJSONArray("pins");
            // => air speed state1 = state 1 or 0
            JSONObject speed1 = AIRSpeed1.getJSONObject(2);
            SpeedAir1_state = speed1.getInt("action");
            // => air speed state2 = state 1 or 0
            JSONObject speed2 = AIRSpeed2.getJSONObject(3);
            SpeedAir2_state = speed2.getInt("action");
            // => air speed state3 state 1 or 0
            JSONObject speed3 = AIRSpeed3.getJSONObject(1);
            SpeedAir3_state = speed3.getInt("action");
            PrefConfig.saveAirSpeed1(getApplicationContext(), SpeedAir1_state);
            PrefConfig.saveAirSpeed2(getApplicationContext(), SpeedAir2_state);
            PrefConfig.saveAirSpeed3(getApplicationContext(), SpeedAir3_state);

            // water pump data 1 or 0
            JSONObject obj11 = jArr.getJSONObject(11); //This index takes from (devicesConfig.py)->device rows(ex:water sensor located in 12 rows of devices and you must take n-1) (FireFox best solution for see)
            water_valve = obj11.getInt("state");

            // water leak sensor data 1 or 0
            JSONObject obj5 = jArr.getJSONObject(5);
            water_leak_sensor = obj5.getInt("state");

            // gerKon sensor data 1 or 0 (1 -> closed 0 -> opened)
            JSONObject obj15 = jArr.getJSONObject(15);
            gerKon_sensor = obj15.getInt("state");

            // door sensor data 1 or 0
            JSONObject obj14 = jArr.getJSONObject(14);
            door_sensor = obj14.getInt("state");

            // gas leak sensor data 1 or 0 (when 1 -> off;  0 -> on)
            JSONObject obj16 = jArr.getJSONObject(16);
            gas_leak_sensor = obj16.getInt("state");

            // gas valve data 1 or 0 (when 1 -> off;  0 -> on)
            JSONObject obj17 = jArr.getJSONObject(17);
            gas_valve = obj17.getInt("state");

            // PIR sensor (light) data 1 or 0 (when 1 -> off;  0 -> on)
            JSONObject obj18 = jArr.getJSONObject(18);
            pir_sensor = obj18.getInt("state");


            System.out.println("Light state1  => " + light_state1);
            System.out.println("Light state2  => " + light_state2);
            System.out.println("Light state3  => " + light_state3);
            System.out.println("water valve   => " + water_valve);
            System.out.println("water leak    => " + water_leak_sensor);
            System.out.println("gerKon sensor => " + gerKon_sensor);
            System.out.println("door sensor   => " + door_sensor);
            System.out.println("Watt value    => " + watt_value);
            System.out.println("Water value   => " + water_value);
            System.out.println("gas leak      => " + gas_leak_sensor);
            System.out.println("gas valve     => " + gas_valve);
            System.out.println("PIR sensor    => " + pir_sensor);
            System.out.println("socket1 state => " + socket1_state);
            System.out.println("socket2 state => " + socket2_state);
            System.out.println("socket3 state => " + socket3_state);
            System.out.println("AirSpd1 state => " + SpeedAir1_state);
            System.out.println("AirSpd2 state => " + SpeedAir2_state);
            System.out.println("AirSpd3 state => " + SpeedAir3_state);
            System.out.println("conditioner inactive (please uncomment) !!!!!!!!!!!!!!!!!!!!!");

            // air state => if all air speed 1|2|3 is the ZERO OR TURN OFF THEN AIR STATE SHOULD TURN OFF
            if (SpeedAir1_state == 1 || SpeedAir2_state == 1 || SpeedAir3_state == 1) {
                air_state = 1;
                PrefConfig.saveAir(getApplicationContext(), air_state);
            } else if (SpeedAir1_state == 0 && SpeedAir2_state == 0 && SpeedAir3_state == 0) {
                air_state = 0;
                PrefConfig.saveAir(getApplicationContext(), air_state);
            }
            //  get device state and set what do you want
//            if (light_state1 == 1) { } else if (light_state1 == 0) { }
//            if (water_valve == 1) { } else if (water_valve == 0) { }
//            if (door_sensor == 1) { } else if (door_sensor == 0) { }
//            if (gas_valve == 1) { } else if (gas_valve == 0) { }

            if (water_leak_sensor == 1 && bool_water) {
                water_off(getApplicationContext()); PrefConfig.saveWaterValve(getApplicationContext(), water_valve);
                if (sms_activator == 1) { smsManager.sendTextMessage("+993" + phoneNumber, null, "Suw agdy!", null, null); }
                if (note_activator == 1) {water_note();}
                bool_water = false;
            } else if (water_leak_sensor == 0 && !bool_water) { bool_water = true;}

            if (gerKon_sensor == 1 && bool_gerKon) { bool_gerKon = false;} else if (gerKon_sensor == 0 && !bool_gerKon) {
                if (sms_activator == 1) { smsManager.sendTextMessage("+993" + phoneNumber, null, "Aýna açyldy!", null, null); }
                if (note_activator == 1) {gerKon_note();}
                bool_gerKon = true;
            }

            if (gas_leak_sensor == 0 && bool_gas) { bool_gas = false;} else if (gas_leak_sensor == 1 && !bool_gas) {
                gasValve_on(getApplicationContext());
                PrefConfig.saveGasValve(getApplicationContext(), gas_valve);
                if (sms_activator == 1) { smsManager.sendTextMessage("+993" + phoneNumber, null, "Gaz çykdy!", null, null);}
                if (note_activator == 1) {gas_note();}
                bool_gas = true;
            }

            if (pir_sensor == 1 && bool_pir) {
                if (pir_activator == 1) { light_off(getApplicationContext()); }
                if (sms_activator == 1) { smsManager.sendTextMessage("+993" + phoneNumber, null, "Garaşylmadyk hereket!", null, null);}
                if (note_activator == 1) {pir_note();}
                bool_pir = false;
            } else if (pir_sensor == 0 && !bool_pir) {
                if (pir_activator == 1) { light_on(getApplicationContext()); }
                bool_pir = true;
            }
        }
    }

    private void water_note() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_home)
                .setContentTitle("Suw agdy")
                .setContentText("Esasy suw bekediji ýapyldy")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationManagerCompat.notify(0, builder.build());
    }
    private void gas_note() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_home)
                .setContentTitle("Gaz syzmasy duýuldy")
                .setContentText("Esasy gaz bekediji ýapyldy")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationManagerCompat.notify(3, builder.build());
    }
    private void gerKon_note() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_home)
                .setContentTitle("Aýna açyldy")
                .setContentText("Ýagdaýy dolandyrmak üçin basyň")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationManagerCompat.notify(3, builder.build());
    }
    private void pir_note() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_home)
                .setContentTitle("Hereket anyklanyldy")
                .setContentText("Ýagdaýy dolandyrmak üçin basyň")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        notificationManagerCompat.notify(3, builder.build());
    }

    //  Give attention !!!  When setting is updated, program will ask you about confirm it
    @Override
    protected void onResume() {
        super.onResume();
        if (recreateAct == 1) {
            AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Sazlamalar")
                    .setMessage("Sazlamalar üýtgedildi")
                    .setCancelable(false)
                    .setPositiveButton("Tassykla", (dialogInterface, i) -> recreate())
                    .create();
            dialog.show();
        }
//        sd.start(sm);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        sd.stop();
    }
}
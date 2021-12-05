package com.unity.tabletgorjav;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import static com.unity.tabletgorjav.Commands.air_power_off;
import static com.unity.tabletgorjav.Commands.air_power_on;
import static com.unity.tabletgorjav.Commands.gasValve_off;
import static com.unity.tabletgorjav.Commands.gasValve_on;
import static com.unity.tabletgorjav.Commands.light_off;
import static com.unity.tabletgorjav.Commands.light_on;
import static com.unity.tabletgorjav.Commands.stoveActive;
import static com.unity.tabletgorjav.Commands.stoveInactive;
import static com.unity.tabletgorjav.Commands.tv_power;
import static com.unity.tabletgorjav.Commands.water_off;
import static com.unity.tabletgorjav.Commands.water_on;
import static com.unity.tabletgorjav.Commands.water_sensor_off;

public class SettingsActivity extends Activity {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch sw_all, sw_light, sw_air, sw_tv, sw_stove, sw_SmsAct, sw_NoteAct, sw_PirAct, GasValve, WaterValve;
    int st_all, st_light, st_air, st_tv, st_stove;
    ConstraintLayout expandableView;
    Button arrowBtn;
    CardView cardView;
    int st_gas_valve, st_water_valve;
    TextView js_water, json_water, js_gerKon, json_gerKon, js_door, json_door; //   js -> clicked;      json -> showed
    TextView cl_phone, click_phone, cl_sms, click_sms;  //  cl -> clicked;      clicked -> showed
    TextView js_delayTime, json_delayTime;
    TextView js_note, json_note;
    TextView js_pir, json_pir;
    EditText editTextField;
    String editTextInput;
    int IDReplace;
    int num_water, num_gerKon, num_door;
    int num_phone, state_sms;
    int time_delay, state_note, state_pir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // declare variables
        GasValve = findViewById(R.id.gas_valve);
        WaterValve = findViewById(R.id.water_valve);

        sw_SmsAct = findViewById(R.id.sw_smsAct);
        sw_NoteAct = findViewById(R.id.sw_noteAct);
        sw_PirAct = findViewById(R.id.sw_pirAct);

        js_water = findViewById(R.id.water_js);
        json_water = findViewById(R.id.water_json);
        js_gerKon = findViewById(R.id.gerkon_js);
        json_gerKon = findViewById(R.id.gerkon_json);
        js_door = findViewById(R.id.door_js);
        json_door = findViewById(R.id.door_json);

        js_delayTime = findViewById(R.id.delay_js);
        json_delayTime = findViewById(R.id.delay_json);

        js_note = findViewById(R.id.note_js);
        json_note = findViewById(R.id.note_json);

        js_pir = findViewById(R.id.pir_js);
        json_pir = findViewById(R.id.pir_json);

        cl_phone = findViewById(R.id.txtPhone);
        click_phone = findViewById(R.id.phoneNumber);
        cl_sms = findViewById(R.id.txtActivator);
        click_sms = findViewById(R.id.smsActivator);

        // Switch of devices
        sw_all = findViewById(R.id.all_sw);
        sw_light = findViewById(R.id.light_sw);
        sw_air = findViewById(R.id.air_sw);
        sw_tv = findViewById(R.id.tv_sw);
        sw_stove = findViewById(R.id.stove_sw);
        // Expandable panel of DEVICES
        expandableView = findViewById(R.id.expandableView);
        arrowBtn = findViewById(R.id.arrowBtn);
        cardView = findViewById(R.id.cardView);

        // load data from PrefConfig
        num_water = PrefConfig.loadRowWater(this);
        num_gerKon = PrefConfig.loadRowGerKon(this);
        num_door = PrefConfig.loadRowDoor(this);
        time_delay = PrefConfig.loadTime(this);
        state_note = PrefConfig.loadStateNote(this);
        state_pir = PrefConfig.loadPirLight(this);
        num_phone = PrefConfig.loadPhone(this);
        state_sms = PrefConfig.loadStateSms(this);
        // get states
        st_all = PrefConfig.loadStateAll(this);
        st_light = PrefConfig.loadStateLight(this);
        st_tv = PrefConfig.loadStateTv(this);
        st_stove = PrefConfig.loadStateStove(this);
        st_air = PrefConfig.loadStateAc(this);
        st_gas_valve = PrefConfig.loadGasValve(this);
        st_water_valve = PrefConfig.loadWaterValve(this);

        // setting text to TextView
        json_water.setText(String.valueOf(num_water));
        json_gerKon.setText(String.valueOf(num_gerKon));
        json_door.setText(String.valueOf(num_door));
        json_delayTime.setText(String.valueOf(time_delay));
        click_phone.setText(String.valueOf(num_phone));

        if (st_gas_valve == 1) {
            GasValve.setChecked(true);
        } else if (st_gas_valve == 0){
            GasValve.setChecked(false);
        }
        if (st_water_valve == 1) {
            WaterValve.setChecked(true);
        } else if (st_water_valve == 0){
            WaterValve.setChecked(false);
        }
        if (state_sms == 1) {
            sw_SmsAct.setChecked(true);
        } else if (state_sms == 0){
            sw_SmsAct.setChecked(false);
        }
        if (state_note == 1) {
            sw_NoteAct.setChecked(true);
        } else if (state_note == 0){
            sw_NoteAct.setChecked(false);
        }
        if (state_pir == 1) {
            sw_PirAct.setChecked(true);
        } else if (state_pir == 0){
            sw_PirAct.setChecked(false);
        }

        // set switches with last states
        if (st_all == 1) { sw_all.setChecked(true); } else if (st_all == 0){ sw_all.setChecked(false); }
        if (st_light == 1) { sw_light.setChecked(true); } else if (st_light == 0){ sw_light.setChecked(false); }
        if (st_tv == 1) { sw_tv.setChecked(true); } else if (st_tv == 0){ sw_tv.setChecked(false); }
        if (st_air == 1) { sw_air.setChecked(true); } else if (st_air == 0){ sw_air.setChecked(false); }
        if (st_stove == 1) { sw_stove.setChecked(true); } else if (st_stove == 0){ sw_stove.setChecked(false); }

        GasValve.setOnClickListener(v -> {
            if (GasValve.isChecked())
                gasValve_on(getApplicationContext());
            else
                gasValve_off(getApplicationContext());
        });
        WaterValve.setOnClickListener(v -> {
            if (WaterValve.isChecked()) {
                water_off(getApplicationContext());
                water_sensor_off(getApplicationContext());
            }
            else {
                water_on(getApplicationContext());
            }
        });
        sw_SmsAct.setOnClickListener(v -> {
            if (sw_SmsAct.isChecked()) { state_sms = 1; }
            else { state_sms = 0; }
            PrefConfig.saveStateSms(getApplicationContext(), state_sms);
        });
        sw_NoteAct.setOnClickListener(v -> {
            if (sw_NoteAct.isChecked()) { state_note = 1; }
            else {  state_note = 0; }
            PrefConfig.saveStateNote(getApplicationContext(), state_note);
        });
        sw_PirAct.setOnClickListener(v -> {
            if (sw_PirAct.isChecked()) { state_pir = 1; }
            else { state_pir = 0; }
            PrefConfig.savePirLight(getApplicationContext(), state_pir);
        });

        // Expand and collapse panel with button
        arrowBtn.setOnClickListener(v -> {
            if (expandableView.getVisibility()==View.GONE){
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                expandableView.setVisibility(View.VISIBLE);
            } else {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                expandableView.setVisibility(View.GONE);
            }
        });
        // turn on or off all activated devices with only one button
        sw_all.setOnClickListener(v -> {
            if (sw_all.isChecked()) {
                on_swl();
                on_swt();
                on_swa();
                on_sws();
                st_all = 1;
            } else {
                off_swl();
                off_swt();
                off_swa();
                off_sws();
                st_all = 0;
            }
            PrefConfig.saveStateAll(getApplicationContext(), st_all);
            PrefConfig.saveStateLight(getApplicationContext(), st_light);
            PrefConfig.saveStateTv(getApplicationContext(), st_tv);
            PrefConfig.saveStateAc(getApplicationContext(), st_air);
            PrefConfig.saveStateStove(getApplicationContext(), st_stove);
        });
        // if device checked (activated) -> turn on or off
        sw_light.setOnClickListener(v -> {
            if (sw_light.isChecked()) { st_light = 1; } else { st_light = 0; }
            PrefConfig.saveStateLight(getApplicationContext(), st_light);
        });
        sw_tv.setOnClickListener(v -> {
            if (sw_tv.isChecked()) { st_tv = 1; } else { st_tv = 0; }
            PrefConfig.saveStateTv(getApplicationContext(), st_tv);
        });
        sw_air.setOnClickListener(v -> {
            if (sw_air.isChecked()) { st_air = 1; } else { st_air = 0; }
            PrefConfig.saveStateAc(getApplicationContext(), st_air);
        });
        sw_stove.setOnClickListener(v -> {
            if (sw_stove.isChecked()) { st_stove = 1; } else { st_stove = 0; }
            PrefConfig.saveStateStove(getApplicationContext(), st_stove);
        });

        // Only one onClick Listener for all Material buttons
        @SuppressLint("NonConstantResourceId") View.OnClickListener onClickListener = v -> {
            switch (v.getId()) {
                case R.id.water_js:
                    IDReplace = 0;
                    dialog();
                    break;
                case R.id.gerkon_js:
                    IDReplace = 1;
                    dialog();
                    break;
                case R.id.door_js:
                    IDReplace = 2;
                    dialog();
                    break;
                case R.id.txtPhone:
                    IDReplace = 3;
                    dialog();
                    break;
                case R.id.delay_js:
                    IDReplace = 5;
                    dialog();
                    break;
            }
        };

        js_water.setOnClickListener(onClickListener);
        js_gerKon.setOnClickListener(onClickListener);
        js_door.setOnClickListener(onClickListener);
        js_delayTime.setOnClickListener(onClickListener);
        cl_phone.setOnClickListener(onClickListener);
    }

    // already activated devices send data to Raspberry Pi4 4Gb
    public void on_swl() {          // light
        if (sw_light.isChecked())
            light_on(this);
    }
    public void off_swl() {
        if (sw_light.isChecked())
            light_off(this);
    }
    public void on_swt() {          // tv
        if (sw_tv.isChecked())
            tv_power(this);
    }
    public void off_swt() {
        if (sw_tv.isChecked())
            tv_power(this);
    }
    public void on_swa() {          // air conditioner
        if (sw_air.isChecked())
            air_power_on(this);
    }
    public void off_swa() {
        if (sw_air.isChecked())
            air_power_off(this);
    }
    public void on_sws() {          // stove
        if (sw_stove.isChecked())
            stoveActive(this);
    }
    public void off_sws() {
        if (sw_stove.isChecked())
            stoveInactive(this);
    }
    public void dialog() {
        editTextField = new EditText(SettingsActivity.this);
        AlertDialog dialog = new AlertDialog.Builder(SettingsActivity.this)
                .setTitle(R.string.command)
                .setMessage(R.string.m5)
                .setView(editTextField)
                .setPositiveButton(R.string.confirm, (dialogInterface, i) -> {
                    editTextInput = editTextField.getText().toString();
                    int number = Integer.parseInt(editTextInput);
                    if (IDReplace == 0) {
                        json_water.setText(editTextField.getText().toString());
                        PrefConfig.saveRowWater(getApplicationContext(), number);
                    } else if (IDReplace == 1) {
                        json_gerKon.setText(editTextField.getText().toString());
                        PrefConfig.saveRowGerKon(getApplicationContext(), number);
                    } else if (IDReplace == 2) {
                        json_door.setText(editTextField.getText().toString());
                        PrefConfig.saveRowDoor(getApplicationContext(), number);
                    } else if (IDReplace == 3) {
                        click_phone.setText(editTextField.getText().toString());
                        PrefConfig.savePhone(getApplicationContext(), number);
                    }  else if (IDReplace == 5) {
                        json_delayTime.setText(editTextField.getText().toString());
                        PrefConfig.saveTime(getApplicationContext(), number);
                    }
                    Log.d("onclick","editText value is: "+ number);
                    Toast.makeText(SettingsActivity.this, R.string.IDUpdated, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
        dialog.show();
    }
}
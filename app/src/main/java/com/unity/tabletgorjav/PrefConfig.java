package com.unity.tabletgorjav;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefConfig {
    private static final String MY_PREFERENCE_NAME = "com.iot.smart_home";
    private static final String PREF_HTTP_KEY = "pref_http_key";
    private static final String PREF_IP_KEY = "pref_ip_key";
    private static final String PREF_PORT_KEY = "pref_port_key";
    private static final String PREF_ZERO_PARAM_KEY = "pref_zero_param_key";
    private static final String PREF_FIRST_PARAM_KEY = "pref_first_param_key";
    private static final String PREF_SECOND_PARAM_KEY = "pref_second_param_key";
    private static final String PREF_STALL_KEY = "pref_stall_key";
    private static final String PREF_STL_KEY = "pref_stl_key";
    private static final String PREF_STT_KEY = "pref_stt_key";
    private static final String PREF_STA_C_KEY = "pref_sta_c_key";
    private static final String PREF_STOVE_KEY = "pref_stove_key";
//    private static final String PREF_CURT_TIME_KEY = "pref_curt_time_key";
    private static final String PREF_WATER_KEY = "pref_water_key";
    private static final String PREF_GER_KON_KEY = "pref_ger_kon_key";
    private static final String PREF_DOOR_KEY = "pref_door_key";
    private static final String PREF_PHONE_KEY = "pref_phone_key";
    private static final String PREF_SMS_KEY = "pref_sms_key";
    private static final String PREF_TIME_KEY = "pref_time_key";
    private static final String PREF_NOTIFICATION_KEY = "pref_notification_key";
//    private static final String PREF_WATT_VALUE_KEY = "pref_watt_value_key";
//    private static final String PREF_WATER_VALUE_KEY = "pref_water_value_key";
    private static final String PREF_WATER_VALVE_KEY = "pref_water_valve_key";
    private static final String PREF_GAS_VALVE_KEY = "pref_gas_valve_key";
    private static final String PREF_PIR_KEY = "pref_pir_key";
    private static final String PREF_SECURITY_KEY = "pref_security_key";
    private static final String PREF_NO_SECURITY_KEY = "pref_no_security_key";
    private static final String PREF_LIGHT1_KEY = "pref_light1_key";
    private static final String PREF_LIGHT2_KEY = "pref_light2_key";
    private static final String PREF_LIGHT3_KEY = "pref_light3_key";
    private static final String PREF_AIR_KEY = "pref_air_key";
    private static final String PREF_AIR_SPEED1_KEY = "pref_air_speed1_key";
    private static final String PREF_AIR_SPEED2_KEY = "pref_air_speed2_key";
    private static final String PREF_AIR_SPEED3_KEY = "pref_air_speed3_key";
    private static final String PREF_CHANGE_KEY = "pref_change_key";
    private static final String PREF_SECRET_KEY = "pref_secret_key";
    private static final String PREF_AIRTIME_KEY = "pref_airtime_key";
    private static final String PREF_SOCK_TIME_KEY = "pref_sock_time_key";
    private static final String PREF_STOVE1_PRO_KEY = "pref_stove1_pro_key";
    private static final String PREF_STOVE2_PRO_KEY = "pref_stove2_pro_key";
    private static final String PREF_WEB_KEY = "pref_web_key";


    // for HTTP or HTTPS
    public static void saveHttpPref(Context context, String http) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_HTTP_KEY, http);
        editor.apply();
    }
    public static String loadHttpPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_HTTP_KEY, "http");
    }

  // for IP address
    public static void saveIpPref(Context context, String ip) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_IP_KEY, ip);
        editor.apply();
    }
    public static String loadIpPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_IP_KEY, "95.85.112.74");
    }

    // for PORT
    public static void savePORTPref(Context context, String port) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_PORT_KEY, port);
        editor.apply();
    }
    public static String loadPORTPref(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_PORT_KEY, "35123");
    }

    // for Zero Parameter (null)
    public static void saveZeroParam(Context context, String zeroParam) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_ZERO_PARAM_KEY, zeroParam);
        editor.apply();
    }
    public static String loadZeroParam(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_ZERO_PARAM_KEY, "");
    }

    // for First Parameter (esp)
    public static void saveFirstParam(Context context, String firstParam) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_FIRST_PARAM_KEY, firstParam);
        editor.apply();
    }
    public static String loadFirstParam(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_FIRST_PARAM_KEY, "esp");
    }

    // for Second Parameter (JsonToArg)
    public static void saveSecondParam(Context context, String secondParam) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_SECOND_PARAM_KEY, secondParam);
        editor.apply();
    }
    public static String loadSecondParam(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_SECOND_PARAM_KEY, "JsonToArg");
    }

    // for all status
    public static void saveStateAll(Context context, int stall) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_STALL_KEY, stall);
        editor.apply();
    }
    public static int loadStateAll(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_STALL_KEY, 0);
    }

    // for light
    public static void saveStateLight(Context context, int stl) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_STL_KEY, stl);
        editor.apply();
    }
    public static int loadStateLight(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_STL_KEY, 0);
    }

    // for tv
    public static void saveStateTv(Context context, int stt) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_STT_KEY, stt);
        editor.apply();
    }
    public static int loadStateTv(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_STT_KEY, 0);
    }

    // for A/C
    public static void saveStateAc(Context context, int sta_c) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_STA_C_KEY, sta_c);
        editor.apply();
    }
    public static int loadStateAc(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_STA_C_KEY, 0);
    }

    // for Stove
    public static void saveStateStove(Context context, int stove) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_STOVE_KEY, stove);
        editor.apply();
    }
    public static int loadStateStove(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_STOVE_KEY, 0);
    }

    // for Curtain time
//    public static void saveStateCurtTime(Context context, int curtain) {
//        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putInt(PREF_CURT_TIME_KEY, curtain);
//        editor.apply();
//    }
//    public static int loadStateCurtTime(Context context) {
//        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        return pref.getInt(PREF_CURT_TIME_KEY, 500);
//    }

    // for DeviceConfig (water -> row)
    public static void saveRowWater(Context context, int water) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_WATER_KEY, water);
        editor.apply();
    }
    public static int loadRowWater(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_WATER_KEY, 11);
    }

    // for DeviceConfig (gerKon -> row)
    public static void saveRowGerKon(Context context, int gerKon) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_GER_KON_KEY, gerKon);
        editor.apply();
    }
    public static int loadRowGerKon(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_GER_KON_KEY, 12);
    }

    // for DeviceConfig (Door -> row)
    public static void saveRowDoor(Context context, int door) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_DOOR_KEY, door);
        editor.apply();
    }
    public static int loadRowDoor(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_DOOR_KEY, 8);
    }

    // for DeviceConfig (phone number)
    public static void savePhone(Context context, int phone) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_PHONE_KEY, phone);
        editor.apply();
    }
    public static int loadPhone(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_PHONE_KEY, 62377995);
    }

    // for DeviceConfig (sms activator)
    public static void saveStateSms(Context context, int sms) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_SMS_KEY, sms);
        editor.apply();
    }
    public static int loadStateSms(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_SMS_KEY, 0);
    }

    // for GetData from Raspberry Pi4
    public static void saveTime(Context context, int time) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_TIME_KEY, time);
        editor.apply();
    }
    public static int loadTime(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_TIME_KEY, 5000);
    }

    // for DeviceConfig (notification activator)
    public static void saveStateNote(Context context, int note) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_NOTIFICATION_KEY, note);
        editor.apply();
    }
    public static int loadStateNote(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_NOTIFICATION_KEY, 0);
    }

    // for Watt value
//    public static void saveWattValue(Context context, int watt) {
//        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putInt(PREF_WATT_VALUE_KEY, watt);
//        editor.apply();
//    }
//    public static int loadWattValue(Context context) {
//        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        return pref.getInt(PREF_WATT_VALUE_KEY, 0);
//    }

    // for Water value
//    public static void saveWaterValue(Context context, int water) {
//        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putInt(PREF_WATER_VALUE_KEY, water);
//        editor.apply();
//    }
//    public static int loadWaterValue(Context context) {
//        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        return pref.getInt(PREF_WATER_VALUE_KEY, 0);
//    }

    // for Water valve
    public static void saveWaterValve(Context context, int waterValve) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_WATER_VALVE_KEY, waterValve);
        editor.apply();
    }
    public static int loadWaterValve(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_WATER_VALVE_KEY, 0);
    }

    // for Gas valve
    public static void saveGasValve(Context context, int gasValve) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_GAS_VALVE_KEY, gasValve);
        editor.apply();
    }
    public static int loadGasValve(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_GAS_VALVE_KEY, 0);
    }

    // for PIR (light)
    public static void savePirLight(Context context, int pirLight) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_PIR_KEY, pirLight);
        editor.apply();
    }
    public static int loadPirLight(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_PIR_KEY, 0);
    }

    // for  goragly we adaty
    public static void saveSecurity(Context context, int security) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_SECURITY_KEY, security);
        editor.apply();
    }
    public static int loadSecurity(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_SECURITY_KEY, 0);
    }

    // for  goragly we adaty
    public static void saveNoSecurity(Context context, int no_security) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_NO_SECURITY_KEY, no_security);
        editor.apply();
    }
    public static int loadNoSecurity(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_NO_SECURITY_KEY, 0);
    }
    // for  light mode 1
    public static void saveLight1(Context context, int light1) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_LIGHT1_KEY, light1);
        editor.apply();
    }
    public static int loadLight1(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_LIGHT1_KEY, 0);
    }
    // for  light mode 2
    public static void saveLight2(Context context, int light2) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_LIGHT2_KEY, light2);
        editor.apply();
    }
    public static int loadLight2(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_LIGHT2_KEY, 0);
    }
    // for  light mode 3
    public static void saveLight3(Context context, int light3) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_LIGHT3_KEY, light3);
        editor.apply();
    }
    public static int loadLight3(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_LIGHT3_KEY, 0);
    }
    // for  air conditioner on/off
    public static void saveAir(Context context, int air) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_AIR_KEY, air);
        editor.apply();
    }
    public static int loadAir(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_AIR_KEY, 0);
    }

    // for  change key (using for one time login)
    public static void saveChange(Context context, int change) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_CHANGE_KEY, change);
        editor.apply();
    }
    public static int loadChange(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_CHANGE_KEY, 0);
    }

    // for secret key
    public static void saveSecretKey(Context context, String secret) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_SECRET_KEY, secret);
        editor.apply();
    }
    public static String loadSecretKey(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_SECRET_KEY, "");
    }
    // for air setting time
    public static void saveAIRTimeKey(Context context, String airTime) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_AIRTIME_KEY, airTime);
        editor.apply();
    }
    public static String loadAIRTimeKey(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_AIRTIME_KEY, "");
    }
    // for socket setting time
    public static void saveSOCKTimeKey(Context context, String sockTime) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_SOCK_TIME_KEY, sockTime);
        editor.apply();
    }
    public static String loadSOCKTimeKey(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_SOCK_TIME_KEY, "");
    }
    // for  stove A progress bar
    public static void saveProST1(Context context, int stove1) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_STOVE1_PRO_KEY, stove1);
        editor.apply();
    }
    public static int loadProST1(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_STOVE1_PRO_KEY, 0);
    }
    // for  stove B progress bar
    public static void saveProST2(Context context, int stove2) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_STOVE2_PRO_KEY, stove2);
        editor.apply();
    }
    public static int loadProST2(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_STOVE2_PRO_KEY, 0);
    }


    // for  air conditioner speed 1
    public static void saveAirSpeed1(Context context, int air_speed1) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_AIR_SPEED1_KEY, air_speed1);
        editor.apply();
    }
    public static int loadAirSpeed1(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_AIR_SPEED1_KEY, 0);
    }


    // for  air conditioner speed 2
    public static void saveAirSpeed2(Context context, int air_speed2) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_AIR_SPEED2_KEY, air_speed2);
        editor.apply();
    }
    public static int loadAirSpeed2(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_AIR_SPEED2_KEY, 0);
    }


    // for  air conditioner speed 3
    public static void saveAirSpeed3(Context context, int air_speed3) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_AIR_SPEED3_KEY, air_speed3);
        editor.apply();
    }
    public static int loadAirSpeed3(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getInt(PREF_AIR_SPEED3_KEY, 0);
    }

    // for webView (video stream)
    public static void saveWeb(Context context, String web) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_WEB_KEY, web);
        editor.apply();
    }
    public static String loadWeb(Context context) {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.getString(PREF_WEB_KEY, "192.168.1.15");
    }

}
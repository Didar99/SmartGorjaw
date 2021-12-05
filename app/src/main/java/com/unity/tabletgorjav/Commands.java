package com.unity.tabletgorjav;
import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class Commands {
    static String ip_address, strPort, strZeroParam, strFirstParam, strSecondParam, strHttp;
    static void tv_power(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        String str = strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/" + strSecondParam + "/";
        System.out.println(str);
        Log.e("tv_power onResponse => ", str);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "command");
                jsonParam.put("action", "power");
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
    static void air_power_on(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "command");
                jsonParam.put("action", "poweron");
                pin_array.put(jsonParam);
                json.put("command", "conditioner");
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
    static void air_power_off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "command");
                jsonParam.put("action", "poweroff");
                pin_array.put(jsonParam);
                json.put("command", "conditioner");
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
    static void light_on(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
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
                json.put("command", "three_mode_switch");
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
    }    // Send json data to ESP 8266 module  -> in Browser you can type like [ip_address/control/pin1=1 or pin2=1 or pin3=1 <-> pin1=0 or pin2=0 or pin3=0]
    static void light_off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
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
                json.put("command", "three_mode_switch");
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
    static void stoveActive(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "auto_manual_switch");
                jsonParam.put("action", "manual");
                pin_array.put(jsonParam);
                json.put("command", "smart_stove");
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
    static void stoveInactive(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "auto_manual_switch");
                jsonParam.put("action", "auto");
                pin_array.put(jsonParam);
                json.put("command", "smart_stove");
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
    static void stove1off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();

                jsonParam.put("command", "cooker1");
                jsonParam.put("action", "0");
                pin_array.put(jsonParam);
                json.put("command", "smart_stove");
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
    static void stove2off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();

                jsonParam.put("command", "cooker2");
                jsonParam.put("action", "0");
                pin_array.put(jsonParam);
                json.put("command", "smart_stove");
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
    static void fingerPrintLock(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "unlock_door");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    static void fingerPrintUnLock(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "unlock_door");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    static void fingerPrintAfterLock(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "unlock_door");
                jsonParam.put("state", "2");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    static void water_on(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "water_pump");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    static void water_off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "water_pump");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    static void water_sensor_off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "water_sensor_van");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    }   // water leak sensor -> for turn off
    static void gas_sensor_off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "gas_sensor");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    }   // gas leak sensor -> for turn off
    static void gerKon_sensor_off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "ping_gerkon");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    }   // gerKon sensor -> for turn off
    static void curtain_up(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "curtain");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    static void curtain_down(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "curtain");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    static void curtain_stop(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "curtain");
                jsonParam.put("state", "2");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    static void gasValve_on(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "gas_sensor");
                jsonParam.put("state", "0");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    static void gasValve_off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(strHttp + "://" + ip_address + ":" + strPort + "/" + strZeroParam + strFirstParam + "/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("command", "gas_sensor");
                jsonParam.put("state", "1");
                jsonParam.put("action", "");
                Log.i("JSON", jsonParam.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(jsonParam.toString());
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
    static void socket1_on(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "socket1");
                jsonParam.put("action", "1");
                pin_array.put(jsonParam);
                json.put("command", "socket");
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
    }    // Send json data to ESP 8266 module  -> in Browser you can type like [ip_address/control/socket1=1 or socket2=1 or socket3=1 <-> socket1=0 or socket2=0 or socket3=0]
    static void socket1_off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "socket1");
                jsonParam.put("action", "0");
                pin_array.put(jsonParam);
                json.put("command", "socket");
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
    static void socket2_on(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "socket2");
                jsonParam.put("action", "1");
                pin_array.put(jsonParam);
                json.put("command", "socket");
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
    static void socket2_off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "socket2");
                jsonParam.put("action", "0");
                pin_array.put(jsonParam);
                json.put("command", "socket");
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
    static void socket3_on(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "socket3");
                jsonParam.put("action", "1");
                pin_array.put(jsonParam);
                json.put("command", "socket");
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
    static void socket3_off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "socket3");
                jsonParam.put("action", "0");
                pin_array.put(jsonParam);
                json.put("command", "socket");
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

    static void air_on(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "mode_low");
                jsonParam.put("action", "1");
                jsonParam2.put("command", "mode_med");
                jsonParam2.put("action", "0");
                jsonParam3.put("command", "mode_high");
                jsonParam3.put("action", "0");
                pin_array.put(jsonParam);
                pin_array.put(jsonParam2);
                pin_array.put(jsonParam3);
                json.put("command", "conditioner_main");
                json.put("pins", pin_array);
                Log.i("JSON", json.toString());
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(json.toString());
                os.flush();
                os.close();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    conn.disconnect();
                }
                Log.e("INTERNET LIGHT => ", String.valueOf(url));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    static void air_off(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "mode_low");
                jsonParam.put("action", "0");
                jsonParam2.put("command", "mode_med");
                jsonParam2.put("action", "0");
                jsonParam3.put("command", "mode_high");
                jsonParam3.put("action", "0");
                pin_array.put(jsonParam);
                pin_array.put(jsonParam2);
                pin_array.put(jsonParam3);
                json.put("command", "conditioner_main");
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
    static void airSpeed1(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "mode_low");
                jsonParam.put("action", "1");
                jsonParam2.put("command", "mode_med");
                jsonParam2.put("action", "0");
                jsonParam3.put("command", "mode_high");
                jsonParam3.put("action", "0");
                pin_array.put(jsonParam);
                pin_array.put(jsonParam2);
                pin_array.put(jsonParam3);
                json.put("command", "conditioner_main");
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
    static void airSpeed2(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "mode_low");
                jsonParam.put("action", "0");
                jsonParam2.put("command", "mode_med");
                jsonParam2.put("action", "1");
                jsonParam3.put("command", "mode_high");
                jsonParam3.put("action", "0");
                pin_array.put(jsonParam);
                pin_array.put(jsonParam2);
                pin_array.put(jsonParam3);
                json.put("command", "conditioner_main");
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
    static void airSpeed3(Context context) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // load data from PrefConfig
        strPort = PrefConfig.loadPORTPref(context);
        strZeroParam = PrefConfig.loadZeroParam(context);
        strFirstParam = PrefConfig.loadFirstParam(context);
        strSecondParam = PrefConfig.loadSecondParam(context);
        strHttp = PrefConfig.loadHttpPref(context);
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
                JSONObject json = new JSONObject();
                JSONArray pin_array = new JSONArray();
                jsonParam.put("command", "mode_low");
                jsonParam.put("action", "0");
                jsonParam2.put("command", "mode_med");
                jsonParam2.put("action", "0");
                jsonParam3.put("command", "mode_high");
                jsonParam3.put("action", "1");
                pin_array.put(jsonParam);
                pin_array.put(jsonParam2);
                pin_array.put(jsonParam3);
                json.put("command", "conditioner_main");
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
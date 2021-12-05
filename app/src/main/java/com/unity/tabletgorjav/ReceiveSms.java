package com.unity.tabletgorjav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;
import static com.unity.tabletgorjav.Commands.air_power_off;
import static com.unity.tabletgorjav.Commands.air_power_on;
import static com.unity.tabletgorjav.Commands.fingerPrintAfterLock;
import static com.unity.tabletgorjav.Commands.gasValve_off;
import static com.unity.tabletgorjav.Commands.gasValve_on;
import static com.unity.tabletgorjav.Commands.light_off;
import static com.unity.tabletgorjav.Commands.light_on;
import static com.unity.tabletgorjav.Commands.socket1_off;
import static com.unity.tabletgorjav.Commands.socket1_on;
import static com.unity.tabletgorjav.Commands.socket2_off;
import static com.unity.tabletgorjav.Commands.socket2_on;
import static com.unity.tabletgorjav.Commands.socket3_off;
import static com.unity.tabletgorjav.Commands.socket3_on;
import static com.unity.tabletgorjav.Commands.stoveActive;
import static com.unity.tabletgorjav.Commands.stoveInactive;
import static com.unity.tabletgorjav.Commands.tv_power;
import static com.unity.tabletgorjav.Commands.water_off;
import static com.unity.tabletgorjav.Commands.water_on;

public class ReceiveSms extends BroadcastReceiver {
    String ip_address;
    String msgBody;
    int phoneNumber;
    @Override
    public void onReceive(Context context, Intent intent) {
        // IP-Address load from pref_config
        ip_address = PrefConfig.loadIpPref(context);
        // Phone Number load from pref_config
        phoneNumber = PrefConfig.loadPhone(context);

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs;
            String msg_from;
            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        msgBody = msgs[i].getMessageBody();

                        if (msg_from.equals("+993" + phoneNumber)) {

                            switch (msgBody) {
                                case "wateron":
                                    water_on(context);
                                    break;
                                case "wateroff":
                                    water_off(context);
                                    break;
                                case "lighton":
                                    light_on(context);
                                    break;
                                case "lightoff":
                                    light_off(context);
                                    break;
                                case "tvpower":
                                    tv_power(context);
                                    break;
                                case "airon":
                                    air_power_on(context);
                                    break;
                                case "airoff":
                                    air_power_off(context);
                                    break;
                                case "stoveon":
                                    stoveActive(context);
                                    break;
                                case "stoveoff":
                                    stoveInactive(context);
                                    break;
                                case "gason":
                                    gasValve_on(context);
                                    break;
                                case "gasoff":
                                    gasValve_off(context);
                                    break;
                                case "gapy":
                                    fingerPrintAfterLock(context);
                                    break;
                                case "socket1on":
                                    socket1_on(context);
                                    break;
                                case "socket1off":
                                    socket1_off(context);
                                    break;
                                case "socket2on":
                                    socket2_on(context);
                                    break;
                                case "socket2off":
                                    socket2_off(context);
                                    break;
                                case "socket3on":
                                    socket3_on(context);
                                    break;
                                case "socket3off":
                                    socket3_off(context);
                                    break;
                            }
                        }
                        Toast.makeText(context, "Tarap: " + msg_from + "   Tekst: " + msgBody, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
package com.unity.tabletgorjav;

import android.app.Activity;
import android.os.Bundle;
import io.github.controlwear.virtual.joystick.android.JoystickView;
import static com.unity.tabletgorjav.Commands.curtain_down;
import static com.unity.tabletgorjav.Commands.curtain_stop;
import static com.unity.tabletgorjav.Commands.curtain_up;

public class CurtainActivity extends Activity {
    JoystickView joystick;
    boolean joystick_pos = false;
    int direction = 0;          // 0 -> stop, 1 -> up, 2 -> down
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curtain);
        // JoyStick (for control curtain UP AND DOWN)
        joystick = findViewById(R.id.joystickView);

        joystick.setOnMoveListener((angle, strength) -> {   //System.out.println("angle is : " + angle); System.out.println("strength is : " + strength);
            // do whatever you want
            if (strength == 0) {
                direction = 0;
                joystick_pos = false;
                curtain_stop(getApplicationContext());
                System.out.println("stopped");
            }
            if (!joystick_pos) {
                if (angle == 90 && strength >= 70) {
                    direction = 1;
                    joystick_pos = true;
                    curtain_up(getApplicationContext());
                    System.out.println("up");
                }
            }
            if (!joystick_pos) {
                if (angle == 270 && strength >= 70) {
                    direction = 2;
                    joystick_pos = true;
                    curtain_down(getApplicationContext());
                    System.out.println("down");
                }
            }
        });
    }
}
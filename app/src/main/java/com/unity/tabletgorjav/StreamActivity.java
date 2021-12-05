package com.unity.tabletgorjav;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class StreamActivity extends Activity {
    EditText editTextField;
    String editTextInput;
    String webIP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        webIP = PrefConfig.loadWeb(getApplicationContext());

        WebView webView = findViewById(R.id.web);
        webView.loadUrl("http://" + webIP);
    }
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            System.out.println("Back button long pressed");
            editTextField = new EditText(StreamActivity.this);
            AlertDialog dialog = new AlertDialog.Builder(StreamActivity.this)
                    .setTitle("IP salgysy")
                    .setMessage("M: 192.168.1.184")
                    .setView(editTextField)
                    .setPositiveButton("Tassykla", (dialogInterface, i) -> {
                        editTextInput = editTextField.getText().toString();
                        PrefConfig.saveWeb(getApplicationContext(), editTextInput);
                        Log.d("onclick","editText value is: "+ editTextInput);
                        Toast.makeText(StreamActivity.this, "IP salgysy täzelendi", Toast.LENGTH_SHORT).show();
                        recreate();

                    })
                    .setNegativeButton("Ýatyr", null)
                    .create();
            dialog.show();
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }
}
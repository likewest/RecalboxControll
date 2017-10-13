package com.example.fabianhrst.recalboxcontroller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.os.Build.VERSION_CODES.O;
import static android.provider.Telephony.Mms.Part.FILENAME;

public class login_activity extends AppCompatActivity {
    //TODO: Variablen
    private String IP;
    EditText IP_Text;
    Button Enter;
    Button Latest_IP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hideSystemUI();
        IP_Text = (EditText) findViewById(R.id.IP);
        Enter = (Button) findViewById(R.id.Enter);
        Latest_IP = (Button) findViewById(R.id.Latest_IP);
        Latest_IP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPrefs = getSharedPreferences(FILENAME, 0);
                IP_Text.setText(sharedPrefs.getString("IP_Key", String.valueOf(Context.MODE_PRIVATE)));
            }
        });
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_ip();
                Intent intent = new Intent(login_activity.this, main.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    protected void onStop(){
        super.onStop();
        new_ip();
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void new_ip(){
        SharedPreferences sharedPrefs = getSharedPreferences(FILENAME, 0);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("IP_Key", IP_Text.getText().toString());
        editor.commit();
    }

}

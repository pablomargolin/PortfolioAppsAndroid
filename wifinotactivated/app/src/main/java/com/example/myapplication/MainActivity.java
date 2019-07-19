package com.example.myapplication;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnEmpezar;
    static final long TIME_IN_MILI = 5000;
    CountDownTimer timer;
    boolean isTimerRunning;
    long timeLeft;
    WifiManager wifi;

    TextView txtActivado;
    TextView txtDesactivado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        
        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isTimerRunning)
                    ActivateApp();
                else
                    DesactivateApp();
            }
        });
        
        
    }
    
    void Init(){
        btnEmpezar = findViewById(R.id.btn_empezar);
        txtActivado = findViewById(R.id.txt_activado);
        txtDesactivado = findViewById(R.id.txt_desactivado);        
        
        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        
        txtActivado.setVisibility(View.GONE);
        txtDesactivado.setVisibility(View.VISIBLE);
        
        timeLeft = TIME_IN_MILI;
        isTimerRunning = false;
    }

    @Override
    protected void onPause() {
            super.onPause();
        if (isTimerRunning){
            CreateOtherTimer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null && isTimerRunning)
            timer.cancel();
        timeLeft = TIME_IN_MILI;
    }

    void CreateOtherTimer(){

        timer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long l) {
                //Toast.makeText(getApplicationContext(), "Empezo a correr el timer!", Toast.LENGTH_SHORT).show();
                timeLeft = l;
            }

            @Override
            public void onFinish() {
                TimerInZero();
            }
        }.start();

    }

    void ActivateApp(){
        txtActivado.setVisibility(View.VISIBLE);
        txtDesactivado.setVisibility(View.GONE);

        isTimerRunning = true;

        moveTaskToBack(true);
        Toast.makeText(getApplicationContext(), "Aplicación activada!", Toast.LENGTH_SHORT).show();
    }

    void DesactivateApp(){
        txtActivado.setVisibility(View.GONE);
        txtDesactivado.setVisibility(View.VISIBLE);

        isTimerRunning = false;

        moveTaskToBack(false);
        Toast.makeText(getApplicationContext(), "Aplicación desactivada", Toast.LENGTH_SHORT).show();
    }
    void VibrateDevice(int mili){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(mili);

    }

    void TimerInZero(){
        timeLeft = TIME_IN_MILI;
        CreateOtherTimer();
        if (wifi.getConnectionInfo().getNetworkId() == -1){
            VibrateDevice(1000);
            Toast.makeText(getApplicationContext(), "WiFi desactivado!", Toast.LENGTH_LONG).show();
        }
    }

}

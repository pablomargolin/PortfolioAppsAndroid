package com.example.payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etMonto;
    Button btnContinuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMonto = findViewById(R.id.et_monto);
        btnContinuar = findViewById(R.id.btn_continuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etMonto.getText().toString().equals("")){
                    Intent intent = new Intent(MainActivity.this, TypeCardActivity.class);
                    intent.putExtra("monto", Integer.parseInt(etMonto.getText().toString()));
                    startActivity(intent);
                }else
                    Toast.makeText(getApplicationContext(), "No ha ingresado ningun importe.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

package com.example.payment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FinalActivity extends AppCompatActivity {

    int mont;
    String bank;

    TextView txtMonto;
    TextView txtBanco;
    Button btnConfirmar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        txtMonto = findViewById(R.id.txt_importe);
        txtBanco = findViewById(R.id.txt_banco);
        btnConfirmar = findViewById(R.id.btn_confirmar);

        Intent intent = getIntent();
        mont = intent.getIntExtra("monto", 0);
        bank = intent.getStringExtra("TipoTarjeta");



        txtMonto.setText("$"+ mont);
        txtBanco.setText(bank);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Pago confirmado!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }
}

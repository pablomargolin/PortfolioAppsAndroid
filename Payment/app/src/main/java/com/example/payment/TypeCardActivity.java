package com.example.payment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.payment.Interface.TypeCardApi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TypeCardActivity extends AppCompatActivity {

    ListView listView;
    int mont;
    final String CREDIT_CARD = "credit_card";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mont = intent.getIntExtra("monto", 0);

        setContentView(R.layout.activity_type_card);
        listView = findViewById(R.id.lst_nameCards);
        getTypeCards();
    }

    private void getTypeCards() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TypeCardApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TypeCardApi api = retrofit.create(TypeCardApi.class);

        Call<List<TypeCards>> call = api.getTypeCards();

        call.enqueue(new Callback<List<TypeCards>>() {
            @Override
            public void onResponse(Call<List<TypeCards>> call, Response<List<TypeCards>> response) {
                List<TypeCards> cardsList = response.body();
                final List<TypeCards> auxiliaryList = new ArrayList<>();

                //Uso una lista auxiliar para separar los que son de tipo CREDIT_CARD
                for (int i = 0; i < cardsList.size(); i++){
                    if (cardsList.get(i).getPayment_type_id().equals(CREDIT_CARD))
                        auxiliaryList.add(cardsList.get(i));
                }

                String[] cards = new String[auxiliaryList.size()];
                String[] ids = new String[auxiliaryList.size()];

                //Cargo en la lista los nombres de las tarjetas
                for (int i = 0; i < auxiliaryList.size(); i++){
                        cards[i] = auxiliaryList.get(i).getName();
                        ids[i] = auxiliaryList.get(i).getId();
                }
                SetAdapter(cards, ids);
            }

            @Override
            public void onFailure(Call<List<TypeCards>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Configuracion de la list view
    void SetAdapter(String[] cards, String[] ids){
        final String[] listValue = cards;
        final String[] id = ids;
        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listValue));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), WichCardActivity.class);
                intent.putExtra("id",id[i]);
                intent.putExtra("monto", mont);
                startActivity(intent);

            }
        });
    }
}




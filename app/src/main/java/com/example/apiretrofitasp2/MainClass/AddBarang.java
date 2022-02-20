package com.example.apiretrofitasp2.MainClass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apiretrofitasp2.Entities.Model;
import com.example.apiretrofitasp2.R;
import com.example.apiretrofitasp2.Services.ApiClient;
import com.example.apiretrofitasp2.Services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBarang extends AppCompatActivity {

    private Button btnSave,btnCanle;
    private EditText editTextName,editTextPrice,editTextQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barang);

        editTextName = (EditText) findViewById(R.id.edtTextName);
        editTextPrice = (EditText) findViewById(R.id.edtTextPrice);
        editTextQty = (EditText) findViewById(R.id.edtTextQty);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Model model = new Model();
                    model.setName(editTextName.getText().toString());
                    model.setPrice(Integer.parseInt(editTextPrice.getText().toString()));
                    model.setQuantity(Integer.parseInt(editTextQty.getText().toString()));
                    ApiService apiService = ApiClient.getClient().create(ApiService.class);

                    Call call = apiService.create(model);
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {

                            if(response.isSuccessful()){
                                Intent intent = new Intent(AddBarang.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Gagal Menambah !",Toast.LENGTH_SHORT).show();

                        }
                    });

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCanle = (Button) findViewById(R.id.btnCancle);
        btnCanle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBarang.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
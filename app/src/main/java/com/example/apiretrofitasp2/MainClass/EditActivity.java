package com.example.apiretrofitasp2.MainClass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
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

public class EditActivity extends AppCompatActivity {

    //copas AddBarang
    private Button btnSave,btnCanle;
    private EditText editTextName,editTextPrice,editTextQty;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //copas AddBarang
        editTextName = (EditText) findViewById(R.id.edtTextName);
        editTextPrice = (EditText) findViewById(R.id.edtTextPrice);
        editTextQty = (EditText) findViewById(R.id.edtTextQty);

        //Untuk get data ke edit text
        Intent intent = getIntent();
        model = (Model) intent.getSerializableExtra("model");
        editTextName.setText(model.getName());
        editTextPrice.setText(String.valueOf(model.getPrice()));
        editTextQty.setText(String.valueOf(model.getQuantity()));

        //Save
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    model.setName(editTextName.getText().toString());
                    model.setPrice(Integer.parseInt(editTextPrice.getText().toString()));
                    model.setQuantity(Integer.parseInt(editTextQty.getText().toString()));
                    ApiService apiService = ApiClient.getClient().create(ApiService.class);

                    Call call = apiService.update(model);
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            if(response.isSuccessful()){
                                Intent intent = new Intent(EditActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                        }
                    });



                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        //copas AddBarang
        btnCanle = (Button) findViewById(R.id.btnCancle);
        btnCanle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
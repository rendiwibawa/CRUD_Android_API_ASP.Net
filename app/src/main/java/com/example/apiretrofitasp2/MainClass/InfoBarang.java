package com.example.apiretrofitasp2.MainClass;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apiretrofitasp2.Entities.Model;
import com.example.apiretrofitasp2.R;
import com.example.apiretrofitasp2.Services.ApiClient;
import com.example.apiretrofitasp2.Services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoBarang extends AppCompatActivity {

    private Button btnEdit, btnDelete, buttonCancle;
    private TextView textViewId, textViewname, textViewPrice, textViewQty;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_barang);

        textViewname = (TextView) findViewById(R.id.textViewName);
        textViewPrice = (TextView) findViewById(R.id.textViewPrice);
        textViewQty = (TextView) findViewById(R.id.textViewQty);

        //Menampilkan data untuk info
        Intent intent = getIntent();
        model = (Model) intent.getSerializableExtra("model");
        textViewname.setText(model.getName());
        textViewPrice.setText(String.valueOf(model.getPrice()));
        textViewQty.setText(String.valueOf(model.getQuantity()));

        //Button Edit
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoBarang.this,EditActivity.class);
                intent.putExtra("model",model);
                startActivity(intent);
            }
        });


        //Button delete
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Alert Dialog Konfirmasi Yes/No
                AlertDialog dialog = new AlertDialog.Builder(InfoBarang.this)
                        .setTitle("Hapus barang")
                        .setMessage("Apakah anda yakin ingin menghapus barang ini ?")
                        .setPositiveButton("Iya", null)
                        .setNegativeButton("Batal",null)
                        .show();
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Delete Data
                        try {
                            ApiService apiService = ApiClient.getClient().create(ApiService.class);
                            Call call = apiService.delete(model.getId());
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    if(response.isSuccessful()){
                                        Intent intent = new Intent(InfoBarang.this,MainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(),"Data Berhasil di Hapus",Toast.LENGTH_SHORT).show();
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

            }
        });
        buttonCancle = (Button) findViewById(R.id.buttonCancle);
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoBarang.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
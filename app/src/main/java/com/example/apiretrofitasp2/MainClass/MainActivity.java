package com.example.apiretrofitasp2.MainClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.apiretrofitasp2.Adapter.Adapter;
import com.example.apiretrofitasp2.Entities.Model;
import com.example.apiretrofitasp2.R;
import com.example.apiretrofitasp2.Services.ApiClient;
import com.example.apiretrofitasp2.Services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout refreshLayout;
    private ListView listViewModel;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = (Button) findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddBarang.class);
                startActivity(intent);
            }
        });


        //Tampilkan Model
        listViewModel = findViewById(R.id.ListViewLayout); //id dari list view di layout.xml
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call call = apiService.findall();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<Model> models = (List<Model>) response.body();
                listViewModel.setAdapter(new Adapter(models, getApplicationContext()));

                //Ketika List Di Click
                listViewModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Model model = (Model) adapterView.getItemAtPosition(i);
                        Intent intent = new Intent(MainActivity.this,InfoBarang.class);
                        intent.putExtra("model",model);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

        //Refresh
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Tampilkan Model
                listViewModel = findViewById(R.id.ListViewLayout); //id dari list view di layout.xml
                ApiService apiService = ApiClient.getClient().create(ApiService.class);
                Call call = apiService.findall();
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        List<Model> models = (List<Model>) response.body();
                        listViewModel.setAdapter(new Adapter(models, getApplicationContext()));
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
                refreshLayout.setRefreshing(false); // Ini supaya Refreshnya bisa berhenti setelah data di load
            }
        });
    }
}
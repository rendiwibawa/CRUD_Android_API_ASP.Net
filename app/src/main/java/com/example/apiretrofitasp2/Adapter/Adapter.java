package com.example.apiretrofitasp2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apiretrofitasp2.Entities.Model;
import com.example.apiretrofitasp2.R;

import java.util.List;

public class Adapter extends ArrayAdapter<Model> {

    private List<Model> models;
    private Context context;

    public Adapter(List<Model> models, Context context) {
        super(context, R.layout.row, models);
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.row, parent, false);

        Model model = models.get(position);
        TextView textViewName = convertView.findViewById(R.id.nameModel);
        textViewName.setText(model.getName());

        TextView textViewPrice = convertView.findViewById(R.id.hargaModel);
        textViewPrice.setText("Rp. "+ String.valueOf(model.getPrice()));

        TextView textViewQty = convertView.findViewById(R.id.qtyModel);
        textViewQty.setText("Qty: "+ String.valueOf(model.getQuantity()));

        return convertView;
    }
}

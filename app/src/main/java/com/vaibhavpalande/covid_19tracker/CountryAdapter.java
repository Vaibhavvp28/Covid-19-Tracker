package com.vaibhavpalande.covid_19tracker;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bumptech.glide.Glide;
import com.vaibhavpalande.covid_19tracker.Api.CountryData;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.countryViewHolder> {

    private Context context;
    private List<CountryData> list;

    public CountryAdapter(Context context, List<CountryData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public countryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_item_layout, parent, false);
        return new countryViewHolder(view);
    }

    public void filterList(List<CountryData> filterList){
        list = filterList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull countryViewHolder holder, int position) {

        CountryData data = list.get(position);
        holder.cCases.setText(NumberFormat.getInstance().format(Integer.parseInt(data.getCases())));
        holder.cName.setText(data.getCountry());
        holder.sNo.setText(String.valueOf(position+1));

        Map<String,String> flag = data.getCountryInfo();
        Glide.with(context).load(flag.get("flag")).into(holder.flag);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("country", data.getCountry());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class countryViewHolder extends RecyclerView.ViewHolder {

        private TextView sNo, cName, cCases;
        private ImageView flag;

        public countryViewHolder(@NonNull View itemView) {
            super(itemView);

            sNo = itemView.findViewById(R.id.sno);
            cName = itemView.findViewById(R.id.cName);
            cCases = itemView.findViewById(R.id.cases);
            flag = itemView.findViewById(R.id.flag);
        }
    }

}

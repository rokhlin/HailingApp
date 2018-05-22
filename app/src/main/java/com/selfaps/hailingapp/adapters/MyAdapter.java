package com.selfaps.hailingapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.selfaps.hailingapp.R;
import com.selfaps.hailingapp.model.Stations;
import com.selfaps.hailingapp.utils.Utils;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Stations[] mDataset;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mETA;
        TextView mCabStation;
        ImageView icon;


        public ViewHolder(View v) {
            super(v);
            mCabStation = v.findViewById(R.id.tv_cab_station);
            mETA = v.findViewById(R.id.tv_eta);
            icon = v.findViewById(R.id.iv_icon);
        }
    }


    public MyAdapter(Stations[] myDataset) {
        this.mDataset = myDataset;
    }


    public void setDataset(Stations[] mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.taxi_item_view, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Stations station = mDataset[position];
        holder.icon.setImageResource(station.getIcon());
        holder.mCabStation.setText(station.getName());
        holder.mETA.setText(Utils.getFormatedTime(station.getEta()));

        //Add listener on item tap
        holder.icon.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                         station.getName() + v.getResources().getString(R.string.selected),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
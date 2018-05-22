package com.selfaps.hailingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.selfaps.hailingapp.adapters.MyAdapter;
import com.selfaps.hailingapp.model.Stations;
import com.selfaps.hailingapp.utils.Utils;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private Stations[] stations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list_of_taxis);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        stations = Utils.generateData();
        mAdapter = new MyAdapter(stations);
        mRecyclerView.setAdapter(mAdapter);
    }
}

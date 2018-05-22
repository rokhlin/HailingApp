package com.selfaps.hailingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.selfaps.hailingapp.adapters.MyAdapter;
import com.selfaps.hailingapp.model.Stations;
import com.selfaps.hailingapp.utils.Utils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list_of_taxis);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Stations[] stations = Utils.generateData();
        mAdapter = new MyAdapter(stations);
        mRecyclerView.setAdapter(mAdapter);

        startTimer();
}

    private void startTimer() {
        timer = new Timer();
        MyTimerTask mTimerTask = new MyTimerTask();
        timer.schedule(mTimerTask, 5000, 5000);
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            mAdapter.setDataset(Utils.updateData(mAdapter.getDataset()));

            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged();
                }});
        }
    }
}

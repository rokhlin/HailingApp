package com.selfaps.hailingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.selfaps.hailingapp.adapters.MyAdapter;
import com.selfaps.hailingapp.model.Stations;
import com.selfaps.hailingapp.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;

public class ListActivity extends AppCompatActivity {
    private static final long UPDATE_PERIOD = 5000;
    private MyAdapter mAdapter;
    private Timer timer;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list_of_taxis);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Stations[] stations = Utils.sort(Utils.generateData());

        mAdapter = new MyAdapter(stations);
        mRecyclerView.setAdapter(mAdapter);

        startTimer();
}

    private void startTimer() {
        timer = new Timer();
        MyTimerTask mTimerTask = new MyTimerTask();
        timer.schedule(mTimerTask, UPDATE_PERIOD, UPDATE_PERIOD);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        timer = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLayoutManager.removeAllViews();
        mLayoutManager = null;
        mRecyclerView = null;
        mAdapter = null;
    }



    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            //Generate the new elements
            if(mAdapter != null){
                mAdapter.setDataset(Utils.sort(Utils.generateData()));

                //Run update visible elements
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }});
            }
        }
    }
}

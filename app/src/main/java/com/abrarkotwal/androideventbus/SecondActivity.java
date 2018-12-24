package com.abrarkotwal.androideventbus;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;


public class SecondActivity extends AppCompatActivity {
    private TextView tvDisplayMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvDisplayMessage = findViewById(R.id.tvDisplayMessage);
    }

    @SuppressLint("SetTextI18n")
    @Subscribe
    public void getMessage(Events.MessageTransfer activityActivityMessage) {
        tvDisplayMessage.setText(getString(R.string.message_received) + " " + activityActivityMessage.getMessage());
        Log.d("Abrar", String.valueOf(activityActivityMessage.getMessage()));

        Toast.makeText(getApplicationContext(), getString(R.string.message_main_activity) + " " + activityActivityMessage.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }
}

package com.abrarkotwal.androideventbus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    private EditText etMessage;
    private TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMessage = (EditText) findViewById(R.id.activityData);
        messageView = (TextView) findViewById(R.id.message);

        addFragment();
    }

    private void addFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new UserFragment())
                .commit();
    }

    public void sendMessageToFragment(View view) {
        Events.MessageTransfer activityFragmentMessageEvent =
                new Events.MessageTransfer(String.valueOf(etMessage.getText()));

        GlobalBus.getBus().post(activityFragmentMessageEvent);
    }

    public void sendMessageToActivity(View view) {
        Events.MessageTransfer activityActivityMessageEvent =
                new Events.MessageTransfer(etMessage.getText().toString());

        GlobalBus.getBus().postSticky(activityActivityMessageEvent);

        // Start SecondActivity.
        startActivity(new Intent(this, SecondActivity.class));

    }

    @SuppressLint("SetTextI18n")
    @Subscribe
    public void getMessage(Events.MessageTransfer fragmentActivityMessage) {
        messageView.setText(getString(R.string.message_received) + " " + fragmentActivityMessage.getMessage());

        Toast.makeText(getApplicationContext(),
                getString(R.string.message_main_activity) + " " + fragmentActivityMessage.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!GlobalBus.getBus().isRegistered(this))
            GlobalBus.getBus().register(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        GlobalBus.getBus().unregister(this);
    }
}
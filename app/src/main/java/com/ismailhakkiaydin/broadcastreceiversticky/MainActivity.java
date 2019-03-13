package com.ismailhakkiaydin.broadcastreceiversticky;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void methodBir(View view) {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

        Intent intent = registerReceiver(null, intentFilter);

        int bataryaDurumu = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        bataryaDurumunuGoster(bataryaDurumu);


    }

    public void methodIki(View view) {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

        registerReceiver(bataryaReceiver, intentFilter);


    }

    private BroadcastReceiver bataryaReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int bataryaDurumu = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            bataryaDurumunuGoster(bataryaDurumu);


        }
    };

    public void bataryaDurumunuGoster(int bataryaDurumu) {

        switch (bataryaDurumu) {

            case 1:
                Toast.makeText(getBaseContext(), "Batarya durumu bilinmiyor", Toast.LENGTH_LONG).show();
                break;

            case 2:
                Toast.makeText(getBaseContext(), "Şarj oluyor", Toast.LENGTH_LONG).show();
                break;

            case 3:
                Toast.makeText(getBaseContext(), "Şarj olmuyor", Toast.LENGTH_LONG).show();
                break;

            case 4:
                Toast.makeText(getBaseContext(), "Şarjdan çıkarıldı", Toast.LENGTH_LONG).show();
                break;

            case 5:
                Toast.makeText(getBaseContext(), "Batarya dolu", Toast.LENGTH_LONG).show();
                break;
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(bataryaReceiver);
    }
}
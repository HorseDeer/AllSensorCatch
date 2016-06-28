package com.example.admin.allsensorcatch;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SensorList extends AppCompatActivity implements SensorEventListener, Serializable {
    private SensorManager sm2;
    private TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensorlayout);
        data = (TextView)findViewById(R.id.sensorData);
        sm2 = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Intent intent = getIntent();
        String type = intent.getStringExtra("SensorType");
        List<Sensor> sensors = sm2.getSensorList(Sensor.TYPE_ALL);
        for(Sensor s : sensors) {
            if (s.getName().equals(type)) {
                sm2.registerListener(this,s,SensorManager.SENSOR_DELAY_UI);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sm2 != null) {
            sm2.unregisterListener(this);
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        String result = "";
        result += event.sensor.getName() + "\n\n";
        result += event.values[0] + "\n";
        result += event.values[1] + "\n";
        result += event.values[2] + "\n";
        data.setText(result);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

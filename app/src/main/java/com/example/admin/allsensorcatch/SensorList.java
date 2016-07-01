package com.example.admin.allsensorcatch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;


public class SensorList extends AppCompatActivity implements SensorEventListener, Serializable {
    private SensorManager sm2;
    private TextView type,x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensorlayout);
        type = (TextView)findViewById(R.id.sensorData);
        x = (TextView)findViewById(R.id.xdata);
        x.setTextColor(Color.RED);
        y = (TextView)findViewById(R.id.ydata);
        y.setTextColor(Color.GREEN);
        z = (TextView)findViewById(R.id.zdata);
        z.setTextColor(Color.BLUE);
        sm2 = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Intent intent = getIntent();
        String setType = intent.getStringExtra("SensorType");
        List<Sensor> sensors = sm2.getSensorList(Sensor.TYPE_ALL);
        for(Sensor s : sensors) {
            if (s.getName().equals(setType)) {
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
    protected void onStop() {
        super.onStop();
        sm2.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        type.setText(event.sensor.getName());
        x.setText("X座標・・・  "+event.values[0]);
        y.setText("Y座標・・・  "+event.values[1]);
        z.setText("Z座標・・・  "+event.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

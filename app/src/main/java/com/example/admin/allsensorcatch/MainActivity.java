package com.example.admin.allsensorcatch;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener ,View.OnClickListener ,Serializable{
    private SensorManager sm;
    private TextView sensorList;
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorList = (TextView)findViewById(R.id.namelist);
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        goButton = (Button)findViewById(R.id.sensorB);
        goButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
        String str = "実装されているセンサー一覧:\n\n";
        for(Sensor s : sensors) {
            str += s.getName() + "\n";
        }
        for(int i = 0; i < 100; i++) {
            str+="✌('ω'✌ )三✌('ω')✌三( ✌'ω')✌ "+(i+1)+"\n";
        }
        sensorList.setText(str);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sm != null) {
            sm.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        if (v == goButton) {
            Intent intent = new Intent(MainActivity.this,SensorList.class);
            startActivity(intent);
        }
    }
}

package com.example.admin.allsensorcatch;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SensorList extends AppCompatActivity implements SensorEventListener, Serializable {
    private SensorManager sm2;
    private TextView sensorLists;
    private ArrayList<String> datas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensorlayout);
        sensorLists = (TextView)findViewById(R.id.sensorlist);
        sm2 = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    //Gyroscope
    //Accelerometer
    //Magnetic
    //Orientation
    //Rotation
    //Liner Acceleration
    //Gravity
    //Proximity sensor
    //Light Sensor
    //Rotation Vector Sensor
    //Liner Acceleration Sensor
    //Orientation Sensor
    //Corrected Gyroscope Sensor
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        List<Sensor> sensors = sm2.getSensorList(Sensor.TYPE_ALL);

        for(Sensor s : sensors) {
            sm2.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
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
        /*boolean isSet = false;
        for (int i = 0; i < datas.size(); i = i+4) {
            if (datas.get(i).equals(event.sensor.getName())) {
                datas.set(i+1,event.values[0]+"");
                datas.set(i+2,event.values[1]+"");
                datas.set(i+3,event.values[2]+"");
                isSet = true;
            }
            if (isSet)break;
        }
        if (!isSet) {
            datas.add(event.sensor.getName());
            datas.add(event.values[0]+"");
            datas.add(event.values[1]+"");
            datas.add(event.values[2]+"");
        }*/
        int search = datas.indexOf(event.sensor.getName());
        if (search != -1) {
            datas.set(search+1,event.values[0]+"");
            datas.set(search+2,event.values[1]+"");
            datas.set(search+3,event.values[2]+"");
        }
        else {
            datas.add(event.sensor.getName());
            datas.add(event.values[0]+"");
            datas.add(event.values[1]+"");
            datas.add(event.values[2]+"");
        }
        String str = "";
        for (int i = 0; i < datas.size(); i++) {
            str +=  datas.get(i)+"\n";
        }
        sensorLists.setText(str);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

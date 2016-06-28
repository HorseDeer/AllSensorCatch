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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener, AdapterView.OnItemClickListener, Serializable{
    //各種変数の定義
    private SensorManager sm;
    private ArrayList<String> sensorType = new ArrayList<String>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //センサマネージャの設定とリストビューのID紐づけ
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        listView = (ListView)findViewById(R.id.namelist);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //全ての種類のセンサーを取得する
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
       for(Sensor s : sensors) {
           //String型のArrayListに追加していく
           sensorType.add(s.getName());
        }
        /*for(int i = 0; i < 100; i++) {
            sensorType.add("✌('ω'✌ )三✌('ω')✌三( ✌'ω')✌ "+(i+1));
        }*/
        //ListViewにアダプターを設定する
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sensorType);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sm != null) {
            sm.unregisterListener(this);
        }
        sensorType.clear();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView clickList = (ListView) parent;
        Intent intent = new Intent(MainActivity.this, SensorList.class);
        intent.putExtra("SensorType", (String)clickList.getItemAtPosition(position));
        //Toast.makeText(getApplicationContext(), intent.getStringExtra("SensorType"),Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}

package com.example.admin.allsensorcatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class GraphView extends View {
    ArrayList<SensorData> list = new ArrayList<SensorData>();

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    float rate = 10;
    float timeRate= 0.1f;

    public void onDraw(Canvas canvas) {
        if(!list.isEmpty()) {
            SensorData prev= list.get(0);
            float prevTimeX = 0f;
            float prevx= getHeight() / 2 -prev.getData()[0] * rate;
            float prevy= getHeight() / 2 -prev.getData()[1] * rate;
            float prevz= getHeight() / 2 -prev.getData()[2] * rate;
            long firstTime= prev.getTime();
            Paint paint= new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paintLine(canvas);
            for(int i= 1; i< list.size(); i++) {
                SensorData now = list.get(i);
                float timeX = (now.getTime() -firstTime) * timeRate;
                float x = getHeight() / 2 -now.getData()[0] * rate;
                paint.setColor(Color.argb(255, 255, 0, 0));
                canvas.drawLine(prevTimeX, prevx, timeX, x, paint);
                prevx= x;
                float y = getHeight() / 2 -now.getData()[1] * rate;
                paint.setColor(Color.argb(255, 0, 255, 0));
                canvas.drawLine(prevTimeX, prevy, timeX, y, paint);
                prevy= y;
                float z = getHeight() / 2 -now.getData()[2] * rate;
                paint.setColor(Color.argb(255, 0, 0, 255));
                canvas.drawLine(prevTimeX, prevz, timeX, z, paint);
                prevz= z;
                prevTimeX = timeX;
                if(timeX > getWidth()) {
                    list.remove(0);
                }
            }
        }
    }
    public void paintLine(Canvas canvas) {
        Paint paint= new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2, paint);
        canvas.drawLine(0, getHeight()/2+10*rate, getWidth(), getHeight()/2+10*rate, paint);
        canvas.drawLine(0, getHeight()/2-10*rate, getWidth(), getHeight()/2-10*rate, paint);
    }

    public void change(float[] data) {
        long time = System.currentTimeMillis();
        list.add(new SensorData(time, data));
        invalidate();
    }
}
class SensorData{
    private long time;
    private float[] data;
    SensorData(long time, float[] data) {
        this.time= time;
        this.data= data;
    }
    long getTime() {
        return time;
    }
    float[] getData() {
        return data;
    }
}

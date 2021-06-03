package com.example.ovningaccelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView txtOnOff, xAxis, yAxis, zAxis;
    private ToggleButton tglOnOff;
    private ImageView imgView;

    private boolean isFalse = false;

    private Sensor mySensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findItems();

        SM = (SensorManager) getSystemService(SENSOR_SERVICE);

        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        tglOnOff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if((tglOnOff).isChecked()){
                    isFalse = true;
                }else{
                    isFalse = false;
                }
            }
        });

    }

    public void findItems(){

        txtOnOff = (TextView) findViewById(R.id.txtOnOff);
        xAxis = (TextView) findViewById(R.id.txtXaxis);
        yAxis = (TextView) findViewById(R.id.txtYaxis);
        zAxis = (TextView) findViewById(R.id.txtZaxis);
        tglOnOff = (ToggleButton) findViewById(R.id.tglOnOff);
        imgView = (ImageView) findViewById(R.id.imgView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(isFalse == true){
            xAxis.setText("X: " + event.values[0]);
            yAxis.setText("Y: " + event.values[1]);
            zAxis.setText("Z: " + event.values[2]);
        }else{
            xAxis.setText("X: ");
            yAxis.setText("Y: ");
            zAxis.setText("Z: ");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
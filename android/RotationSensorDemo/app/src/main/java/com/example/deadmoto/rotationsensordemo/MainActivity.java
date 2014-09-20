package com.example.deadmoto.rotationsensordemo;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;


public class MainActivity extends Activity implements SensorEventListener {

    private int mMargin;
    private ImageView mImageView;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private float[] mRotationMatrix = new float[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, getResources().getDisplayMetrics());
        mImageView = (ImageView) findViewById(R.id.imageView);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mSensor != null) {
            mSensorManager.registerListener(this, mSensor, 1000 * 72);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mSensor != null) {
            mSensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(mRotationMatrix, sensorEvent.values);
            RelativeLayout.LayoutParams mMarginLayoutParams = new RelativeLayout.LayoutParams(mImageView.getLayoutParams());
            int shift = (int) (mRotationMatrix[0] * mMargin / 2);
            mMarginLayoutParams.leftMargin = -mMargin + shift;
            mMarginLayoutParams.rightMargin = -mMargin - shift;
            int angle = (int) (mRotationMatrix[1] * mMargin / 2);
            mMarginLayoutParams.topMargin = -mMargin + angle;
            mMarginLayoutParams.bottomMargin = -mMargin - angle;
            mImageView.setLayoutParams(mMarginLayoutParams);
        }
    }
}

package com.example.sensorsreadings;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer, lightSensor, proximitySensor, gyroscopeSensor, pressureSensor;
    private TextView accelerometerXTextView, accelerometerYTextView, accelerometerZTextView;
    private TextView lightTextView, proximityTextView, gyroscopeXTextView, gyroscopeYTextView, gyroscopeZTextView;
    private TextView pressureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        accelerometerXTextView = findViewById(R.id.accelerometerXTextView);
        accelerometerYTextView = findViewById(R.id.accelerometerYTextView);
        accelerometerZTextView = findViewById(R.id.accelerometerZTextView);
        lightTextView = findViewById(R.id.lightTextView);
        proximityTextView = findViewById(R.id.proximityTextView);
        gyroscopeXTextView = findViewById(R.id.gyroscopeXTextView);
        gyroscopeYTextView = findViewById(R.id.gyroscopeYTextView);
        gyroscopeZTextView = findViewById(R.id.gyroscopeZTextView);
        pressureTextView = findViewById(R.id.pressureTextView);

        // Initialize SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            // Initialize sensors
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Register sensor listeners
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (gyroscopeSensor != null) {
            sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (pressureSensor != null) {
            sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Unregister sensor listeners
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        // Update UI elements based on sensor readings
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                accelerometerXTextView.setText("X-Axis: " + event.values[0]);
                accelerometerYTextView.setText("Y-Axis: " + event.values[1]);
                accelerometerZTextView.setText("Z-Axis: " + event.values[2]);
                break;
            case Sensor.TYPE_LIGHT:
                lightTextView.setText("Light: " + event.values[0]);
                break;
            case Sensor.TYPE_PROXIMITY:
                proximityTextView.setText("Proximity: " + event.values[0]);
                break;
            case Sensor.TYPE_GYROSCOPE:
                gyroscopeXTextView.setText("X-Axis: " + event.values[0]);
                gyroscopeYTextView.setText("Y-Axis: " + event.values[1]);
                gyroscopeZTextView.setText("Z-Axis: " + event.values[2]);
                break;
            case Sensor.TYPE_PRESSURE:
                pressureTextView.setText("Pressure: " + event.values[0]);
                break;
            default:
                break;
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}

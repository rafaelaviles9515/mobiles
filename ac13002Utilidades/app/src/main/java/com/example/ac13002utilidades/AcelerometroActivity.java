package com.example.ac13002utilidades;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
public class AcelerometroActivity extends Activity implements SensorEventListener {
    TextView x,y,z;
    EditText caja;
    int colorr, colorv, colora;
    private Sensor mAccelerometer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_acelerometro_activity);
        x = (TextView)findViewById(R.id.xID);
        y = (TextView)findViewById(R.id.yID);
        z = (TextView)findViewById(R.id.zID);
        caja= (EditText)findViewById(R.id.editText);
        this.caja.setEnabled(false);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
    protected void onResume()
    {
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0)
        //dispositivo android tiene acelerometro
        {
            sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
    }
    protected void onPause()
    {
        SensorManager mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this, mAccelerometer);
        super.onPause();
    }
    protected void onStop()
    {
        SensorManager mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager.unregisterListener(this, mAccelerometer);
        super.onStop();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        this.x.setText("X = "+ event.values[SensorManager.DATA_X]);
        this.y.setText("Y = "+event.values[SensorManager.DATA_Y]);
        this.z.setText("Z = " + event.values[SensorManager.DATA_Z]);
        if ( event.values[SensorManager.DATA_Z]>5 &&
                event.values[SensorManager.DATA_X]<5&& event.values[SensorManager.DATA_Y]<5){
            this.caja.setBackgroundColor(Color.rgb(0, 0, 255));
            this.caja.setText("Horizontal");
        }
        else
        {
            if ( event.values[SensorManager.DATA_Y]>5 &&
                    event.values[SensorManager.DATA_X]<2&& event.values[SensorManager.DATA_Z]<2){
                this.caja.setBackgroundColor(Color.rgb(0, 255, 0));
                this.caja.setText("Vertical");
            }
            else
            {
                this.caja.setBackgroundColor(Color.rgb(255, 0, 0));
                this.caja.setText("Otra Posicion");
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}

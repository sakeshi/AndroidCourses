package upec.spirit;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor accelerometer;
    private Display mDisplay;
    float x=0,y=0,z=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplay = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, 50000);

    }
    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this, accelerometer);
        super.onPause();
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Récupérer les valeurs du capteur

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Log.d(LOG_TAG, "TYPE_ACCELEROMETER");
            // En fonction de l'orientation de l'appareil, on corrige les valeurs x et y du capteur
            switch (mDisplay.getRotation()) {
                case Surface.ROTATION_0:
                    x = event.values[0];
                    y = event.values[1];
                    break;
                case Surface.ROTATION_90:
                    x = -event.values[1];
                    y = event.values[0];
                    break;
                case Surface.ROTATION_180:
                    x = -event.values[0];
                    y = -event.values[1];
                    break;
                case Surface.ROTATION_270:
                    x = event.values[1];
                    y = -event.values[0];
                    break;
            }

            // la valeur de z
            z = event.values[2];
            TextView t = (TextView) findViewById(R.id.textView);

            t.setText("( x "+ (double)Math.round(x * 100) / 100  + " , y "+ (double)Math.round(y * 100) / 100   +  " , z " + (double)Math.round(z * 100) / 100  + ") ");

            Dessin d = (Dessin) findViewById(R.id.dessin);
            Dessin.Point p = d.getPs();
            p.setX(p.getX() - x );
            p.setY(p.getY()  -y );
            d.invalidate();
        }
    }
}

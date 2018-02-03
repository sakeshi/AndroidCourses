package upec.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import static java.lang.Character.getType;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Display mDisplay;
    Sensor accelerometer;
    float x=0, y=0, z=0;
    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Et enfin instancier le display qui connaît l'orientation de l'appareil
        mDisplay = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
         accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        listSensor();
    }

    @Override
    protected void onPause() {
        // désenregistrer tous le monde
        sensorManager.unregisterListener(this, accelerometer);
        super.onPause();
    }

    @Override
    protected void onResume() {
    /* Ce qu'en dit Google&#160;:
     * «&#160; Ce n'est pas nécessaire d'avoir les évènements des capteurs à un rythme trop rapide.
     * En utilisant un rythme moins rapide (SENSOR_DELAY_UI), nous obtenons un filtre
     * automatique de bas-niveau qui "extrait" la gravité  de l'accélération.
     * Un autre bénéfice étant que l'on utilise moins d'énergie et de CPU.&#160;»
     */
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);

        super.onResume();
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

            System.out.println(x + " et " + y + " et " + z);
            // faire quelque chose, par exemple un Log&#160;:
            //Log.d(LOG_TAG, "Sensor's values (" + x + "," + y + "," + z + ") and maxRange : " + maxRange);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void listSensor() {

        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);



        LinearLayout layout = findViewById(R.id.layout);

        for (final Sensor sensor : sensors) {
            final StringBuffer sensorDesc = new StringBuffer();
            final TextView t = new TextView(this);
            sensorDesc.append("------------------------------------------------\nNew sensor detected : \r\n ");

            sensorDesc.append("\tName: " + sensor.getName() + "\r\n");
/*
            sensorDesc.append("\tType: " + getType(sensor.getType()) + "\r\n");

            sensorDesc.append("Version: " + sensor.getVersion() + "\r\n");

            sensorDesc.append("Resolution (in the sensor unit): " + sensor.getResolution() + "\r\n");

            sensorDesc.append("Power in mA used by this sensor while in use" + sensor.getPower() +

                    "\r\n");

            sensorDesc.append("Vendor: " + sensor.getVendor() + "\r\n");

            sensorDesc.append("Maximum range of the sensor in the sensor's unit." +

                    sensor.getMaximumRange() + "\r\n");

            sensorDesc.append("Minimum delay allowed between two events in microsecond"

                    + " or zero if this sensor only returns a value when the data it's measuring changes"

                    + sensor.getMinDelay() + "\r\n");*/
            t.setText(sensorDesc);
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sensorDesc.append("\tType: " + getType(sensor.getType()) + "\r\n");

                    sensorDesc.append("Version: " + sensor.getVersion() + "\r\n");

                    sensorDesc.append("Resolution (in the sensor unit): " + sensor.getResolution() + "\r\n");

                    sensorDesc.append("Power in mA used by this sensor while in use" + sensor.getPower() +

                            "\r\n");

                    sensorDesc.append("Vendor: " + sensor.getVendor() + "\r\n");

                    sensorDesc.append("Maximum range of the sensor in the sensor's unit." +

                            sensor.getMaximumRange() + "\r\n");

                    sensorDesc.append("Minimum delay allowed between two events in microsecond"

                            + " or zero if this sensor only returns a value when the data it's measuring changes"

                            + sensor.getMinDelay() + "\r\n");
                   t.setText(sensorDesc);
                }
            });
            layout.addView(t);
        }



//        Toast.makeText(this, sensorDesc.toString(), Toast.LENGTH_LONG).show();



    }




}

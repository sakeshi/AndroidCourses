package upec.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_ENABLE_BT = 1;
    private final static int  REQUEST_DISCOVERABLE_BT = 0;
    BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (blueAdapter == null) {
            // Le terminal ne possède pas le Bluetooth
        }
        else
        {
            //Activation
            if (!blueAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);


            }
            //Ajout des appareils deja connectes
            Set<BluetoothDevice> pairedDevices = blueAdapter.getBondedDevices();
            for(BluetoothDevice bt : pairedDevices)
                mArrayAdapter.add(bt.getName());
            //Co une new
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(receiver, filter);
            //Affiche la liste
            System.out.println("Appareil co : ");
            for(String str : mArrayAdapter){
                System.out.println("nom : "+str);
            }
            //
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 100000); // Cette ligne permet de définir une durée de visibilité de notre choix
            startActivityForResult(discoverableIntent, REQUEST_DISCOVERABLE_BT);
            //
            ClientBluetooth serv = new ClientBluetooth(blueAdapter);
            System.out.println("nom : "+blueAdapter.getName());
            serv.run();
        }

    }
    ArrayList<String> mArrayAdapter = new ArrayList<>();
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // Quand la recherche trouve un terminal
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // On récupère l'object BluetoothDevice depuis l'Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // On ajoute le nom et l'adresse du périphérique dans un ArrayAdapter (par exemple pour l'afficher dans une ListView)

                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };


}

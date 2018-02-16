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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private Set<BluetoothDevice> devices;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (bluetoothAdapter == null)
            Toast.makeText(MainActivity.this, "Pas de Bluetooth",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "Avec Bluetooth",
                    Toast.LENGTH_SHORT).show();
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBlueTooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBlueTooth, REQUEST_CODE_ENABLE_BLUETOOTH);
        }
        //Affiche existe deja
        devices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice blueDevice : devices) {
            Toast.makeText(MainActivity.this, "Device = " + blueDevice.getName(), Toast.LENGTH_SHORT).show();
        }
        //HELLO je suis la
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);

        //Affiche all possible
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(bluetoothReceiver, filter);
        bluetoothAdapter.startDiscovery();

      action();
    }
    void action(){
        //Do smth
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServeurBluetooth serv = new ServeurBluetooth(bluetoothAdapter);
                System.out.println("nom : " + bluetoothAdapter.getName());
                serv.start();
                TextView t = (TextView) findViewById(R.id.textView);
                t.setText("Serveur");
            }
        });
    //Do smth
        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<BluetoothDevice> pairedDevices  = bluetoothAdapter.getBondedDevices();
                for (BluetoothDevice device : pairedDevices) {
                    TextView t = (TextView) findViewById(R.id.textView);
                    ClientBluetooth cli = new ClientBluetooth(device,bluetoothAdapter,t);
                    System.out.println("nom : " + bluetoothAdapter.getName());
                    cli.start();
                    t.setText("Client");
                }

            }
        });
    }
    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(MainActivity.this, "New Device = " + device.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bluetoothAdapter.cancelDiscovery();
        unregisterReceiver(bluetoothReceiver);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CODE_ENABLE_BLUETOOTH)
            return;
        if (resultCode == RESULT_OK) {
            // L'utilisation a activé le bluetooth


        } else {

        }
    }
    /*
    private final static int REQUEST_ENABLE_BT = 1;
    private final static int  REQUEST_DISCOVERABLE_BT = 0;
   final BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (blueAdapter == null) {
            // Le terminal ne possède pas le Bluetooth
        }
        else {
            //Activation
            if (!blueAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);


            }
            //Ajout des appareils deja connectes
            Set<BluetoothDevice> pairedDevices = blueAdapter.getBondedDevices();
            for (BluetoothDevice bt : pairedDevices)
                mArrayAdapter.add(bt.getName());
            //Co une new
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(receiver, filter);
            //Affiche la liste
            System.out.println("Appareil co : ");
            for (String str : mArrayAdapter) {
                System.out.println("nom : " + str);
            }
            //
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 100000); // Cette ligne permet de définir une durée de visibilité de notre choix
            startActivityForResult(discoverableIntent, REQUEST_DISCOVERABLE_BT);
            //
            Button b = (Button) findViewById(R.id.button);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ServeurBluetooth serv = new ServeurBluetooth(blueAdapter);
                    System.out.println("nom : " + blueAdapter.getName());
                    serv.run();
                }
            });

            Button b2 = (Button) findViewById(R.id.button2);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Set<BluetoothDevice> pairedDevices  = blueAdapter.getBondedDevices();
                    for (BluetoothDevice device : pairedDevices) {
                        ClientBluetooth cli = new ClientBluetooth(device);
                        System.out.println("nom : " + blueAdapter.getName());
                        cli.run();
                    }

                }
            });
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

*/
}

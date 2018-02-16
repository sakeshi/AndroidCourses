package upec.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.widget.TextView;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by quentin on 13/02/18.
 */

public class ClientBluetooth extends Thread {
    private final BluetoothSocket blueSocket;
    private final BluetoothDevice blueDevice;
    BluetoothAdapter blueAdapter;
    TextView t;

    private UUID DEFAULT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public ClientBluetooth(BluetoothDevice device, BluetoothAdapter blueAdapter, TextView t) {
        BluetoothSocket tmp = null;
        blueDevice = device;
        this.t=t;
        this.blueAdapter = blueAdapter;
        try {
              tmp = device.createRfcommSocketToServiceRecord(DEFAULT_UUID);
        } catch (IOException e) { }
        blueSocket = tmp;
    }

    public void run() {

        blueAdapter.cancelDiscovery();

        try {
            blueSocket.connect();
        } catch (IOException connectException) {
            // do smth
            SendReceive s = new SendReceive(blueSocket,t);
            s.start();
            try {
                blueSocket.close();
            } catch (IOException closeException) { }
            return;
        }

        manageConnectedSocket(blueSocket);
    }

    private void manageConnectedSocket(BluetoothSocket blueSocket) {
    }

    // Annule toute connexion en cours et tue le thread
    public void cancel() {
        try {
            blueSocket.close();
        } catch (IOException e) { }
    }
}

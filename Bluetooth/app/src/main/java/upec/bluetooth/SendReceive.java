package upec.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Quentin on 13/02/2018.
 */

public class SendReceive extends Thread {
    private final BluetoothSocket bluetoothSocket;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    TextView t;
    public SendReceive(BluetoothSocket socket, TextView t){
        this.t=t;
        bluetoothSocket = socket;
        InputStream tempIn =null;
        OutputStream tempOut=null;
        try {
            tempIn=bluetoothSocket.getInputStream();
            tempOut=bluetoothSocket.getOutputStream();
        }
        catch(IOException e){}

        inputStream =tempIn;
        outputStream=tempOut;
    }
    public void run(){
        byte[] buffer = new byte[1024];
        int bytes;

        while(true){
            try {
                bytes=inputStream.read(buffer);
                t.setText("Yolo");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void write(byte[] bytes){
        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package upec.web;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 class AsynTask extends AsyncTask<String,Void,Boolean>{
    private TextView t;
    private String str;
    public AsynTask(TextView t){
        this.t = t;
    }
    protected void onPreExecute() {
    }

    @Override
    protected Boolean doInBackground(String... voids) {
        try {
            URL url = new URL("http://www.android.com");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setReadTimeout(1000);
            httpConn.setConnectTimeout(1000);
            httpConn.setRequestMethod( "GET");
            httpConn.setDoInput(true);
            httpConn.connect();
            int response = httpConn.getResponseCode();
            InputStream is = httpConn.getInputStream() ;
             str = convertInputStreamToString(is);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


     protected void onPostExecute(Boolean result) {
        if(result)
       t.setText(str);

     }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        line = bufferedReader.readLine();
        System.out.println("bonjour " + line );
        inputStream.close();
        return line;

    }

}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b =(Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               action();
            }
        });

    }
    public void action(){
        ConnectivityManager conM = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conM.getActiveNetworkInfo();
        if(networkInfo.isConnected()){
            AsynTask a = new AsynTask((TextView)findViewById(R.id.textView));
            a.execute();
        }
    }
}

package t.upec.weather;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class AsynTask extends AsyncTask<String,Void,Boolean> {
    private TextView t;
    private TextView t2;
    private String str;
    private String lieu;
    public AsynTask(TextView t,TextView t2,String lieu){
        this.t = t;
        this.t2 = t2;
        this.lieu = lieu;
    }
    protected void onPreExecute() {
    }

    @Override
    protected Boolean doInBackground(String... voids) {
        try {
            StringBuilder urlStr = new StringBuilder();
            urlStr.append("http://api.openweathermap.org/data/2.5/weather?q=");
            urlStr.append(lieu);
            urlStr.append("&mode=xml?&APPID=855114b6016a9acccdb5871893a7971f");

            URL url = new URL(urlStr.toString());
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
        if(result) {
            t.setText(str);
            Double cacl = temp -273.15;
            t2.setText("Temp = "+cacl.toString());
        }

    }
    private  Double temp;
    private  String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        line = bufferedReader.readLine();
        parser(line);
        inputStream.close();
        return line;

    }
    private  void parser(String str){


        for (int i =0; i < str.length(); i++){
            if(str.charAt(i) == '"'){
                i++;
                StringBuilder buffer = new StringBuilder();
                while(str.charAt(i) != '"') {
                    buffer.append(str.charAt(i));
                    i++;
                }


                if(buffer.toString().equals("temp")){
                    i++;
                    i++;
                    StringBuilder temperature = new StringBuilder();
                    while(str.charAt(i) != ',') {
                        temperature.append(str.charAt(i));
                        i++;
                    }
                    if(temperature.toString().length()!=0)
                        try {
                           temp =  (Double.parseDouble(temperature.toString()));
                        }
                        catch(Exception e){
                            System.out.println("Erreur "+ e);
                        }
                    // temp = Double.valueOf(temperature.toString());
                    break;
                }

            }
        }
        System.out.println("temp : " + temp);
    }

}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ed = (EditText) findViewById(R.id.editText);
        ed.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                String lieu = textView.getText().toString();
                action(lieu);
                return true;
            }
        });

    }
    public void action(String lieu){

        ConnectivityManager conM = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conM.getActiveNetworkInfo();
        if(networkInfo.isConnected()){
            AsynTask a = new AsynTask((TextView)findViewById(R.id.textView),(TextView)findViewById(R.id.textView2),lieu);
            a.execute();
        }

    }
}

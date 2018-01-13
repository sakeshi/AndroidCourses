package suite.tp1.convertisseur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.input);
                TextView text = (TextView) findViewById(R.id.text);
                if(TextUtils.isEmpty(input.getText())) {
                    text.setText("0" );
                }
                else{
                    double i = Double.valueOf(input.getText().toString());
                    i = i * 0.8;
                    text.setText(i + "£" );
                }



            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.input);
                TextView text = (TextView) findViewById(R.id.text);
                if(TextUtils.isEmpty(input.getText())) {
                    text.setText("0" );
                }
                else{
                    double i = Double.valueOf(input.getText().toString());
                    i = i / 0.8;
                    text.setText(i + "€" );
                }



            }
        });
    }
}

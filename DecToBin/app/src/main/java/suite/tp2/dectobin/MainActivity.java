package suite.tp2.dectobin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nb = (Button) findViewById(R.id.nb);
        nb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                EditText input = (EditText) findViewById(R.id.input);
                if (input.getText().toString().length() == 0) {

                }
                else intent.putExtra("max",Integer.valueOf(input.getText().toString()));
                startActivity(intent);
            }
        });

    }

}
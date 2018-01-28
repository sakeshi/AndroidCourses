package upec.tp3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Dessin dessin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("test");
         dessin = (Dessin) findViewById(R.id.dessin);

        Button pal = (Button) findViewById(R.id.palette);
        pal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Palette.class);


                startActivityForResult(intent,1);
            }
        }

        );


    }
    public void clearCanvas(View v){
        dessin.clearDessin();
    }
    String test = "mdr";
    float width = 0.0f;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {


                String returnString = data.getStringExtra("Color");
                test = returnString;
                dessin.setValue(test);

                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(returnString);

                TextView textView2 = (TextView) findViewById(R.id.textView2);
                Bundle bundle = data.getExtras();
                width = bundle.getFloat("Width");

                dessin.setWidth(width);
                System.out.println(width + "hello");
                String s = Float.toString(width);
                textView2.setText(s);
            }
        }
    }
}

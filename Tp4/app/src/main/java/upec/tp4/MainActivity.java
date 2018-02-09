package upec.tp4;

import android.content.Intent;
import android.os.Parcelable;
import android.os.PersistableBundle;
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
        dessin = (Dessin) findViewById(R.id.dessin);

        //dessin.onSaveInstanceState(savedInstanceState);
/*
        if(savedInstanceState != null){
            dessin.onRestoreInstanceState(savedInstanceState.getParcelable("points"));

            //dessin.setPoints(savedInstanceState.<Point>getParcelableArrayList(parc));
        }*/
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Palette p = data.getParcelableExtra("palette");

                dessin.setValue(p.getColor());

                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(p.getColor());

                dessin.setWidth(p.getWidth());

                TextView textView2 = (TextView) findViewById(R.id.textView2);

                String s = Float.toString(p.getWidth());
                textView2.setText(s);
            }
        }
    }


    /*
    public Parcelable onSaveInstanceState(){
        Parcelable superSave = super.onSaveInstanceState();
        Save saveState = new Save(superSave);

        return saveState;
    }

*/
}

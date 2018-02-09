package upec.decompte;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start();
    }
    public void start(){
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView tV = (TextView) findViewById(R.id.textView);
                ProgressBar p = new ProgressBar(getBaseContext(),null, android.R.attr.progressBarStyleHorizontal);
                p.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                LinearLayout l = (LinearLayout) findViewById(R.id.linearLayout);
                l.addView(p);
                 EditText eT = (EditText) findViewById(R.id.editText);
                Tache t;
                if(!(eT.getText().toString().length() == 0))
                  t = new Tache(p,getBaseContext(),Integer.valueOf(eT.getText().toString()),tV);
                else
                 t = new Tache(p,getBaseContext(),0,tV);
                t.execute();

            }
        });
    }

    public void action(int nb){
        TextView t = (TextView) findViewById(R.id.textView);

        for (int i = nb; i >= 0; i--){
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            t.setText(Integer.toString(i));
        }
    }
}

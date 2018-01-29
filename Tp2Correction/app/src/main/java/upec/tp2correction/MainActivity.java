package upec.tp2correction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int n = 6;
    ArrayList<ToggleButton> togbuttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        LinearLayout ly = (LinearLayout) findViewById(R.id.linearLayout);
        final EditText ed = (EditText) findViewById(R.id.editText);
        //GESTION CLICK
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nn = 0;
                for (int i = 0; i < togbuttons.size();i++){
                    ToggleButton tb = togbuttons.get(togbuttons.size()-i-1);
                    if(tb.isChecked()) nn+=1 << i;
                }
                ed.setText(Integer.toString(nn));
            }
        };
        //CREATION BUTTON
        for(int i = 0; i <n; i++){
            ToggleButton tb = new ToggleButton(this);
            tb.setText("0");
            tb.setTextOff("0");
            tb.setTextOn("1");
            tb.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            tb.setMinimumWidth(100);
            tb.setOnClickListener(ocl);
            ly.addView(tb);
            togbuttons.add(tb);
        }
        //GESTION EDITTEXT
        ed.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                int nn = Integer.parseInt(textView.getText().toString());
                for(int i = 0; i < togbuttons.size();i++){
                    ToggleButton tb = togbuttons.get(togbuttons.size()-i-1);
                    tb.setChecked(nn%2!=0);
                    nn=nn/2;
                }
                return false;
            }
        });
    }
}

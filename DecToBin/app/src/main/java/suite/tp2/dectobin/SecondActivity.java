package suite.tp2.dectobin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends AppCompatActivity {
    private int max;
    private List<ToggleButton> tB = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EditText input = (EditText) findViewById(R.id.input);
         max = getIntent().getIntExtra("max",0);
         Log.i("me","e" + max);
         int cons = 0;
         for (int i = 0; i< max;i++){

             ToggleButton t = new ToggleButton(this);
             t.setChecked(false);
             t.setText("0");
             t.setTextOff("0");
             t.setTextOn("1");

             RelativeLayout.LayoutParams params  = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
             params.setMargins(cons,0,0,0);
             params.width = 90;
             t.setLayoutParams(params);
             t.requestLayout();
             tB.add(t);
            RelativeLayout sndAct = (RelativeLayout) findViewById(R.id.sndAct);
            sndAct.addView(t);
            cons += 100;
         }

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 0) {
                    tBzero();
                } else {
                    int test = Integer.parseInt(charSequence.toString());

                    decToBin(test, 0);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        gestButton();


    }
    private void tBzero() {
        for(int i = 0; i< tB.size();i++){
            ToggleButton toggle = tB.get(i);
            toggle.setChecked(false);
        }

    }
    private void decToBin(int num, int i) {
        if (num == 0) {
            return;
        }

        ToggleButton toggle = tB.get(tB.size()-1 -i);

        if (num % 2 == 0) {
            toggle.setChecked(false);
        } else {
            toggle.setChecked(true);
        }
        i++;
        decToBin(num / 2, i);

    }

    private void gestButton() {
        for(int i = 0; i <tB.size();i++){
           final ToggleButton toggle = tB.get(i);
            toggle.setOnClickListener(new View.OnClickListener() {
                int count =  tB.size() - 1 - tB.indexOf(toggle);
                int sum = 0;
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    if (toggle.isChecked()) {

                        EditText input = (EditText) findViewById(R.id.input);
                        if (input.getText().toString().length() == 0) {
                            sum = (int)Math.pow(2,count);
                        } else sum = Integer.valueOf(input.getText().toString()) + (int)Math.pow(2,count);
                        input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);


                    } else {

                        EditText input = (EditText) findViewById(R.id.input);
                        if (input.getText().toString().length() == 0) {
                            sum = (int)Math.pow(2,count);
                        } else sum = Integer.valueOf(input.getText().toString()) - (int)Math.pow(2,count);
                        input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);
                        toggle.setChecked(false);
                    }
                }
            });
        }



    }




}

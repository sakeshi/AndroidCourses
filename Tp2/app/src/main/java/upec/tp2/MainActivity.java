package upec.tp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText input = (EditText) findViewById(R.id.input);

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

    private void gestButton() {

        ToggleButton b0 = (ToggleButton) findViewById(R.id.b0);

        b0.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                ToggleButton b0 = (ToggleButton) findViewById(R.id.b0);
                if (b0.isChecked()) {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 1;
                    } else sum = Integer.valueOf(input.getText().toString()) + 1;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);


                } else {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 1;
                    } else sum = Integer.valueOf(input.getText().toString()) - 1;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);
                    b0.setChecked(false);
                }
            }
        });
        ToggleButton b1 = (ToggleButton) findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                ToggleButton b1 = (ToggleButton) findViewById(R.id.b1);
                if (b1.isChecked()) {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 2;
                    } else sum = Integer.valueOf(input.getText().toString()) + 2;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);


                } else {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 2;
                    } else sum = Integer.valueOf(input.getText().toString()) - 2;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);
                    b1.setChecked(false);
                }
            }
        });

        ToggleButton b2 = (ToggleButton) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                ToggleButton b2 = (ToggleButton) findViewById(R.id.b2);
                if (b2.isChecked()) {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 4;
                    } else sum = Integer.valueOf(input.getText().toString()) + 4;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);


                } else {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 4;
                    } else sum = Integer.valueOf(input.getText().toString()) - 4;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);
                    b2.setChecked(false);
                }
            }
        });
        ToggleButton b3 = (ToggleButton) findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                ToggleButton b3 = (ToggleButton) findViewById(R.id.b3);
                if (b3.isChecked()) {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 8;
                    } else sum = Integer.valueOf(input.getText().toString()) + 8;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);


                } else {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 8;
                    } else sum = Integer.valueOf(input.getText().toString()) - 8;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);
                    b3.setChecked(false);
                }
            }
        });
        ToggleButton b4 = (ToggleButton) findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                ToggleButton b4 = (ToggleButton) findViewById(R.id.b4);
                if (b4.isChecked()) {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 16;
                    } else sum = Integer.valueOf(input.getText().toString()) + 16;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);


                } else {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 16;
                    } else sum = Integer.valueOf(input.getText().toString()) - 16;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);
                    b4.setChecked(false);
                }
            }
        });

        ToggleButton b5 = (ToggleButton) findViewById(R.id.b5);
        b5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                ToggleButton b5 = (ToggleButton) findViewById(R.id.b5);
                if (b5.isChecked()) {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 32;
                    } else sum = Integer.valueOf(input.getText().toString()) + 32;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);


                } else {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 32;
                    } else sum = Integer.valueOf(input.getText().toString()) - 32;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);
                    b5.setChecked(false);
                }
            }
        });

        ToggleButton b6 = (ToggleButton) findViewById(R.id.b6);
        b6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                ToggleButton b6 = (ToggleButton) findViewById(R.id.b6);
                if (b6.isChecked()) {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 64;
                    } else sum = Integer.valueOf(input.getText().toString()) + 64;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);


                } else {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 64;
                    } else sum = Integer.valueOf(input.getText().toString()) - 64;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);
                    b6.setChecked(false);
                }
            }
        });

        ToggleButton b7 = (ToggleButton) findViewById(R.id.b7);
        b7.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                ToggleButton b7 = (ToggleButton) findViewById(R.id.b7);
                if (b7.isChecked()) {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 128;
                    } else sum = Integer.valueOf(input.getText().toString()) + 128;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);


                } else {
                    int sum = 0;
                    EditText input = (EditText) findViewById(R.id.input);
                    if (input.getText().toString().length() == 0) {
                        sum = 128;
                    } else sum = Integer.valueOf(input.getText().toString()) - 128;
                    input.setText(Integer.toString(sum), TextView.BufferType.EDITABLE);
                    b7.setChecked(false);
                }
            }
        });
    }

    private void tBzero() {
        ToggleButton toggle = (ToggleButton) findViewById(R.id.b0);
        toggle.setChecked(false);
        toggle = (ToggleButton) findViewById(R.id.b1);
        toggle.setChecked(false);
        toggle = (ToggleButton) findViewById(R.id.b2);
        toggle.setChecked(false);
        toggle = (ToggleButton) findViewById(R.id.b3);
        toggle.setChecked(false);
        toggle = (ToggleButton) findViewById(R.id.b4);
        toggle.setChecked(false);
        toggle = (ToggleButton) findViewById(R.id.b5);
        toggle.setChecked(false);
        toggle = (ToggleButton) findViewById(R.id.b6);
        toggle.setChecked(false);
        toggle = (ToggleButton) findViewById(R.id.b7);
        toggle.setChecked(false);
    }

    private void decToBin(int num, int i) {
        if (num == 0) {
            return;
        }

        ToggleButton toggle = (ToggleButton) findViewById(R.id.b0);
        if (i == 0) {
            toggle = (ToggleButton) findViewById(R.id.b0);

        }
        if (i == 1) {
            toggle = (ToggleButton) findViewById(R.id.b1);

        }
        if (i == 2) {
            toggle = (ToggleButton) findViewById(R.id.b2);

        }
        if (i == 3) {
            toggle = (ToggleButton) findViewById(R.id.b3);

        }
        if (i == 4) {
            toggle = (ToggleButton) findViewById(R.id.b4);

        }
        if (i == 5) {
            toggle = (ToggleButton) findViewById(R.id.b5);

        }
        if (i == 6) {
            toggle = (ToggleButton) findViewById(R.id.b6);

        }
        if (i == 7) {
            toggle = (ToggleButton) findViewById(R.id.b7);

        }
        if (num % 2 == 0) {
            toggle.setChecked(false);
        } else {
            toggle.setChecked(true);
        }
        i++;
        decToBin(num / 2, i);

    }
}

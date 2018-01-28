package upec.tp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Quentin on 27/01/2018.
 */

public class Palette extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        Spinner spinner = (Spinner) findViewById(R.id.color);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.colorArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Spinner editText = (Spinner) findViewById(R.id.color);
                                       String stringToPassBack = editText.getSelectedItem().toString();
                                       EditText width = (EditText) findViewById(R.id.editText);

                                       float number = Float.valueOf(width.getText().toString());
                                       System.out.println(number + "hello");
                                       Intent intent = new Intent();
                                       intent.putExtra("Color", stringToPassBack);
                                       intent.putExtra("Width", number);
                                       setResult(RESULT_OK, intent);
                                       finish();
                                   }
                               }

        );

    }

}

package upec.tp4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Quentin on 03/02/2018.
 */

public class Palette extends AppCompatActivity implements Parcelable {
    float width;

    public float getWidth() {
        return width;
    }

    public String getColor() {
        return color;
    }

    String color;
    public Palette(){
        this.width = 5.0f;
        this.color = "Black";
    }
    public Palette(float width, String color){
        this.width = width;
        this.color = color;
    }
    public Palette(Parcel in) {
        this.width = in.readFloat();
        this.color= in.readString();
    }
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
                                      Palette p = new Palette(number,stringToPassBack);
                                      Intent intent = new Intent();
                                      intent.putExtra("palette",p);
                                      setResult(RESULT_OK, intent);
                                      finish();
                                  }
                              }

        );

    }
    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeFloat(width);
        dest.writeString(color);

    }
    public static final Parcelable.Creator<Palette> CREATOR = new Parcelable.Creator<Palette>()
    {
        @Override
        public Palette createFromParcel(Parcel source)
        {
            return new Palette(source);
        }

        @Override
        public Palette[] newArray(int size)
        {
            return new Palette[size];
        }
    };


}
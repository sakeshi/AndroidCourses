package com.game.quentin.puissance;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tab[][] = new TextView[4][4];
    private String joueur = "X";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        addTextView();
    }
    void addTextView(){
        tab[0][0] = findViewById(R.id.textView2);
        tab[0][1] = findViewById(R.id.textView3);
        tab[0][2] = findViewById(R.id.textView4);
        tab[0][3] = findViewById(R.id.textView5);
        tab[1][0] = findViewById(R.id.textView6);
        tab[1][1] = findViewById(R.id.textView7);
        tab[1][2] = findViewById(R.id.textView8);
        tab[1][3] = findViewById(R.id.textView9);
        tab[2][0] = findViewById(R.id.textView10);
        tab[2][1] = findViewById(R.id.textView11);
        tab[2][2] = findViewById(R.id.textView12);
        tab[2][3] = findViewById(R.id.textView13);
        tab[3][0] = findViewById(R.id.textView14);
        tab[3][1] = findViewById(R.id.textView15);
        tab[3][2] = findViewById(R.id.textView16);
        tab[3][3] = findViewById(R.id.textView17);
    }
    @Override
    protected void onResume() {
        super.onResume();

        initGame();


    }
    void initGame(){

        for(int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab[i].length; j++){
                final int h = j;
                tab[i][j].setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                tab[i][j].setText("_");
                tab[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           for (int v = tab.length-1;v >=0;v-- ){
                               if(tab[v][h].getText().equals("_")){
                                   tab[v][h].setText(joueur);
                                   joueur = inverse(joueur);
                                   TextView player  = (TextView) findViewById(R.id.joueur);
                                   player.setText(joueur);
                                   boolean etat = gestionGame();
                                   if(etat){
                                       reinit();
                                   }
                                   break;
                               }
                           }
                        }
                    }
                );

            }
        }
    }
    void reinit(){
        for(int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        initGame();
                    }
                });
            }
        }

    }
    boolean gestionGame(){
        for(int i = tab.length-1; i >=0; i--){
            for (int j = tab[i].length-3; j >=0 ; j--) {
                if(tab[i][j].getText().equals(tab[i][j+1].getText()) && tab[i][j].getText().equals(tab[i][j+2].getText())  && !(tab[i][j].getText().equals("_"))){
                    TextView player  = (TextView) findViewById(R.id.joueur);
                    player.setText("winner " + inverse(joueur));
                    notif(inverse(joueur));
                    //onResume();
                    return true;
                }
            }
        }
        return false;
    }
    String inverse(String str){
        if(str.equals("X")) return "O";
        else return "X";
    }

    void notif(String winner){
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "YO")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!" + winner)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(0, mBuilder.build());
    }
}

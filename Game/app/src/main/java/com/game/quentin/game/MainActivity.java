package com.game.quentin.game;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<TextView> list = new ArrayList<>();
     String tour = "X";
     String gg = "";
     boolean game = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView t00 = (TextView) findViewById(R.id.t00);
        list.add(t00);
        TextView t01 = (TextView) findViewById(R.id.t01);
        list.add(t01);
        TextView t02 = (TextView) findViewById(R.id.t02);
        list.add(t02);
        TextView t10 = (TextView) findViewById(R.id.t10);
        list.add(t10);
        TextView t11 = (TextView) findViewById(R.id.t11);
        list.add(t11);
        TextView t12 = (TextView) findViewById(R.id.t12);
        list.add(t12);
        TextView t20 = (TextView) findViewById(R.id.t20);
        list.add(t20);
        TextView t21 = (TextView) findViewById(R.id.t21);
        list.add(t21);
        TextView t22 = (TextView) findViewById(R.id.t22);
        list.add(t22);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(game) {
            for (final TextView t : list) {
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (t.getText().equals("_")) {
                            t.setText(tour);
                            reverseTour(tour);
                            game = finishing();
                            if(game == false){
                                TextView joueur = (TextView) findViewById(R.id.Joueur);
                                joueur.setText("gg : " + gg);
                                notification();
                                for (final TextView t : list) {
                                    t.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            init();

                                        }
                                    });
                                }
                            }
                        }
                    }
                });
            }

        }

       TextView joueur = (TextView) findViewById(R.id.Joueur);
        joueur.setText("Joueur " + tour );

    }
    public void reverseTour(String str){
        if(str.equals("X")) tour = "O";
        else tour = "X";

    }

    boolean finishing(){
        //ligne
        for(int i = 0; i < 8; i =  i + 3){
            if(list.get(i).getText().equals(list.get(i+1).getText()) && list.get(i).getText().equals(list.get(i+2).getText()) &&  !(list.get(i).getText().equals("_"))){
                gg = list.get(i).getText().toString();
                onResume();
                return false;
            }
        }
        //Colonne
        for(int i = 0; i < 3; i =  i + 1){
            if(list.get(i).getText().equals(list.get(i+3).getText()) && list.get(i).getText().equals(list.get(i+6).getText()) &&  !(list.get(i).getText().equals("_"))){
                gg = list.get(i).getText().toString();
                onResume();
                return false;
            }
        }
        //diag
        if(list.get(0).getText().equals(list.get(4).getText()) && list.get(0).getText().equals(list.get(8).getText()) &&  !(list.get(0).getText().equals("_"))){
            gg = list.get(0).getText().toString();
            onResume();
            return false;
        }
        if(list.get(2).getText().equals(list.get(4).getText()) && list.get(2).getText().equals(list.get(6).getText()) &&  !(list.get(2).getText().equals("_"))){
            gg = list.get(2).getText().toString();
            onResume();
            return false;
        }
        //Null
        boolean to = true;
        for (final TextView t : list) {
            if(t.getText().toString().equals("_")){
                to = false;
            }
        }
        if(to == true){
            gg = "Null";
            onResume();
            return false;
        }
        return true;
    }
    void init(){
        for(final TextView t : list){
            t.setText("_");
            game = true;
            onResume();
        }
    }
    void notification(){
        if(gg.equals("X")) {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "ok")
                    .setSmallIcon(R.drawable.x)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                            R.drawable.x))
                    .setContentTitle("Vainqueur")
                    .setContentText("Joueur " + gg)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Joueur " + gg))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            notificationManager.notify(0, mBuilder.build());
        }
        else{
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "ok")
                    .setSmallIcon(R.drawable.o)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                            R.drawable.o))
                    .setContentTitle("Vainqueur")
                    .setContentText("Joueur " + gg)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Joueur " + gg))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            notificationManager.notify(0, mBuilder.build());
        }
    }
}

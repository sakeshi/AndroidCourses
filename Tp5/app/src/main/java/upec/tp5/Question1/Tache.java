package upec.tp5.Question1;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Quentin on 09/02/2018.
 */
public class Tache extends AsyncTask<Void, Integer, Void>
{
    private Context context;
    private ProgressBar mProgressBar;
    public Tache(ProgressBar p, Context context){
        mProgressBar = p;
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context.getApplicationContext(), "Début du traitement asynchrone", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        mProgressBar.setProgress(values[0]);
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        int progress;
        for (progress=0;progress<=100;progress++)
        {
            for (int i=0; i<1000000; i++){}
            //la méthode publishProgress met à jour l'interface en invoquant la méthode onProgressUpdate
            publishProgress(progress);
            progress++;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        Toast.makeText(context.getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
    }
}
package upec.decompte;

import android.content.Context;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Quentin on 09/02/2018.
 */

public class Tache extends AsyncTask<Void, Integer, Void> {

    private Context context;
    private ProgressBar mProgressBar;
    private TextView t;
    private int nb;
    public Tache(ProgressBar p, Context context,int nb,TextView t){
        p.setMax(nb);
        mProgressBar = p;
        this.context = context;
        this.nb = nb;
        this.t = t;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context.getApplicationContext(), "Début du décompte", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        t.setText(values[0].toString());
        mProgressBar.setProgress(values[0]);
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        int progress;
        for (progress=nb;progress>=0;progress--)
        {

            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            publishProgress(progress);

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        Toast.makeText(context.getApplicationContext(), "Stop", Toast.LENGTH_LONG).show();
        ((ViewGroup)mProgressBar.getParent()).removeView(mProgressBar);

    }
}

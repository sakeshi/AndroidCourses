package upec.spirit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Created by quentin on 04/02/18.
 */

public class Dessin extends View{
    Dessin d = (Dessin) findViewById(R.id.dessin);

    Point p = new Point(400,400);

    public Point getPs() {
        return ps;
    }

    Point ps = new Point(400,400);
   public Dessin(Context context, AttributeSet attrs){
       super(context,attrs);
   }
   protected void onDraw(Canvas canvas){
       super.onDraw(canvas);
       Paint paint = new Paint();

       paint.setColor(Color.BLUE);
       canvas.drawCircle(p.getX(),p.getY(),200,paint);
       paint.setColor(Color.WHITE);

       canvas.drawCircle(ps.getX(),ps.getY(),10,paint);

   }



    public class Point{
        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        float x;

        public void setX(float x) {
            this.x = x;
        }

        public void setY(float y) {
            this.y = y;
        }

        float y;
        public Point(float x, float y){
            this.x = x;
            this.y = y;
        }
    }

}

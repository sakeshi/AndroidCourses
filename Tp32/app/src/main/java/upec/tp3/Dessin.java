package upec.tp3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quentin on 14/01/2018.
 */

public class Dessin extends View {
    private List<Point> points = new ArrayList<>();
    private Paint cpaint= new Paint();
    public Dessin(Context context) {
        super(context);
        cpaint.setStrokeWidth(5.0f);
        cpaint.setColor(Color.BLACK);
        //points.add(new Point(10,10,cpaint));
    }/*
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        for(Point p : points){
            canvas.drawPoint(p.x,p.y,p.e);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        points.add(new Point(5,5,cpaint));
        invalidate();
        return true;

    }*/
}

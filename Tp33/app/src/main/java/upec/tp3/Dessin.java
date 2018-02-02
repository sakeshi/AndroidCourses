package upec.tp3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Dessin extends View {

    List<Point> points = new ArrayList<>();

    String color ="Black";
    float stroke = 5.0f;
    public Dessin(Context context,  AttributeSet attrs) {
        super(context,attrs);
    }

    public void setValue(String a){
        color = a;
    }
    public void setWidth(float f){
        stroke = f;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(Point p : points){
            canvas.drawPoint(p.getX(),p.getY(), p.getPaint());
        }
    }
    public boolean onTouchEvent( MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        Paint pt = new Paint();
        pt.setStrokeWidth(stroke);
        if(color.equals("BLACK"))
            pt.setColor(Color.BLACK);
        if(color.equals("Red"))
            pt.setColor(Color.RED);
        if(color.equals("Green"))
            pt.setColor(Color.GREEN);
        if(color.equals("Blue"))
            pt.setColor(Color.BLUE);
        addPoint(event.getX() , event.getY(), pt) ;
        invalidate();
        return true;

    }
    public void clearDessin(){
        points.clear();
        invalidate();
    }
    public void addPoint(float x, float y, Paint c){
        points.add(new Point(x,y,c));
    }
}

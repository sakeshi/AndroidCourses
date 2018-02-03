package upec.tp4;

import android.graphics.Paint;

/**
 * Created by Quentin on 03/02/2018.
 */


public class Point {
    float x;
    float y;
    Paint paint;
    int e;
    public Point(float x, float y, Paint paint){
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public float getE() {
        return y;
    }
    public Paint getPaint(){
        return paint;
    }
}

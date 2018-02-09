package upec.tp4;

import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Quentin on 03/02/2018.
 */


public class Point implements Parcelable {
    float x;
    float y;
    Paint paint;
    int e;
    public Point(float x, float y, Paint paint){
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    protected Point(Parcel in) {
        x = in.readFloat();
        y = in.readFloat();
        e = in.readInt();
    }

    public static final Creator<Point> CREATOR = new Creator<Point>() {
        @Override
        public Point createFromParcel(Parcel in) {
            return new Point(in);
        }

        @Override
        public Point[] newArray(int size) {
            return new Point[size];
        }
    };

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public Paint getPaint(){
        return paint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(x);
        parcel.writeFloat(y);
        parcel.writeInt(e);
    }
}

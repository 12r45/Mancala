package ryan.mancala.board;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by ryan on 4/27/2015.
 */
public class Rock {

    //radial percentage of the radius in each direction
    private float x;
    private float y;
    private int red;
    private int g;
    private int b;
    private int a;


    public Rock(float x, float y)
    {
        this.x = x;
        this.y = y;
       red= ((int)(Math.random()*255));
        g = ((int)(Math.random()*255));
        b = ((int)(Math.random()*255));
        a =255;
    }

    public float getX()
    {
        return x;

    }

    public float getY()
    {
        return y;
    }

    public void render(Canvas canvas,Paint paint, float lx, float ly ,float r)
    {
        paint.setARGB(a,red,g,b);

        if(canvas != null)
        canvas.drawCircle(x*r+lx,y*r+ly,r/5,paint);
    }
    public void renderM(Canvas canvas,Paint paint, float lx, float ly ,float rx,float ry,float r)
    {
        paint.setARGB(a,red,g,b);

        if(canvas != null)
            canvas.drawCircle(x*rx+lx,y*ry+ly,r/5,paint);
    }





}

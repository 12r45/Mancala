package ryan.mancala.board;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by ryan on 4/27/2015.
 */
public class Hole {
    final String TAG = Hole.class.getSimpleName();
    //private int rocks;
    protected ArrayList<Rock> rocks;
    private boolean mancala;
    protected boolean team;
    public Hole(int numRocks,boolean mancala,boolean team)
    {
        this.mancala = mancala;
        this.team = team;
        rocks = new ArrayList<>();
        for(int k = 0 ; k < numRocks; k++)
        {
            addRock();
        }
    }

    public int getRocks()
    {
        return rocks.size();
    }
    public boolean isMancala()
    {
        return this.mancala;
    }
    public boolean team()
    {
        return this.team;
    }
    public void addRock()
    {
        rocks.add(new Rock((float)(Math.random()*1.25 -.5),(float)(Math.random()*1.25-.5)));
    }
    public int take()
    {
        int temp = rocks.size();
        rocks.clear();
        return temp;
    }




    public void render(Canvas canvas,Paint paint, float x, float y ,float r, boolean selected)
    {
        if(team)
        paint.setColor(Color.BLACK);
        else
        {
            paint.setColor(Color.MAGENTA);
        }
        if(selected)
        {
            paint.setColor(Color.CYAN);
        }
        if(canvas != null)
        canvas.drawCircle(x,y,r,paint);
        paint.setColor(Color.BLACK);
        if(canvas != null) {
            if (team) {
                float lx = x - 2 * r;
                canvas.rotate(90, lx, y);
                canvas.drawText("" + rocks.size(), lx, y, paint);
                canvas.rotate(-90, lx, y);
            } else {
                float lx = x + 2 * r;
                canvas.rotate(-90, lx, y);
                canvas.drawText("" + rocks.size(), lx, y, paint);
                canvas.rotate(90, lx, y);
            }
        }
        float rRock = r /10;
        float locy = y -r + rRock;
        float locx = x;
        for(int k = 0 ;k<rocks.size();k++ )
        {
        rocks.get(k).render(canvas,paint,x,y,r);
        }


    }


}

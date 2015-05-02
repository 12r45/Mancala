package ryan.mancala.board;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by ryan on 4/27/2015.
 */
public class Mancala extends Hole {
    final String TAG = Mancala.class.getSimpleName();
    public Mancala(int numRocks,boolean mancala,boolean team) {

        super(numRocks,mancala,team);


    }

    @Override
    public void addRock()
    {
        rocks.add(new Rock((float)(Math.random()),(float)(Math.random())));
    }

    @Override
    public void render(Canvas canvas,Paint paint, float x, float y ,float r,boolean selected)
    {
        paint.setTextSize(64);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.YELLOW);

        float width = x*3;

        if(canvas != null) {

            canvas.drawRect(x - r, y, x + 5 * r, y + r, paint);
            paint.setColor(Color.BLACK);
            if (super.team) {
                canvas.rotate(-90, width/2, y + r*2);
                canvas.drawText("" + rocks.size(), width/2, y + r*2, paint);
                canvas.rotate(90,width/2, y + r*2);
            } else {
                canvas.rotate(90, width/2, y - r);
                canvas.drawText("" + rocks.size(), width/2, y - r, paint);
                canvas.rotate(-90, width/2,  y - r);
            }

        }
        float rRock = r /10;

        float locy = y -r + rRock;
        float locx = x;

        for(int k = 0 ;k< rocks.size();k++ )
        {
            //Log.d(TAG,""+k);
            super.rocks.get(k).renderM(canvas,paint,x,y+r/5, r*4,(float) (r*.6),r);

        }


    }


}

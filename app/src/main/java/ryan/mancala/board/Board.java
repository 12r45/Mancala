package ryan.mancala.board;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import ryan.mancala.MThread;

/**
 * Created by ryan on 4/27/2015.
 */
public class Board extends SurfaceView implements SurfaceHolder.Callback{
    final String TAG = Board.class.getSimpleName();
    final float r = 100;
    private ArrayList<Hole> holes;
    private MThread thread;
    private int numHoles;
    private Paint paint;
    private int selected;
    private boolean turn;

    public Board(Context context,int numHoles){
        super(context);
        selected  = -1;
        paint = new Paint();
        this.numHoles = numHoles;
        turn  = true;
        holes = new ArrayList<>();

        holes.add(new Mancala(0,true,true));

        for(int k = 0; k < numHoles; k++)
        {
            holes.add(new Hole(5,false,true));
        }

        holes.add(new Mancala(0,true,false));

        for(int k = 0; k < numHoles; k++)
        {
            holes.add(new Hole(5,false,false));
        }



    }


    public void takeTurn()
    {
        int rocks = holes.get(selected).take();

        int temp = selected;
        boolean lastRock = false;
        while(rocks != 0 )
        {
            temp = temp < holes.size()-1 ? temp +1 : 0;
            Log.d(TAG, "" + temp);
            holes.get(temp).addRock();
            rocks--;


        }

        if(holes.get(temp).getRocks() == 1 && holes.get(temp).team() == turn )
        {
            Log.d(TAG, "capture");

            int captured = holes.get(holes.size() - temp).take();

            if(turn)
            {
                for(int k = 0; k <= captured;k++)
                holes.get(7).addRock();
            }
            else
            {
                for(int k = 0; k <= captured;k++)
                    holes.get(0).addRock();
            }
        }

        if(  temp == 7 && turn)
        {
        lastRock = true;
        }
        else if(temp == 0 && !turn)
        {
            lastRock = true;
        }
        Log.d(TAG, "++++" + lastRock + " " + temp);




        if(!lastRock)
            turn = !turn;

        selected = -1;
    }


    public void update()
    {
        if(selected != -1 && holes.get(selected).team() == turn)
        {
            takeTurn();
        }

    }

    public void select(float x, float y)
    {


        boolean team = false;


        float lx = (x) /(getWidth()/3);
        if(x > getWidth()/3 - r && x < getWidth()/3 + r)
        {
            team =true;
        }
        else if(x > getWidth()/3 +r*3&& x < getWidth()/3 + r*5)
        {
            team  =false;
        }
        else
        {
           return;
        }

        int num = (int)((y+r)/(getHeight()/7));




        if(!team)
        {
            num = 14-num;
            //num += 7;
        }
        Log.d(TAG, team + " " + num);
        selected = num;

    }
    public void render(Canvas canvas)
    {




        if(canvas !=null)
        {
            if(turn)
            paint.setColor(Color.GREEN);
            else
            paint.setColor(Color.RED);
            canvas.drawRect(0,0,getWidth()/2,getHeight(),paint);

            if(turn)
                paint.setColor(Color.RED);
            else
                paint.setColor(Color.GREEN);
            canvas.drawRect(getWidth()/2,0,getWidth(),getHeight(),paint);

        }


        int count1 = 0;
        int count2 = 0;



        for(Hole h : holes)
        {

            if(h.team())
            {
                if(!h.isMancala())
                {
                    h.render(canvas,paint,getWidth()/3,getHeight()*count1 * 1/7  ,r,count2 == selected);

                }
                else{
                    h.render(canvas,paint,getWidth()/3,getHeight()*count1 * 1/8  ,r,count2 == selected);
                }

                count1++;
            }

            else{
                int tempcount = count2;

                if(!h.isMancala())
                {
                    count1--;
                    h.render(canvas,paint,getWidth()*2/3 ,(getHeight()*count1 * 1/7) ,r,tempcount == selected);
                }

                else{
                    h.render(canvas,paint,getWidth()/3,(getHeight()-r) ,r,tempcount == selected);
                }



            }
            count2++;
        }


    }



    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {


    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
    }

    public void killTread()
    {
       thread.setRunning(false);
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
    }
    public void stopThread()
    {
        thread.setRunning(false);
    }

    public void reStartThread() {

        thread.setRunning(true);
        //thread.start();
    }

    public void startThread()
    {
        thread = new MThread(getHolder(),this);
        thread.setRunning(true);
        thread.start();
    }

}

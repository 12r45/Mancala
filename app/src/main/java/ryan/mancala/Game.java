package ryan.mancala;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.*;
import com.google.android.gms.common.api.ResultCallback;
import ryan.mancala.board.Board;


public class Game extends Activity  {

    final String TAG = Game.class.getSimpleName();
    Board board;
    SharedPreferences levels;
    public static final String PREFS_NAME = "levels";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        board = (new Board(this,6));
        setContentView(board);
        Log.d(TAG, "View added");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);
        //

        if (event.getAction() == MotionEvent.ACTION_UP)
        {

            board.select(event.getX(), event.getY());

        }

        return true;

    }

    @Override

    protected void onDestroy() {

        Log.d(TAG, "Destroying...");

        super.onDestroy();
        Log.d(TAG, "destroy");

        board.killTread();
    }



    @Override

    protected void onStop() {


        Log.d(TAG, "stop");
        super.onStop();
        board.stopThread();
        board.killTread();

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //board.killTread();
        Log.d(TAG, "pause");
        //board.stopThread();
        board.stopThread();
        board.killTread();
    }

    @Override
    protected void onResume()
    {   super.onResume();
        //board.reStartThread();
        Log.d(TAG, "resume");
       board.startThread();

    }




}

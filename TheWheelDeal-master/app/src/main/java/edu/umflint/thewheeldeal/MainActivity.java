package edu.umflint.thewheeldeal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.GestureDetectorCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;
import edu.umflint.thewheeldeal.OpenALPR_API.API;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String testImageBytes = "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"; //This is a yellow cobalt

        /*
        try {
            JSONObject info = new API().execute(testImageBytes).get(); //This is how you call the API. testImageBytes variable must be Base64 Encoded Image
            Log.i("Info", info.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        */

        gestureObject = new GestureDetectorCompat(this, new LearnGesture());

    }

    @Override
        public boolean onTouchEvent(MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY){
            if (event2.getX() > event1.getX()){
            //left to right swipe
                Intent intent = new Intent(
                        MainActivity.this, Main2Activity.class);
                finish();
                startActivity(intent);
            }
            else if (event2.getX() < event1.getX()){
            //right to left swipe
            }
            return true;
        }
    }
}

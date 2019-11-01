package com.kunal.gestureplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements  GestureDetector.OnGestureListener {

    String videoPath;
    TextView editText ;
    Button button;
    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        editText.setText("");
        VideoView videoView = findViewById(R.id.videoView);
         videoPath = "android.resource://"+getPackageName()+"/"+R.raw.happy_birthday;

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        gestureDetector = new GestureDetector(this);

    }

    @Override
    public boolean onDown(MotionEvent e) {
       return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
       return false;
    }

    @Override
    public boolean onScroll(MotionEvent downEvent, MotionEvent moveEvent, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result = false;
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();
        if (Math.abs(diffX) > Math.abs(diffY)){
            // right or left swipe
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
                if (diffX > 0){
                    onSwipeRight();
                }
                else {
                    onSwipeLeft();
                }
                result = true;
            }
        }
        else {
            // up or down swipe
            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD){
                if (diffY>0){
                    onSwipeBottom();
                }else {
                    onSwipeTop();
                }
                result = true;
            }
        }

        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void onSwipeTop() {
        button.setVisibility(View.GONE);
        button.setEnabled(false);
        editText.setText("");
        editText.setVisibility(View.GONE);
        editText.setEnabled(false);
        VideoView videoView = findViewById(R.id.videoView);
         videoPath = "android.resource://"+getPackageName()+"/"+R.raw.lahore_official_video;

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    private void onSwipeBottom() {
        button.setVisibility(View.GONE);
        button.setEnabled(false);
        editText.setText("");
        editText.setVisibility(View.GONE);
        editText.setEnabled(false);
        VideoView videoView = findViewById(R.id.videoView);
         videoPath = "android.resource://"+getPackageName()+"/"+R.raw.kya_baat_ay;

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    private void onSwipeLeft() {
        editText.setVisibility(View.GONE);
        editText.setEnabled(false);
        editText.setText("");
        button.setVisibility(View.VISIBLE);
        button.setEnabled(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FancyToast.makeText(getApplicationContext(),"Subscribed !",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
            }
        });
    }

    private void onSwipeRight() {
        String kyabaatay = "android.resource://"+getPackageName()+"/"+R.raw.kya_baat_ay;
        String lahore = "android.resource://"+getPackageName()+"/"+R.raw.lahore_official_video;
        String happy_birthday = "android.resource://"+getPackageName()+"/"+R.raw.happy_birthday;
        editText.setVisibility(View.VISIBLE);
        editText.setEnabled(true);
        button.setEnabled(false);
        button.setVisibility(View.GONE);
        if (videoPath.equals(kyabaatay)){
            editText.setText("User Profile \n\n Song : Kya Baat Ay \n\n  ");
        }
        else if (videoPath.equals(lahore)){
            editText.setText("User Profile \n\n Song : Lahore \n\n  ");
        }
        else if (videoPath.equals(happy_birthday)){
            editText.setText("User Profile \n\n Song : Happy Birthday \n\n  ");
        }

    }

}

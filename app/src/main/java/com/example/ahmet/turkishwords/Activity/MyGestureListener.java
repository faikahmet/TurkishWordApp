package com.example.ahmet.turkishwords.Activity;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class MyGestureListener implements GestureDetector.OnGestureListener {
    private Context context;
    public MyGestureListener() {
    }

    public MyGestureListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (motionEvent.getX()-motionEvent1.getX()>50){
       //     Toast.makeText(context, "Left", Toast.LENGTH_SHORT).show();
            return  true;
        }
        if (motionEvent1.getX()-motionEvent.getX()>50){
            Toast.makeText(context, "Right", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}

package com.example.ac13002utilidades;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
public class GestosActivity extends Activity implements OnGestureListener {
    private TextView viewA;
    private ImageView imagen1,imagen2;
    private GestureDetector gestureScanner;
    /* fill the background ImageView with the resized image */
    LinearLayout linearLayout;
    Drawable drawable1,drawable2,drawable3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gestos_activity);
        gestureScanner = new GestureDetector(this);
        viewA = (TextView) findViewById(R.id.gesture_text);
        imagen1=(ImageView) findViewById(R.id.imageView1);
        imagen2=(ImageView) findViewById(R.id.imageView2);
        imagen1.setVisibility(View.INVISIBLE);
        Resources res = getResources(); //resource handle
        drawable1 = res.getDrawable(R.drawable.fondo1);
        drawable2 = res.getDrawable(R.drawable.fondo2);
        drawable3 = res.getDrawable(R.drawable.fondo3);
        //new Image that was added to the res folder
        linearLayout = (LinearLayout)findViewById(R.id.gesture_layout);
        linearLayout.setBackgroundDrawable(drawable1);
    }
    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return gestureScanner.onTouchEvent(me);
    }
    @Override
    public boolean onDown(MotionEvent e) {
        viewA.setText("-" + "PULSACIÓN" + "-");
        return true;
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float
            velocityY) {
        viewA.setText("-" + "LANZAMIENTO" + "-");
        imagen1.setVisibility(View.VISIBLE);
        imagen2.setVisibility(View.INVISIBLE);
        return true;
    }
    @Override
    public void onLongPress(MotionEvent e) {
        viewA.setText("-" + "PULSACIÓN LARGA" + "-");
        linearLayout.setBackgroundDrawable(drawable1);
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float
            distanceY) {
        viewA.setText("-" + "ARRASTRE" + "-");
        imagen2.setVisibility(View.VISIBLE);
        imagen1.setVisibility(View.INVISIBLE);
        return true;
    }
    @Override
    public void onShowPress(MotionEvent e) {
        viewA.setText("-" + "MANTENIENDO PULSACIÓN" + "-");
        linearLayout.setBackgroundDrawable(drawable2);
    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        viewA.setText("-" + "PULSACIÓN SIMPLE" + "-");
        linearLayout.setBackgroundDrawable(drawable3);
        return true;
    }
}

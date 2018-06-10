package com.techblogopedia.crazyeight;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class TitleView extends View {

    private Bitmap titleGraphic;
    private Bitmap playButtonUp;
    private Bitmap playButtonDown;
    private boolean playButtonPressed;
    private int screenW;
    private int screenH;
    private Context myContext;

    public TitleView (Context context){
        super(context);
        myContext=context;
        titleGraphic = BitmapFactory.decodeResource(getResources(), R.drawable.title_graphic);
        playButtonUp = BitmapFactory.decodeResource(getResources(),R.drawable.play_button_up);
        playButtonDown = BitmapFactory.decodeResource(getResources(),R.drawable.play_button_down);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(titleGraphic, (screenW - titleGraphic.getWidth()) / 2, 0, null);

        if (playButtonPressed) {
            canvas.drawBitmap(playButtonDown,(screenW-playButtonDown.getWidth())/2,(int)(screenH*0.6),null);
        } else {
            canvas.drawBitmap(playButtonUp, (screenW - playButtonUp.getWidth()) / 2, (int) (screenH * 0.6), null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();
        int X = (int)event.getX();
        int Y = (int)event.getY();

        switch (eventaction){
            case MotionEvent.ACTION_DOWN:
                if(X > (screenW-playButtonUp.getWidth())/2 && X < ((screenW-playButtonUp.getWidth())/2+playButtonUp.getWidth()) &&
                Y > (int)(screenH*0.6) && Y < (int) (screenH*0.6)+playButtonUp.getHeight()){
                    playButtonPressed = true;
            }
                break;
            case MotionEvent.ACTION_MOVE: break;
            case MotionEvent.ACTION_UP:
                if(playButtonPressed){
                    Intent gameIntent = new Intent(myContext,GameActivity.class);
                    myContext.startActivity(gameIntent);
                }

                playButtonPressed = false;
                break;
        }
        invalidate();
        return true;
    }
}

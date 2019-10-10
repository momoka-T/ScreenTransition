package jp.ac.shohoku.O.screentransition;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MainView extends View{
    public final int FIRST = 1;
    public final int SECOND = 2; //状態を表す定数
    int state;

    public MainView(Context context, AttributeSet attrs) {
      super(context, attrs);
      state = FIRST;
      }
     @Override
     protected void onDraw(Canvas canvas) {
       super.onDraw(canvas);
       Paint p = new Paint();

       if(state == FIRST) { //状態1の場合の描画
           p.setColor(Color.BLUE);
           canvas.drawARGB(255, 255, 255, 255);
           canvas.drawRect(100, 100, 300, 200, p);
       } else if (state == SECOND){  //状態2の場合の描画
           p.setColor(Color.RED);
           canvas.drawARGB(255, 255, 255, 0);
           canvas.drawRect(100, 100, 300, 200, p);
           } else {  //それ以外の場合は，Logにエラーを吐き出す
               Log.d("error", "never come here");
           }
       }

       @Override
       public boolean onTouchEvent(MotionEvent event) {
           int x = (int) event.getX();
           int y = (int) event.getY();

           if (x > 100 && x < 300 && y > 100 && y < 200) {
               if (state == FIRST) {  //状態１だったら状態２へ
                   state = SECOND;
               } else if (state == SECOND) { //状態2だったら状態1へ
                   state = FIRST;
               } else {  //それ以外だったらエラーを吐き出す
                   Log.d("error", "never come here");
               }
               invalidate();  //再描画
               return super.onTouchEvent(event);
           }
       }

package com.fullall.lmq.lmqmotion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {
    //创建一个用于识别收拾的GestureDetector对象waiyuwu.blogcn.com
    private GestureDetector detector = new GestureDetector(this);
    //定义一个数组，用于放漂亮的图片
    int[] girls = new int[]{R.drawable.pic1, R.drawable.pic2, R.drawable.pic3};
    //定义数组下标，以方便观看各个图片
    private int index;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView)findViewById(R.id.image);
        //设置一个初始显示的girl吧
        image.setImageResource(girls[index]);
        //监听这个ImageView组件上的触摸屏时间
        image.setOnTouchListener(this);
        //下面两个要记得设哦，不然就没法处理轻触以外的事件了，例如抛掷动作。
        image.setLongClickable(true);
        detector.setIsLongpressEnabled(true);
    }

    //用于呼喊下一个女孩的方法
    public void goNext(){
        index++;
        index = Math.abs(index % girls.length);
        image.setImageResource(girls[index]);
    }

    //用户呼唤上一个女孩的方法
    public void goPrevious(){
        index--;
        index = Math.abs(index % girls.length);
        image.setImageResource(girls[index]);
    }

    //重写OnTouchListener的onTouch方法
    //此方法在触摸屏被触摸，即发生触摸事件（接触和抚摸两个事件，挺形象）的时候被调用。
//    按下（onDown）： 刚刚手指接触到触摸屏的那一刹那，就是触的那一下。
//    抛掷（onFling）： 手指在触摸屏上迅速移动，并松开的动作。
//    长按（onLongPress）： 手指按在持续一段时间，并且没有松开。
//    滚动（onScroll）： 手指在触摸屏上滑动。
//    按住（onShowPress）： 手指按在触摸屏上，它的时间范围在按下起效，在长按之前。
//    抬起（onSingleTapUp）：手指离开触摸屏的那一刹那。
//    除了这些定义之外，鄙人也总结了一点算是经验的经验吧，在这里和大家分享一下。
//
//    任何手势动作都会先执行一次按下（onDown）动作。
//    长按（onLongPress）动作前一定会执行一次按住（onShowPress）动作。
//    按住（onShowPress）动作和按下（onDown）动作之后都会执行一次抬起（onSingleTapUp）动作。
//    长按（onLongPress）、滚动（onScroll）和抛掷（onFling）动作之后都不会执行抬起（onSingleTapUp）动作。
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    //在按下动作时被调用
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    //在抛掷动作时被调用
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        //velocityX表示横向的移动，根据手指移动的方向切换女孩
        if(velocityX < 0){
            goNext();
        }else if(velocityX > 0){
            goPrevious();
        }
        return false;
    }

    //在长按时被调用
    @Override
    public void onLongPress(MotionEvent e) {
        Toast.makeText(this,"长按了",Toast.LENGTH_SHORT).show();
    }

    //在滚动时调用
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return false;
    }

    //在按住时被调用
    @Override
    public void onShowPress(MotionEvent e) {
    }

    //在抬起时被调用
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
}
package sidebar.eastaeon.com.contactssidebar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wu.xingbiao on 2016/1/13.
 */
public class ContactsSideBar extends View {

    public static String [] letters = new String[] { "#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z" };

    private OnTouchingLetterChangedListener touchListener;
    Paint p = new Paint(); //字母画笔
    Paint bgRectP = new Paint();//选中字母的背景画笔
    float firstLeth = 0f;//第一个字母的长度
    int choose = -1;//记录选中的字母
    boolean isBg = false;

    public ContactsSideBar(Context context) {
        super(context);
        init();
    }

    public ContactsSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ContactsSideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        p.setTextSize(24.0f);
        bgRectP.setColor(Color.parseColor("#CCCCCC"));
        firstLeth = p.measureText("#");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int width = getWidth();
        final int height = getHeight();
        final int count = letters.length;
        final int itemHeight = height / count;
        float sRectPos = (float) (width - firstLeth) / 2 - firstLeth - 2;
        float sRectPos2 = sRectPos + 3.0f * firstLeth;
        float xPos = (float) (width - firstLeth) / 2;
        for (int i = 0; i < count; i++) {
            p.setFakeBoldText(true);
            p.setAntiAlias(true);
            int yPos = itemHeight * (i + 1);
            if (choose == i) {
                isBg = true;
                p.setTextSize(25.0f);
                p.setColor(Color.RED);
                canvas.drawRect(sRectPos, yPos - firstLeth * 2, sRectPos2, yPos + firstLeth, bgRectP);
            }
            canvas.drawText(letters[i], xPos, yPos, p);
            p.reset();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float downY = event.getY();
        int c = (int) (downY / getHeight() * letters.length);

        switch (action){
            case MotionEvent.ACTION_DOWN:
                isBg = true;
                if (choose != c && touchListener != null) {
                    doOnActionDown(c);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (choose != c && touchListener != null) {
                    doOnActionDown(c);
                }
                break;
            case MotionEvent.ACTION_UP:
                isBg = false;
                invalidate();
                break;
        }
        return true;
    }

    public void doOnActionDown(int c){
        if(c > 0 && c < letters.length) {
            if (touchListener != null) {
                touchListener.onTouchingLetterChanged(c);
                choose = c;
                invalidate();
            }
//        }else{
//            c = c-1;
//            doOnActionDown(c);
        }
    }

    /**
     * 用来通知activity显示选中的字母
     */
    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(int s);
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener touchListener) {
        this.touchListener = touchListener;
    }

}

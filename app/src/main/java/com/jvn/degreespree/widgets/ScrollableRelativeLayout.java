package com.jvn.degreespree.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by john on 9/25/15.
 */
public class ScrollableRelativeLayout extends RelativeLayout {

    public ScrollableRelativeLayout(Context context) {
        super(context);
        initScrollableImageView();
    }

    public ScrollableRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScrollableImageView();
    }

    public ScrollableRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initScrollableImageView();
    }

    private void initScrollableImageView() {

    }

    private int parentWidth;
    private int parentHeight;
    private int maxX, maxY;
    private float downX, downY;
    private int totalX = 0;
    private int totalY = 0;

    private int maxLeft, maxRight, maxTop, maxBottom;

    private void setScrollLimits() {
        parentWidth = ((View) this.getParent()).getWidth();
        parentHeight = ((View) this.getParent()).getHeight();

        maxX = this.getWidth() - parentWidth;
        maxY = this.getHeight() - parentHeight;

        maxLeft = 0;
        maxRight = maxX;
        maxTop = 0;
        maxBottom = maxY;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setScrollLimits();
        float currentX, currentY;
        int scrollByX, scrollByY;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                currentY = event.getY();
                scrollByX = (int)(downX - currentX);
                scrollByY = (int)(downY - currentY);

                // scrolling to left side of image (pic moving to the right)
                if (currentX > downX)
                {
                    if (totalX == maxLeft)
                    {
                        scrollByX = 0;
                    }
                    if (totalX > maxLeft)
                    {
                        totalX = totalX + scrollByX;
                    }
                    if (totalX < maxLeft)
                    {
                        scrollByX = maxLeft - (totalX - scrollByX);
                        totalX = maxLeft;
                    }
                }

                // scrolling to right side of image (pic moving to the left)
                if (currentX < downX)
                {
                    if (totalX == maxRight)
                    {
                        scrollByX = 0;
                    }
                    if (totalX < maxRight)
                    {
                        totalX = totalX + scrollByX;
                    }
                    if (totalX > maxRight)
                    {
                        scrollByX = maxRight - (totalX - scrollByX);
                        totalX = maxRight;
                    }
                }

                // scrolling to top of image (pic moving to the bottom)
                if (currentY > downY)
                {
                    if (totalY == maxTop)
                    {
                        scrollByY = 0;
                    }
                    if (totalY > maxTop)
                    {
                        totalY = totalY + scrollByY;
                    }
                    if (totalY < maxTop)
                    {
                        scrollByY = maxTop - (totalY - scrollByY);
                        totalY = maxTop;
                    }
                }

                // scrolling to bottom of image (pic moving to the top)
                if (currentY < downY)
                {
                    if (totalY == maxBottom)
                    {
                        scrollByY = 0;
                    }
                    if (totalY < maxBottom)
                    {
                        totalY = totalY + scrollByY;
                    }
                    if (totalY > maxBottom)
                    {
                        scrollByY = maxBottom - (totalY - scrollByY);
                        totalY = maxBottom;
                    }
                }

                this.scrollBy(scrollByX, scrollByY);
                downX = currentX;
                downY = currentY;
                break;
            case MotionEvent.ACTION_UP:
            //    currentX = event.getRawX();
            //    currentY = event.getRawY();
           //     this.scrollBy((int) (downX - currentX), (int) (downY - currentY));
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }


        return true;
    }



}

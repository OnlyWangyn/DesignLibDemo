package com.wyn.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.wyn.activity.R;

/**
 * Created by nancy on 15-9-2.
 */
public class NestScrollingCoordinatorLayout extends CoordinatorLayout {
    private View mFab;
    private Context mContext;
    public NestScrollingCoordinatorLayout(Context context) {
        super(context);
        mContext = context;
    }

    public NestScrollingCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public NestScrollingCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        init();
    }

    private void init(){

        for(int i=0;i<getChildCount();i++){
            View view = getChildAt(i);
            if(view instanceof FloatingActionButton){
                mFab = view;
                break;
            }
        }
    }
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if(dyConsumed > 0 && mFab !=null){
            ObjectAnimator yAnimator = ObjectAnimator.ofFloat(mFab,"translationY",0,mFab.getHeight()+
                    mContext.getResources().getDimensionPixelOffset(R.dimen.activity_vertical_margin));
            yAnimator.setInterpolator(new AccelerateInterpolator(2));
            yAnimator.start();
        }else if(dyConsumed < 0 && mFab !=null){
            ObjectAnimator yAnimator = ObjectAnimator.ofFloat(mFab,"translationY",0f,0f);
            yAnimator.setInterpolator(new AccelerateInterpolator(2));
            yAnimator.start();
        }
    }
}

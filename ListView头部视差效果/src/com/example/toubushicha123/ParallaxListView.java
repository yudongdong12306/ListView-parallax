package com.example.toubushicha123;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

public class ParallaxListView extends ListView {

	private ImageView imageView;
	protected int maxHeight;
	private int imageViewheight;

	public ParallaxListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ParallaxListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ParallaxListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void setImagerView(final ImageView imageView) {
		this.imageView = imageView;
		imageView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						imageView.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						int drawableHeight = imageView.getDrawable()
								.getIntrinsicHeight();
						imageViewheight = imageView.getHeight();
						if (drawableHeight > imageViewheight) {
							maxHeight = drawableHeight;
						} else {
							maxHeight = 2 * imageViewheight;
						}
					}
				});
	}

	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
			int scrollY, int scrollRangeX, int scrollRangeY,
			int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
		if (deltaY < 0 && isTouchEvent) {
			int height = imageView.getHeight() - deltaY / 3;
			if (height > maxHeight)
				height = maxHeight;
			imageView.getLayoutParams().height = height;
			imageView.requestLayout();
		}
		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
				scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY,
				isTouchEvent);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_UP) {
			ValueAnimator valueAnimator = ValueAnimator.ofInt(
					imageView.getHeight(), imageViewheight);
			valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					int animatedValue = (Integer) animation.getAnimatedValue();
					imageView.getLayoutParams().height = animatedValue;
					imageView.requestLayout();
				}
			});
			valueAnimator.setDuration(350);
			valueAnimator.setInterpolator(new OvershootInterpolator());
			valueAnimator.start();
		}
		return super.onTouchEvent(ev);
	}

}

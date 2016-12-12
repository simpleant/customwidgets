package com.antwei.customwidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Ant on 2015/10/21.
 */
public class AtMostListView extends ListView {


    public interface PagingableListener {
        void onLoaderMore();
    }

    private OnScrollListener mOnScrollListener;
    private PagingableListener pagingableListener;
    private boolean isLoading, hasMore, scrollable;


    public AtMostListView(Context context) {
        super(context);
        init();
    }

    public AtMostListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AtMostListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (scrollable) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            int mostSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, mostSpec);
        }

    }


    private void init() {
        hasMore = false;
        isLoading = false;
        scrollable = true;
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (mOnScrollListener != null) {
                    mOnScrollListener.onScrollStateChanged(view, scrollState);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mOnScrollListener != null) {
                    mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                }

                int lastVisibleItem = firstVisibleItem + visibleItemCount;
                if (hasMore && !isLoading && lastVisibleItem == totalItemCount) {
                    if (pagingableListener == null) return;
                    isLoading = true;
                    pagingableListener.onLoaderMore();
                }

            }

        });
    }

    public void setmOnScrollListener(OnScrollListener mOnScrollListener) {
        this.mOnScrollListener = mOnScrollListener;
    }

    public void setPagingableListener(PagingableListener pagingableListener) {
        this.pagingableListener = pagingableListener;
    }

    public void loadingFinished() {
        this.isLoading = false;
    }

    public void hasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public boolean isScrollable() {
        return scrollable;
    }
}

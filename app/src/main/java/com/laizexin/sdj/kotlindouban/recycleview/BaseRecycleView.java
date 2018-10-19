package com.laizexin.sdj.kotlindouban.recycleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.laizexin.sdj.kotlindouban.R;

/**
 * Created by laizexin on 2018/7/9.
 */

public class BaseRecycleView extends RecyclerView {
    private boolean isOpen = true;//默认打卡
    private View emptyView;

    public BaseRecycleView(Context context) {
        super(context);
    }

    public BaseRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BaseRecycleView openAnimation(boolean open){
        this.isOpen = open;
        return this;
    }

    public BaseRecycleView addAnim(LayoutAnimationController loadLayoutAnimation){
        if(isOpen){
            this.setLayoutAnimation(loadLayoutAnimation);
        }
        return this;
    }

    public BaseRecycleView setMyLayoutManager(LayoutManager layout){
        this.setLayoutManager(layout);
        return this;
    }

    public BaseRecycleView setDefaultAnimation(){
        this.addAnim(AnimationUtils.loadLayoutAnimation(getContext(), R.anim.recycle_item_anim));
        return this;
    }

//    //解决与SwipeRefreshLayout冲突
//    @Override
//    public boolean canScrollVertically(int direction) {
//        if (direction < 1) {
//            boolean original = super.canScrollVertically(direction);
//            return !original && getChildAt(0) != null && getChildAt(0).getTop() < 0 || original;
//        }
//        return super.canScrollVertically(direction);
//    }

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            checkIfEmpty();
        }
    };

    private void checkIfEmpty(){
        if(emptyView != null && getAdapter() != null){
            final boolean emptyViewVisible = getAdapter().getItemCount() == 0;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE:GONE);
            setVisibility(emptyViewVisible ? GONE:VISIBLE);
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
        checkIfEmpty();
    }

    public void setEmptyView(View emptyView){
        this.emptyView = emptyView;
        checkIfEmpty();
    }
}

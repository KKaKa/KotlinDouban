package com.laizexin.sdj.kotlindouban.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laizexin on 2018/7/9.
 * 多类型item适配器
 */

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{

    protected Context mContext;
    protected List<T> mDatas;
    protected OnItemClickListener mOnItemClickListener;
    //偏移位
    public int offset = 0;

    protected ItemViewDelegateManager mItemViewDelegateManager;

    public MultiItemTypeAdapter(Context mContext, List<T> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    public MultiItemTypeAdapter(Context context) {
        this(context, new ArrayList<T>());
    }

    @Override
    public int getItemViewType(int position) {
        if(!useItemViewDelegateManager())
            return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mDatas.get(position),position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mItemViewDelegateManager.getItemViewLayoutId(viewType);
        BaseViewHolder holder = BaseViewHolder.createViewHolder(mContext,parent,layoutId);
        setListener(parent, holder, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void addDataAll(List data) {
        mDatas.addAll(data);
    }

    public void clearData() {
        mDatas.clear();
    }

    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getDelegatesCount() > 0;
    }

    protected void setListener(final ViewGroup parent, final BaseViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType))
            return;

        viewHolder.getConvertView().setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                int position = viewHolder.getAdapterPosition();
                mOnItemClickListener.onItemClick(v, viewHolder, mDatas.get(position - offset)
                        , position);
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(v -> {
            if (mOnItemClickListener != null) {
                int position = viewHolder.getAdapterPosition();
                return mOnItemClickListener.onItemLongClick(v, viewHolder, mDatas.get
                        (position - offset), position);
            }
            return false;
        });
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, RecyclerView.ViewHolder holder, T o, int position);
        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, T o, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    public void convert(BaseViewHolder holder, T t) {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public MultiItemTypeAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T>
            itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }

}

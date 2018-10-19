package com.laizexin.sdj.kotlindouban.recycleview;

/**
 * Created by laizexin on 2018/7/9.
 */

public interface ItemViewDelegate<T>{

    /**
     * 向Adapter提供布局文件
     * @return
     */
    int getItemViewLayoutId();

    /**
     * 判断传入的item是否是需要处理的类型
     * @param item
     * @param position
     * @return
     */
    boolean isForViewType(T item, int position);

    /**
     * 绑定 holder 和数据
     * @param holder
     * @param t
     * @param position
     */
    void convert(BaseViewHolder holder, T t, int position);
}

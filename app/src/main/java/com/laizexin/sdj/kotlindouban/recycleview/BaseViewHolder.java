package com.laizexin.sdj.kotlindouban.recycleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.laizexin.sdj.kotlindouban.R;
import com.laizexin.sdj.kotlindouban.utils.DimensionUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by laizexin on 2018/7/9.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    //缓存视图
    private View mConvertView;
    private Context mContext;

    public BaseViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public static BaseViewHolder createViewHolder(Context context,View itemView){
        return new BaseViewHolder(context,itemView);
    }

    public static BaseViewHolder createViewHolder(Context context, ViewGroup parent,int layout){
        View itemView = LayoutInflater.from(context).inflate(layout,parent,false);
        return new BaseViewHolder(context,itemView);
    }

    public <T extends View> T getView(int viewId){
        //先去SparseArray取view
        View view = mViews.get(viewId);
        if(view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public View getConvertView(){
        return mConvertView;
    }

    //设置TextView内容
    public BaseViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseViewHolder setTextColor(int viewId,int colorId){
        TextView tv = getView(viewId);
        tv.setTextColor(mContext.getResources().getColor(colorId));
        return this;
    }

    public BaseViewHolder setTextSize(int viewId,int px){
        TextView tv = getView(viewId);
        tv.setTextSize(DimensionUtil.Companion.px2sp(mContext,px));
        return this;
    }

    //设置ImageView资源
    public BaseViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    //设置ImageView图片Url
    public BaseViewHolder setImageUrl(int viewId, String imgUrl) {
        ImageView view = getView(viewId);
        Picasso.with(mContext)
                .load(imgUrl)
                .error(R.mipmap.blank_img)
                .placeholder(R.mipmap.blank_img)
                .centerCrop()
                .fit()
                .into(view);
        return this;
    }

    //设置ImageView图片Bitmap
    public BaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    //设置ImageView图片Drawable
    public BaseViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public BaseViewHolder setOnclickListener(int viewId,View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder setOnLongClickListener(int viewId,View.OnLongClickListener longClickListener){
        View view = getView(viewId);
        view.setOnLongClickListener(longClickListener);
        return this;
    }

    public BaseViewHolder setHeight(int viewId,int px){
        View view = getView(viewId);
        view.getLayoutParams().height = DimensionUtil.Companion.px2dip(mContext,px);
        return this;
    }

    public BaseViewHolder setVisiable(int viewId,int visiable){
        View view = getView(viewId);
        view.setVisibility(visiable);
        return this;
    }

    public BaseViewHolder setChecked(int viewId,boolean checked){
        CheckBox view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    public BaseViewHolder setOnChangeListener(int viewId, CompoundButton.OnCheckedChangeListener onCheckedChangeListener){
        CheckBox view = getView(viewId);
        view.setOnCheckedChangeListener(onCheckedChangeListener);
        return this;
    }

    public BaseViewHolder setBackgroundDrawable(int viewId,int drawable){
        View view = getView(viewId);
        view.setBackgroundResource(drawable);
        return this;
    }

    public BaseViewHolder setRating(int viewId,float rating){
        AppCompatRatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

}

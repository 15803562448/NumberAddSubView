package com.sunny.zh.numberaddsubview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/26.
 */
public class NumberAddSubView extends LinearLayout implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Button mAddBtn;
    private Button mSubBtn;
    private TextView mNumTv;
    private int value;
    private int minValue;
    private int maxValue;

    private OnButtonClickListener mOnButtonClickListener;
    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener){

        this.mOnButtonClickListener = onButtonClickListener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add){
            numAdd();
            if(mOnButtonClickListener !=null){
                mOnButtonClickListener.onButtonAddClick(v,value);
            }



        }else if (v.getId() == R.id.btn_sub){
            numSub();
            if (mOnButtonClickListener !=null){
                mOnButtonClickListener.onButtonSubClick(v,value);
            }

        }
    }
    private void numAdd(){
        if (value <maxValue){
            value++;
        }
        mNumTv.setText(value+"");
    }

    private void numSub(){
        if(value>minValue){
            value--;
        }
        mNumTv.setText(value+"");
    }

    public interface OnButtonClickListener{
        void onButtonAddClick(View view,int value);
        void onButtonSubClick(View view,int value);

    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getValue() {
        String val = mNumTv.getText().toString();
        if(val != null && !"".equals(val)){
            this.value = Integer.parseInt(val);
        }
        return value;
    }

    public void setValue(int value) {
        mNumTv.setText(value+"");
        this.value = value;
    }

    public NumberAddSubView(Context context) {
        this(context,null);
    }

    public NumberAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumberAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater=LayoutInflater.from(context);
        initView();
        if (attrs !=null){
            TintTypedArray tta = TintTypedArray.obtainStyledAttributes(
                    context,attrs,R.styleable.NumberAddSubView,defStyleAttr,0);
            int val = tta.getInt(R.styleable.NumberAddSubView_value,0);
            setValue(val);

            int minval = tta.getInt(R.styleable.NumberAddSubView_minValue,0);
            setMinValue(minval);


            int maxval = tta.getInt(R.styleable.NumberAddSubView_maxValue,0);
            setMaxValue(maxval);


            Drawable addBtnDrawable = tta.getDrawable(
                    R.styleable.NumberAddSubView_addBtnBg);
            setAddBtnBackdround(addBtnDrawable);


            Drawable subBtnDrawable = tta.getDrawable(
                    R.styleable.NumberAddSubView_subBtnBg);
            setSubBtnBackdround(subBtnDrawable);

            int numTvDrawable = tta.getResourceId(R.styleable.NumberAddSubView_numTvBg,
                    android.R.color.transparent);
            setNumBackdround(numTvDrawable);




            //一定要回收
            tta.recycle();;

        }
    }

    private void setNumBackdround(int drawableId) {
        this.mNumTv.setBackgroundResource(drawableId);
    }

    private void setSubBtnBackdround(Drawable drawable) {
        this.mSubBtn.setBackground(drawable);
    }

    private void setAddBtnBackdround(Drawable drawable) {
        this.mAddBtn.setBackground(drawable);
    }

    private void initView(){
        View view = mInflater.inflate(R.layout.widget_number_add_sub,this,true);
        mAddBtn= (Button) view.findViewById(R.id.btn_add);
        mSubBtn = (Button) view.findViewById(R.id.btn_sub);
        mNumTv = (TextView) view.findViewById(R.id.tv_num);


        mAddBtn.setOnClickListener(this);
        mSubBtn.setOnClickListener(this);
    }

}

package com.example.lzq.mycar.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lzq.mycar.R;


/**
 * ly
 */
public class CicleAddAndSubView extends LinearLayout {

	private int max = 99;
	
	private int min = 1;
	
	private int defaultColor = 0xff000000;
	
    private Context mContext = null;

    private OnNumChangeListener onNumChangeListener;

    private TextView addButton;//加按钮

    private TextView subButton;//减按钮

    private TextView editText;//数量显示

    int num = 1;          //数量值
    /**
     * 最小值
     */
    private int minNumber = min;
    /**
     * 最大值
     */
    private int maxNumber = max;
    
    /**
     * 加号背景
     */
    private int addBackground ;
    
    /**
     * 最大值时加号背景
     */
    private int addMaxBackground;
    
    
    /**
     * 减号背景
     */
    private int subBackground;
    
    /**
     * 最小值时减号背景
     */
    
    private int subMinBackground;
    
    
    private int textBackground;
    
    /**
     * 加号颜色
     */
    private int addColor = defaultColor;
    
    /**
     * 最大值时加号颜色
     */
    
    private int addMaxColor = defaultColor;
    
    
    /**
     * 减号颜色
     */
    
    private int subColor = defaultColor;
    
    /**
     *  最小值时减号颜色
     */
    
    
    private int subMinColor = defaultColor;
    
    
    private int textColor = defaultColor;
    
    
    
    
    private float textSize = 16;
    
    
    
    /**
     * 构造方法
     */
    public CicleAddAndSubView(Context context){
        super(context);
        this.mContext = context;
        num = 1;
        control(null);
    }

    /**
     *构造方法
     * @param context
     * @param
     */
    public CicleAddAndSubView(Context context, int num){
        super(context);
        this.mContext = context;
        this.num = num;
        control(null);
    }

    public CicleAddAndSubView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.mContext = context;
        num = 1;
        control(attrs);
    }

    /**
     * 初始化
     */
    private void control(AttributeSet attrs){
        setPadding(1, 1, 1, 1);
        initView(attrs);
        setViewListener();
    }


    /**
     * 初始化view
     */
    @SuppressLint({ "InflateParams", "Recycle" })
    private void initView(AttributeSet attrs){
        View view= LayoutInflater.from(mContext).inflate(R.layout.add_sub_view, null);
        addButton =(TextView)view.findViewById(R.id.add_btn_id);
        subButton =(TextView)view.findViewById(R.id.sub_btn_id);
        editText =(TextView) view.findViewById(R.id.num_text_id);
      
        
        if(attrs!=null){
        	TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.addSubView);
        	addBackground = ta.getResourceId(R.styleable.addSubView_add_background, R.drawable.shape_right_bg);
        	addMaxBackground = ta.getResourceId(R.styleable.addSubView_add_max_background, R.drawable.shape_empty_right_bg);
        	subBackground = ta.getResourceId(R.styleable.addSubView_sub_background, R.drawable.shape_left_bg);
        	subMinBackground = ta.getResourceId(R.styleable.addSubView_sub_min_background, R.drawable.shape_empty_left_bg);
        	textBackground = ta.getResourceId(R.styleable.addSubView_text_background, R.drawable.shape_text_bg);
        	addColor = ta.getColor(R.styleable.addSubView_add_textColor, defaultColor);	
        	addMaxColor = ta.getColor(R.styleable.addSubView_add_max_textColor, defaultColor);
        	subColor = ta.getColor(R.styleable.addSubView_sub_textColor, defaultColor);
        	subMinColor = ta.getColor(R.styleable.addSubView_sub_min_textColor, defaultColor);
        	textColor = ta.getColor(R.styleable.addSubView_text_textColor, defaultColor);
        	num = ta.getInteger(R.styleable.addSubView_initial_value, 1);
        	minNumber = ta.getInt(R.styleable.addSubView_min_value, min);
        	maxNumber = ta.getInt(R.styleable.addSubView_max_value, max);
        	textSize = ta.getDimensionPixelSize(R.styleable.addSubView_textSize, 16);
        	ta.recycle();
        	setAddBtnBackgroudResource(addBackground);
        	setAddMaxBackgroudResource(addMaxBackground);
        	setSubBackgroudResource(subBackground);
        	setSubMinMBackgroudResource(subMinBackground);
        	setTextBackgroudResource(textBackground);
        }else{
        	num = 1;
        	maxNumber = max;
        	minNumber = min;
        	setAddBtnBackgroudResource(R.drawable.shape_right_bg);
        	setAddMaxBackgroudResource(R.drawable.shape_empty_right_bg);
        	setSubBackgroudResource(R.drawable.shape_left_bg);
        	setSubMinMBackgroudResource(R.drawable.shape_empty_left_bg);
        	setTextBackgroudResource(R.drawable.shape_text_bg);
        }
        
        
    	setTextSize(textSize);
    	setAddTextColor(addColor);
    	setAddMaxTextColor(addMaxColor);
    	setSubMinTextColor(subMinColor);
    	setSubTextColor(subColor);
    	setMaxValue(maxNumber);
    	setMinValue(minNumber);
        
        
        addView(view);
    
    }

    /**
     *  设置中间的距离
     * @param distance
     */
    public void setMiddleDistance(int distance){
        editText.setPadding(distance,0,distance,0);
    }
    
    
    
    private void setNum(int num){
        this.num = num;
        editText.setText(String.valueOf(num));
        if (onNumChangeListener != null){
        onNumChangeListener.onNumChange(CicleAddAndSubView.this, getNum());
    }
    }

    /**
     * 获取值
     * @return
     */
    public int getNum(){
        if (!TextUtils.isEmpty(editText.getText().toString().trim())){
            return Integer.parseInt(editText.getText().toString());
        }else {
            return 0;
        }
    }

    public void setInitial(int value){
    	this.num = value;
    	if(value ==1 && maxNumber==1 ){
    		setAddMaxTextColor(addMaxColor);
		 	setSubMinTextColor(subMinColor);
			setAddMaxBackgroudResource(addMaxBackground);
			setSubMinMBackgroudResource(subMinBackground);
    	}else {
    		
    		if(num>=maxNumber){
	    		setAddMaxBackgroudResource(addMaxBackground);
	    		setSubBackgroudResource(subBackground);
	    		setSubTextColor(subColor);
	    		setAddMaxTextColor(addMaxColor);
	    	}else if(num<= minNumber){
	    		setAddTextColor(addColor);
	    		setSubMinTextColor(subMinColor);
	    		setSubMinMBackgroudResource(subMinBackground);
	    		setAddBtnBackgroudResource(addBackground);
	    	}else{
	    		setSubBackgroudResource(subBackground);
	    		setAddBtnBackgroudResource(addBackground);
	    	}
       		 
    		 	
    	}
         editText.setText(String.valueOf(num));
    }
    
    public void setMaxValue(int max){
    	this.maxNumber = max;
    }
    
    public void setMinValue(int min){
    	this.minNumber = min;
    }
    
    public void setTextSize(float size){
    	addButton.setTextSize(size);
    	editText.setTextSize(size);
    	subButton.setTextSize(size);
    }
    
    public void setTextColor(int color){
    	editText.setTextSize(textColor);
    }
    
    public void setAddTextColor(int color){
    	addColor = color;
    	addButton.setTextColor(addColor);
    }
    
    public void setAddMaxTextColor(int color){
    	addMaxColor = color;
    	if(num>=maxNumber){
    		addButton.setTextColor(addMaxColor);
    	}
    }
    
    public void setSubTextColor(int color){
    	subColor = color;
    	subButton.setTextColor(subColor);
    }
    
    
    public void setSubMinTextColor(int color){
    	subMinColor = color;
    	if(num<= minNumber){
    		subButton.setTextColor(subMinColor);
    	}
    }

    public void setAddBtnBackgroudResource(int addBtnDrawable){
    	addBackground = addBtnDrawable;
        addButton.setBackgroundResource(addBackground);
    }

    public void setAddMaxBackgroudResource(int addBtnDrawable){
    	addMaxBackground = addBtnDrawable;
    	if(num>=maxNumber){
    		addButton.setBackgroundResource(addMaxBackground);
    	}
    }
    
    public void setSubBackgroudResource(int subBtnDrawable){
    	subBackground = subBtnDrawable;
        subButton.setBackgroundResource(subBackground);
    }

    public void setSubMinMBackgroudResource(int subBtnDrawable){
    	subMinBackground = subBtnDrawable;
    	if(num<= minNumber){
    		subButton.setBackgroundResource(subMinBackground);
    	}
    }
    
    
    @SuppressWarnings("unused")
	private void setTextBackgroudResource(int textDrawable){
    	textBackground = textDrawable;
    	editText.setBackgroundResource(textDrawable);
    }
    
    

    /**
     * 设置监听回调
     * @param onNumChangeListener
     */
    public void setOnNumChangeListener(OnNumChangeListener onNumChangeListener){
        this.onNumChangeListener = onNumChangeListener;
    }


    /**
     * 设置监听器
     */
    private void setViewListener(){
    	OnButtonClickListener listener = new OnButtonClickListener();
        addButton.setOnClickListener(listener);
        subButton.setOnClickListener(listener);
    }

    
    

    /**
     * 监听器监听事件
     */
    private class OnButtonClickListener implements OnClickListener {

        @Override
        public void onClick(View v){
        	 String numString = editText.getText().toString();
        	 if (TextUtils.isEmpty(numString)){
                 num = minNumber;
                 editText.setText(minNumber+"");
                 return;
        	 }
        	 
        	 	if(maxNumber == 1 && num ==1 ){
        		 	setAddMaxTextColor(addMaxColor);
        		 	setSubMinTextColor(subMinColor);
					setAddMaxBackgroudResource(addMaxBackground);
					setSubMinMBackgroudResource(subMinBackground);
        		 return;
        	 }
        	 
        	 
        	switch (v.getId()) {
			case R.id.add_btn_id:
				
				int temp_add = num+1;
				
				if(temp_add>=maxNumber){ 
					setAddMaxTextColor(addMaxColor);
					setAddMaxBackgroudResource(addMaxBackground);
					setSubTextColor(subColor);
					setSubBackgroudResource(subBackground);
					setNum(maxNumber);
					return;
				}else{
					setAddTextColor(addColor);
					setSubTextColor(subColor);
					setAddBtnBackgroudResource(addBackground);
					setSubBackgroudResource(subBackground);
					setNum(temp_add);
				}
				break;
				
			case R.id.sub_btn_id:
				int temp_sub = num-1;
				if(temp_sub<=minNumber){
					setNum(minNumber);
					setSubMinMBackgroudResource(subMinBackground);
					setSubMinTextColor(subMinColor);
					setAddTextColor(addColor);
					setAddBtnBackgroudResource(addBackground);
					return;
				}else{
					setSubTextColor(subColor);
					setAddTextColor(addColor);
					setAddBtnBackgroudResource(addBackground);
					setSubBackgroudResource(subBackground);
					setNum(temp_sub);
				}
				
				break;
			default:
				break;
			}
        	
        }
    }


    public interface OnNumChangeListener{
        /**
         * 监听方法
         * @param view
         * @param stype 点击按钮的类型
         * @param num  返回的数值
         */
        public void onNumChange(View view, int num);
    }

}

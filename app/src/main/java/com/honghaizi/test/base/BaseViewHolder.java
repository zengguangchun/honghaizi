package com.honghaizi.test.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 基类ViewHolder
 * @author 黄洞洞
 *
 */
public class BaseViewHolder extends RecyclerView.ViewHolder{

	protected Context context;


	public BaseViewHolder(View view,Context context) {
		super(view);
		this.context = context;
	}
	
	
	public void initData(Object data){
		
	}

}

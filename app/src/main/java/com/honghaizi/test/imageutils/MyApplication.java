package com.honghaizi.test.imageutils;


import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.honghaizi.test.honghaizi.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext
		SDKInitializer.initialize(getApplicationContext());
		imgto();
		super.onCreate();

	}

	private void imgto() {
		// TODO Auto-generated method stub
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisk(true)
				.showImageOnLoading(R.mipmap.ic_launcher)
				.showImageOnFail(R.mipmap.ic_launcher)
				.showImageOnFail(R.mipmap.ic_launcher).build();
		int Maxsize = (int) (Runtime.getRuntime().maxMemory() / 8);
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.discCache(new UnlimitedDiskCache(getCacheDir()))
				.memoryCache(new UsingFreqLimitedMemoryCache(Maxsize))
				.threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 1)
				.defaultDisplayImageOptions(options).build();
		ImageLoader.getInstance().init(configuration);
	}

}

# BigPictureview
![image](https://github.com/jkdsking/BigPictureview/blob/master/test1.gif)


gradle接入

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        implementation 'com.github.jkdsking:BigPictureview:1.0.1'
	}
 
  具体使用：注意长按可以保存图片

                List<String> list = new ArrayList<>();
                //网络图片
                list.add("https://upload-images.jianshu.io/upload_images/5809200-a99419bb94924e6d.jpg");
                list.add("https://upload-images.jianshu.io/upload_images/5809200-736bc3917fe92142.jpg");
                list.add("https://upload-images.jianshu.io/upload_images/5809200-7fe8c323e533f656.jpg");
                /**
                 * 亦可本地图片
                 */
                //使用方式
                PictureConfig config = new PictureConfig.Builder()
                        .setListData((ArrayList<String>) list)	//图片数据List<String> list
                        .setPosition(0)	//图片下标（从第position张图片开始浏览）
                        .setDownloadPath("pictureviewer")	//图片下载文件夹地址
                        .setIsShowNumber(true)//是否显示数字下标
                        .needDownload(true)	//是否支持图片下载 长按可实现下载
                        .setPlacrHolder(R.drawable.ic_photo_white_300dp)	//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                        .build();
                ImagePagerActivity.startActivity(MainActivity.this, config);

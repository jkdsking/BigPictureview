## 图片查看大图器 长按图片可实现保存本地 6.0以上不需要再动态获取权限 可以手势（双击）放大缩小 已适配android11(即targetSdkVersion=30)


![image](https://github.com/jkdsking/BigPictureview/blob/master/test1.gif)


## gradle接入

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        implementation 'com.github.jkdsking:BigPictureview:1.1.1'
	}
 ## 具体使用

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
## 开源协议
```
Copyright jkdsking BigPictureview

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```        

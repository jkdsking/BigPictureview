## 图片查看大图器 需要展示图片的activity不能沉嵌式状态栏 必须不能


![image](https://github.com/jkdsking/BigPictureview/blob/master/test1.gif)


## gradle接入

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        implementation 'com.github.jkdsking:BigPictureview:1.2.0'
	}
 ## 具体使用
                 protected Transferee transferee;声明
     
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarColor(R.color.colorPrimary).init();//修改状态栏
        transferee = Transferee.init(this);//初始化
        setContentView(R.layout.activity_main);

    }
   
    @Override
    protected void onDestroy() {
        super.onDestroy();
        transferee.destroy();
    }
    
    TransferConfig recyclerTransConfig = TransferConfig.build()
                .setListData(SourceConfig.getOriginalSourceGroup())//数据源
                .setIndexIndicator(new NumberIndexIndicator())//是否显示指示器 传null 表示不显示
                .setImageLoader(GlideImageLoader.with(MainActivity.this))
                .bindRecyclerView(recyclerView, R.id.iv_thum);//第一个参数 recyview(也可以绑定ListView/GridView/ImageView) 第二个参数是item布局的iamgeview id 

    recyclerTransConfig.setLongClickListener(new Transferee.OnTransfereeLongClickListener() {
            @Override
            public void onLongClick(ImageView imageView, String imageUri, int pos) {

    Toast.makeText(MainActivity.this,"长按了"+imageUri,Toast.LENGTH_SHORT).show();
        }
        });
	
    recyclerTransConfig.setPosition(pos);//pos 是你点击的图片下标
    transferee.apply(recyclerTransConfig).show();//展示
    
    
    



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

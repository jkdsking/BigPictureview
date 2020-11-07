## 仿朋友圈图片视频查看 
## 注意 需要展示图片的activity不能沉嵌式状态栏 必须不能

![image](https://github.com/jkdsking/BigPictureview/blob/master/test2.gif)

## gradle接入

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        implementation 'com.github.jkdsking:BigPictureview:1.3.3'
	}
 ## 具体使用
     protected Transferee transferee;声明
     
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarColor(R.color.colorPrimary).init();//修改状态栏背景颜色
        transferee = Transferee.init(this);//初始化
        setContentView(R.layout.activity_main);

    }
   
    @Override
    protected void onDestroy() {
        super.onDestroy();
        transferee.destroy();//资源销毁，防止内存泄漏
    }
    
    RecyclerView 使用
          TransferConfig recyclerTransConfig = TransferConfig.build()
                .setListData()//图片或者视频高清数据源
                .setIndexIndicator(new NumberIndexIndicator())//是否显示指示器 传null 表示不显示
                .setImageLoader(GlideImageLoader.with(MainActivity.this))
		    .setMissPlaceHolder()//缺省的占位图(资源ID)
		    .setErrorPlaceHolder()//图片加载错误显示的图片(资源ID)
                .enableScrollingWithPageChange(true)
                .bindRecyclerView(recyclerView, R.id.iv_thum);//第一个参数传入RecyclerView 第二个参数 传入 item 布局中的ImageView id

        recyclerTransConfig.setLongClickListener(new Transferee.OnTransfereeLongClickListener() {
            @Override
            public void onLongClick(ImageView imageView, String imageUri, int pos) {

                Toast.makeText(MainActivity.this,"长按了"+imageUri,Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
        recyclerAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int pos) {
                recyclerTransConfig.setPosition(pos);//显示第几个
                transferee.apply(recyclerTransConfig).show();//显示
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(recyclerAdapter);
    
       
       
       listview/GridView使用
       TransferConfig gridTransConfig = TransferConfig.build()
                .setListData()//图片或视频数据源
                .setIndexIndicator(new NumberIndexIndicator())//是否显示指示器 传null 表示不显示
                .setImageLoader(GlideImageLoader.with(getApplicationContext()))
                .enableScrollingWithPageChange(true)
                .bindListView(listview/gridView, R.id.iv_thum);//注意 bindListView 可以传入GridView也可以传入listview  第二个参数 传入 item 布局中的ImageView id
        gridView.setAdapter(new GridAdapter());
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            gridTransConfig.setPosition(position);//显示第几个
            transferee.apply(gridTransConfig).show();//显示
        });
	
	
	
	//单个imageview 使用
	  transferee.apply(TransferConfig.build()
                    .setListData()//图片或者视频的高清资源
                    .setImageLoader(GlideImageLoader.with(getApplicationContext()))
                    .enableJustLoadHitPage(true)//只加载当前显示的图片
                    .bindImageView(imageView)// imageview控件
            ).show();
	
	
	
	//无view绑定使用
	      transferee.apply(TransferConfig.build()
                        .setImageLoader(GlideImageLoader.with(getApplicationContext()))
                        .setListData()////图片或者视频的高清资源
                        .setIndexIndicator(new NumberIndexIndicator())//是否显示指示器 传null 表示不显示
                        .setNowThumbnailIndex(position)//显示第几个
                        .create()
                ).show();
	
	
    
    



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

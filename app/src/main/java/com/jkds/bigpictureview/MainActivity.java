package com.jkds.bigpictureview;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;
import com.hitomi.tilibrary.style.index.NumberIndexIndicator;
import com.hitomi.tilibrary.style.progress.ProgressBarIndicator;
import com.hitomi.tilibrary.transfer.TransferConfig;
import com.hitomi.tilibrary.transfer.Transferee;
import com.hitomi.tilibrary.utils.GlideImageLoader;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GridView gridView;
    private ImageView getGridView;
    private Button btn_test;
    protected Transferee transferee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不用沉嵌式代码写这句

        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.colorWhite).statusBarDarkFont(true)
                .navigationBarColor(R.color.colorWhite).init();
        transferee = Transferee.init(this);//初始化
        setContentView(R.layout.activity_main);
        initView();
        testTransferee();


    }

//
////        List<String> list = new ArrayList<>();
//////                //网络图片
////                list.add("https://upload-images.jianshu.io/upload_images/5809200-a99419bb94924e6d.jpg");
////                list.add("https://upload-images.jianshu.io/upload_images/5809200-736bc3917fe92142.jpg");
////                list.add("https://upload-images.jianshu.io/upload_images/5809200-7fe8c323e533f656.jpg");
////                //这是你的数据
////
////                /**
////                 * 亦可本地图片
////                 */
////                //使用方式
////                PictureConfig config = new PictureConfig.Builder()
////                        .setListData((ArrayList<String>) list)	//图片数据List<String> list
////                        .setPosition(0)	//图片下标（从第position张图片开始浏览）
////                        .setDownloadPath("pictureviewer")	//图片下载文件夹地址
////                        .setIsShowNumber(true)//是否显示数字下标
////                        .needDownload(true)	//是否支持图片下载 长按可实现下载
////                        .setPlacrHolder(R.drawable.ic_photo_white_300dp)	//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
////                        .build();
////                ImagePagerActivity.startActivity(MainActivity.this, config);
//    }
    private class RecyclerAdapter extends CommonAdapter<String> {
        RecyclerAdapter() {
            super(MainActivity.this, R.layout.item_image, SourceConfig.getThumbSourceGroup());
        }

        @Override
        protected void convert(ViewHolder viewHolder, String item, final int position) {
            final ImageView imageView = viewHolder.getView(R.id.iv_thum);
            Glide.with(MainActivity.this).load(item).into(imageView);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        transferee.destroy();
    }

    protected void initView() {
        recyclerView = findViewById(R.id.rv_transferee);
        gridView = findViewById(R.id.gv_transferee);
        getGridView=findViewById(R.id.gv_transgif);
        btn_test=findViewById(R.id.btn_test);
    }

    protected void testTransferee() {
        //图片
        TransferConfig recyclerTransConfig = TransferConfig.build()
                .setListData(SourceConfig.getOriginalSourceGroup())//数据源
                .setIndexIndicator(new NumberIndexIndicator())//是否显示指示器 传null 表示不显示
                .setImageLoader(GlideImageLoader.with(MainActivity.this))
                .enableJustLoadHitPage(true)//只加载当前显示的图片
                .enableScrollingWithPageChange(true)
                .bindRecyclerView(recyclerView, R.id.iv_thum);

        recyclerTransConfig.setLongClickListener(new Transferee.OnTransfereeLongClickListener() {
            @Override
            public void onLongClick(ImageView imageView, String imageUri, int pos) {

                Toast.makeText(MainActivity.this,"长按了:"+imageUri,Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
        recyclerAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int pos) {
                recyclerTransConfig.setPosition(pos);
                transferee.apply(recyclerTransConfig).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(recyclerAdapter);

        //视频

        final TransferConfig gridTransConfig = TransferConfig.build()
                .setListData(SourceConfig.getMixingSourceGroup())
                .setIndexIndicator(new NumberIndexIndicator())
                .setImageLoader(GlideImageLoader.with(getApplicationContext()))
                .enableJustLoadHitPage(true)
                .enableScrollingWithPageChange(true)
                .bindListView(gridView, R.id.iv_thum);
        gridView.setAdapter(new GridAdapter());
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            gridTransConfig.setPosition(position);
            transferee.apply(gridTransConfig).show();
        });

    //gif

        Glide.with(MainActivity.this)
                .load(SourceConfig.getMixingSourceGroupGif().get(0))
                .placeholder(R.mipmap.ic_empty_photo)
                .into(getGridView);


        getGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferee.apply(TransferConfig.build()
                        .setListData(SourceConfig.getMixingSourceGroupGif())//图片或者视频的高清资源
                        .setImageLoader(GlideImageLoader.with(getApplicationContext()))
                        .setIndexIndicator(new NumberIndexIndicator())
                        .enableJustLoadHitPage(true)//只加载当前显示的图片
                        .bindImageView(getGridView)
                ).show();
            }
        });

        //无view绑定

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                transferee.apply(TransferConfig.build()
                        .setImageLoader(GlideImageLoader.with(getApplicationContext()))
                        .setListData(SourceConfig.getOriginalSourceGroup())
                        .setIndexIndicator(new NumberIndexIndicator())//是否显示指示器 传null 表示不显示
                        .setNowThumbnailIndex(0)//显示第几个
                        .setOnLongClickListener(new Transferee.OnTransfereeLongClickListener() {
                            @Override
                            public void onLongClick(ImageView imageView, String imageUri, int pos) {
                                Toast.makeText(MainActivity.this,"长按了:"+imageUri,Toast.LENGTH_SHORT).show();

                            }
                        })
                        .create()
                ).show();


            }
        });


    }


    private class GridAdapter extends com.zhy.adapter.abslistview.CommonAdapter<String> {

        GridAdapter() {
            super(MainActivity.this, R.layout.item_image, SourceConfig.getMixingSourceGroup());
        }

        @Override
        protected void convert(com.zhy.adapter.abslistview.ViewHolder viewHolder, String item, final int position) {
            final ImageView imageView = viewHolder.getView(R.id.iv_thum);
                Glide.with(imageView)
                        .load(item)
                        .frame(1000_000)
                        .placeholder(R.mipmap.ic_empty_photo)
                        .into(imageView);

        }
    }



}
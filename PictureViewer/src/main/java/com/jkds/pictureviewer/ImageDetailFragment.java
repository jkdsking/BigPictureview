package com.jkds.pictureviewer;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jkds.permission.OnPermission;
import com.jkds.permission.PermissionsRequest;

import java.util.List;

public class ImageDetailFragment extends Fragment {
    public static int mImageLoading;//占位符图片
    public static boolean mNeedDownload = false;//默认不支持下载
    private String mImageUrl;
    private ImageView mImageView;
    private PhotoViewAttacher mAttacher;
    private Bitmap mBitmap;

    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment imageDetailFragment = new ImageDetailFragment();

        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        imageDetailFragment.setArguments(args);

        return imageDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_image_detail, container, false);
        mImageView = (ImageView) v.findViewById(R.id.image);
        mAttacher = new PhotoViewAttacher(mImageView);
        mAttacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mNeedDownload) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("保存图片");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //给权限

                            PermissionsRequest.with(getActivity())
                                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                    // 申请多个权限
                                    .request(new OnPermission() {
                                        @Override
                                        public void hasPermission(List<String> granted, boolean all) {
                                            if (all) {
                                                ImageUtil.saveImage(getActivity(), mImageUrl, mBitmap);
                                            } else {
                                                Toast.makeText(getActivity(), "请打开存储权限后保存图片", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        @Override
                                        public void noPermission(List<String> denied, boolean never) {
                                            if (never) {
                                                Toast.makeText(getActivity(), "请打开存储权限后保存图片", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getActivity(), "请打开存储权限后保存图片", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });



//                            PermissionRequest.getInstance().build(getActivity()).requestPermission(new PermissionRequest.PermissionListener() {
//                                @Override
//                                public void permissionGranted() {
//                                    ImageUtil.saveImage(getActivity(), mImageUrl, mBitmap);
//                                }
//
//                                @Override
//                                public void permissionDenied(ArrayList<String> permissions) {
//                                    Toast.makeText(getActivity(), "请打开存储权限后保存图片", Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void permissionNeverAsk(ArrayList<String> permissions) {
//                                    Toast.makeText(getActivity(), "请打开存储权限后保存图片", Toast.LENGTH_SHORT).show();
//                                }
//                            }, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE});


                        }
                    });
                    builder.create().show();
                }
                return false;
            }
        });
        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                getActivity().finish();
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!TextUtils.isEmpty(mImageUrl)) {

            Glide.with(getActivity()).asBitmap().load(mImageUrl).apply(new RequestOptions().placeholder(mImageLoading).error(mImageLoading).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH)).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    mBitmap = resource;
                    mImageView.setImageBitmap(mBitmap);
                    mAttacher.update();

                }
            });


        } else {
            mImageView.setImageResource(mImageLoading);

        }
    }
}

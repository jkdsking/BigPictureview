package com.jkds.pictureviewer.permission;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

/**
 * @author 王金珂
 * @date 2020/7/4
 */
public class PermissionRequest {

    private static final String TAG = PermissionRequest.class.getSimpleName();
    private PermissionFragment fragment;

    private static PermissionRequest permissionRequest;

    public static PermissionRequest getInstance(){
        if(permissionRequest == null) {
            permissionRequest = new PermissionRequest();
        }
        return permissionRequest;
    }

    private PermissionRequest() {

    }

    public PermissionRequest build(FragmentActivity activity) {
        fragment = getFragmentInstance(activity.getSupportFragmentManager());
        return permissionRequest;
    }

    public PermissionRequest build(Fragment fragment) {
        this.fragment = getFragmentInstance(fragment.getChildFragmentManager());
        return permissionRequest;
    }


    /**
     * 获取fragment单例
     * @param fragmentManager
     * @return
     */
    private PermissionFragment getFragmentInstance(FragmentManager fragmentManager) {
        if(findFragment(fragmentManager)==null) {
            fragment = new PermissionFragment();
            fragmentManager.beginTransaction()
                    .add(fragment,TAG).commitNow();
            return fragment;
        }
        return findFragment(fragmentManager);
    }

    private PermissionFragment findFragment(FragmentManager fragmentManager) {
        return (PermissionFragment)fragmentManager.findFragmentByTag(TAG);
    }

    /**
     * 请求权限，根据是否拥有权限，是否拒绝再次请求等调用不同回调
     * @param listener          请求回调
     * @param permissions       请求的权限数组
     */
    public void requestPermission(@NonNull PermissionListener listener, String[] permissions) {
        fragment.requestPermissions(permissions,listener);
    }



    /**
     * 权限请求结果回调
     */
    public interface PermissionListener {
        /**
         * 通过授权
         */
        void permissionGranted();

        /**
         * 拒绝授权
         * @param permissions       被拒绝的权限
         */
        void permissionDenied(ArrayList<String> permissions);

        /**
         * 勾选不再询问按钮，做一些提示
         * @param permissions       勾选不再询问的权限
         */
        void permissionNeverAsk(ArrayList<String> permissions);
    }
}

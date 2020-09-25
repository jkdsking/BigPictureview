package com.jkds.pictureviewer.permission;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author 王金珂
 * @date 2020/7/4
 */
public class PermissionFragment extends Fragment {
    private static final int PERMISSIONS_REQUEST_CODE = 0;
    private PermissionRequest.PermissionListener listener;

    public void requestPermissions(String[] permissions, PermissionRequest.PermissionListener listener) {
        this.listener = listener;
        if(!PermissionUtil.hasPermission(getContext(),permissions)) {
            requestPermissions(permissions, PERMISSIONS_REQUEST_CODE);
        }else {
            listener.permissionGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != PERMISSIONS_REQUEST_CODE) {
            return;
        }
        if(listener!=null) {
            if (PermissionUtil.hasPermission(getContext(), permissions)) {
                listener.permissionGranted();
            } else {
                if (PermissionUtil.isNeverAsk(getContext(), permissions)) {
                    listener.permissionNeverAsk(PermissionUtil.getNeverAskPermissions(getContext(), permissions));
                } else {
                    listener.permissionDenied(PermissionUtil.getDeniedPermissions(getContext(), permissions));
                }
            }
        }
    }
}

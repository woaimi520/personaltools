package com.example.personaltools;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

public class FlashlightControlManager implements SurfaceHolder.Callback{
    private Context mContext;
    //android M 以上 start
    private CameraManager manager;
    private String cameraId;
    //android M 以上 end

    //android M 以下 start
    private Camera mCamera;
    private Camera.Parameters mParameters;
    //android M 以下 end

    private FlashlightControlManager() {
        mContext = MyAppllication.getContext();
    }

    public static FlashlightControlManager getInstance() {
        return ManagerHolder.manager;
    }

    /**
     * 开启闪光灯
     */
    public void openFlashlight() {

        if(isHaveFlash()) {
            if (isVerGreaterOrEqualM()) {
                if(manager==null) {
                    try {
                        manager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
                        cameraId = manager.getCameraIdList()[0];
                        manager.setTorchMode(cameraId, true);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (mCamera == null) {
                    try {
                        mCamera = Camera.open();
                        mParameters = mCamera.getParameters();
                        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        mCamera.setParameters(mParameters);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//            });

            }
        }else{
            Toast.makeText(mContext, "设备没有闪光灯", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 关闭闪光灯
     */
    public void closeFlashlight() {

        if(isHaveFlash()) {
            if (isVerGreaterOrEqualM()) {
                if (manager != null) {
                    try {
                        cameraId = manager.getCameraIdList()[0];
                        manager.setTorchMode(cameraId, false);
                        manager = null;
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                        manager = null;
                    }
                }
            } else if (mCamera != null) {
                try {
                    mParameters = mCamera.getParameters();
                    mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    mCamera.setParameters(mParameters);
                    mCamera.release();
                    mCamera = null;
                } catch (Exception e) {
                    e.printStackTrace();
                    mCamera = null;
                }
            }
        }else{
            Toast.makeText(mContext, "设备没有闪光灯", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 判断是否为6.0以上系统
     * @return true 6.0及以上  false 6.0以下
     */
    private boolean isVerGreaterOrEqualM(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 是否设备有闪光灯
     * @return true 有  false 没有
     */
    private boolean isHaveFlash(){
        return mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    /**
     * 判断是否打开闪光灯
     * @return true 闪光灯关闭  false 闪光灯开启
     */
    public boolean isFlashOff(){
          if(isVerGreaterOrEqualM()){
              return manager == null;
          }else{
              return mCamera == null;
          }
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    static class ManagerHolder {
        private static FlashlightControlManager manager = new FlashlightControlManager();
    }
}

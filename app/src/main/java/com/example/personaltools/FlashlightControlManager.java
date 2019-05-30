package com.example.personaltools;

import android.content.Context;
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

public class FlashlightControlManager implements SurfaceHolder.Callback{
    private Context mContext;
    //android M 以上 start
    private CameraManager manager;
    private String cameraId;
    //android M 以上 end
    //android M 以下 start
    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private Camera.Parameters mParameters;
    //android M 以下 end
    private Handler mHandler = new Handler();
    private FlashlightControlManager() {
        mContext = MyAppllication.getContext();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            initAboveM();
        }else{
            initUnderM();
        }
    }

    private void initAboveM(){
        manager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = manager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    private void initUnderM(){
        mSurfaceView = new SurfaceView(mContext) {
            @Override
            protected void onAttachedToWindow() {
                /**
                 * avoid NullPointerException in SurfaceView.onAttachedToWindow
                 */
                if (this.getParent() != null) {
                    super.onAttachedToWindow();
                }
            }
        };

        mSurfaceView.setZOrderOnTop(true);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);

        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        mLayoutParams = new WindowManager.LayoutParams(1, 1, type, 65832,
                PixelFormat.TRANSLUCENT);
        mLayoutParams.gravity = Gravity.LEFT | Gravity.BOTTOM;
        setupCamera();
    }

    private void setupCamera() {
        try {
            mCamera = Camera.open();
            mParameters = mCamera.getParameters();
            mWindowManager.addView(mSurfaceView, mLayoutParams);
            mSurfaceHolder.addCallback(this);
        } catch (WindowManager.BadTokenException e) {
            if (e.getMessage().contains("permission denied for window type 2038")) {

            }else{
                e.printStackTrace();
            }
        } catch(IllegalStateException e){
            if(e.getMessage().contains("has already been added to the window manager")){
                mSurfaceHolder.addCallback(this);
            }else{
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }




    public static FlashlightControlManager getInstance() {
        return ManagerHolder.manager;
    }

    public void openFlashlight() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            try {
                manager.setTorchMode(cameraId,true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

        }else{

            mHandler.post(() -> {
                // 开启电筒的过程需要放到子线程（和初始化分开，否则电筒不亮）
                if ( mCamera != null) {
                    try {
                        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        mCamera.setPreviewDisplay(mSurfaceHolder);
                        mCamera.setParameters(mParameters);
                        mCamera.startPreview();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }

    public void closeFlashlight() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                try {
                    manager.setTorchMode(cameraId,false);

                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
        } else if(mCamera != null) {
            try {
                mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(mParameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }




    public  boolean getFlashlightStatus(){




        return false;
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

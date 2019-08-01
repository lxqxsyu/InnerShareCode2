package com.dlc.helloword.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.dlc.helloword.R;
import com.dlc.helloword.util.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 描述：
 * 日期：2019/8/1
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class FileProviderTestActivity extends BaseActivity {

    private static final int REQUEST_CAMERA = 1006;
    private static final int REQUEST_CROP_PHOTO = 1007;
    private static final int REQUEST_PERMISSON_CAMERA = 0x12;

    private ImageView mPhotoImage;
    private Uri mImageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_file_provider);
        mPhotoImage = findViewById(R.id.iv_photo);
    }

    public void takePhoto(View view) {
        checkPermission();
    }

    private void requestSystemCamera(){
        File file = new File(getFilesDir(), "/myphoto/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        mImageUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//将拍取的照片保存到指定URI
        startActivityForResult(intent,REQUEST_CAMERA);
    }

    private void checkPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            requestSystemCamera();
        }else {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                requestSystemCamera();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CAMERA},
                        REQUEST_PERMISSON_CAMERA);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSON_CAMERA:
                boolean granted = true;
                for(int i = 0; i < grantResults.length; i++){
                    if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                        granted = false;
                        ToastUtil.showToast(this, "请先同意" + permissions[i] + "权限");
                        break;
                    }
                }
                if(granted){
                    requestSystemCamera();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CAMERA && resultCode == RESULT_OK){
            mPhotoImage.setImageURI(mImageUri);
        }
    }
}

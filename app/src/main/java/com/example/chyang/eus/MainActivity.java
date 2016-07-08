package com.example.chyang.eus;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.bl.Tools.PatchHelper;

import java.io.File;

public class MainActivity extends AppCompatActivity {



    private PatchHelper mPatchHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPatchHelper = new PatchHelper();
    }

    public void onClick(View view) {
    File mFile = Environment.getExternalStorageDirectory();
        File oldApk = new File(mFile, "iReader1.6.2.0(v35).apk");
        if(oldApk.exists()) {
            File aPatch = new File(mFile, "update.patch");
            if(aPatch.exists()) {
              int a =  mPatchHelper.applyPatch(oldApk.getAbsolutePath(), mFile.getAbsolutePath()+"new.apk", aPatch.getAbsolutePath());
                System.out.println(a+"=----== ");
            }else{
                Toast.makeText(this, "update.patch不存在", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "oldApk不存在", Toast.LENGTH_SHORT).show();
        }
    }
}

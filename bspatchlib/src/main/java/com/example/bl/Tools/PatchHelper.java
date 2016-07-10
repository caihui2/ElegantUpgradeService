package com.example.bl.Tools;

/**
 * Created by chyang on 16-7-8.
 */
public class PatchHelper {



  public static native int applyPatch(String oldApkFilePath, String newApkFilePath, String patchFilePath);
}

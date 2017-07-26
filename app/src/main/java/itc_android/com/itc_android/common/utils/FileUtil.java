package itc_android.com.itc_android.common.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import itc_android.com.itc_android.Constant;

/**
 * Created by hdadmin on 2/7/2017.
 */

public class FileUtil {
    /**
     * Get a usable cache directory (external if available, internal otherwise).
     *
     * @param context The context to use
     * @param uniqueName A unique directory name to append to the cache dir
     * @return The cache dir
     */

    public  static  final String TAG = FileUtil.class.getSimpleName();
    private ListenerFileUtil listenerFileUtil ;
    private static FileUtil instance ;
    private Context context ;

    public static FileUtil instance(Context context,ListenerFileUtil listenerFileUtil){
        if (instance == null){
            instance = new FileUtil();
        }
        instance.context = context;
        instance.listenerFileUtil = listenerFileUtil ;
        return instance;
    }

    public static File getDiskCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        final String cachePath =
                Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                        !isExternalStorageRemovable() ? getExternalCacheDir(context).getPath() :
                        context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    public static File getExternalCacheDir(Context context) {
        if (hasFroyo()) {
            return context.getExternalCacheDir();
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static boolean isExternalStorageRemovable() {
        if (hasGingerbread()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    public static boolean hasFroyo() {
        // Can use static final constants like FROYO, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static String getPathToSaveFile(Context context, String url, String nameFolder, boolean isUrl, String id) {
        Uri uri = Uri.parse(url);
        String fileName = uri.getLastPathSegment();
        String endName = "";
        if (!isUrl){
            String[] parts = fileName.split(Pattern.quote("."));
            endName = id + "." + parts[1];
        }else {
            endName = fileName;
        }
        File diskCacheDir = getDiskCacheDir(context, nameFolder);
        if (!diskCacheDir.exists()) {
            diskCacheDir.mkdirs();
        }
        String path = diskCacheDir.getPath() + "/" + endName;
        return path;
    }

    public static void deleteImageStore(String url){
        if (!TextUtils.isEmpty(url)) {
            File file = new File(url);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static boolean createdFolder(){
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "TollCulator");
        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
           return success ;
        }
        return success ;
    }

    public static void mCreateAndSaveFile(String fileName,String responseJson) {
            try {
                File root = new File("/data/data/" + instance.context.getPackageName() + "/" + fileName);
                if (!root.exists()){
                    root.createNewFile();
                }
                FileWriter file = new FileWriter(root);
                file.write(responseJson);
                file.flush();
                file.close();
                instance.listenerFileUtil.onSuccess(true);
            } catch (IOException e) {
                instance.listenerFileUtil.onSuccess(false);
                e.printStackTrace();
            }
    }

    public static void mReadJsonDataSymbologies(String fileName) {
            try {
                File f = new File("/data/data/" + instance.context.getPackageName() + "/" + fileName);
                FileInputStream is = new FileInputStream(f);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                String mResponse = new String(buffer);
                instance.listenerFileUtil.onResponse(mResponse);
            } catch (IOException e) {
                instance.listenerFileUtil.onResponse("");
                e.printStackTrace();
            }
    }



    public static boolean mCheckFileExisting(String fileName){
            File root = new File("/data/data/" + instance.context.getPackageName() + "/" + fileName);
            if (root.exists()){
               return true;
            }
            return false;
    }

    public interface ListenerFileUtil{

        void onResponse(String result);
        void onSuccess(boolean flag);

    }

}

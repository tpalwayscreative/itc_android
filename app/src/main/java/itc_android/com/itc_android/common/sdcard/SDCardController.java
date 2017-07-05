package itc_android.com.itc_android.common.sdcard;

/**
 * Created by PC on 7/5/2017.
 */

public class SDCardController {

    static private SDCardController instance ;

    public static SDCardController getInstance(){
        if (instance == null){
            instance = new SDCardController();
        }
        return instance;
    }







}

package itc_android.com.itc_android.model;
/**
 * Created by mac10 on 6/8/17.
 */
public class CPalindrome {

    public String id ;
    public String value ;
    public String result ;

    public CPalindrome(){

    }

    public CPalindrome(String value,String result){
        this.value = value ;
        this.result = result ;
    }

    public CPalindrome(String id,String value,String result){
        this.id = id ;
        this.value = value ;
        this.result = result ;
    }

}

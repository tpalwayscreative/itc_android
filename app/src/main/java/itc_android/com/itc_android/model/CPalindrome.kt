package itc_android.com.itc_android.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mac10 on 6/8/17.
 */

class CPalindrome  : Parcelable{

    var value: String = ""
    var result: String = ""

    constructor() {

    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(value)
        parcel?.writeString(result)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<CPalindrome> = object : Parcelable.Creator<CPalindrome> {
            override fun createFromParcel(parcelIn: Parcel): CPalindrome {
                return CPalindrome(parcelIn.readString(), parcelIn.readString())
            }

            override fun newArray(size: Int): Array<CPalindrome> {
                return arrayOf()
            }
        }
    }


    constructor(value: String, result: String) {
        this.value = value
        this.result = result
    }


}



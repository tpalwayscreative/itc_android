package itc_android.com.itc_android.common.controller;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import itc_android.com.itc_android.common.realm.Palindrome;
import itc_android.com.itc_android.model.CPalindrome;

/**
 * Created by PC on 7/3/2017.
 */

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {
        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {
        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {
        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {
        realm.beginTransaction();
        realm.clear(Palindrome.class);
        realm.commitTransaction();
    }

    //find all objects in the Book.class
    public RealmResults<Palindrome> getBooks() {
        return realm.where(Palindrome.class).findAll();
    }

    //Inserting epc to realm data
    public void mInsetBook(Palindrome protocol){
        if (realm == null){
            return;
        }
        realm.beginTransaction();
        realm.copyToRealm(protocol);
        realm.commitTransaction();
    }

    public void mEditItem(int position,Palindrome palindrome){
        RealmResults<Palindrome> results = realm.where(Palindrome.class).findAll();
        realm.beginTransaction();
        results.get(position).setId(palindrome.getId());
        results.get(position).setValue(palindrome.getValue());
        results.get(position).setResult(palindrome.getResult());
        realm.commitTransaction();
    }

    public void mDeleteItem(int position){
        RealmResults<Palindrome> results = realm.where(Palindrome.class).findAll();
        realm.beginTransaction();
        results.remove(position);
        realm.commitTransaction();
    }



    //query a single item with the given id
    public Palindrome getBook(String id) {
        return realm.where(Palindrome.class).equalTo("id", id).findFirst();
    }
    //check if Book.class is empty
    public boolean hasBooks() {
        return !realm.allObjects(Palindrome.class).isEmpty();
    }

    //query example
    public RealmResults<Palindrome> queryedBooks() {
        return realm.where(Palindrome.class)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll();
    }

}

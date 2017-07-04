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

        if (realm != null)
        {
            Log.d("action","This action is existing");
        }
        return realm.where(Palindrome.class).findAll();
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

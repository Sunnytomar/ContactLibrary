package com.trivedi.contact.UI.cache;

import android.content.Context;

import com.trivedi.contact.UI.Model.People;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by daffolap-01 on 9/6/16.
 */
public class ContactCache {

    static ContactCache _mCacheInstance;
    static Context _mContext;

    public static ContactCache getInstance() {
        if (_mCacheInstance == null)
            _mCacheInstance = new ContactCache();
        return _mCacheInstance;
    }

    public void makeContactCacheSpace(Context context,ArrayList<People> peopleArrayList) {
        try {
            peopleArrayList.addAll(peopleArrayList);

            File _cacheFile=new File(context.getCacheDir().getPath()+ File.pathSeparator+"cache");
            if(_cacheFile.exists()){
                _cacheFile.delete();
            }
            FileOutputStream fos = new FileOutputStream(context.getCacheDir().getPath()+ File.pathSeparator+"cache");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(peopleArrayList);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }





    public  ArrayList<People> getContactCacheObject(Context context) {
        ArrayList<People> peopleArrayList=null;
        try {
            FileInputStream fis = new FileInputStream(context.getCacheDir().getPath()+ File.pathSeparator+"cache");
            ObjectInputStream ois = new ObjectInputStream(fis);
            peopleArrayList = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        return peopleArrayList;
    }

}

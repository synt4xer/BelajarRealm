package com.syntaxer.belajarrealm;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    private Realm realm;

    RealmHelper(Realm realm){
        this.realm = realm;
    }

    //TODO: To save data into database
    public void save(final MahasiswaModel mahasiswaModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(MahasiswaModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    mahasiswaModel.setId(nextId);
                    MahasiswaModel model = realm.copyToRealm(mahasiswaModel);
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    //TODO: TO get all data from database
    List<MahasiswaModel> getAllMahasiswa(){
        return realm.where(MahasiswaModel.class).findAll();
    }

    //TODO: To update data from database
    public void update(final Integer id, final Integer nim, final String name){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MahasiswaModel model = realm.where(MahasiswaModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setNim(nim);
                model.setName(name);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("pppp", "onSuccess: Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    public void delete(Integer id){
        final RealmResults<MahasiswaModel> model = realm.where(MahasiswaModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}

package com.syntaxer.belajarrealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MahasiswaModel extends RealmObject {

    @PrimaryKey
    private Integer id;
    private Integer nim;
    private String name;

    public MahasiswaModel() {

    }

    MahasiswaModel(Integer nim, String name) {
        this.nim = nim;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNim(Integer nim) {
        this.nim = nim;
    }

    public Integer getNim() {
        return nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

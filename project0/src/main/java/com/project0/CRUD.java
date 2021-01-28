package com.project0;

public interface CRUD<T> {

    T saveChanges(T target);

    T insertNew(T target);

    T read(T target);

    static void setDAO(CRUD self){
    }

    static CRUD getDAO() {
        return null;
    }
}

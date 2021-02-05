package com.ocrown;

public class Pair<T1,T2> {
    T1 form;
    T2 late;
    Pair(T1 form,T2 late){
        this.form=form;
        this.late=late;
    }
    public T1 getForm() {
        return form;
    }
    public T2 getLate() {
        return late;
    }
}

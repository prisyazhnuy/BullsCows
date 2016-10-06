package com.prisyazhnuy.bullscows;

import android.view.View;

import java.util.ArrayList;

public class ViewList<T extends View> extends ArrayList<T> {

    @Override
    public boolean add(T t) {
        t.setEnabled(false);
        return super.add(t);
    }

    @Override
    public boolean remove(Object o) {
        ((View) o).setEnabled(true);
        return super.remove(o);
    }

    @Override
    public T remove(int index) {
        if (index >= 0) {
            get(index).setEnabled(true);
            return super.remove(index);
        } else {
            return null;
        }

    }

    @Override
    public void clear() {
        for (int i = 0; i < size(); i++) {
            get(i).setEnabled(true);
        }
        super.clear();
    }
}

package com.example.diplom.adapters;

import android.content.Context;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;

import com.example.diplom.App;
import com.sagutdinov.diplom.R;

import java.util.List;
import java.util.Map;

public class AdapterList {
    private final static String HEAD = "head";
    private final static String BODY = "body";
    private final static String DATE = "date";
    private List<Map<String,String>> adapterList;
    Context context;
    String admin;
    String name;
    public AdapterList(Context context, String admin, String name){
        this.context=context;
        this.admin=admin;
        this.name=name;
    }
    @NonNull
    public SimpleAdapter createAdapter() {
        adapterList= App.getNoteHolder().getData(admin,name);
        return new SimpleAdapter(context, adapterList, R.layout.list_item, new String[]{HEAD, BODY, DATE}, new int[]{R.id.head, R.id.body, R.id.date});
    }

    public List<Map<String, String>> getAdapterList() {
        return adapterList;
    }
}

package com.veryworks.android.memowithnodejs;

import com.veryworks.android.memowithnodejs.domain.Data;

import java.util.List;

/**
 * Created by pc on 3/24/2017.
 */

public class DataStore {
    private static DataStore instance = null;
    private DataStore(){}

    public static DataStore getInstance(){
        if(instance == null){
            instance = new DataStore();
        }
        return instance;
    }

    private List<Data.Qna> datas;

    public List<Data.Qna> getDatas() {
        return datas;
    }

    public void setDatas(List<Data.Qna> datas) {
        this.datas = datas;
    }
}

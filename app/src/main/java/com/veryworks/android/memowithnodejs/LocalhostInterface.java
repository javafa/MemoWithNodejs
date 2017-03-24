package com.veryworks.android.memowithnodejs;

import com.veryworks.android.memowithnodejs.domain.Data;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pc on 3/24/2017.
 */

public interface LocalhostInterface {
    @GET("bbs")
    Call<Data> getData();
}

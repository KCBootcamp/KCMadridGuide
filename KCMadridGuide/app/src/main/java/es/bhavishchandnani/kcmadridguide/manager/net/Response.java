package es.bhavishchandnani.kcmadridguide.manager.net;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response<T> {
    @SerializedName("result")
    List<T> result;
}

package unibratec.edu.br.tvmazeapp.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import unibratec.edu.br.tvmazeapp.model.Result;
import unibratec.edu.br.tvmazeapp.model.Show;

public class TvParser {

    public static final String URL = "http://api.tvmaze.com/search/shows?q=%s";
    public static final String URL_BYID = "http://api.tvmaze.com/shows/%s";

    public static List<Result> searchByName(String q) throws IOException{
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format(URL, q)).build();

        Response response = client.newCall(request).execute();
        if(response.networkResponse().code() == HttpURLConnection.HTTP_OK){
            String json = response.body().string();

            Gson gson = new Gson();
            Result[] searchResult =
                    gson.fromJson(json, Result[].class);
            if(searchResult != null){
                return Arrays.asList(searchResult);
            }
        }
        return null;
    }

    public static Show searchById(String id) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format(URL_BYID, id)).build();

        Response response = client.newCall(request).execute();
        if(response.networkResponse().code() == HttpURLConnection.HTTP_OK){
            String json = response.body().string();

            Gson gson = new Gson();
            Show result = gson.fromJson(json, Show.class);
            return result;
        }
        return null;
    }
}

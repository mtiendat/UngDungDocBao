package com.example.ungdungdocbao;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetWorkUltis {
    public static String getNewspaper(String danhMuc) throws MalformedURLException {
        Uri builtlURI = Uri.parse("http://10.0.2.2:8000/api/bai-viet").buildUpon()
                .appendQueryParameter("danhmuc",danhMuc).build();
        try {
            URL requestURL = new URL(builtlURI.toString());

            return callAPI(requestURL,"GET");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getDetailNewspaper(int id) {
        Uri builtURI = Uri.parse("http://10.0.2.2:8000/api/chitiet").buildUpon()
                .appendQueryParameter("id",String.valueOf(id)).build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL,"GET");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getBinhLuan(int id) {
        Uri builtURI = Uri.parse("http://10.0.2.2:8000/api/binhluan").buildUpon()
                .appendQueryParameter("id_baiviet",String.valueOf(id)).build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL,"GET");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getTieuDe(String tukhoa) {
        Uri builtURI = Uri.parse("http://10.0.2.2:8000/api/timkiem").buildUpon()
                .appendQueryParameter("tukhoa",tukhoa).build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL,"GET");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String callAPI(URL requestURL,String method) throws IOException {
        HttpURLConnection urlConnection =null;
        String results="";

        try {
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod(method);
            urlConnection.connect();
            InputStream inputStream=urlConnection.getInputStream();
            results=convertISToString(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
        }

        return results;
    }
    public static String convertISToString(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        try{
            while ((line=reader.readLine())!=null){
                builder.append(line+"\n");
                if (builder.length()==0){
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}

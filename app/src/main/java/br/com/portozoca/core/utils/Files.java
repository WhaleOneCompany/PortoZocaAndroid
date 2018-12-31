package br.com.portozoca.core.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Files {

    public static String fromAssets(Context ctx, String name){
        StringBuilder sb = new StringBuilder();
        try (InputStream dbSchema = ctx.getAssets().open(name)){
            try(InputStreamReader r = new InputStreamReader(dbSchema)){
                try(BufferedReader bufferedReader = new BufferedReader(r)){
                    String l;
                    while((l = bufferedReader.readLine()) != null){
                        sb.append(l);
                    }
                }
            }
        } catch (IOException e){
            Log.e("**** ERRO DE SQL", e.getMessage());
        }
        return sb.toString();
    }

}

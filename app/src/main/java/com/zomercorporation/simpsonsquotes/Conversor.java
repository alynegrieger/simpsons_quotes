package com.zomercorporation.simpsonsquotes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class Conversor {


    public CharacterQuote getInformacao(String end) throws Exception {
       String json = ConexaoApi.getJsonFromApi(end);

       if(json.isEmpty()){
           throw new Exception("Não foi possível capturar o json");
       }
       String jsonSemParenteses =  json.substring(1, json.length() -1);
       return parseJson(jsonSemParenteses);
    }

    private CharacterQuote parseJson(String json){
        try {
            CharacterQuote quote = new CharacterQuote();

            JSONObject obj = new JSONObject(json);
            quote.setCharacter(obj.getString("character"));
            quote.setCharacterDirection(obj.getString("characterDirection"));
            quote.setImage(converteImagem((obj.getString("image"))));
            quote.setQuote(obj.getString("quote"));

            return quote;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap converteImagem(String url) {
        try {
            URL urlImagem = new URL(url);
            InputStream inputStream = urlImagem.openStream();
            Bitmap image = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

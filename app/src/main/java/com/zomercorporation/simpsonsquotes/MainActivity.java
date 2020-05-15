package com.zomercorporation.simpsonsquotes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private TextView character;
    private TextView quote;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Simpson's Quote");

        DownloadCharacter download = new DownloadCharacter();

        character = (TextView)findViewById(R.id.textCharacter);
        quote = (TextView)findViewById(R.id.textQuote);
        image = (ImageView)findViewById(R.id.imageView);

        //Chama Async Task
        download.execute();
    }

    private class DownloadCharacter extends AsyncTask<Void, Void, CharacterQuote> {

        @Override
        protected void onPreExecute(){
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected CharacterQuote doInBackground(Void... params) {
            Conversor util = new Conversor();
            try {
                return util.getInformacao("https://thesimpsonsquoteapi.glitch.me/quotes");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(CharacterQuote quoteCharacter){
            character.setText(quoteCharacter.getCharacter());
            quote.setText(quoteCharacter.getQuote());
            image.setImageBitmap(quoteCharacter.getImage());
            load.dismiss();
        }
    }
}

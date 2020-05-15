package com.zomercorporation.simpsonsquotes;

import android.graphics.Bitmap;

import java.io.Serializable;

public class CharacterQuote implements Serializable {

    private String quote;

    private String character;

    private Bitmap image;

    private String characterDirection;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getCharacter() {
        return character;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getCharacterDirection() {
        return characterDirection;
    }

    public void setCharacterDirection(String characterDirection) {
        this.characterDirection = characterDirection;
    }

    public void setCharacter(String character) {
        this.character = character;
    }


}

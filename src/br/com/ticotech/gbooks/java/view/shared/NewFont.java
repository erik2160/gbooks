package br.com.ticotech.gbooks.java.view.shared;

import java.awt.*;
import java.util.Objects;

public class NewFont {

    public Font externalFont;

    public Font getExternalFont() {
        return externalFont;
    }

    public NewFont() {

        try {
            externalFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(NewFont.class.getResourceAsStream("../../../resources/Ubuntu-Medium.ttf")));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(externalFont);
        } catch (Exception e) {
            throw new RuntimeException("Error loading custom font.", e);
        }

    }
}

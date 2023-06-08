package br.com.ticotech.gbooks.java.view.shared;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class NewFont {

    public Font fonteExterna;

    public Font getFonteExterna() {
        return fonteExterna;
    }

    public NewFont() {

        try {
            fonteExterna = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(NewFont.class.getResourceAsStream("../../../resources/BebasNeue-Regular.ttf")));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fonteExterna);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar a fonte personalizada.", e);
        }

    }
}

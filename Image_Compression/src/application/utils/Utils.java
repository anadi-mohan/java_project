package application.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public final class Utils
{
    public static Image BufferedImagetoImage(BufferedImage img)
    {
        try
        {
            return SwingFXUtils.toFXImage(img,null);
        }
        catch(Exception e)
        {
            System.err.println("Cannot convert the Buffered Image to Image"+e);
            return null;
        }
    }
    public static <T>void onFXThread(final ObjectProperty<T> property, final T value)
    {
        Platform.runLater(() -> {
            property.set(value);
        });
    }
}

package application;

import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import application.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.plugins.jpeg.*;
import javax.swing.JFileChooser;

public class FXController 
{
    @FXML
    private Button add_file;

    @FXML
    private ImageView originalPhoto;

    @FXML
    protected void openDialogue(ActionEvent event)
    {
        JFileChooser chooser = new JFileChooser();
        File f=null;
        BufferedImage img = null;
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
           try
           {
              f=chooser.getSelectedFile();
              img = ImageIO.read(f);
           }
           catch(Exception e)
           {
              System.out.println("Could not load image"+e);
           }
           int width = img.getWidth();
           int height = img.getHeight();
           Image imge = Utils.BufferedImagetoImage(img);
           updateImageView(originalPhoto,imge);
        }
        else
        {
            System.err.println("Impossible to open dialog box");
        }
    }

    private void updateImageView(ImageView view, Image imag)
    {
        Utils.onFXThread(view.imageProperty(), imag);
    }
}

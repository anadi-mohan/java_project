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
    private ImageView modifiedImage;

    @FXML
    protected void openDialogue(ActionEvent event)
    {
        JFileChooser chooser = new JFileChooser();
        File f=null;
        BufferedImage img = null;
        Image imge=null;
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
           try
           {
              f=chooser.getSelectedFile();
              img = ImageIO.read(f);
              imge = new Image(f.toURI().toString());
           }
           catch(Exception e)
           {
              System.out.println("Could not load image"+e);
           }
           int width = img.getWidth();
           int height = img.getHeight();
           try
           {
        	   updateImageView(originalPhoto,imge);
           }
           catch(Exception e)
           {
        	   System.err.println("Error here");
           }
           for(int i=0;i<height;++i)
        	   for(int j=0;j<width;++j)
        	   {
        		   int p = img.getRGB(j, i);
        		   
        		   int a = p >> 24 & 0xff;
        		   int r = p >> 16 & 0xff;
        		   int g = p>> 8 & 0xff;
        		   int b = p & 0xff;
        		   int avg = (r+g+b)/3;
        		    p = a << 24 | avg << 16 | avg << 8 | avg;
        		    img.setRGB(j, i, p);
        	   }
           try
           {
        	   f = new File("./b_w.jpg");
        	   ImageIO.write(img,"jpg",f);
        	   imge = new Image(f.toURI().toString());
        	   updateImageView(modifiedImage,imge);
           }
           catch(Exception e)
           {
        	   System.err.println("Cannot show the modified Image"+e);
           }
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

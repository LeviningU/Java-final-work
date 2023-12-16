package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//import App.Tetris;
import block.Cell;
//import block.Tetromino;

public class Dirt extends Cell{
    public static BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("images/dirt.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Dirt(int row, int col)
    {
        super(row, col, image);
    }
    
    @Override
    public void onLand()
    {
        
        //System.out.println("dirt placed!");
    }
}

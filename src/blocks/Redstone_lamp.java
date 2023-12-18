package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import App.Tetris;

import java.awt.image.BufferedImage;

import block.Cell;

//红石灯，周围有红石灯会亮
public class Redstone_lamp extends Cell{
    //图像  
    public int off_time;
    public static BufferedImage image_off;
    public static BufferedImage image_on;
    static {
        try {
            image_off = ImageIO.read(new File("images/redstone_lamp_off.png"));
            image_on = ImageIO.read(new File("images/redstone_lamp_on.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Redstone_lamp(int row, int col, Tetris tetris)
    {
        super(row, col, image_off, tetris);
        off_time = 0;
    }
    
    @Override
    public void onTrigger()
    {
        off_time = 2;
        this.setImage(image_on);
    }

    @Override
    public void onUpdate()
    {
        if (off_time == 0)
        {
            this.setImage(image_off);
        }
        else
        {
            off_time = off_time - 1;
        }
    }

}

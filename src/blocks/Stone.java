package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import App.Tetris;

import java.awt.image.BufferedImage;

import block.Cell;

//石头
public class Stone extends Cell{
    //图像
    public static BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("images/stone.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stone(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }

}

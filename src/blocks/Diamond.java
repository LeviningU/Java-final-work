package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import App.Tetris;

import java.awt.image.BufferedImage;

import block.Cell;

//钻石，无特殊效果
public class Diamond extends Cell{
    //图像
    public static BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("images/diamond_block.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Diamond(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }

}

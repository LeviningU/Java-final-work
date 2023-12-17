package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import App.Tetris;

import java.awt.image.BufferedImage;

//import App.Tetris;
import block.Cell;
//import block.Tetromino;

//石头
public class Obsidian extends Cell{
    //图像
    public static BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("images/obsidian.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Obsidian(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }

    public void onDestory()
    {
        tetris.landToActualWall(new Stone(getRow(), getCol(), tetris), false);
    }

}

package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import App.Tetris;

import java.awt.image.BufferedImage;

import block.Cell;

//红石块，会触发周围的红石元件
public class Redstone extends Cell{
    //图像
    public static BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("images/redstone_block.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Redstone(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }

    public void onUpdate()
    {
        if(tetris.getCell(getRow() - 1, getCol()) != null)
        {
            tetris.getCell(getRow() - 1, getCol()).onTrigger();
        }
        if(tetris.getCell(getRow() + 1, getCol()) != null)
        {
            tetris.getCell(getRow() + 1, getCol()).onTrigger();
        }
        if(tetris.getCell(getRow(), getCol() - 1) != null)
        {
            tetris.getCell(getRow(), getCol() - 1).onTrigger();
        }
        if(tetris.getCell(getRow(), getCol() + 1) != null)
        {
            tetris.getCell(getRow(), getCol() + 1).onTrigger();
        }
    }
}

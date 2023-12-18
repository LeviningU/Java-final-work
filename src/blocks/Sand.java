package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import App.Tetris;

import java.awt.image.BufferedImage;

import block.Cell;

//沙子，会掉落，能被岩浆块烧成玻璃
public class Sand extends Cell{
    //图像，48*48
    public static BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("images/sand.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sand(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }

    @Override
    public void onUpdate()
    {
        if(tetris.getCell(getRow() + 1, getCol()) == null && getRow() < 17)
        {
            tetris.moveTo(this, getRow() + 1, getCol(), true);
        }
    }
}

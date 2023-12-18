package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import App.Tetris;

import java.awt.image.BufferedImage;

import block.Cell;

//泥土，当其上方无方块时变为草方块
public class Dirt extends Cell{
    //泥土图像，48*48
    public static BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("images/dirt.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dirt(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }

    @Override
    public void onAllLand()
    {
        if(tetris.getCell(getRow() - 1, getCol()) == null)
        {
            tetris.landToActualWall(new Grass(getRow(), getCol(), tetris), false);
        }
    }

    @Override
    public void onUpdate()
    {
        if(tetris.getCell(getRow() - 1, getCol()) == null)
        {
            tetris.landToActualWall(new Grass(getRow(), getCol(), tetris), false);
        }
        
    }
}

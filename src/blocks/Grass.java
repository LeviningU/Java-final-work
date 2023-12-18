package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import App.Tetris;

import java.awt.image.BufferedImage;

import block.Cell;

//草方块，当上方存在方块时变为泥土
public class Grass extends Cell{
    //图像，48*48
    public static BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("images/grass_block_side.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Grass(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }

    @Override
    public void onUpdate()
    {
        if(tetris.getCell(getRow() - 1, getCol()) != null)
        {
            tetris.landToActualWall(new Dirt(getRow(), getCol(), tetris), false);
        }
        
    }
}

package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import App.Tetris;

import java.awt.image.BufferedImage;

//import App.Tetris;
import block.Cell;
//import block.Tetromino;

//沙砾
public class Gravel extends Cell{
    //图像，48*48
    public static BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("images/gravel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Gravel(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }

    @Override
    public void onUpdate()
    {
        if(tetris.getCell(getRow() + 1, getCol()) == null && getRow() < 17)
        {
            tetris.moveTo(this, getRow() + 1, getCol(), true);
            //tetris.landToActualWall(new Gravel(getRow() + 1, getCol(), tetris), true);
            //tetris.destroyWall(getRow(), getCol());
        }
    }
}

package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import App.Tetris;

import java.awt.image.BufferedImage;

//import App.Tetris;
import block.Cell;
//import block.Tetromino;

//树叶
public class Log extends Cell{
    //图像
    public static BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("images/Log_oak.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Log(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }

    public void onAllLand()
    {
        tetris.landToActualWall(new Leaves(getRow() - 1, getCol(), tetris), true);
        tetris.landToActualWall(new Leaves(getRow(), getCol() - 1, tetris), true);
        tetris.landToActualWall(new Leaves(getRow() + 1, getCol(), tetris), true);
        tetris.landToActualWall(new Leaves(getRow(), getCol() + 1, tetris), true);
    }
}

package blocks;

import App.Tetris;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import block.Cell;
public class Magma extends Cell{
    public static BufferedImage image;
    protected int[][] offstets={{-1,0},{0,1},{1,0},{0,-1}};
    static {
        try {
            image = ImageIO.read(new File("images/magma.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate()
    {
        ArrayList<Cell> nearbyCells = new ArrayList<>();
        for(int[] offset : offstets)
        {
            Cell cell = tetris.getCell(getRow() + offset[0], getCol() + offset[1]);
            if(cell != null){
                nearbyCells.add(cell);
            }
        }
        for(Cell cell : nearbyCells)
        {
            if(cell instanceof Sand)
            {
                tetris.landToActualWall(new Glass(cell.getRow(), cell.getCol(),tetris),false);
            }
        }
    }
    public Magma(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }

}

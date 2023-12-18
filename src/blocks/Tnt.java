package blocks;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import App.Tetris;

import java.awt.image.BufferedImage;

//import App.Tetris;
import block.Cell;
//import block.Tetromino;

//tnt
public class Tnt extends Cell{
    //图像  
    public boolean trigger;
    public int boom_time;
    public static BufferedImage image;
    public static BufferedImage image_boom;
    static {
        try {
            image = ImageIO.read(new File("images/tnt_side.png"));
            image_boom = ImageIO.read(new File("images/tnt_boom.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tnt(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
        trigger = false;
        boom_time = 3;
    }

    public void onTrigger()
    {
        trigger = true;
    }

    public void onUpdate()
    {
        if (trigger == false)
        {
            boom_time = 3;
        }
        if (trigger == true && boom_time != 0)
        {
            if(tetris.getCell(getRow() + 1, getCol()) == null && getRow() < 17)
            {
                tetris.moveTo(this, getRow() + 1, getCol(), true);
                boom_time = boom_time + 1;
            }
            boom_time = boom_time - 1;
            if(boom_time % 2 == 0)
            {
                this.setImage(image_boom);
            }
            else
            {
                this.setImage(image);
            }
        }
        else if (trigger == true && boom_time == 0)
        {
            //System.out.println("boom");
            int[] rowandcol = {0, -1, 1};
            tetris.destroyWall(getRow(), getCol());
            for(int i : rowandcol)
            {
                for(int j : rowandcol)
                {
                    if(tetris.getCell(getRow() + i, getCol() + j) == null || i == 0 && j == 0)
                    {
                        continue;
                    }
                    if(tetris.getCell(getRow() + i, getCol() + j).getClass() == Tnt.class)
                    {
                        tetris.getCell(getRow() + i, getCol() + j).onTrigger();
                    }
                    else
                    {
                        tetris.destroyWall(getRow() + i, getCol() + j);
                    }
                }
            }
        }
    }

}

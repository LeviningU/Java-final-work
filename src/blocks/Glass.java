package blocks;

import App.Tetris;
import block.Cell;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Glass extends Cell {
    public static BufferedImage image;
    static {
        try {
            image = ImageIO.read(new File("images/glass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate()
    {
        Cell upperCell = tetris.getCell(getRow() - 1, getCol());
        if(upperCell != null&& !(upperCell instanceof Glass))
        {
            tetris.destroyWall(getRow(), getCol());
        }
    }

    public Glass(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }
}

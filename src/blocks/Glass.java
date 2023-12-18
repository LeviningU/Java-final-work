package blocks;

import App.Tetris;
import block.Cell;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//玻璃，上方落下方块的时候破碎（消失）
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
    public void glassBreak()
    {
        tetris.destroyWall(getRow(), getCol());
    }

    public Glass(int row, int col, Tetris tetris)
    {
        super(row, col, image, tetris);
    }
}

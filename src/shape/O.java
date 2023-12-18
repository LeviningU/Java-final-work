package shape;

import java.lang.reflect.Constructor;

import App.Tetris;
import block.Cell;
import block.Tetromino;


public class O extends Tetromino {
    //反射，初始化特定的方块
    public O(Class<? extends Cell> cellClass, Tetris tetris) {
        try {
            Constructor<? extends Cell> constructor = cellClass.getDeclaredConstructor(int.class, int.class, Tetris.class);
            cells[0] = constructor.newInstance(0, 4, tetris);
            cells[1] = constructor.newInstance(0, 5, tetris);
            cells[2] = constructor.newInstance(1, 4, tetris);
            cells[3] = constructor.newInstance(1, 5, tetris);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //cells[0] = new Cell(0, 4, Tetris.O);
        //cells[1] = new Cell(0, 5, Tetris.O);
        //cells[2] = new Cell(1, 4, Tetris.O);
        //cells[3] = new Cell(1, 5, Tetris.O);

        //无旋转状态
        states = new State[0];
    }
}

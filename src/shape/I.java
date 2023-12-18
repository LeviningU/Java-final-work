package shape;

import java.lang.reflect.Constructor;

import App.Tetris;
import block.Cell;
import block.Tetromino;


public class I extends Tetromino {
    //反射，初始化特定的方块
    public I(Class<? extends Cell> cellClass, Tetris tetris) {
        try {
            //Cell cellInstance = cellClass.getDeclaredConstructor(int.class, int.class).newInstance(x, y);
            Constructor<? extends Cell> constructor = cellClass.getDeclaredConstructor(int.class, int.class, Tetris.class);
            cells[0] = constructor.newInstance(0, 4, tetris);
            cells[1] = constructor.newInstance(0, 3, tetris);
            cells[2] = constructor.newInstance(0, 5, tetris);
            cells[3] = constructor.newInstance(0, 6, tetris);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //cells[0] = new Cell(0,4, Tetris.I);
        //cells[1] = new Cell(0,3, Tetris.I);
        //cells[2] = new Cell(0,5, Tetris.I);
        //cells[3] = new Cell(0,6, Tetris.I);

        //共有两种旋转状态
        states =new State[2];
        //初始化两种状态的相对坐标
        states[0]=new State(0,0,0,-1,0,1,0,2);
        states[1]=new State(0,0,-1,0,1,0,2,0);
    }

}

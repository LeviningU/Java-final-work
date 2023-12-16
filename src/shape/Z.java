package shape;

import java.lang.reflect.Constructor;

//import App.Tetris;
import block.Cell;
import block.Tetromino;


public class Z extends Tetromino {
    //反射，初始化特定的方块
    public Z(Class<? extends Cell> cellClass) {
        try {
            //Cell cellInstance = cellClass.getDeclaredConstructor(int.class, int.class).newInstance(x, y);
            Constructor<? extends Cell> constructor = cellClass.getDeclaredConstructor(int.class, int.class);
            cells[0] = constructor.newInstance(1, 4);
            cells[1] = constructor.newInstance(0, 3);
            cells[2] = constructor.newInstance(0, 4);
            cells[3] = constructor.newInstance(1, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //cells[0] = new Cell(1,4, Tetris.Z);
        //cells[1] = new Cell(0,3, Tetris.Z);
        //cells[2] = new Cell(0,4, Tetris.Z);
        //cells[3] = new Cell(1,5, Tetris.Z);

        //共有两种旋转状态
        states =new State[2];
        //初始化两种状态的相对坐标
        states[0]=new State(0,0,-1,-1,-1,0,0,1);
        states[1]=new State(0,0,-1,1,0,1,1,0);
    }
}

package shape;

import java.lang.reflect.Constructor;

//import App.Tetris;
import block.Cell;
import block.Tetromino;


public class L extends Tetromino {
    //反射，初始化特定的方块
    public L(Class<? extends Cell> cellClass) {
        try {
            //Cell cellInstance = cellClass.getDeclaredConstructor(int.class, int.class).newInstance(x, y);
            Constructor<? extends Cell> constructor = cellClass.getDeclaredConstructor(int.class, int.class);
            cells[0] = constructor.newInstance(0, 4);
            cells[1] = constructor.newInstance(0, 3);
            cells[2] = constructor.newInstance(0, 5);
            cells[3] = constructor.newInstance(1, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //cells[0] = new Cell(0,4, Tetris.L);
        //cells[1] = new Cell(0,3, Tetris.L);
        //cells[2] = new Cell(0,5, Tetris.L);
        //cells[3] = new Cell(1,3, Tetris.L);

        states=new State[4];
        states[0]=new State(0,0,0,-1,0,1,1,-1);
        states[1]=new State(0,0,-1,0,1,0,-1,-1);
        states[2]=new State(0,0,0,1,0,-1,-1,1);
        states[3]=new State(0,0,1,0,-1,0,1,1);
    }
}

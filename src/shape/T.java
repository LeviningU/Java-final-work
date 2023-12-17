package shape;

import java.lang.reflect.Constructor;

import App.Tetris;
//import App.Tetris;
import block.Cell;
import block.Tetromino;



public class T extends Tetromino {
    //反射，初始化特定的方块
    public T(Class<? extends Cell> cellClass, Tetris tetris) {
        try {
            //Cell cellInstance = cellClass.getDeclaredConstructor(int.class, int.class).newInstance(x, y);
            Constructor<? extends Cell> constructor = cellClass.getDeclaredConstructor(int.class, int.class, Tetris.class);
            cells[0] = constructor.newInstance(0, 4, tetris);
            cells[1] = constructor.newInstance(0, 3, tetris);
            cells[2] = constructor.newInstance(0, 5, tetris);
            cells[3] = constructor.newInstance(1, 4, tetris);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //cells[0] = new Cell(0,4, Tetris.T);
        //cells[1] = new Cell(0,3, Tetris.T);
        //cells[2] = new Cell(0,5, Tetris.T);
        //cells[3] = new Cell(1,4, Tetris.T);

        states=new State[4];
        states[0]=new State(0,0,0,-1,0,1,1,0);
        states[1]=new State(0,0,-1,0,1,0,0,-1);
        states[2]=new State(0,0,0,1,0,-1,-1,0);
        states[3]=new State(0,0,1,0,-1,0,0,1);
    }
}

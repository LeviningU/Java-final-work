package shape;

import java.lang.reflect.Constructor;

//import App.Tetris;
import block.Cell;
import block.Tetromino;
//import blocks.*;

/**
 * @author xiaoZhao
 * @date 2022/5/11
 * @describe
 */
public class I extends Tetromino {

    public I(Class<? extends Cell> cellClass) {
        try {
            //Cell cellInstance = cellClass.getDeclaredConstructor(int.class, int.class).newInstance(x, y);
            Constructor<? extends Cell> constructor = cellClass.getDeclaredConstructor(int.class, int.class);
            cells[0] = constructor.newInstance(0, 4);
            cells[1] = constructor.newInstance(0, 3);
            cells[2] = constructor.newInstance(0, 5);
            cells[3] = constructor.newInstance(0, 6);
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

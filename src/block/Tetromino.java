package block;

import java.util.ArrayList;
import java.util.List;

import App.Tetris;
import blocks.*;
import shape.*;

//写四方格父类
public class Tetromino {

    public Cell[] cells = new Cell[4];
    
    //方块类数组
    public static List<Class<? extends Cell>> cellClasses = new ArrayList<>();

    static {
        cellClasses.add(Dirt.class);
        cellClasses.add(Grass.class);
        cellClasses.add(Sand.class);
        cellClasses.add(Gravel.class);
        cellClasses.add(Stone.class);
        cellClasses.add(Diamond.class);
        cellClasses.add(Obsidian.class);
        cellClasses.add(Leaves.class);
        cellClasses.add(Log.class);
        cellClasses.add(Redstone.class);
        cellClasses.add(Redstone_lamp.class);
        cellClasses.add(Tnt.class);
        cellClasses.add(Glass.class);
        cellClasses.add(Magma.class);
        //在此添加更多类型
    }

    //旋转的状态
    protected State[] states;
    //声明旋转次数
    protected int count = 10000;


    //左移方法
    public void moveLeft() {
        for (Cell cell : cells) {
            cell.left();
        }
    }

    //右移方法
    public void moveRight() {
        for (Cell cell : cells) {
            cell.right();
        }
    }

    //单元格下落
    public void moveDrop() {
        for (Cell cell : cells) {
            cell.down();
        }
    }

    //编写随机生成四方格
    public static Tetromino randomOne(Tetris tetris) {
        int num = (int) (Math.random() * 7);

        //随机获取一个类
        Class<? extends Cell> c = cellClasses.get((int) (Math.random() * cellClasses.size()));

        Tetromino tetromino = null;
        switch (num) {
            case 0:
                tetromino = new I(c, tetris);
                break;
            case 1:
                tetromino = new J(c, tetris);
                break;
            case 2:
                tetromino = new L(c, tetris);
                break;
            case 3:
                tetromino = new O(c, tetris);
                break;
            case 4:
                tetromino = new S(c, tetris);
                break;
            case 5:
                tetromino = new T(c, tetris);
                break;
            case 6:
                tetromino = new Z(c, tetris);
                break;
        }

        return tetromino;
    }

    //顺时针旋转的方法
    public void rotateRight() {
        if (states.length == 0) {
            return;
        }

        //旋转次数+1
        count++;
        State s = states[count % states.length];
        Cell cell = cells[0];
        int row = cell.getRow();
        int col = cell.getCol();
        cells[1].setRow(row + s.row1);
        cells[1].setCol(col + s.col1);
        cells[2].setRow(row + s.row2);
        cells[2].setCol(col + s.col2);
        cells[3].setRow(row + s.row3);
        cells[3].setCol(col + s.col3);
    }

    //逆时针旋转的方法
    public void rotateLeft() {
        if (states.length == 0) {
            return;
        }

        //旋转次数+1
        count--;
        State s = states[count % states.length];
        Cell cell = cells[0];
        int row = cell.getRow();
        int col = cell.getCol();
        cells[1].setRow(row + s.row1);
        cells[1].setCol(col + s.col1);
        cells[2].setRow(row + s.row2);
        cells[2].setCol(col + s.col2);
        cells[3].setRow(row + s.row3);
        cells[3].setCol(col + s.col3);
    }

    //四方格旋转状态的内部类
    protected class State {
        //存储四方格各元素的位置
        int row0, col0, row1, col1, row2, col2, row3, col3;

        public State() {
        }

        public State(int row0, int col0, int row1, int col1, int row2, int col2, int row3, int col3) {
            this.row0 = row0;
            this.col0 = col0;
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.row3 = row3;
            this.col3 = col3;
        }

        public int getRow0() {
            return row0;
        }

        public void setRow0(int row0) {
            this.row0 = row0;
        }

        public int getCol0() {
            return col0;
        }

        public void setCol0(int col0) {
            this.col0 = col0;
        }

        public int getRow1() {
            return row1;
        }

        public void setRow1(int row1) {
            this.row1 = row1;
        }

        public int getCol1() {
            return col1;
        }

        public void setCol1(int col1) {
            this.col1 = col1;
        }

        public int getRow2() {
            return row2;
        }

        public void setRow2(int row2) {
            this.row2 = row2;
        }

        public int getCol2() {
            return col2;
        }

        public void setCol2(int col2) {
            this.col2 = col2;
        }

        public int getRow3() {
            return row3;
        }

        public void setRow3(int row3) {
            this.row3 = row3;
        }

        public int getCol3() {
            return col3;
        }

        public void setCol3(int col3) {
            this.col3 = col3;
        }

        @Override
        public String toString() {
            return "State{" +
                    "row0=" + row0 +
                    ", col0=" + col0 +
                    ", row1=" + row1 +
                    ", col1=" + col1 +
                    ", row2=" + row2 +
                    ", col2=" + col2 +
                    ", row3=" + row3 +
                    ", col3=" + col3 +
                    '}';
        }
    }
}

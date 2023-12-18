package block;

//import blocks.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import App.Tetris;
import blocks.Glass;


//小方块类
public class Cell {
    // 行
    private int row;
    // 列
    private int col;
    private BufferedImage image;

    protected Tetris tetris;

    public Cell() {
    }

    public Cell(int row, int col, BufferedImage image, Tetris tetris) {
        this.row = row;
        this.col = col;
        this.image = image;
        this.tetris = tetris;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", col=" + col +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Cell)) {
            return false;
        }
        Cell cell = (Cell) o;
        return getRow() == cell.getRow() &&
                getCol() == cell.getCol() &&
                Objects.equals(getImage(), cell.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getCol(), getImage());
    }

    //左移动一格
    public void left(){
        col--;
    }

    //右移动一格
    public void right(){
        col++;
    }

    //下移动一格
    public void down(){
        row++;
    }

    //该细胞嵌入时
    public void onLand()
    {
    }

    //该组细胞全部嵌入后
    public void onAllLand()
    {
        Cell cell = tetris.getCell(getRow() + 1, getCol());
        if(cell != null && cell instanceof Glass && !(this instanceof Glass))
        {
            cell.glassBreak();
        }
    }

    //该细胞被删除前
    public void beforeDestory()
    {
    }

    //该细胞被删除后
    public void onDestory()
    {
    }

    //该细胞被触发时（红石）
    public void onTrigger()
    {
    }

    //该细胞被更新时
    public void onUpdate()
    {
    }

    //玻璃破碎
    public void glassBreak()
    {
    }
}

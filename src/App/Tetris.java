package App;

import block.Cell;
import block.Tetromino;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//主类
public class Tetris extends JPanel {

    //正在下落的方块
    private Tetromino currentOne = Tetromino.randomOne(this);
    //将要下落的方块
    private Tetromino nextOne = Tetromino.randomOne(this);
    //游戏主区域
    private Cell[][] wall = new Cell[18][9];
    //声明单元格的大小
    private static final int CELL_SIZE = 48;

    //游戏分数池，连续消除行，分数高
    int[] scores_pool = {0, 1, 2, 5, 10};
    //当前游戏的分数
    private int totalScore = 0;
    //当前消除的行数
    private int totalLine = 0;

    //游戏三种状态 游戏中、暂停、结束
    public static final int PLING = 0;
    public static final int STOP = 1;
    public static final int OVER = 2;
    //当前游戏状态值
    private int game_state;
    //显示游戏状态
    String[] show_state = {"P[pause]", "C[continue]", "S[replay]"};

    public static BufferedImage background;

    static {
        try {
            background = ImageIO.read(new File("images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);
        //平移坐标轴
        g.translate(22, 15);
        //绘制游戏主区域
        paintWall(g);
        //绘制正在下落的四方格
        paintCurrentOne(g);
        //绘制下一个将要下落的四方格
        paintNextOne(g);
        //绘制游戏得分
        paintSource(g);
        //绘制当前游戏状态
        paintState(g);
    }

    //主程序
    public void start() {
        game_state = PLING;
        KeyListener l = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                switch (code) {
                    case KeyEvent.VK_DOWN:
                        sortDropActive();
                        break;
                    case KeyEvent.VK_LEFT:
                        moveleftActive();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRightActive();
                        break;
                    case KeyEvent.VK_UP:
                        rotateRightActive();
                        break;
                    case KeyEvent.VK_SPACE:
                            hadnDropActive();
                        break;
                    case KeyEvent.VK_P:
                        //判断当前游戏状态
                        if (game_state == PLING) {
                            game_state = STOP;
                        }
                        break;
                    case KeyEvent.VK_C:
                        if (game_state == STOP) {
                            game_state = PLING;
                        }
                        break;
                    case KeyEvent.VK_S:
                        //重新开始
                        game_state = PLING;
                        wall = new Cell[18][9];
                        currentOne = Tetromino.randomOne(Tetris.this);
                        nextOne = Tetromino.randomOne(Tetris.this);
                        totalScore = 0;
                        totalLine = 0;
                        break;
                }
            }
        };
        //将窗口设置为焦点
        this.addKeyListener(l);
        this.requestFocus();
        int time = 0;
        while (true) {
            if (game_state == PLING) {
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time = time + 1;
                //每800ms更新并下落一次
                if(time % 50 == 0)
                {
                    //调用每个cell的更新函数
                    for(Cell[] rowCells : wall)
                    {
                        for(Cell cell : rowCells)
                        {
                            if(cell != null)
                            {
                                cell.onUpdate();
                            }
                        }
                    }
                    //检测并删除行
                    destroyLine();
                    time = 0;
                    if (canDrop()) {
                        currentOne.moveDrop();
                    } else {
                        landToWall();
                        destroyLine();
                        if (isGameOver()) {
                            game_state = OVER;
                        } else {
                            //游戏没有结束
                            currentOne = nextOne;
                            nextOne = Tetromino.randomOne(this);
                        }
                    }
                }
                
            }
            //更新屏幕
            repaint();
        }
    }

    //创建顺时针旋转
    public void rotateRightActive() {
        currentOne.rotateRight();
        if (outOFBounds() || coincide()) {
            currentOne.rotateLeft();
        }
    }

    //瞬间下落
    public void hadnDropActive() {
        while (true) {
            //判断能否下落
            if (canDrop()) {
                currentOne.moveDrop();
            } else {
                break;
            }
        }
        //嵌入到墙中
        landToWall();
        //检测并删除行
        destroyLine();
        if (isGameOver()) {
            game_state = OVER;
        } else {
            //游戏没有结束
            currentOne = nextOne;
            nextOne = Tetromino.randomOne(this);
        }
    }

    //按键一次，下落一格
    public void sortDropActive() {
        if (canDrop()) {
            //当前四方格下落一格
            currentOne.moveDrop();
        } else {
            //嵌入到墙中
            landToWall();
            //检测并删除行
            destroyLine();
            if (isGameOver()) {
                game_state = OVER;
            } else {
                //游戏没有结束
                currentOne = nextOne;
                nextOne = Tetromino.randomOne(this);
            }
        }
    }

    //返回指定位置的cell，不存在或出界则返回null
    public Cell getCell(int row, int col)
    {
        //是否出界
        if(row <= 17 && row >= 0 && col <= 8 && col >= 0)
        {
            if(wall[row][col] != null)
            {
                return wall[row][col];
            }
        }
        return null;
    }

    //删除指定位置
    public void destroyWall(int row, int col) {
        if (wall[row][col] != null)
        {
            //先删除，再调用被删除函数
            Cell cell = wall[row][col];
            wall[row][col] = null;
            cell.onDestory();
        }
    }

    //将特定类型嵌入到指定位置的墙中，check为是否检测指定位置存在方块，若为false则会覆盖，true则只替换空格
    public void landToActualWall(Cell cell, boolean check) {
        int row = cell.getRow();
        int col = cell.getCol();
        if(row <= 17 && row >= 0 && col <= 8 && col >= 0)
        {
            if(wall[row][col] == null || !check)
            {
                wall[row][col] = cell;
                cell.onLand();
            }
        }
    }

    //移动cell到指定位置，check意义同上
    public void moveTo(Cell cell, int row, int col, boolean check) {
        
        if(row <= 17 && row >= 0 && col <= 8 && col >= 0)
        {
            if(wall[row][col] == null || !check)
            {
                wall[row][col] = wall[cell.getRow()][cell.getCol()];
                wall[cell.getRow()][cell.getCol()] = null;
                wall[row][col].setRow(row);
                wall[row][col].setCol(col);
            }
        }
    }

    //单元格嵌入墙中
    private void landToWall() {
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            wall[row][col] = cell;
            //单细胞嵌入时
            cell.onLand();
        }
        
        for (Cell cell : cells) {
            //该组细胞全部嵌入后
            cell.onAllLand();
        }
    }

    //判断四方格能否下落
    public boolean canDrop() {
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            //判断是否到达底部
            if (row == wall.length - 1) {
                return false;
            } else if (wall[row + 1][col] != null) {
                return false;
            }
        }
        return true;
    }

    //消除行
    public void destroyLine() {
        int line = 0;
        for (int row = 0; row < 18; row++) 
        {
            if (isFullLine(row)) {
                line++;
                //当满行的cell被删除前
                for(Cell cell : wall[row])
                {
                    cell.beforeDestory();
                }
                //当满行的cell被删除时
                for(int j = 0; j < 9; j++)
                {
                    Cell cell = wall[row][j];
                    wall[row][j] = null;
                    cell.onDestory();
                }
                //从下往上开始把方块往下移动
                for (int i = row - 1; i > 0; i--) {
                    for(int j = 0; j < 9; j++)
                    {
                        if (wall[i][j] != null)
                        {
                            moveTo(wall[i][j], i + 1, j, true);
                        }
                    }
                    //System.arraycopy(wall[i - 1], 0, wall[i], 0, wall[0].length);
                    for(Cell cell : wall[i])
                    {
                        if (cell != null)
                        {
                            cell.setRow(i);
                        }
                    }
                }
                wall[0] = new Cell[9];
            }
        }
        //分数池获取分数，累加到总分
        totalScore += scores_pool[line];
        //总行数
        totalLine += line;
    }

    //判断当前行是否已经满了
    public boolean isFullLine(int row) {
        Cell[] cells = wall[row];
        for (Cell cell : cells) {
            if (cell == null) {
                return false;
            }
        }
        return true;
    }

    //判断游戏是否结束
    public boolean isGameOver() {
        Cell[] cells = nextOne.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            if (wall[row][col] != null) {
                return true;
            }
        }
        return false;
    }

    //显示状态
    private void paintState(Graphics g) {
        if (game_state == PLING) {
            g.drawString(show_state[PLING], 500, 660);
        } else if (game_state == STOP) {
            g.drawString(show_state[STOP], 500, 660);
        } else {
            g.drawString(show_state[OVER], 500, 660);
            g.setColor(Color.RED);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
            g.drawString("GAME OVER!", 30, 400);
        }
    }

    //显示分数
    private void paintSource(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        g.drawString("分数: " + totalScore, 500, 250);
        g.drawString("行数: " + totalLine, 500, 430);
    }

    //显示下一个四方格
    private void paintNextOne(Graphics g) {
        Cell[] cells = nextOne.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE + 370;
            int y = cell.getRow() * CELL_SIZE + 25;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    //显示当前四方格
    private void paintCurrentOne(Graphics g) {
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE;
            int y = cell.getRow() * CELL_SIZE;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    //显示格线
    private void paintWall(Graphics g) {
        for (int i = 0; i < wall.length; i++) {
            for (int j = 0; j < wall[i].length; j++) {
                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;
                Cell cell = wall[i][j];
                //判断是否有小方块
                if (cell == null) {
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                    g.drawRect(x - 1, y - 1, CELL_SIZE, CELL_SIZE);
                } else {
                    g.drawImage(cell.getImage(), x, y, null);
                }
            }
        }
    }

    //判断是否出界
    public boolean outOFBounds() {
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int col = cell.getCol();
            int row = cell.getRow();
            if (row < 0 || row > wall.length - 1 || col < 0 || col > wall[0].length-1) {
                return true;
            }
        }
        return false;
    }

    //按键一次，左移一次
    public void moveleftActive() {
        currentOne.moveLeft();
        //判断是否越界或重合
        if (outOFBounds() || coincide()) {
            currentOne.moveRight();
        }
    }

    //按键一次，右移一次
    public void moveRightActive() {
        currentOne.moveRight();
        //判断是否越界或重合
        if (outOFBounds() || coincide()) {
            currentOne.moveLeft();
        }
    }

    //判断是否重合
    public boolean coincide() {
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            if (wall[row][col] != null) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Tetris but Minecraft");
        //创建游戏界面
        Tetris panel = new Tetris();
        jFrame.add(panel);
        //设置可见
        jFrame.setVisible(true);
        //设置窗口大小
        jFrame.setSize(810, 940);
        //设置剧中
        jFrame.setLocationRelativeTo(null);
        //设置窗口关闭时停止
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //游戏主要开始逻辑
        panel.start();
    }
}

# Java-final-work
Tetris but Minecraft


# 给方块类写了以下方法，除onTrigger外均会在主类特定时期被调用
    //该细胞嵌入时
    public void onLand()
    //该组细胞全部嵌入后
    public void onAllLand()
    //该细胞被删除时
    public void onDestory()
    //该细胞被触发时（红石）
    public void onTrigger()
    //该细胞被更新时
    public void onUpdate()

# 主类给方块类提供了以下方法
    //将特定类型嵌入到指定位置的墙中，check为false不检查指定位置是否存在方块，用于替换或覆盖，为true会检查，指定位置没方块才放置
    public void landToActualWall(Cell cell, boolean check)
    //返回wall中指定位置的cell
    public Cell getCell(int row, int col)

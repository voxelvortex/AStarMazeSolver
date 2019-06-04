public class Point
{
    private int x;
    private int y;
    private boolean isWall;
    private boolean isStart;
    private boolean isEnd;
    private int f;
    private int g;
    private int h;
    private Point parentPoint;

    Point(int x, int y, int type) {
        this.x = x;
        this.y = y;
        isWall = false;
        if(type == 1) {
            isWall = true;
        }
        if(type == 2) {
            isStart = true;
        }
        if(type == 3) {
            isEnd = true;
        }
    }

    public void setParentPoint(Point parentPoint){this.parentPoint = parentPoint;}

    public Point getParentPoint(){return parentPoint;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean isStart() {
        return isStart;
    }

    public boolean isWall() {
        return isWall;
    }

    public int getF()
    {
        return f;
    }

    public void setF()
    {
        f = g + h;
    }

    public void setG(int g)
    {
        this.g = g;
    }

    public void setH(int h)
    {
        this.h = h;
    }

    public String toString()
    {
        return "Coords: " +x + ","+y+"\n\tisWall"+isWall;
    }
}

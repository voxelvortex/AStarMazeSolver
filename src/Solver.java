import java.util.ArrayList;

public abstract class Solver
{
    int[][] maze;
    static Point[][] points;
    static Point start;
    static Point end;
    static ArrayList<Point> open;
    static ArrayList<Point> closed;
    static ArrayList<Point> solution;
    static boolean solvable;

    Solver(int[][] maze)
    {
        solution = new ArrayList<>();
        this.maze = maze;
        open = new ArrayList<>();
        closed = new ArrayList<>();
        points = new Point[maze.length][maze[0].length];
        setPointsArray();
        open.add(start);
    }

    private void setPointsArray()
    {
        for (int row = 0; row < points.length; row++) {
            for (int col = 0; col < points[row].length; col++) {
                points[row][col] = new Point(row, col, maze[row][col]);
                if (maze[row][col] == 2)
                {
                        start = points[row][col];
                }
                if (maze[row][col] == 3)
                {
                    end = points[row][col];
                }
            }
        }
    }

    protected abstract void algorithm();

    ArrayList<Point> getSolution()
    {
        algorithm();
        Point current = end;
        while(true)
        {
            if(!current.isWall())
                solution.add(current);
            if(current.getParentPoint() == null)
                break;
            current = current.getParentPoint();
        }
        solvable = solution.contains(end) && solution.contains(start);

        return solution;
    }

    public boolean isSolvable()
    {
        return solvable;
    }

    void addPoint(ArrayList<Point> points, Point p) {
    if(points.size() == 0){
        points.add(p);
        return;
    }
    for(int i = 0; i < points.size(); i++)
        if (points.get(i).getF() <= p.getF()) {
            points.add(i, p);
            return;
        }
    points.add(p);
}


    Point getLowestInList(ArrayList<Point> ps)
    {
        if(ps.isEmpty())
            return null;
        return ps.get(0);
    }

    public void printMaze() {
        for (int[] is : maze)
        {
            for (int i : is)
                System.out.print(" "+i);
            System.out.println();
        }
    }
}

import java.util.ArrayList;

public class Solver
{
    private int[][] maze;
    private static Point[][] points;
    private static int[] start;
    private static int[] end;
    private static ArrayList<Point> open;
    private static ArrayList<Point> closed;

    Solver(int[][] maze)
    {
        this.maze = maze;
        open = new ArrayList<>();
        closed = new ArrayList<>();
        points = new Point[maze.length][maze[0].length];
        start = new int[2];
        end = new int[2];
        setPointsArray();
        open.add(points[start[0]][start[1]]);
    }

    private void setPointsArray()
    {
        for (int row = 0; row < points.length; row++) {
            for (int col = 0; col < points[row].length; col++) {
                points[row][col] = new Point(row, col, maze[row][col]);
                if (maze[row][col] == 2)
                        start = new int[]{row, col};
                if (maze[row][col] == 3)
                    end = new int[]{row, col};
            }
        }
    }

    public ArrayList<Point> getSolution()
    {
        Point current = points[end[0]][end[1]];
        ArrayList<Point> solution = new ArrayList<>();

        AStarAlgorithm();

        while(true)
        {
            try
            {
                if(!current.isWall())
                    solution.add(current);
                current = current.getParentPoint();
            }catch(NullPointerException e){break;}
        }
        if(!solution.contains(points[end[0]][end[1]]) || !solution.contains(points[start[0]][start[1]]))
            System.out.println("No solution found!");


        return solution;
    }

    private void AStarAlgorithm()
    {
        open.add(points[start[0]][start[1]]);
        Point current;
        while(!open.isEmpty() && !closed.contains(points[end[0]][end[1]]))
        {
            current = getLowestInList(open);
            open.remove(current);
            closed.add(current);
            open = setupAdjacentPoints(current, open, closed);
        }

    }

    private void setupPoint(Point p)
    {
        p.setH(Math.abs(end[0] - p.getX()) + Math.abs(end[1] - p.getY()));
        p.setG(Math.abs(start[0] - p.getX()) + Math.abs(start[1] - p.getY()));
        p.setF();
    }

    public ArrayList<Point> setupAdjacentPoints(Point p, ArrayList<Point> open, ArrayList<Point> closed)
    {
        int x = p.getX();
        int y = p.getY();
        int width = points[0].length-1;
        int height = points.length-1;

        for(int row = -1; row <= 1; row++)
        {
            for(int col = -1; col <= 1; col++)
            {
                //check if it's p
                if(row == 0 && col==0)
                    continue;
                //make sure it isn't out of bounds
                if(x + row < 0 || x + row > width)
                    continue;
                if(y + col < 0 || y + col > height)
                    continue;
                //make sure this is ok to check
                if(closed.contains(points[x+row][y+col]))
                    continue;
                //make sure it isn't a wall
                if(points[x+row][y+col].isWall())
                    continue;
                if(row == -1)
                {
                    if(col == -1)
                        continue;
                    if(col == 1)
                        continue;
                }
                if(row == 1)
                {
                    if(col == -1)
                        continue;
                    if(col == 1)
                        continue;
                }
                if(open.contains(points[x+row][y+col]))
                {
                    if(p.getF() < points[row+x][col+y].getParentPoint().getF())
                        points[x+row][y+col].setParentPoint(p);
                    continue;
                }
                setupPoint(points[row+x][col+y]);
                points[row+x][col+y].setParentPoint(p);
                open.add(points[row+x][col+y]);
            }

        }
        return open;
    }

    private Point getLowestInList(ArrayList<Point> ps)
    {
        if(ps.isEmpty())
            return null;
        int lowest = Integer.MAX_VALUE;
        Point lowestP = ps.get(0);
        for(Point p: ps)
        {
            if(lowest < p.getF())
            {
                lowest = p.getF();
                lowestP = p;
            }
        }
        return lowestP;
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
public class AStarSolver extends Solver {

    public AStarSolver(int[][] maze) {
        super(maze);
    }

    public void algorithm()
    {
        Point current;
        while(!open.isEmpty() && !closed.contains(end))
        {
            current = getLowestInList(open);
            open.remove(current);
            closed.add(current);
            setupAdjacentPoints(current);
        }

    }

    private void setupPoint(Point p)
    {
        p.setH(Math.abs(end.getX() - p.getX()) + Math.abs(end.getY() - p.getY()));
        p.setG(Math.abs(start.getX() - p.getX()) + Math.abs(start.getY() - p.getY()));
        p.setF();
    }

    private void setupAdjacentPoints(Point p)
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
                addPoint(open, points[row+x][col+y]);

            }

        }
    }
}

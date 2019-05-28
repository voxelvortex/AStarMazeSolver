public class AStarSolver extends Solver {

    public AStarSolver(int[][] maze) {
        super(maze);
    }

    protected void algorithm()
    {
        Point current;
        while(!open.isEmpty() && !closed.contains(end))
        {
            current = getLowestInList(open);
            open.remove(current);
            closed.add(current);
            open = setupAdjacentPoints(current, open, closed);
        }

    }

    protected void setupPoint(Point p)
    {
        p.setH(Math.abs(end.getX() - p.getX()) + Math.abs(end.getY() - p.getY()));
        p.setG(Math.abs(start.getX() - p.getX()) + Math.abs(start.getY() - p.getY()));
        p.setF();
    }
}

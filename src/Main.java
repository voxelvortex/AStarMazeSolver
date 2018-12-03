import java.util.ArrayList;

public class Main {
    public static int[][] maze2 =   {{1,2,1,3},
            {0,0,1,0},
            {0,1,0,0},
            {0,0,0,1}};
    public static int[][] maze1 =   {{2,1,1,3},
            {0,1,1,0},
            {0,1,0,0},
            {0,0,0,1}};

    public static int[][] maze =   {{1,2,3,1},
            {0,1,1,0},
            {0,1,0,0},
            {0,0,0,1}};

    public static int[][] maze3 = {{3,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,2}};

    public static void main(String[] args)
    {
        System.out.println("Importing maze...");
        //import the maze
        String input = "maze2";
        MazeMaker mm = new MazeMaker("C:\\Users\\maest\\IdeaProjects\\MazeSolver\\mazes\\"+ input+".png");

        //make maze into 2d array
        maze = mm.getMaze();

        //solve the maze
        System.out.println("Solving maze...");
        Solver solver = new Solver(maze);

        //get solution into a ArrayList
        ArrayList<Point> solution = solver.getSolution();

        //print out the maze
        System.out.println("Printing maze");
        //solver.printMaze();

        System.out.println();

        //print out the maze with the solution
        //buildSolvedMaze(solution, maze);

        //draw the solution on the imported image
        System.out.println("Printing img...");
        mm.drawOnImg(solution);

        //write the image with the solution drawn on it to the hdd
        mm.writeImageToHDD("C:\\Users\\maest\\IdeaProjects\\MazeSolver\\mazes\\"+input+"_solved.png");
    }

    public static void buildSolvedMaze(ArrayList<Point> solution, int[][] maze)
    {
        String[][] mazeS = new String[maze.length][maze[0].length];
        for(int row = 0; row < maze.length; row++)
            for(int col = 0; col < maze[0].length; col++)
            {
                mazeS[row][col] = Integer.toString(maze[row][col]);
            }

        for(Point p: solution)
        {
            mazeS[p.getX()][p.getY()] = "#";
        }


        for(String[] m: mazeS)
        {
            for(String i: m)
                System.out.print((" "+i).replace('#','+').replace('1','#'));
            System.out.println();
        }
    }
}

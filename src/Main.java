import javax.sound.midi.SysexMessage;
import java.io.File;
import java.util.ArrayList;

public class Main {
    //testing mazes
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
        if(args.length != 1)
        {
            System.out.println("Incorrect number of parameters!");
            System.exit(1);
        }
        if(!filetypeIsSupported(args[0]))
        {
            System.out.println("Invalid file path!");
            System.exit(1);
        }

        System.out.println("Importing maze...");
        //import the maze
        MazeMaker mm = new MazeMaker(args[0]);

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
        mm.writeImageToHDD(outputFileName(args[0]));
    }

    public static boolean filetypeIsSupported(String filepath)
    {
        String[] supportedFiletypes = {".jpeg",".png",".bmp",".wbmp",".gif"}; /*pulled from oracle.com, . included to
                                                                              avoid files like picturejpg, that don't
                                                                              include a filetype
                                                                              */
        for(String ft: supportedFiletypes)
            if(filepath.toLowerCase().endsWith(ft.toLowerCase()))
                return true;

        return false;
    }

    public static String outputFileName(String inputFileName)
    {
        File file = new File(inputFileName);
        String parent = file.getParent();
        String filename = getFileNameWithoutExt(file);
        String outputFileName;
        //System.out.println(parent.equals("null"));
        if(parent == null || parent.equals("null"))
            outputFileName = filename+"_solved.png";
        else
            outputFileName = parent+"\\"+filename+"_solved.png";
        System.out.println(outputFileName);
        return outputFileName;
    }

    public static String getFileNameWithoutExt(File file)
    {
        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            name = name.substring(0, pos);
        }
        return name;
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

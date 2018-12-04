import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MazeMaker
{
    private BufferedImage img;
    MazeMaker(String path)
    {
        try {
            img = ImageIO.read(new File(path));
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
            System.exit(1);
        }
    }

    public int[][] getMaze()
    {
        System.out.println(img.toString());
        int[][] key = {{255,255,255},{0,0,0},{255,0,0},{0,0,255}};
        int[][] maze = new int[img.getWidth()][img.getHeight()];
        for(int row = 0; row < maze.length; row++)
        {
            for(int col = 0; col < maze[row].length; col++)
            {
                Color color = new Color(img.getRGB(row,col));
                int[] current = {color.getRed(), color.getGreen(), color.getBlue()};

                for(int i = 0; i < key.length; i++)
                {
                    boolean same = true;
                    for(int f = 0; f <3; f++)
                        if(key[i][f] != current[f])
                        {
                            same = false;
                            break;
                        }
                    if(same)
                        maze[row][col] = i;
                }
            }
        }
        return maze;
    }

    public void drawOnImg(ArrayList<Point> solution)
    {
        for(Point p: solution)
        {
            int x = p.getX();
            int y = p.getY();
            int color = (255<<24) | (0<<16) | (255<<8) | 0; //A R G B

            img.setRGB(x, y, color);
        }
    }

    public void writeImageToHDD(String path)
    {
        try
        {
            ImageIO.write(img, "png", new File(path));
        }catch(IOException ioe)
        {
            System.out.println(ioe);
        }
    }
}

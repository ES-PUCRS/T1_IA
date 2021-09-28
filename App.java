import javax.swing.*;
import java.awt.*;
import java.io.*;

public class App {

	public static final int delay = 100;

	public static void main(String[] args) throws IOException, InterruptedException {
		try {
			Maze maze = new Maze(args[0]);

			JFrame frame = new JFrame("Maze");
			MazePanel panel = new MazePanel(maze);
			JScrollPane scrollPane = new JScrollPane(panel);
			
			PathFinderAStar pathFinderAStar = new PathFinderAStar(maze);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(scrollPane, BorderLayout.CENTER);
			frame.setVisible(true);
			frame.setSize(maze.getXLength(), maze.getYLength());
			frame.setLocationRelativeTo(null);

			/* Magic happens here ------------------------*/
			/**/	//CALL IA 							/**/
			/**/	try {								/**/
			/**/		Thread.sleep(1000);				/**/
			/**/		pathFinderAStar.findPath();		/**/
			/**/	} catch (Exception e) {				/**/
			/**/		e.printStackTrace();			/**/
			/**/	}									/**/
			/*--------------------------------------------*/

		} catch(NumberFormatException exception) {
			System.out.println("The input must be the maze file name.");
		}
	}
}

class MazePanel extends JPanel {
	private Maze maze;

	public MazePanel(Maze maze) {
		this.maze = maze;
		maze.setPanel(this);
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);		
		setBackground(Color.white);
		this.setPreferredSize(maze.windowSize());
		maze.draw(page);
	}
}
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class App {

	public static final int delay = 5; //ms
	public static final int begin = 1500; //ms

	public static void main(String[] args) throws IOException, InterruptedException {
		try {
			Table table = new Table(args[0]);

			JFrame frame = new JFrame("Maze");
			TablePanel panel = new TablePanel(table);
			JScrollPane scrollPane = new JScrollPane(panel);
			
			PathFinderAStar agent = new PathFinderAStar(table);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(scrollPane, BorderLayout.CENTER);
			frame.setVisible(true);
			frame.setSize(table.getXLength(), table.getYLength());
			frame.setLocationRelativeTo(null);

			/* Magic happens here ------------------------*/
			/**/	//CALL IA 							/**/
			/**/	try {								/**/
			/**/		Thread.sleep(begin);			/**/
			/**/		agent.findPath();				/**/
			/**/	} catch (Exception e) {				/**/
			/**/		e.printStackTrace();			/**/
			/**/	}									/**/
			/*--------------------------------------------*/

		} catch(NumberFormatException exception) {
			System.out.println("The input must be the table file name.");
		}
	}
}

class TablePanel extends JPanel {
	private Table table;

	public TablePanel(Table table) {
		this.table = table;
		table.setPanel(this);
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);		
		setBackground(Color.white);
		this.setPreferredSize(table.windowSize());
		table.draw(page);
	}
}
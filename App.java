import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class App {

	public static final int delay 	= 5; 	//ms
	public static final int begin 	= 1500; //ms
	public static final int between = 3000; //ms

	public static final boolean PDM = false;

	private static JFrame frame;
	public static void main(String[] args) throws IOException, InterruptedException {
		String instructions = "exec.exe <maze file> <number of generations> <population size> <mutation rate>";
		Table table;
		try {
			//table = init(args[0], "Path finder - A*");
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			System.out.println(instructions);
		}
		/* Magic happens here ------------------------*/
	
		// try {
		// 	Thread.sleep(begin);
		// 	new PathFinderAStar(table)
		// 			.findPath();
								
		// 	Thread.sleep(between);
		// 	close();
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }

		try {
			instructions = args[3];
			table = init(args[0], "Genetic Algorith");
			Thread.sleep(begin);
			new GeneticAlgorith(
				table,
		 		Integer.parseInt(args[1]),
		 		Integer.parseInt(args[2]),
		 		Integer.parseInt(args[3])
		 	);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(instructions);
		}
	}

	private static Table init(String tableFile, String windowName) {
		Table table = new Table(tableFile);
		frame = new JFrame(windowName);
		TablePanel panel = new TablePanel(table);
		JScrollPane scrollPane = new JScrollPane(panel);

		frame.setSize(table.getXLength(), table.getYLength());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		return table;
	}

	private static void close() {
		frame.dispatchEvent(
			new WindowEvent(
				frame,
				WindowEvent.WINDOW_CLOSING
			)
		);
	}
}

class TablePanel extends JPanel {
	private Table table;

	public TablePanel(Table table) {
		table.setPanel(this);
		this.table = table;
	}

	public void paintComponent(Graphics page) {
		this.setPreferredSize(table.windowSize());
		super.paintComponent(page);		
		setBackground(Color.white);
		table.draw(page);
	}
}

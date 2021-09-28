/*
 * Java Graphics idea from: Irene Alvarado 
 *
 *	moodle.pucrs.br/pluginfile.php/3858618/mod_resource/content/1/NRainhas.java
 *	moodle.pucrs.br/pluginfile.php/3849965/mod_resource/content/1/AG.java
 */

import java.lang.NoSuchFieldException;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileReader;
import java.util.Random;
import java.io.File;
import java.awt.*;

import java.util.Arrays;
import java.util.List;

public class Maze {
	public static final Font font = new Font("Arial", Font.PLAIN, 20);

	public static final int CELL_WIDTH = 20; // maze square size
	public static final int DOT_MARGIN = 5; // space between wall and dot
	public static final int DOT_SIZE = 10; // size of maze solution dot
	public static final int MARGIN = 50; // buffer between window edge and maze
	
	public final int X_LENGTH; // buffer between window edge and maze
	public final int Y_LENGTH; // buffer between window edge and maze
	
	private Block[] blocks; // array containing all the blocks in the maze	

    private int mazeSize;
    
    private ArrayList<Integer> road;
    private Integer entrance;
    private Integer exit;

    private String message;

	private MazePanel panel;


	final int NORTH = 0 ;
	final int SOUTH = 1 ;
	final int EAST = 2 ;
	final int WEST = 3 ;

	public class Block {
		final boolean wall;
		int visitedBy;
		String label;

		boolean ball;
		Color color;
		
		public Block(boolean wall) {
			this.wall = wall;
			visitedBy = -1;
			label = null;

			ball = false;
			color = null;
		}
	}
	
	public void setPanel(MazePanel panel) {
		this.panel = panel;
	}

	public Maze(String filename) {
		ArrayList<String[]> fileArray = new ArrayList<String[]>();
		road = new ArrayList<Integer>();
		File file = new File(filename);
		entrance = exit = null;
		FileReader fileReader;
		BufferedReader reader;
		message = "";
		String line;

		try {
			fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);
			
			try {
				while((line = reader.readLine()) != null)
					fileArray.add(line.split(" "));

				reader.close();
			} catch (IOException e) {
				System.out.println("ERRO ao ler o aquivo");
				System.exit(0);
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERRO ao ler o aquivo");
			System.exit(0);
		}

		X_LENGTH = fileArray.get(0).length;
		Y_LENGTH = fileArray.size();
		mazeSize = X_LENGTH * Y_LENGTH;
		String[] matriz = new String[mazeSize];

		int count = 0;
		for(String[] s : fileArray) {
			for(String c : s) {
				matriz[count] = c;
				count++;
			}
		}


		blocks = new Block[mazeSize];
		for (int i = 0; i < mazeSize; i++) {
			if(matriz[i].equals("1")) {
				blocks[i] = new Block(true);
			} else {
				blocks[i] = new Block(false);
				road.add(i);

				if(matriz[i].equals("E")) {
					blocks[i].label = matriz[i];
					entrance = i;
				}
				if(matriz[i].equals("S")) {
					blocks[i].label = matriz[i];
					exit = i;
				}
			}
		}
	}


	public Integer getUpPos(int cur) {
		if( (cur + X_LENGTH) > mazeSize ) return null;
		return cur + X_LENGTH;
	}
	public Integer getDownPos(int cur)  {
		if( (cur - X_LENGTH) < 0 ) return null;
		return cur - X_LENGTH;
	}
	public Integer getLeftPos(int cur)  {
		if( (cur - 1)/X_LENGTH != (cur/X_LENGTH) ) return null;
		return cur - 1;
	}
	public Integer getRightPos(int cur) {
		if( (cur + 1)/X_LENGTH != (cur/X_LENGTH) ) return null;
		return cur + 1;
	}

	public Integer 	getExit() 	  { return exit; }
	public Integer 	getEntrance() { return entrance; }
	public List 	getRoad() 	  { return Arrays.asList(road.toArray()); }

	public boolean isWall(int pos) { return blocks[pos].wall; }
	public boolean isWall(int x, int y) { return blocks[(y * Y_LENGTH) + x].wall; }

	public void setMessage(String message){
		this.message = message;
		panel.repaint();
	}

	public void setSpot(Color color, int x, int y) throws NoSuchFieldException {
		try {
			setSpot(color, ((y * Y_LENGTH) + x));
		} catch (NoSuchFieldException e) {
			throw new NoSuchFieldException(e.getMessage() + " X:" + x + " Y:" + y);
		}
	}
	public void setSpot(Color color, int pos) throws NoSuchFieldException {
		if(blocks[pos].wall == true)
			throw new NoSuchFieldException("You are trying to move inside a wall.\nPOS:" + pos);
		blocks[pos].ball = true;
		blocks[pos].color = color;
		panel.repaint();
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		
		for (int i = 0; i < X_LENGTH; i++) {
			int count = i;
			for (int j = 0; j < Y_LENGTH; j++) {
				if (j != 0) {
					count += X_LENGTH;
				}
				

				if (blocks[count].label != null) {
					g.setFont(font);
					g.drawString(blocks[count].label, (i * (CELL_WIDTH) + (CELL_WIDTH/3) + MARGIN), (j * (CELL_WIDTH) + (CELL_WIDTH) + MARGIN));
				}

				if (blocks[count].ball == true) {
					g.setColor(blocks[count].color);
					g.fillOval(i * CELL_WIDTH + MARGIN + DOT_MARGIN, j * CELL_WIDTH
						+ MARGIN + DOT_MARGIN, DOT_SIZE, DOT_SIZE);
					g.setColor(Color.BLACK);
				}

				if (blocks[count].wall == true) {
					g.drawLine((i * CELL_WIDTH + MARGIN), (j * CELL_WIDTH + MARGIN),
						((i + 1) * CELL_WIDTH + MARGIN), (j * CELL_WIDTH + MARGIN));
					g.drawLine(i * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
						+ MARGIN, (i + 1) * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
						+ MARGIN);
					g.drawLine((i + 1) * CELL_WIDTH + MARGIN, j * CELL_WIDTH
						+ MARGIN, (i + 1) * CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH
						+ MARGIN);
					g.drawLine(i * CELL_WIDTH + MARGIN, j * CELL_WIDTH + MARGIN, i
						* CELL_WIDTH + MARGIN, (j + 1) * CELL_WIDTH + MARGIN);
				}
			}

			if (!message.equals("")) {
					g.drawString(message, (MARGIN), (Y_LENGTH * (CELL_WIDTH) + (CELL_WIDTH) + MARGIN));
			}


			for (int x = 0; x < X_LENGTH; x++) {
				g.drawLine(MARGIN, MARGIN, (X_LENGTH * CELL_WIDTH + MARGIN), (0 + MARGIN));
				g.drawLine((X_LENGTH * CELL_WIDTH + MARGIN), (Y_LENGTH * CELL_WIDTH + MARGIN), MARGIN, (Y_LENGTH * CELL_WIDTH + MARGIN));
				g.drawLine(MARGIN, MARGIN, MARGIN, (Y_LENGTH * CELL_WIDTH + MARGIN));
				g.drawLine((X_LENGTH * CELL_WIDTH + MARGIN), (Y_LENGTH * CELL_WIDTH + MARGIN), (X_LENGTH * CELL_WIDTH + MARGIN), (MARGIN));
			}
		}
	}

	public int getXLength() {
		return (((X_LENGTH+1) * CELL_WIDTH) + (MARGIN * 2));
	}

	public int getYLength() {
		return (((Y_LENGTH+3) * CELL_WIDTH) + (MARGIN * 2));
	}

	public Dimension windowSize() {
		return new Dimension(X_LENGTH * CELL_WIDTH + MARGIN * 2, Y_LENGTH * CELL_WIDTH + MARGIN * 2);
	}
}
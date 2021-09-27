/*
 * Maze.java 
 * Author: Irene Alvarado 
 * Maze object that creates a maze using a
 * disjoint set representing blocks and running a modified version of Kruskal's
 * algorithm to remove walls. In the end, the maze walls are drawn as well as a
 * unique path in red dots.
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

public class Maze {
	public static final Font font = new Font("Arial", Font.PLAIN, 20);

	public static final int CELL_WIDTH = 20; // maze square size
	public static final int DOT_MARGIN = 5; // space between wall and dot
	public static final int DOT_SIZE = 10; // size of maze solution dot
	public static final int MARGIN = 50; // buffer between window edge and maze
	
	public final int X_LENGTH; // buffer between window edge and maze
	public final int Y_LENGTH; // buffer between window edge and maze
	
	private int N;
	private Block[] blocks; // array containing all the blocks in the maze
	private boolean[] path; // array representing the unique path solution
	

	private MazePanel panel;


	final int NORTH = 0 ;
	final int SOUTH = 1 ;
	final int EAST = 2 ;
	final int WEST = 3 ;

	public class Block {
		boolean ball;
		Color color;
		boolean wall;
		int visitedBy;
		String label;
		
		public Block() {
			wall = false;
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
		File file = new File(filename);
		FileReader fileReader;
		BufferedReader reader;
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
		int mazeSize = X_LENGTH * Y_LENGTH;
		String[] matriz = new String[mazeSize];
		N = 12;

		int count = 0;
		for(String[] s : fileArray) {
			for(String c : s) {
				matriz[count] = c;
				count++;
			}
		}

		blocks = new Block[mazeSize];
		for (int i = 0; i < mazeSize; i++) {
			blocks[i] = new Block();


			if(matriz[i].equals("1")) {
				blocks[i].wall = true;
			} else {
				if(matriz[i].equals("E") || matriz[i].equals("S"))
					blocks[i].label = matriz[i];

				blocks[i].wall = false;
			}
		}
	
		if(N > 0) {	
			path = new boolean[mazeSize];
			// createPath();
		}
	}


	public boolean isWall(int pos) { return blocks[pos].wall; }
	public boolean isWall(int x, int y) { return blocks[(y * Y_LENGTH) + x].wall; }

	public void setSpot(Color color, int x, int y) throws NoSuchFieldException {
		if(blocks[(y * Y_LENGTH) + x].wall == true)
			throw new NoSuchFieldException("You are trying to move inside a wall.\nPOS:"+(y * Y_LENGTH) + x+" -> X:"+x+" Y:"+y);
		blocks[(y * Y_LENGTH) + x].ball = true;
		blocks[(y * Y_LENGTH) + x].color = color;
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
				

				if(blocks[count].label != null) {
					g.setFont(font);
					g.drawString(blocks[count].label, (i * (CELL_WIDTH) + (CELL_WIDTH/3) + MARGIN), (j * (CELL_WIDTH) + (CELL_WIDTH) + MARGIN));
					// g.drawString(blocks[count].label, (i * (CELL_WIDTH+2) + MARGIN), (j * (CELL_WIDTH+2) + MARGIN));
				}

				if(blocks[count].ball == true) {
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
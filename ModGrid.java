

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ModGrid extends JFrame {
	
	//added a Jpanel and a string in order to use the title
	private int mod;
	private int[][] grid;
	private String title;
	private JPanel gridPanel;
	
	//Constructs a new ModGrid of a specified size consisting of random numbers.
	//added a string to thge parameter
	public ModGrid(int rows, int cols, int mod, String title) {
		this.title = title;
		this.mod = mod;
		grid = new int[rows][cols];
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				grid[r][c] = (int)(Math.random() * mod);
			}
		}
		setTitle(this.title);
		CreateGrid();
	}
	
	//Constructs a new ModGrid based on an existing 2D array.
	//Later changes to either will not affect the other.
	//added a string to the parameter 
	public ModGrid(int[][] numGrid, int mod, String title) {
		this.title = title;
		this.mod = mod;
		grid = new int[numGrid.length][numGrid[0].length];
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				grid[r][c] = numGrid[r][c];
			}
		}
		setTitle(this.title);
		CreateGrid();
	}
	
	//Constructs a new ModGrid based on a String of integers.
	//The numbers in the String represent the number of rows, the number of columns, the modular number, and the numbers in the 2D array in that order.
	public ModGrid(String input) {
		String[] nums = input.split(" ");
		grid = new int[Integer.parseInt(nums[0])][Integer.parseInt(nums[1])];
		mod = Integer.parseInt(nums[2]);
		int index = 3;
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				grid[r][c] = Integer.parseInt(nums[index]);
				index++;
			}
		}
		setTitle(this.title);
		CreateGrid();
	}
	
	//Returns a ModGrid that is a copy of this ModGrid.
	public ModGrid copy() {
		setTitle(this.title);
		CreateGrid();
		//returns a new mod grid object with the new appropiate paramters
		return new ModGrid(grid, mod, "copy of " + title);
	}
	
	//If the other ModGrid has a different number of rows, columns, or modular number, returns false.
	//Otherwise, adds the numbers from the other ModGrid to the numbers in this ModGrid, modding by the modular number, and returns true.
	//the add now direclty changes the first grid and updates 
	public boolean add(ModGrid otherGrid) {
		if(mod != otherGrid.mod || grid.length != otherGrid.grid.length || grid[0].length != otherGrid.grid[0].length) {
			return false;
		}
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				grid[r][c] = (grid[r][c] + otherGrid.grid[r][c]) % mod;
			}
		}
		CreateGrid();//updates grid
		return true;
	}
	
	//Returns a String representation of the contents of the 2D array.
	public String toString() {
		String out = "";
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				out += grid[r][c] + " ";
			}
			out += "\n";
		}
		return out;
	}
	
	//Returns true if this ModGrid is equal in all respects to other, false otherwise.
	public boolean equals(Object other) {
		if(!(other instanceof ModGrid)) {
			return false;
		}
		ModGrid otherGrid = (ModGrid) other;
		if(mod != otherGrid.mod || grid.length != otherGrid.grid.length || grid[0].length != otherGrid.grid[0].length) {
			return false;
		}
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				if(grid[r][c] != otherGrid.grid[r][c]) {
					return false;
				}
			}
		}
		return true;
	}

	private void CreateGrid() {
        //crate panel with a grid layout 
		gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(grid.length, grid[0].length));

        //fill in the panel with approapirte values from random grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                JLabel label = new JLabel(String.valueOf(grid[i][j]), SwingConstants.CENTER);
                label.setOpaque(true);

                //sets color to the grid(red) collection of youtube and stack overflow examples
                Color red;
                if (grid[i][j] == 0) red = Color.WHITE; //0 is white
                else {
                	int redValue = (int) (255 * (float) grid[i][j] / mod); //suppose to darken the red but it looks backweards
                    red = new Color(redValue, 0, 0); 
                }
                label.setBackground(red);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setFont(new Font(Font.SERIF, Font.BOLD,30));
                gridPanel.add(label);
            }
        }

        this.add(gridPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }
	
}
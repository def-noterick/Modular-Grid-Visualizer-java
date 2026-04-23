

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
	
		int rows = Integer.parseInt(JOptionPane.showInputDialog("Enter number of rows:"));
		int cols = Integer.parseInt(JOptionPane.showInputDialog("Enter number of columns:"));
		int mod = Integer.parseInt(JOptionPane.showInputDialog("Enter a mod number:"));
		
		ModGrid grid1 = new ModGrid(rows, cols, mod, "Grid 1");
		ModGrid grid2 = new ModGrid(rows, cols, mod, "Grid 2");
		ModGrid originalCopy = grid1.copy();
		
		JFrame button = new JFrame("addition button");
        JButton addButton = new JButton("Add Grid 2 to Grid 1");
        button.setSize(300, 100);
        button.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.add(addButton);
        button.setVisible(true);
	
        JOptionPane.showMessageDialog(null, "this means that the grids have been created succesfully");
       //reference of prof solution 
        /*int iter = 0;
		do {
			iter++;
			grid1.add(grid2);
			System.out.println("Iteration " + iter + ":");
			System.out.println(grid1);
		}while(!(grid1.equals(grid2) || grid1.equals(originalCopy)));
		*/
        //adds action listener also used tutorials and examples from in class
        addButton.addActionListener(new ActionListener() {
            
        private int count = 0;

        //the button will call the action and check the if statemnts each time it is clicked
        //displays a message gui once the prgram has ended
        @Override
        public void actionPerformed(ActionEvent e) {
        	count++;
            grid1.add(grid2);

            if (grid1.equals(grid2)) {
                    JOptionPane.showMessageDialog(null, "Grid 1 equaled Grid 2 after " + count + " iterations.");
                    button.dispose();//deletes everything after the program has finshed
                    grid1.dispose();
                    grid2.dispose();
                    originalCopy.dispose();
            } else if (grid1.equals(originalCopy)) {
                    JOptionPane.showMessageDialog(null, "Grid 1 returned to its original state after " + count + " iterations.");
                    button.dispose();//deletes evyehting after the program has finished
                    grid1.dispose();
                    grid2.dispose();
                    originalCopy.dispose();
            }
          
       }
       });
	}
}
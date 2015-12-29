// Matthew Cucuzza
// SER210 
// Version 1.0 
// DuckLlama Panel which creates the outline and functions of the DuckLlama game 

import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;


public class DuckLlamaPanel extends javax.swing.JPanel{

	// Variables 
	private int score = 0; 
	private int counter = 0; 
	private int time = 2; 
	private final int DELAY= 1000; 
	private boolean gameOver = false; 
	private ImageIcon picures[] = new ImageIcon[10]; 
	private JButton duckButton, llamaButton, imageButton, resetButton;
	private JLabel label, timeLabel, label2; 
	final ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();

	// Constructor 
	public DuckLlamaPanel() {
		// Accesses the super class constructor 
		super(); 
		setLayout(new BorderLayout()); 
		
		// Makes a new grid layout for the labels on top 
		GridLayout labelGrid = new GridLayout(2, 1); 
		labelGrid.setVgap(10);
		
		// Makes a new LabelPanel and sets the layout in relation to the grid labelGrid
		JPanel labelPanel = new JPanel(); 
		labelPanel.setLayout(labelGrid);
		
		// Creates the score label on top 
		label = new JLabel("Score: " + score, SwingConstants.CENTER); 
		//Creates the instructors label on top 
		label2 = new JLabel("You have 2 seconds to decide if the image is a DUCK or LLAMA", SwingConstants.CENTER);
		labelPanel.add(label, BorderLayout.NORTH);
		labelPanel.add(label2, BorderLayout.NORTH);
		
		// The main panel in which the images reside 
		JPanel panel = new JPanel(); 
		
		// For loop to create the image icons, resize them, and add them to the arraylist
		for (int i = 1; i <= 5; i++) { 
			ImageIcon duck = createImageIcon("images/duck"+ i +".jpg", "duck");
			Image duckImg = duck.getImage();
			Image newDuckImg = duckImg.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
			duck = new ImageIcon (newDuckImg, "duck");
			
			ImageIcon llama = createImageIcon("images/llama"+ i + ".jpg", "llama");
			Image llamaImg = llama.getImage(); 
			Image newLlamaImg = llamaImg.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
			llama = new ImageIcon (newLlamaImg, "llama");
			
			images.add(duck);
			images.add(llama);
		}
		
		
		// Shuffles the images in the ArrayList 
		Collections.shuffle(images);
		
		// Creates the image button and displays the images 
		imageButton = new JButton(images.get(counter));
		panel.add(imageButton);
		
		// Creates a buttonPanel and adds the Duck, Llama, and Restart buttons to the panel 
		JPanel buttonPanel = new JPanel();
		llamaButton = new JButton("Llama"); 
		duckButton = new JButton("Duck");
		resetButton = new JButton("RESTART GAME");
		
		// Set the size of the buttons 
		llamaButton.setPreferredSize(new Dimension(50, 40));
		duckButton.setPreferredSize(new Dimension(50, 40));
		resetButton.setPreferredSize(new Dimension(50,40));
		
		GridLayout grid = new GridLayout(1, 2); 
		// The gap between the buttons 
		grid.setHgap(10);
		
		// Sets the layout for the button panel, and makes the reset button invisible  
		buttonPanel.setLayout(grid);
		buttonPanel.add(llamaButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(duckButton);
		resetButton.setVisible(false);
		
		// Adds the panels to the layouts 
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(labelPanel, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		
		// Creates a timer to be used for the game 
		Timer t = new Timer(DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (time > 0) { 
					time -= 1;
				} else { 
					// if the game timer runs out, set game state to be over,
					// make the reset button visible, and display game over 
					gameOver = true;
					resetButton.setVisible(true);
					label.setText("GAME OVER! " + "FINAL SCORE: " + score);
				}
			}
		}); // end timer parameter 
		
		if (time > 0) t.start(); 
		else { 
			t.stop();
			resetButton.setVisible(true);
		}
		
		// Creates an Action Listener for the reset button 
		resetButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){ 
				// if the reset button is displayed reset all the variables 
				if (resetButton.isVisible() == true) {
					time = 2; 
					score = 0; 
					counter = 0; 
					gameOver = false; 
					label.setText("Current Score: " + score);
					Collections.shuffle(images);
					resetButton.setVisible(false);
					imageButton.setIcon(images.get(counter));
				}
			}
		});
		
		// Creates an Action Listener for the llama button 
		llamaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if (gameOver == false) { 
					checkIf("llama");
				}
			}
		});
		
		// Creates an Action Listener for the duck button 
		duckButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if (gameOver == false) { 
					checkIf("duck");
				}
			}
		});
	} // end constructor 
	
	public void checkIf(String desc) { 
		if (counter == 9) {
			Collections.shuffle(images);
			counter = 0;
		}
		
		// Update the score if you choose duck image and reset the timer
		ImageIcon temp = images.get(counter);
		
		// Update the score if you choose duck image and reset the timer
		if (temp.getDescription() == desc) { 
			score++; 
			time = 2;
		} 
		// Set the game to over and display the reset button
		else {
			gameOver = true; 
			resetButton.setVisible(true);
			
		}
		
		// if the time is greater than 0 keep updating the images 
		// and set the score label to the updated score
		if (time > 0) { 
			counter++; 
			imageButton.setIcon(images.get(counter));
			label.setText("Current Score: " + score);
		}
		repaint();
	}
	
	// Helper method to create an Image Icon
	private static ImageIcon createImageIcon(String path, String desc) {
	       java.net.URL imgURL = DuckLlamaPanel.class.getResource(path);
	       if (imgURL != null) {
	           return new ImageIcon(imgURL, desc);
	       } else {
	           System.err.println("Couldn't find file: " + path);
	           return null;
	       }
	   }
}

// Matthew Cucuzza
// SER210 
// Version 1.0 
// DuckLlama Frame which displays the DuckLlama Panel 


import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class DuckLlamaFrame extends JFrame{

	public DuckLlamaFrame() {
		// Title of Window 
		super("Duck or Llama Game");
		
		DuckLlamaPanel game = new DuckLlamaPanel();
		this.setSize(500,425);
		this.add(game);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	public static void main(String[] args) {
		new DuckLlamaFrame();
	}

}

package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;

import javax.swing.UIManager.*;

public class Window extends JFrame {

	private Container cp;
	
	public Window() {
		super("EasyBooking");
		
		this.cp = this.getContentPane();
		cp.add(new SearchPage());
		//this.setLookAndFeel();
		
	    this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// I don't know why but this does not work
	private void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {}
	}	
}

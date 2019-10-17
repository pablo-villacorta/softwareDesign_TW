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
	
	public static void main(String[] args) {
		Window w = new Window();
		showOnScreen(1, w);
	}
	
	public static void showOnScreen(int screen, JFrame frame ) {
	    GraphicsEnvironment ge = GraphicsEnvironment
	        .getLocalGraphicsEnvironment();
	    GraphicsDevice[] gs = ge.getScreenDevices();
	    if( screen > -1 && screen < gs.length )
	    {
	        gs[screen].setFullScreenWindow( frame );
	    }
	    else if( gs.length > 0 )
	    {
	        gs[0].setFullScreenWindow( frame );
	    }
	    else
	    {
	        throw new RuntimeException( "No Screens Found" );
	    }
	}
	
}

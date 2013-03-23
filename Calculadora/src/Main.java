
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.*;

public class Main
{
	public static void main(String[] args)
	{
		
		calcFrame frame = new calcFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.setTitle("                                         CALCULADORA");
		frame.setSize(500,500);
	}
}

class calcFrame  extends JFrame
{
	public calcFrame()
	{
		
		calcPanel panel = new calcPanel();
		add(panel);
		pack();
	}
}

class calcPanel extends JPanel
{
	private JButton display;
	private JPanel panel;
	private double result;
	private String lastCommand;
	private boolean start;
	
	public calcPanel()
	{
		setLayout(new BorderLayout());
		
		result = 0;
		lastCommand = "=";
		start = true;
		
		//add the display
		
		display = new JButton("0");
		display.setEnabled(false);
		add(display, BorderLayout.NORTH);
		
		ActionListener insert = new InsertAction();
		ActionListener command = new CommandAction();
		
		//add the buttons in a 10 x 100 grid
		

		JPanel west = new JPanel();
		JLabel wlabel = new JLabel("calcualdora", SwingConstants.CENTER);
		//west.add(wlabel);
		//west.setBackground(Color.BLACK);
		add(west, BorderLayout.EAST);
		panel = new JPanel();
		panel.setLayout(new GridLayout(8,8));
		panel.setBackground(Color.white);
		
		addButton("7", insert);
		addButton("8", insert);
		addButton("9", insert);
		addButton("/", command);
		display.setBackground(Color.white);
		
		
		addButton("4", insert);
		addButton("5", insert);
		addButton("6", insert);
		addButton("*", command);
		
		addButton("1", insert);
		addButton("2", insert);
		addButton("3", insert);
		addButton("-", command);
		
		addButton("0", insert);
		addButton(".", insert);
		addButton("=", command);
		addButton("+", command);
		
		
		add(panel, BorderLayout.WEST);
		
	}
	
	private void addButton(String label, ActionListener listener)
	{
		JButton button = new JButton(label);
		button.addActionListener(listener);
		panel.add(button);
	}
	
	private class InsertAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String input = event.getActionCommand();
			if(start)
			{
				display.setText("");
				start = false;
			}
			display.setText(display.getText() + input);
		}
	}
	
	private class CommandAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String command = event.getActionCommand();
			
			if(start)
			{
				if(command.equals("-"))
				{
					display.setText(command);
					start = false;
				}
				else lastCommand = command;
			}
			else
			{
				calculate(Double.parseDouble(display.getText()));
				lastCommand = command;
				start = true;
			}
		}
	}

	public void calculate(double x)
	{
		if(lastCommand.equals("+")) result += x;
		else if (lastCommand.equals("-")) result -= x;
		else if (lastCommand.equals("*")) result *= x;
		else if (lastCommand.equals("/")) result /= x;
		else if (lastCommand.equals("=")) result = x;
		display.setText("" + result);
	}
}
package com.radomskikrzysztof;

import javax.swing.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.GridLayout;
import java.awt.*;

public class CalendarV1 extends JFrame {
	static int prefix = 0;
	public CalendarV1() throws FileNotFoundException {
		super("Kalendarz 2018");
		setSize(760, 520);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLookAndFeel();
		File monthsFile = new File("months.txt");
		Scanner scanner = new Scanner(monthsFile);
		String[] months = new String[12];// names of tabs
		for (int i =0; i < months.length; i++) {
			months[i] = scanner.next();
		}
		scanner.close();
		
		JLabel[] monthName = new JLabel[12];
		for (int i = 0; i < monthName.length; i++) {
			monthName[i] = new JLabel(months[i]);
		}
		JTabbedPane tabs = new JTabbedPane(); //
		for (int i = 0; i < 12; i++) {
			tabs.addTab(months[i], create(i));
		}
		add(tabs); //
		setVisible(true);
	}
	private void setLookAndFeel() {
		try {
		UIManager.setLookAndFeel(
				"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
				);
		SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
		System.err.println("Nie mogê wyœwietliæ wygl¹du " + e);
		}
	}
	
	private JPanel create(int innerCount) {
		JButton[] buttons = new JButton[31];
		for (int i =0; i < buttons.length; i++) {
			buttons[i] = new JButton(Integer.toString(i+1));
			buttons[i].setBackground(new Color(0.807F, 1F, 0F));
		}
		String[] dayArray = {"PN", "WT", "ŒR", "CZ", "PT",  "SB", "ND" };
		JLabel[] days = new JLabel[7];
		for (int i = 0; i < days.length; i++) {
			days[i] = new JLabel(dayArray[i], SwingConstants.CENTER);
		}
		JPanel pane = new JPanel();
		GridLayout grid = new GridLayout(6, 7);
		if (innerCount == 3 || innerCount == 6 || innerCount == 8
				|| innerCount == 11) {
			grid = new GridLayout(7, 7);
		}
		pane.setLayout(grid);			
		for (int j = 0; j < days.length; j++) {
			pane.add(days[j]);
			}			
		if (innerCount != 0)
			setDays(innerCount, pane);
		if(innerCount == 1) { 
			for (int q = 0; q < buttons.length - 3; q++) {
				pane.add(buttons[q]);
				} 
		} else if (innerCount == 3 || innerCount == 5 || innerCount == 8
				|| innerCount == 10) {
		for (int q = 0; q < buttons.length - 1; q++) {
			pane.add(buttons[q]);
			}
		}
		else
			for (int q = 0; q < buttons.length; q++) {
				pane.add(buttons[q]);
				}
		addition(innerCount, pane);
		
		return pane;
		
	}
	
	private void addButton(JPanel panel) {
		JButton additionalButton = new JButton(" ");
		additionalButton.setEnabled(false);
		panel.add(additionalButton);
	}
	
	private void addition(int monthCount, JPanel panel) {
		final int WEEK = 7;
		int daysNumber;
		
		if (monthCount == 1) {
			daysNumber = 28 + CalendarV1.prefix;
		} else if (monthCount == 3 || monthCount == 5 ||
				monthCount == 8 || monthCount == 10) {
			daysNumber = 30 + CalendarV1.prefix;
		} else
			daysNumber = 31 + CalendarV1.prefix;
		
		while (daysNumber >= WEEK) {
			daysNumber -= WEEK;
		}
		int rest = WEEK - daysNumber; // amount of additional buttons at the end of the month
		CalendarV1.prefix = daysNumber;
		
			for (int q = 0; q < rest; q++) {
				addButton(panel);
			} 
		 
	}
	
	private void setDays(int monthCount, JPanel panel) {
		
		final int WEEK = 7;
		int daysNumber = 0;
		if (monthCount == 1) {
			daysNumber = 28;
		} else if (monthCount == 3 || monthCount == 5 ||
				monthCount == 8 || monthCount == 10) {
			daysNumber = 30;
		} else
			daysNumber = 31;
		
		while (daysNumber >= WEEK) {
			daysNumber -= WEEK;
		}
		//int prefix = daysNumber; // quantity of additional buttons at the start of the month
		
		
			for (int q = 0; q < CalendarV1.prefix; q++) {
				 addButton(panel);
			} 
		 
	}
	
	public static void main(String[] arguments) throws FileNotFoundException {
		CalendarV1 watch = new CalendarV1();
	}
}

//	CoinAppGUI by Justin Klinger & Michael Brennan on 20121113


import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class CoinAppGUI
{
	public CoinAppGUI()
	{
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIPanel();
            }
        });
	}

	public static void main(String[] args)
	{
		new CoinAppGUI();
	}
}

class GUIPanel
{
	private JFrame 		frame;
	private JPanel 		framePanel, dataPanel, statusPanel;
	private JTextArea 	dataTA, statusTA, resultTA;
	private JMenuItem	printMI, exitMI, totalValueMI, yearValueMI, sortByQualityMI, sortByMintMI, sortByDenominationMI, sortByYearMI, aboutMI;
	private CoinArray 	coinData;

	public GUIPanel()
	{
		framePanel = new JPanel();
		dataPanel = new JPanel();
		statusPanel = new JPanel();

		dataTA = new JTextArea(10, 40);
		dataTA.setBackground(new Color(200,200,200));
		dataTA.setLineWrap(true);
		dataTA.setWrapStyleWord(true);
		dataTA.setEditable(true);
		JScrollPane dataSP = new JScrollPane(dataTA,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dataPanel.add(dataSP);

		statusTA = new JTextArea("Status Panel", 5, 40);
		statusTA.setBackground(new Color(200,200,200));
		statusTA.setLineWrap(true);
		statusTA.setWrapStyleWord(true);
		statusTA.setEditable(false);
		JScrollPane statusSP = new JScrollPane(statusTA,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		statusPanel.add(statusSP);

        framePanel.setOpaque(true);
        framePanel.setLayout(new BorderLayout());
        framePanel.add(dataPanel, BorderLayout.CENTER);
        framePanel.add(statusPanel, BorderLayout.SOUTH);

		frame = new JFrame("Coin App GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(createMenuBar());
		frame.setContentPane(framePanel);
		frame.pack();
		frame.setLocation(100, 150);
		frame.setResizable(true);
		frame.setVisible(true);

		coinData = new CoinArray();

	}

    public JMenuBar createMenuBar()
    {
		JMenuBar menuBar;
		JMenu menu;

		menuBar = new JMenuBar();

		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);

		printMI = new JMenuItem("Print...");
		printMI.addActionListener(new PrintListener());
		menu.add(printMI);

		exitMI = new JMenuItem("Exit");
		exitMI.addActionListener(new ExitListener());
		menu.add(exitMI);

		menu = new JMenu("Sorting");
		menu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(menu);

		sortByQualityMI = new JMenuItem("Quality");
		sortByQualityMI.addActionListener(new sortByQualityListener());
		menu.add(sortByQualityMI);

		sortByMintMI = new JMenuItem("Mint");
		sortByMintMI.addActionListener(new sortByMintListener());
		menu.add(sortByMintMI);

		sortByDenominationMI = new JMenuItem("Denomination");
		sortByDenominationMI.addActionListener(new sortByDenominationListener());
		menu.add(sortByDenominationMI);

		sortByYearMI = new JMenuItem("Year");
		sortByYearMI.addActionListener(new sortByYearListener());
		menu.add(sortByYearMI);

		menu = new JMenu("Calculation");
		menu.setMnemonic(KeyEvent.VK_C);
		menuBar.add(menu);

		totalValueMI = new JMenuItem("Total Value");
		totalValueMI.addActionListener (new totalValueListener());
		menu.add(totalValueMI);

		yearValueMI = new JMenuItem("Year Value");
		yearValueMI.addActionListener (new yearValueListener());
		menu.add(yearValueMI);


		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(menu);

		aboutMI = new JMenuItem("About");
		aboutMI.addActionListener(new AboutListener());
		menu.add(aboutMI);

		return menuBar;
    } // end createMenuBar

	class PrintListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				dataTA.print();
				statusTA.setText("Print dialog finished");
			} catch (Exception pe) {
				statusTA.setText("Printer error");
			}
		}
	}

	class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}


	class totalValueListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				coinData.totalValue();

				dataTA.setText(String.format("Total Value of coins on file is $ %10.2f", coinData.totalValue()));
			}
	}

	class yearValueListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					String ys = JOptionPane.showInputDialog(frame, "Enter a year");
					int year = Integer.parseInt(ys);

					dataTA.setText(String.format("Total for year: %4d is $ %10.2f",
					year, coinData.yearValue(year)));

				}
	}

	class sortByQualityListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			coinData.sortByQuality();

			dataTA.setText(coinData.toString());
		}
	}

	class sortByMintListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			coinData.sortByMint();

			dataTA.setText(coinData.toString());
		}
	}

	class sortByDenominationListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				coinData.sortByDenomination();

				dataTA.setText(coinData.toString());
			}
	}

	class sortByYearListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				coinData.sortByYear();

				dataTA.setText(coinData.toString());
			}
	}


	class AboutListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			statusTA.setText("All Systems Go");
			JOptionPane.showMessageDialog(framePanel,
				"CoinAppGUI by Justin Klinger & Michael Brennan 2012.11.08",
				"About", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
/*
 * 
 * This is the main gui for the program 
 * @author Ballyn Miller 
 * @version 0.1.0 
 */

package Gui;

//import B;
import backend.*;
import McCabe.*;
import StringParser.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JFrame tempFrame;
	// Panel Declarations
	private JPanel mainPanel;
	private JPanel mainPanelHolder;
	private JPanel metaPanel;
	private JPanel sourcePanel;
	private JPanel middlePanel;
	private JPanel mcPanel;
	private JPanel inputHolder;
	private JPanel homePanel;
	private JPanel buttonPanel;
	private JPanel tempSourceHolder;
	// Label Declarations
	private JLabel metaLabel;
	private JLabel mcLabel;
	private JLabel pathLabel;
	private JLabel inputLabel;
	private JLabel sourceLabel;
	// TextArea Declarations
	private JTextArea metaOutput;
	private JTextArea mcOutput;
	private JTextArea sourceInput;
	private JTextArea sourceOutput;
	private JTextArea tempSourceInput;
	// Buttons Declarations
	private JButton generateButton;
	private JButton prevButton;
	private JButton nextButton;
	private JButton submitButton;
	// Menu Item Declarations
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu mcCabeMenu;
	private JMenu metaMenu;
	private JMenu subCounterMenu;
	// ScrollPane Declarations
	private JScrollPane metaScroll;
	private JScrollPane mcScroll;
	private JScrollPane scInput;
	private JScrollPane scOutput;
	// Tabbed Pane Declarations Here
	private JTabbedPane mainPane;
	// FileChooser Declarations
	private JFileChooser fileChooser;
	// ArrayList
	ArrayList<String> classes;
	// Strings
	String mcOutputString = "";
	static String methodCalled;
	// Private Int
	int index = 0;

	public Gui() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setStyle();
		create();
	}

	public void create() {
		if (frame == null) {
			frame = new JFrame();
			frame.setTitle("McCabe");
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.setSize(1000, 700);
			frame.setResizable(false);
			frame.add(getTabbedPane());
			frame.setJMenuBar(obtainMenuBar());
			frame.setLocationRelativeTo(null);
			SwingUtilities.updateComponentTreeUI(frame);
			frame.setVisible(true);

		}
	}

	public JFrame getTempFrame(JTextArea component) {

		return tempFrame;
	}

	public void setStyle() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		LookAndFeelInfo[] look = UIManager.getInstalledLookAndFeels();
		for (int i = 0; i < look.length; i++) {
			if ("Nimbus".equals(look[i].getName()))
				UIManager.setLookAndFeel(look[i].getClassName());
		}

	}

	// All menu items are listed below and should be edited there;

	public JMenuBar obtainMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getFileMenu());
			menuBar.add(getMcCabeMenu());
			menuBar.add(getMetaMenu());

		}
		return menuBar;
	}

	public JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu("File");
			fileMenu.setMnemonic(KeyEvent.VK_F);
			JMenuItem importFile = new JMenuItem("Import File", KeyEvent.VK_I);
			importFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						getFileChooser();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			fileMenu.add(importFile);
			fileMenu.addSeparator();
			JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_E);
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			fileMenu.add(exit);

		}
		return fileMenu;
	}

	public JMenu getMcCabeMenu() {
		if (mcCabeMenu == null) {
			mcCabeMenu = new JMenu("McCabe Options");
			mcCabeMenu.add(getSubMenu());

		}
		return mcCabeMenu;
	}

	public JMenu getMetaMenu() {
		if (metaMenu == null) {
			metaMenu = new JMenu("Meta Options");
			JMenuItem createClass = new JMenuItem("Create MetaClass");
			JMenuItem saveClass = new JMenuItem("Save MetaClass");
			metaMenu.add(createClass);
			metaMenu.addSeparator();
			metaMenu.add(saveClass);

		}
		return metaMenu;
	}
	
	public JMenu getSubMenu() {
		if (subCounterMenu == null) {
			subCounterMenu = new JMenu("Class Metrics");
			JMenuItem generateComments = new JMenuItem("Count Comments");
			generateComments.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					StringParser.countAndRemoveComments(getSourceOutputTextArea().getText());
					Object tempObj = StringParser.getCount();
					String tempString =  "Comment Count: " + tempObj.toString() + "\n";
					getMcTextArea().setText(mcOutputString + tempString);
				}
			});
			subCounterMenu.add(generateComments);
			JMenuItem generateLines = new JMenuItem("Count Lines");
			generateLines.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int count = 0;
					Counter.countLines(getSourceOutputTextArea().getText(), 0,
							getSourceOutputTextArea().getText().lastIndexOf(
									"\n"), count);
					Object tempObj = Counter.getCount();
					mcOutputString = "Line Count: " + tempObj.toString() + "\n";
					getMcTextArea().setText(mcOutputString);

				}
			});
			subCounterMenu.addSeparator();
			subCounterMenu.add(generateLines);

		}
		return subCounterMenu;
	}

	// Add FileChooser Functions Here;

	public void getFileChooser() throws IOException {
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File temp = fileChooser.getSelectedFile();
			if (temp.isDirectory())
				pathLabel.setText("Project Path Selected is "
						+ (fileChooser.getSelectedFile().getPath()));
			else
				pathLabel.setText("File Selected is "
						+ (fileChooser.getSelectedFile().getName()));
			classes = FileParser.parse(temp.getPath());
			if (classes != null && classes.size() <= 1)
				prevButton.setEnabled(false);
			else
				prevButton.setEnabled(true);
			if (classes != null && classes.size() > 1)
				nextButton.setEnabled(true);
			else
				nextButton.setEnabled(false);
			getSourceOutputTextArea().setText(classes.get(0));
		}
	}

	// Edit TextArea's here;

	public JTextArea getMetaTextArea() {
		if (metaOutput == null) {
			metaOutput = new JTextArea();
			metaOutput.setEditable(false);
			metaOutput.setWrapStyleWord(true);
			metaOutput.setLineWrap(true);

		}
		return metaOutput;
	}

	public JTextArea getMcTextArea() {
		if (mcOutput == null) {
			mcOutput = new JTextArea();
			mcOutput.setEditable(false);
			mcOutput.setWrapStyleWord(true);
			mcOutput.setLineWrap(true);

		}
		return mcOutput;
	}
	
	public JTextArea getSourceInputTextArea() {
		if (sourceInput == null) {
			sourceInput = new JTextArea();
			sourceInput.setWrapStyleWord(true);
			sourceInput.setLineWrap(true);
			sourceInput.setToolTipText("Double Click To Expand");
			sourceInput.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						if (tempFrame == null) {
							tempFrame = new JFrame();
							tempFrame.setSize(600, 600);
							tempFrame.setTitle("Source Input");
							tempFrame.add(getTempSourceHolder());
							tempFrame.setVisible(true);
							tempFrame.setLocationRelativeTo(null);
							tempFrame
									.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						} else
							tempFrame.setVisible(true);

					}

				}

				public void mouseEntered(MouseEvent arg0) {
				}

				public void mouseExited(MouseEvent arg0) {
				}

				public void mousePressed(MouseEvent arg0) {
				}

				public void mouseReleased(MouseEvent arg0) {
				}
			});

		}
		return sourceInput;
	}

	public JTextArea getSourceOutputTextArea() {
		if (sourceOutput == null) {
			sourceOutput = new JTextArea();
			sourceOutput.setEditable(false);
			sourceOutput.setWrapStyleWord(true);
			sourceOutput.setLineWrap(true);
			sourceOutput.setToolTipText("This is your source code");

		}
		return sourceOutput;
	}

	public JTextArea getTempTextArea() {
		if (tempSourceInput == null) {
			tempSourceInput = new JTextArea();
		}
		return tempSourceInput;
	}

	// Edit the panels here;

	public JPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new JPanel();
			mainPanel.setLayout(new GridLayout(0, 3, 0, 5));
			mainPanel.setBackground(Color.GRAY);
			mainPanel.add(getMetaPanel());
			mainPanel.add(getMiddlePanel());
			mainPanel.add(getMcPanel());

		}
		return mainPanel;
	}

	public JPanel getMainPanelHolder() {
		if (mainPanelHolder == null) {
			mainPanelHolder = new JPanel();
			mainPanelHolder.setLayout(new BorderLayout());
			mainPanelHolder.add(getMainPanel(), BorderLayout.CENTER);
			mainPanelHolder.add(getPathLabel(), BorderLayout.SOUTH);

		}
		return mainPanelHolder;
	}

	public JPanel getMetaPanel() {
		if (metaPanel == null) {
			metaPanel = new JPanel();
			metaPanel.setLayout(new BorderLayout());
			metaPanel.add(getMetaScroll(), BorderLayout.CENTER);
			metaPanel.add(getMetaLabel(), BorderLayout.NORTH);
		}
		return metaPanel;
	}

	public JPanel getMcPanel() {
		if (mcPanel == null) {
			mcPanel = new JPanel();
			mcPanel.setLayout(new BorderLayout());
			mcPanel.add(getMcScroll(), BorderLayout.CENTER);
			mcPanel.add(getMcLabel(), BorderLayout.NORTH);
		}
		return mcPanel;
	}

	public JPanel getMiddlePanel() {
		if (middlePanel == null) {
			middlePanel = new JPanel();
			middlePanel.setLayout(new GridLayout(2, 0, 5, 0));
			middlePanel.setBackground(Color.lightGray);
			middlePanel.add(getInputHolderPanel());
			middlePanel.add(getOutputPanel());
		}
		return middlePanel;
	}

	public JPanel getOutputPanel() {
		if (sourcePanel == null) {
			sourcePanel = new JPanel();
			sourcePanel.setLayout(new BorderLayout());
			sourcePanel.add(getScOutputScroll(), BorderLayout.CENTER);
			sourcePanel.add(getSourceLabel(), BorderLayout.NORTH);
			sourcePanel.add(getButtonPanel(), BorderLayout.SOUTH);

		}
		return sourcePanel;
	}

	public JPanel getInputHolderPanel() {
		if (inputHolder == null) {
			inputHolder = new JPanel();
			inputHolder.setLayout(new BorderLayout());
			inputHolder.add(getScInputScroll(), BorderLayout.CENTER);
			inputHolder.add(getInputLabel(), BorderLayout.NORTH);
			inputHolder.add(getInputButton(), BorderLayout.SOUTH);
		}

		return inputHolder;
	}

	public JPanel getHomePanel() {
		if (homePanel == null) {
			homePanel = new JPanel();
		}
		return homePanel;
	}

	public JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(0, 2, 5, 5));
			buttonPanel.add(getPrevButton());
			buttonPanel.add(getNextButton());
		}
		return buttonPanel;
	}

	public JPanel getTempSourceHolder() {
		if (tempSourceHolder == null) {
			tempSourceHolder = new JPanel();
			tempSourceHolder.setLayout(new BorderLayout(5, 5));
			tempSourceHolder.add(getTempTextArea(), BorderLayout.CENTER);
			tempSourceHolder.add(getInputSubmitButton(), BorderLayout.SOUTH);
		}
		return tempSourceHolder;
	}

	// Edit Buttons here;

	public JButton getInputButton() {
		if (generateButton == null) {
			generateButton = new JButton("Generate");
			generateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int count = 0;
					Counter.countLines(getSourceOutputTextArea().getText(), 0,
							getSourceOutputTextArea().getText().lastIndexOf(
									"\n"), count);
					Object tempObj = Counter.getCount();
					mcOutputString = "Line Count: " + tempObj.toString() + "\n";
					getMcTextArea().setText(mcOutputString);
				}
			});

		}
		return generateButton;
	}

	public JButton getPrevButton() {
		if (prevButton == null) {
			prevButton = new JButton("Previous");
			prevButton.putClientProperty("JComponent.sizeVariant", "small");
			if (classes == null)
				prevButton.setEnabled(false);
			prevButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((classes.size() > 1 && classes != null) && index > 0) {
						index--;
						getSourceOutputTextArea().setText(classes.get(index));
						nextButton.setEnabled(true);
					} else
						prevButton.setEnabled(false);
				}
			});
		}
		return prevButton;
	}

	public JButton getNextButton() {
		if (nextButton == null) {
			nextButton = new JButton("Next");
			nextButton.putClientProperty("JComponent.sizeVariant", "small");
			if (classes == null)
				nextButton.setEnabled(false);
			nextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((classes != null && classes.size() > 1)
							&& index != classes.size() - 1) {
						index++;
						getSourceOutputTextArea().setText(classes.get(index));
						prevButton.setEnabled(true);
					} else
						nextButton.setEnabled(false);

				}
			});
		}
		return nextButton;
	}

	public JButton getInputSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			
				}
			});
		}

		return submitButton;
	}

	// Edit Labels beneath this point;

	public JLabel getMetaLabel() {
		if (metaLabel == null) {
			metaLabel = new JLabel("MetaClass Information:", JLabel.CENTER);

		}
		return metaLabel;
	}

	public JLabel getMcLabel() {
		if (mcLabel == null) {
			mcLabel = new JLabel("McCabe Information:", JLabel.CENTER);

		}
		return mcLabel;
	}

	public JLabel getPathLabel() {
		if (pathLabel == null) {
			pathLabel = new JLabel("No Path/File Selected Yet", JLabel.CENTER);

		}
		return pathLabel;
	}

	public JLabel getInputLabel() {
		if (inputLabel == null) {
			inputLabel = new JLabel("Enter Source Code Here:", JLabel.CENTER);

		}
		return inputLabel;
	}

	public JLabel getSourceLabel() {
		if (sourceLabel == null) {
			sourceLabel = new JLabel("Your Source Code(s):", JLabel.CENTER);

		}
		return sourceLabel;
	}

	// Edit ScrollPanes Beneath this Point;

	public JScrollPane getMetaScroll() {
		if (metaScroll == null) {
			metaScroll = new JScrollPane(getMetaTextArea());

		}
		return metaScroll;
	}

	public JScrollPane getMcScroll() {
		if (mcScroll == null) {
			mcScroll = new JScrollPane(getMcTextArea());

		}
		return mcScroll;
	}

	public JScrollPane getScInputScroll() {
		if (scInput == null) {
			scInput = new JScrollPane(getSourceInputTextArea());

		}
		return scInput;
	}

	public JScrollPane getScOutputScroll() {
		if (scOutput == null) {
			scOutput = new JScrollPane(getSourceOutputTextArea());

		}
		return scOutput;
	}

	// Edit JTabbed Panes Here

	public JTabbedPane getTabbedPane() {
		if (mainPane == null) {
			mainPane = new JTabbedPane();
			mainPane.addTab("Welcome Screen", null, getHomePanel(),
					"Welcome Screen Contains Help Information");
			mainPane.addTab("Home Screen", null, getMainPanelHolder(),
					"Home Screen Contains all of McCabe Features");
			mainPane.setMnemonicAt(0, KeyEvent.VK_W);
			mainPane.setMnemonicAt(1, KeyEvent.VK_H);

		}
		return mainPane;
	}
	
	public void getString(){
		System.out.println(getSourceInputTextArea().getText());
	}
		
	
}
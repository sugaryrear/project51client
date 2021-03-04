package com.client;

import java.awt.event.*;
import java.net.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.client.features.gameframe.ScreenMode;
import com.client.features.settings.InformationFile;
import net.miginfocom.swing.MigLayout;

public class ClientWindow extends Client implements ActionListener, WindowListener {

	private static final long serialVersionUID = -6978617783576386732L;

	private InformationFile informationFile = new InformationFile();
	
	String userNameFrameTitle;

	private void setLogo() {
		URL url2 = null;
		boolean update = true;
		try {
			url2 = new URL("https://www.dropbox.com/s/48sentezwfe7p76/output-onlinepngtools_1_-removebg-preview.png?dl=1");
		} catch (MalformedURLException ex) {
			update = false;
			ex.printStackTrace();
		}
		if (update) {
			Image bimg = Toolkit.getDefaultToolkit().getImage(url2);
			frame.setIconImage(bimg);
			frame.setBackground(Color.BLACK);
		}
	}
	public void initializeMenuBar() {

		/*
		 * Initialize our menu panel to hold our menu buttons
		 */

		menuPanel = new MotionPanel(frame);
		MigLayout layout = new MigLayout("insets 0 0 0 0, fill", "[left,:20:20][][right][left,:20:20]");
		menuPanel.setLayout(layout);
		menuPanel.setPreferredSize(new Dimension(765, 26));

		/*
		 * Set the menu panel as non focusable
		 */
		menuPanel.setFocusable(false);

		/*
		 * Disable focus traversal keys
		 */
		menuPanel.setFocusTraversalKeysEnabled(false);

		menuPanel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		menuPanel.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

		/*
		 * Create our buttons
		 */
		menuPanel.setBackground(Color.decode("#1e1e1e"));

		/*
		 * Add our buttons to the menu panel
		 */

		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(ResourceLoader.loadImage("logo.png").getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
	//	label.setIcon(new ImageIcon(new ImageIcon("/images/logo.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		menuPanel.add(label);

		text = new JLabel("bear");
		text.setFont(new Font("Tahoe Bold", Font.BOLD, 15));
		text.setForeground(Color.decode("#c6c6c6"));
		//text.setLayout(new FlowLayout(FlowLayout.LEADING));
		//text.setBorder(BorderFactory.createEmptyBorder());
		//text.setBorder(new EmptyBorder(0, 50, 0,50));
		menuPanel.add(text);

		JButton screenshot = createButton("Minimize", "minimize", "minimize");
		//screenshot.setBorder(new EmptyBorder(0, 0, 0, 0));
		//screenshot.setBorder(BorderFactory.createEmptyBorder());
		menuPanel.add(screenshot);

		JButton close = createButton("Close", "close", "close");
		//close.setBorder(BorderFactory.createEmptyBorder(0,90,00,90));
		//close.setBounds(20, 2, 20, 20);
		menuPanel.add(close);

		/*
		 * Add our menu panel to our frame
		 */

		frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
	}
	public void initUI() {
		try {
		/*	informationFile.read();
			String playerName = informationFile.getStoredUsername();
			//System.out.print("Name:" + playerName.substring(0, 1).toUpperCase() + playerName.substring(1)  + ": \n");
			if(!playerName.equalsIgnoreCase("")) {
				userNameFrameTitle = " - [" + playerName.substring(0, 1).toUpperCase() + playerName.substring(1) + "]";
			} else {
				userNameFrameTitle = "";
			}
				*/
			//icon = new ImageIcon(new URL("https://i.stack.imgur.com/KSnus.gif")).getImage();
			frame = new JFrame();
			frame.setLayout(new BorderLayout());

			setFocusTraversalKeysEnabled(false);
			frame.setResizable(false);

			frame.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
			frame.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

			Set<KeyStroke> forwardKeys = new HashSet<KeyStroke>(1);
			forwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.CTRL_MASK));
			setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);

			Set<KeyStroke> backwardKeys = new HashSet<KeyStroke>(1);
			backwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
			setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardKeys);
			frame.setUndecorated(true);
			frame.setEnabled(true);
			/*
			 * Fetch our screen size and set the location to the center of the
			 * screen
			 */
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			int x = (int) (toolkit.getScreenSize().getWidth() / 2 - (765 / 2));
			int y = (int) (toolkit.getScreenSize().getHeight() / 2 - (503 / 2));
			frame.setLocation(new Point(x, y));

			initializeMenuBar();
			JPanel gamePanel = new JPanel();


			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			gamePanel.setPreferredSize(new Dimension(765, 503));
			gamePanel.setFocusable(false);
			gamePanel.requestFocus();
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();
			insets = frame.getInsets();
			frame.setFocusable(false);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			init();
			setLogo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private int REGULAR_WIDTH = 503;
	private int REGULAR_HEIGHT = 765;

	private JButton createButton(String title, String image, String tooltip) {
		final JButton button = new JButton();
		ResourceLoader.loadImage(image+".png",button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(title) {
					case "Close":
						System.exit(0);
						break;
					case "Minimize":
						frame.setState(JFrame.ICONIFIED);
						break;
				}
				setBackground(UIManager.getColor("control"));

				button.setContentAreaFilled(true);
				//System.out.println("button was clicked!");
			}
		});
		if (tooltip != null)
			button.setToolTipText(tooltip);
		//button.setLayout(null);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setFont(new Font("Dialog", Font.PLAIN, 11));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setForeground(Color.BLACK);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				//button.setContentAreaFilled(true); //uncomment this so you dont have that default blueish rectangle fill shit
				button.setBackground(Color.decode("#1e1e1e"));
				ResourceLoader.loadImage(image+"_h.png",button);
			}


			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(Color.decode("#1e1e1e"));
				button.setContentAreaFilled(false);
				ResourceLoader.loadImage(image+".png",button);
			}
		});
		return button;
	}
	public void resize(ScreenMode screenMode) {
		try {
			int width =  REGULAR_WIDTH;
			int height =  REGULAR_HEIGHT;

			Dimension dimension = new Dimension(width, height);

			gamePanel.setPreferredSize(dimension);
			menuPanel.setPreferredSize(dimension);
			frame.setMinimumSize(dimension);

			Insets insets = frame.getInsets();
			int widthModifier = 0 + insets.left;//6
			int heightModifier = 0 + insets.top; // 60
			frame.setBounds(0, 0, width + widthModifier, height + heightModifier);

			super.myWidth = width;
			super.myHeight = height;

			frame.setResizable(screenMode != Client.currentScreenMode.FIXED);
			frame.setLocationRelativeTo(null);
			frame.pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getClientUserName() {
		return Client.myPlayer.name;
	}

	public ClientWindow(String args[]) {
		super();
		try {
			com.client.sign.Signlink.startpriv(InetAddress.getByName(server));
			initUI();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public URL getCodeBase() {
		try {
			return new URL("http://" + server + "/overlays");
		} catch (Exception e) {
			return super.getCodeBase();
		}
	}

	@Override
	public URL getDocumentBase() {
		return getCodeBase();
	}

	public void loadError(String s) {
		System.out.println("loadError: " + s);
	}

	@Override
	public String getParameter(String key) {
		return "";
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		
	}
	
	private static JFrame frame;
	
	public static JFrame getFrame() {
		return frame;
	}
	
	private static Insets insets;
	
	public static Insets getInset() {
		return insets;
	}
	private JPanel gamePanel;
	public JPanel getGamePanel() {
		return gamePanel;
	}
	public static JLabel text;

	public static JLabel getTextLabel() {
		return text;
	}
	public static JPanel menuPanel;

	public static JPanel getMenuPanel() {
		return menuPanel;
	}
}
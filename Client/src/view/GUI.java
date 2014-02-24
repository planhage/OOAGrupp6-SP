/**
 * All unique swing components for the GUI.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-13
 */

package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ActionHandler;

public abstract class GUI extends JPanel {
	private static final long serialVersionUID = -6618159364253053973L;
	protected HashMap<String, Container> components;

	public GUI() {
		setLayout(new BorderLayout());
		components = new HashMap<String, Container>();
		
		initLabels();
		initPanels();
		initTextFields();
		initButtons();
		
		buildGUI();
	}

	/**
	 * @return canvas Contains all objects to be displayed in a single panel.
	 */
	public JPanel getCanvas() {
		return this;
	}

	/**
	 * Create all labels
	 */
	protected void initLabels() {}

	/**
	 * Create all panels
	 */
	protected void initPanels() {
		setLayout(new BorderLayout());
		
		JPanel tempPanel = new JPanel();
		components.put("southPanel", tempPanel);
		tempPanel.setLayout(new GridBagLayout());
	}

	/**
	 * Create all text fields.
	 */
	protected void initTextFields() {}
	
	/**
	 * Create all buttons
	 */
	protected void initButtons() {
		JButton tempButton = new JButton("Network Config");
		tempButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ActionHandler.getInstance().networkConfig();
			}
		});
		components.put("networkButton", tempButton);
	}

	/**
	 * Place all components
	 */
	protected void buildGUI() {
		GridBagConstraints c;
		JPanel panel = (JPanel) components.get("southPanel");
		
		// networkButton
		c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.anchor = GridBagConstraints.SOUTHEAST;
		c.insets = new Insets(0, 0, 25, 25);
		panel.add(components.get("networkButton"), c);
		
		getCanvas().add(panel, BorderLayout.SOUTH);
	}
}
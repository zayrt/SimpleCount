package simpleCount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Calculator extends JFrame  {
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private String tab[] = {"x²", "√", "%", "log", "AC", "7", "8", "9", "exp", "/", "4", "5", "6", "cos", "*", "1", "2", "3", "tan", "-", "0", ".", "=", "sin", "+"};
	private JButton[] tab_button = new JButton[tab.length];
	private JPanel pan = new JPanel();
	private JPanel pan2 = new JPanel();
	private JLabel lab = new JLabel("0");
	private boolean clicOp = false, update = false, same = true;
	private double nb1;
	private String op = "";	
	
	public Calculator() {
		this.setTitle("My calculator");
		this.setSize(350, 350);
	    
	    this.setLocationRelativeTo(null);
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    this.setLocation((int) this.screenSize.getWidth() / 3,(int) this.screenSize.getHeight() / 3);
	    
	    createPanel();
	    this.setVisible(true);
	}
	
	public boolean isSame() {
		return same;
	}

	public void setSame(boolean same) {
		this.same = same;
	}

	public void createPanel() {
		lab.setFont(new Font("Serif", Font.PLAIN, 30));
	    lab.setHorizontalAlignment(SwingConstants.RIGHT);
	    pan.setLayout(new BorderLayout());
	    pan.add(lab, BorderLayout.NORTH);
	    
	    pan2.setLayout(new GridLayout(5, 5, 3, 3));
	    for (int i = 0; i < this.tab.length; i++) {
	    	tab_button[i] = new JButton(tab[i]);
	    	if ((i >= 0 && i <= 4) || i == 8 || i == 9 || i == 13 || i == 14
	    			|| i == 18 || i == 19 || i == 23 || i == 24)
                tab_button[i].setBackground(new Color(215, 216, 216));
	    	if (tab[i].equals("AC") || tab[i].equals("=") || tab[i].equals("tan") || tab[i].equals("sin") || tab[i].equals("cos") || tab[i].equals("exp") || tab[i].equals("log") || 
	    			tab[i].equals("x²") || tab[i].equals("√") || tab[i].equals("+") || tab[i].equals("-") || tab[i].equals("*") || tab[i].equals("/") || tab[i].equals("%"))
	    		tab_button[i].addActionListener(new OperatorListener(this));
	    	else
	    		tab_button[i].addActionListener(new NumListener(this));
	    	pan2.add(tab_button[i]);
	    }	
	    pan.add(pan2);
	    this.setContentPane(pan);
	}
	
	public int do_precision(String str) {
		int i2 = -1;
		boolean after = false;
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '.')
				after = true;
			if (after)
				i2++;
		}
		if (i2 > 8)
			i2 = 8;
		else if (i2 < 2)
			i2 = 2;
		return (i2);
	}
	
	public void calcul() {
		if (op.equals("+")) {
			nb1 = nb1 + Double.valueOf(lab.getText()).doubleValue();
		}
		if (op.equals("-")) {
			nb1 = nb1 - Double.valueOf(lab.getText()).doubleValue();
		}
		if (op.equals("*")) {
			nb1 = nb1 * Double.valueOf(lab.getText()).doubleValue();
		}
		if (op.equals("%")) {
			nb1 = nb1 % Double.valueOf(lab.getText()).doubleValue();
		}
		if(op.equals("/")){
			if (Double.valueOf(lab.getText()).doubleValue() == 0) {
				lab.setText("Error: divide by 0");
				return ;
			}
			nb1 = nb1 / Double.valueOf(lab.getText()).doubleValue();
		}
		if (op.equals("x²")) 
			nb1 = Math.pow(Double.valueOf(lab.getText()).doubleValue(), 2.0);
		if (op.equals("√"))
			nb1 = Math.sqrt(Double.valueOf(lab.getText()).doubleValue());
		if (op.equals("log"))
			nb1 = Math.log(Double.valueOf(lab.getText()).doubleValue());
		if (op.equals("exp"))
			nb1 = Math.exp(Double.valueOf(lab.getText()).doubleValue());
		if (op.equals("cos"))
			nb1 = Math.cos(Double.valueOf(lab.getText()).doubleValue());
		if (op.equals("tan"))
			nb1 = Math.tan(Double.valueOf(lab.getText()).doubleValue());
		if (op.equals("sin"))
			nb1 = Math.sin(Double.valueOf(lab.getText()).doubleValue());
		int n = do_precision(String.valueOf(nb1));
		lab.setText(String.format("%." + String.valueOf(n) + "f", nb1));
	}
	
	public Dimension getScreenSize() {
		return screenSize;
	}

	public String[] getTab() {
		return tab;
	}

	public JButton[] getTab_button() {
		return tab_button;
	}

	public JPanel getPan() {
		return pan;
	}

	public JPanel getPan2() {
		return pan2;
	}

	public JLabel getLab() {
		return lab;
	}

	public boolean isClicOp() {
		return clicOp;
	}

	public boolean isUpdate() {
		return update;
	}

	public double getNb1() {
		return nb1;
	}

	public String getOp() {
		return op;
	}

	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
	}

	public void setTab(String[] tab) {
		this.tab = tab;
	}

	public void setTab_button(JButton[] tab_button) {
		this.tab_button = tab_button;
	}

	public void setPan(JPanel pan) {
		this.pan = pan;
	}

	public void setPan2(JPanel pan2) {
		this.pan2 = pan2;
	}

	public void setLab(JLabel lab) {
		this.lab = lab;
	}

	public void setClicOp(boolean clicOp) {
		this.clicOp = clicOp;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public void setNb1(double nb1) {
		this.nb1 = nb1;
	}

	public void setOp(String op) {
		this.op = op;
	}
}


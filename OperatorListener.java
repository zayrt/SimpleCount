package calculette;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OperatorListener implements ActionListener {
	Calculator calc;
	
	public OperatorListener(Calculator c) {
		calc = c;
	}
	
	public boolean checkDote(String lab) {
		int c = 0;
		
		for (int i = 0; i < lab.length(); i++) {
			if (lab.charAt(i) == '.')
				c++;
		}
		if (c <= 1)
			return (true);
		return (false);
	}
	
	public void reset(String str) {
		calc.getLab().setText(str);
		calc.setUpdate(true);
		calc.setClicOp(false);
		calc.setNb1(0);
		calc.setOp("");
		calc.setSame(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String but = ((JButton)e.getSource()).getText();
		
		if (but.equals("AC")) {
			reset("0");
		}
		else if (but.equals("=")){
			if (!checkDote(calc.getLab().getText())) {
				reset("Error too many '.'");
				return ;
			}
			if (!calc.isSame()) {
				calc.calcul();
				calc.setUpdate(true);
				calc.setClicOp(false);
				calc.setSame(true);
			}
		}
		else {
			if (!checkDote(calc.getLab().getText())) {
				reset("Error too many '.'");
				return ;
			}
			if(calc.isClicOp() && !calc.isSame()) {
				calc.calcul();
				calc.getLab().setText(String.valueOf(calc.getNb1()));
				calc.setSame(true);
			}
			else {
				
				calc.setNb1(Double.valueOf(calc.getLab().getText()).doubleValue());
				calc.setClicOp(true);
			}
			calc.setOp(but);
			calc.setUpdate(true);
		}
	}
}
package simpleCount;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NumListener implements ActionListener {
	Calculator calc;
	
	public NumListener(Calculator c) {
		calc = c;
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = ((JButton)e.getSource()).getText();
		
		if (calc.isUpdate()) {
			calc.setUpdate(false);
		}
		else {
			if(!calc.getLab().getText().equals("0"))
				str = calc.getLab().getText() + str;
		}
		calc.setSame(false);
		calc.getLab().setText(str);
	}
}


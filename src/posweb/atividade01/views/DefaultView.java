package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.interfaces.View;

public class DefaultView implements View{
	@Override
	public void generate() {
		JOptionPane.showMessageDialog(null, "Código de operação inválido!");	
	}
}

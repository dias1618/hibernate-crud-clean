package posweb.atividade01.views;

import javax.swing.JOptionPane;

import posweb.atividade01.interfaces.View;
import posweb.atividade01.services.AlunoServices;

public class ListarAlunosView implements View{
	
	AlunoServices alunoServices = new AlunoServices();
	
	@Override
	public void generate() {
		String listaAlunosToString = alunoServices.listar();
		JOptionPane.showMessageDialog(null, listaAlunosToString);
	}
}

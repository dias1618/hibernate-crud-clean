package posweb.atividade01;

import posweb.atividade01.interfaces.View;
import posweb.atividade01.views.MainView;

public class Teste {
	
	public static void main(String[] args) {
		View view = new MainView();
		view.generate();
	}

}

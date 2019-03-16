package br.ucsal.lojinha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import br.ucsal.lojinha.gui.ProdutoView;
import br.ucsal.lojinha.gui.VendaView;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProdutoView produtoView = new ProdutoView();
	private VendaView vendaView = new VendaView();
	
	public Principal() {
		super("Loja");
		// centralizado
		setLocationRelativeTo(null);
		menu();
		// setando a ação padrão de fechamento do Formulário,
		// neste caso irá fechar o programa
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 320);
		



	}
	
	
	private void menu() {
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
		JMenu cadastroMenu = new JMenu("Cadastro");
		menubar.add(cadastroMenu);
		
		JMenuItem produtoMenuItem = new JMenuItem("Produto");
		cadastroMenu.add(produtoMenuItem);
		
		JMenuItem vendaMenuItem = new JMenuItem("Venda");
		cadastroMenu.add(vendaMenuItem);
		
		vendaMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog frame = new JDialog(Principal.this, "Venda", true);
				frame.getContentPane().add(vendaView);
				frame.pack();
				frame.setVisible(true);				
			}
		});
		
		produtoMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JDialog frame = new JDialog(Principal.this, "Produto", true);
				frame.getContentPane().add(produtoView);
				frame.pack();
				frame.setVisible(true);
			}
		});
		
	}

	public static void main(String[] args) {
		new Principal().setVisible(true);
	}
}

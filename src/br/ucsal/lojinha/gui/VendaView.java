package br.ucsal.lojinha.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.ucsal.lojinha.model.ItemVenda;
import br.ucsal.lojinha.model.Produto;

public class VendaView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7522762364011486592L;
		
    private JButton btAdicionar = new JButton("Adicionar");
    private JLabel lbNome = new JLabel("Nome ");
    private JLabel lbQuantidade = new JLabel("Quantidade");
    private JLabel lbPreco = new JLabel("Preço");
    private JTextField txQuantidade = new JTextField(10);;
    private JTextField txPreco = new JTextField(10);;
    private JComboBox<Produto> cbProduto;
	
    private JPanel painelFormulario = new JPanel();

    private JTable tabela;
    private JScrollPane barraRolagem;
    
    private JPanel painelBotoes;
    private JButton btExcluir = new JButton("Excluir");
    
    private ItemVendaTableModel modelo;
    private int linha = -1;
    
    List<ItemVenda> lista = new ArrayList<>();
    
    List<Produto> produtos = new ArrayList<>();
    
	public VendaView() {
		Produto p = new Produto("prod","desc",BigDecimal.TEN);
		produtos.add(p);
		cbProduto = new JComboBox<Produto>(new ProdutoComboBoxModel(produtos));
		cbProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Produto produto = (Produto) cbProduto.getSelectedItem();
				txPreco.setText(produto.getPreco().toPlainString());
				txQuantidade.setText(1+"");
			}
		});
		this.setLayout(new BorderLayout());
		criaTabela();
		criaFormulario();
		criaBotoes();
//		criaJanela();
	}
	
    private void criaTabela() {
    	modelo = new ItemVendaTableModel(lista);
		// instanciando a JTable
        tabela = new JTable(modelo);
    	tabela.setFillsViewportHeight(true);
        barraRolagem = new JScrollPane(tabela);
        add(BorderLayout.CENTER, barraRolagem);
    }
    
    public void criaFormulario() {
    	painelFormulario.setLayout(new GridLayout(4, 2, 2, 4));
    	painelFormulario.add(lbNome);
    	painelFormulario.add(cbProduto);
    	painelFormulario.add(lbQuantidade);
    	painelFormulario.add(txQuantidade);
    	painelFormulario.add(lbPreco);
    	painelFormulario.add(txPreco);
    	painelFormulario.add(new JPanel());
    	painelFormulario.add(btAdicionar);
        add(BorderLayout.NORTH, painelFormulario);
        
    	
    	btAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					ItemVenda novo = new ItemVenda();
					novo.setProduto((Produto)cbProduto.getSelectedItem());
					novo.setQuntidade(Integer.parseInt(txQuantidade.getText()));
					novo.setPreco(new BigDecimal(txPreco.getText()));
					modelo.addItemVenda(novo);
			}
		});
    	

    }
    

    
    
  
    
    public void criaBotoes() {
    	
    	
    	
    	btExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	            linha = -1;
	            linha = tabela.getSelectedRow();
	            if (linha >= 0) {
	                modelo.removeItemVenda(linha);
	                linha = -1;
	            } else {
	                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
	            }

			}
		});
    	
        painelBotoes = new JPanel();
        painelBotoes.add(btExcluir);
        add(BorderLayout.SOUTH, painelBotoes);
    }



}

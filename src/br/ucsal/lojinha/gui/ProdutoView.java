package br.ucsal.lojinha.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.ucsal.lojinha.model.Produto;

public class ProdutoView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7522762364011486592L;
	public static final int DISABLE =0 ;
	public static final int NOVO = 1;
	public static final int EDITAR = 2;
	
	private int acao = DISABLE;
	private Produto selecionado;
	
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JLabel lbNome = new JLabel("Nome ");
    private JLabel lbDescricao = new JLabel("Descrição");
    private JLabel lbPreco = new JLabel("Preço");
    private JTextField txNome = new JTextField(10);;
    private JTextField txDescricao = new JTextField(10);;
    private JTextField txPreco = new JTextField(10);;

	
    private JPanel painelFormulario = new JPanel();

    private JTable tabela;
    private JScrollPane barraRolagem;
    
    private JPanel painelBotoes;
    private JButton btNovo = new JButton("Novo");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btEditar = new JButton("Editar");
    
    private ProdutoTableModel modelo;
    private int linha = -1;
    
    List<Produto> lista = new ArrayList<>();
    
	public ProdutoView() {
		this.setLayout(new BorderLayout());
		criaTabela();
		criaFormulario();
		criaBotoes();
//		criaJanela();
	}
	
    private void criaTabela() {
    	modelo = new ProdutoTableModel(lista);
		// instanciando a JTable
        tabela = new JTable(modelo);
    	tabela.setFillsViewportHeight(true);
        barraRolagem = new JScrollPane(tabela);
        add(BorderLayout.CENTER, barraRolagem);
    }
    
    public void criaFormulario() {
    	painelFormulario.setLayout(new GridLayout(4, 2, 2, 4));
    	painelFormulario.add(lbNome);
    	painelFormulario.add(txNome);
    	painelFormulario.add(lbDescricao);
    	painelFormulario.add(txDescricao);
    	painelFormulario.add(lbPreco);
    	painelFormulario.add(txPreco);
    	painelFormulario.add(btCancelar);
    	painelFormulario.add(btSalvar);
    	
    	enable(false);
        add(BorderLayout.NORTH, painelFormulario);
        
    	btCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acao = DISABLE;
				clear();
				enable(false);
			}
		});
    	
    	btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (acao) {
				case ProdutoView.NOVO:
					selecionado = new Produto();
					selecionado.setNome(txNome.getText());
					selecionado.setDescricao(txDescricao.getText());
					selecionado.setPreco(new BigDecimal(txPreco.getText()));
					modelo.addProduto(selecionado);
					break;
				case ProdutoView.EDITAR:
					selecionado.setNome(txNome.getText());
					selecionado.setDescricao(txDescricao.getText());
					selecionado.setPreco(new BigDecimal(txPreco.getText()));
					modelo.updateProduto(linha, selecionado);
					break;
				default:
					break;
				}
				acao = DISABLE;
				enable(false);
		    	clear();
			}
		});
    	

    }
    

    
    
    public void enable(boolean bool) {
    	txNome.setEnabled(bool);
    	txDescricao.setEditable(bool);
    	txPreco.setEnabled(bool);
    }
    
    public void setFormulario(Produto produto) {
    	txNome.setText(produto.getNome());
    	txDescricao.setText(produto.getDescricao());
    	txPreco.setText(produto.getPreco().toPlainString());
    }
    
    
    public void clear() {
    	txNome.setText("");
    	txDescricao.setText("");
    	txPreco.setText("");
    }
    
    public void criaBotoes() {
    	
    	
    	btNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				enable(true);
				acao = NOVO;
			}
		});
    	
    	btEditar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
	            linha = -1;
	            linha = tabela.getSelectedRow();
	            if (linha >= 0) {
	                Produto p = modelo.getProduto(linha);
					enable(true);
	                setFormulario(p);
	                acao = EDITAR;
	            } else {
	                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
	            }

		
				
			}
		});
    	
    	
    	btExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	            linha = -1;
	            linha = tabela.getSelectedRow();
	            if (linha >= 0) {
	                modelo.removeProduto(linha);
	                linha = -1;
	                acao = DISABLE;
	            } else {
	                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
	            }

			}
		});
    	
        painelBotoes = new JPanel();
        painelBotoes.add(btNovo);
        painelBotoes.add(btEditar);
        painelBotoes.add(btExcluir);
        add(BorderLayout.SOUTH, painelBotoes);
    }



}

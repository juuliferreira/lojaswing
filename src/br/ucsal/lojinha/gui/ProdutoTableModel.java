package br.ucsal.lojinha.gui;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import br.ucsal.lojinha.model.Produto;


public class ProdutoTableModel extends AbstractTableModel {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_NOME = 0;
    private static final int COL_DESCRICAO = 1;
    private static final int COL_PRECO = 2;
    List<Produto> linhas;
    private String[] colunas = new String[]{"Nome", "Descição", "Preço"};

    public ProdutoTableModel(List<Produto> produtos) {
        this.linhas = produtos;
    }
    
    public ProdutoTableModel() {
        this.linhas = new ArrayList<>();
    }

    public int getRowCount() {
        return linhas.size();
    }

    public int getColumnCount() {
        return colunas.length;
    }

    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COL_PRECO) {
            return BigDecimal.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

        Produto m = linhas.get(row);

        if (column == COL_NOME) {
            return m.getNome();
        } else if (column == COL_DESCRICAO) {
            return m.getDescricao();
        } else if (column == COL_PRECO) {
            return m.getPreco();
        }
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
    	
    	Produto u = linhas.get(row);
        if (column == COL_NOME) {
            u.setNome(aValue.toString());
        } else if (column == COL_DESCRICAO) {
            u.setDescricao(aValue.toString());
        } else if (column == COL_PRECO) {
            u.setPreco(new BigDecimal(aValue.toString()));
        }
    }

    public Produto getProduto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addProduto(Produto produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateProduto(int indiceLinha, Produto produto) {
        linhas.set(indiceLinha, produto);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    }

    public void removeProduto(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
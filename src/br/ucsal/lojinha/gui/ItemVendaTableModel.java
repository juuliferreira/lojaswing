package br.ucsal.lojinha.gui;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import br.ucsal.lojinha.model.ItemVenda;
import br.ucsal.lojinha.model.Produto;


public class ItemVendaTableModel extends AbstractTableModel {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int COL_PRODUTO = 0;
    private static final int COL_QUANTIDADE = 1;
    private static final int COL_PRECO = 2;
    List<ItemVenda> linhas;
    private String[] colunas = new String[]{"Produto", "Quantidade", "Preço"};

    public ItemVendaTableModel(List<ItemVenda> itemVendas) {
        this.linhas = itemVendas;
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
        }else if (columnIndex == COL_QUANTIDADE){
        	return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

        ItemVenda m = linhas.get(row);

        if (column == COL_PRODUTO) {
            return m.getProduto().getNome();
        } else if (column == COL_QUANTIDADE) {
            return m.getQuntidade();
        } else if (column == COL_PRECO) {
            return m.getPreco();
        }
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
    	ItemVenda u = linhas.get(row);
        if (column == COL_PRODUTO) {
        	
            u.getProduto().setNome(aValue.toString());
        } else if (column == COL_QUANTIDADE) {
            u.setQuntidade(Integer.parseInt( aValue.toString()));
        } else if (column == COL_PRECO) {
            u.setPreco(new BigDecimal(aValue.toString()));
        }
    }

    public ItemVenda getItemVenda(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addItemVenda(ItemVenda itemVenda) {
        linhas.add(itemVenda);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }


    public void removeItemVenda(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
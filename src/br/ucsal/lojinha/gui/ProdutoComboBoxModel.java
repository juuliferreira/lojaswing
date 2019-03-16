package br.ucsal.lojinha.gui;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.MutableComboBoxModel;

import br.ucsal.lojinha.model.Produto;

public class ProdutoComboBoxModel extends AbstractListModel<Produto> implements MutableComboBoxModel<Produto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Produto> produtos;

	private Produto selected = null;

	public ProdutoComboBoxModel(List<Produto> lista) {
		produtos = lista;
	}

	public ProdutoComboBoxModel() {
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public int getSize() {
		return produtos.size();
	}

	@Override
	public Produto getElementAt(int index) {
		return produtos.get(index);
	}


	@Override
	public void setSelectedItem(Object anItem) {
		if ((selected != null && !selected.equals(anItem)) || selected == null && anItem != null) {
			selected = (Produto) anItem;
			fireContentsChanged(this, -1, -1);
		}
	}

	@Override
	public Produto getSelectedItem() {
		return selected;
	}

	@Override
	public void addElement(Produto item) {
		produtos.add(item);
	}

	@Override
	public void removeElement(Object anObject) {
        int index = produtos.indexOf(anObject);
        if ( index != -1 ) {
            removeElementAt(index);
        }
	}

	@Override
	public void insertElementAt(Produto item, int index) {
        produtos.add(index,item);
        fireIntervalAdded(this, index, index);		
	}

	@Override
	public void removeElementAt(int index) {
        if ( getElementAt( index ) == selected ) {
            if ( index == 0 ) {
                setSelectedItem( getSize() == 1 ? null : getElementAt( index + 1 ) );
            }
            else {
                setSelectedItem( getElementAt( index - 1 ) );
            }
        }
        produtos.remove(index);
        fireIntervalRemoved(this, index, index);
		
	}

}

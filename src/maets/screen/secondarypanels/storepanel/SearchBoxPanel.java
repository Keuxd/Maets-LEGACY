package maets.screen.secondarypanels.storepanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import maets.core.Cache;
import maets.core.Cache.CacheType;
import maets.games.GamesTable;

public class SearchBoxPanel extends JPanel {

	private static final long serialVersionUID = -3500076235155238365L;

	protected JFrame parentFrame;
	private JTextField textField;
	
	public SearchBoxPanel(JFrame parentFrame) {
		setBounds(0, 110, 410, 970);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Games");
		lblNewLabel.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 35));
		lblNewLabel.setBounds(10, 11, 207, 49);
		add(lblNewLabel);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setMaximum(11);
		scrollBar.setBounds(368, 127, 32, 445);
		scrollBar.addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				System.out.println(e.getValue());
			}
			
		});
		add(scrollBar);
		
		GamesTable gt = (GamesTable) Cache.get(CacheType.GAMES_TABLE);
		JList<String> list = new JList(gt.getNames());
		list.setBounds(10, 128, 348, 445);
		list.setFont(new Font("Nirmala UI", Font.PLAIN, 30));
		list.setForeground(Color.WHITE);
		list.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = -3339892752454882521L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		    	JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		        Icon icon = getIcon(list, value, index, isSelected, cellHasFocus);
		        label.setIcon(icon);
		        return label;
		    }
			
		    protected Icon getIcon(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		    	return gt.getIcon(index);
		    }
		});
		add(list);
		
		textField = new JTextField();
		textField.setBounds(10, 71, 390, 46);
		textField.setFont(new Font("Nirmala UI", Font.PLAIN, 25));
		textField.setForeground(Color.WHITE);
		textField.setFocusable(false);
		textField.setEnabled(false);
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				list.clearSelection();
				textField.setEnabled(true);
				textField.setFocusable(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				textField.setEnabled(false);
				textField.setFocusable(false);
			}
		});
		add(textField);
	}
}
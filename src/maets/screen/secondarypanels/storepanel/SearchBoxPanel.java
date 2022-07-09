package maets.screen.secondarypanels.storepanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import maets.core.Cache;
import maets.core.Cache.CacheType;
import maets.games.GamesTable;

public class SearchBoxPanel extends JPanel {

	private static final long serialVersionUID = -3500076235155238365L;

	protected JFrame parentFrame;
	private JTextField textField;
	
	public SearchBoxPanel(JFrame parentFrame, GamePanel gamePanel) {
		setBounds(0, 110, 490, 970);
		setLayout(null);
		
		JLabel title = new JLabel("Games");
		title.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 35));
		title.setBounds(10, 10, 210, 50);
		add(title);
		
		GamesTable gt = (GamesTable) Cache.get(CacheType.GAMES_TABLE);
		JList<String> list = new JList<>();
		updateGamesList("", list, gt.getNames());
		list.setEnabled(false);
		list.setFocusable(false);
		list.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = -3339892752454882521L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		    	JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, false, cellHasFocus);
		    	label.setText(null);

		    	Icon icon = gt.getIcon((String) value);
		        label.setIcon(icon);
		        
		        label.setHorizontalAlignment(SwingConstants.CENTER);
		        
		        Border border = label.getBorder();
		        Border margin = new EmptyBorder(2, -6, 2, -6);
		        label.setBorder(new CompoundBorder(border, margin));
		        
		        return label;
		    }
		});
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String selectedGame = list.getSelectedValue();
				if(e.getValueIsAdjusting() || selectedGame == null || gamePanel.getGameName().equals(selectedGame)) return;
				
				gamePanel.repopulatePanel(selectedGame);
			}
		});
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				list.setEnabled(true);
				list.setFocusable(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				list.setEnabled(false);
				list.setFocusable(false);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 480, 830);
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		add(scrollPane);

		textField = new JTextField();
		textField.setBounds(10, 70, 480, 50);
		textField.setFont(new Font("Nirmala UI", Font.PLAIN, 25));
		textField.setForeground(Color.WHITE);
		textField.setFocusable(false);
		textField.setEnabled(false);
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				textField.setEnabled(true);
				textField.setFocusable(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				textField.setEnabled(false);
				textField.setFocusable(false);
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateGamesList(textField.getText(), list, gt.getNames());
			}
		});
		add(textField);
	}
	
	private void updateGamesList(String searchString, JList<String> list, Set<String> gamesList) {
		DefaultListModel<String> model = new DefaultListModel<>();

		for(String game : gamesList) {
			if(game.toLowerCase().matches(".*" + searchString.toLowerCase().trim() + ".*")) {
				model.addElement(game);
			}
		}
		
		list.setModel(model);
	}
}
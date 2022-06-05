package maets.screen.mainpanel.midpanel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import maets.core.Main;
import maets.screen.mainpanel.ButtonsResponsivityMouseAdapter;
import maets.screen.mainpanel.upperpanel.UserPanel;

public class MidPanel extends JPanel {

	private static final long serialVersionUID = 3896718565332803227L;

	public MidPanel() {
		setBounds(0, 201, 1920, 390);
		setLayout(null);
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setIcon(Main.getImageIconInResources("warning.png", 175, Image.SCALE_SMOOTH));
		btnNewButton_1.setBounds(280, 93, 433, 203);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.addMouseListener(new ButtonsResponsivityMouseAdapter(btnNewButton_1) {
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				System.out.println("Warning 1");
			}
			
//			private Dimension originalSize;
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				super.mouseEntered(e);
//				
//				originalSize = btnNewButton_1.getSize();
//				
//				int h = originalSize.height + (originalSize.height * 5/100);
//				int w = originalSize.width + (originalSize.width * 5/100);
//				
//				btnNewButton_1.setSize(w, h);
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				super.mouseExited(e);
//				
//				btnNewButton_1.setSize(originalSize);
//			}
		});
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setIcon(Main.getImageIconInResources("warning.png", 175, Image.SCALE_DEFAULT));
		btnNewButton_2.setBounds(743, 93, 433, 203);
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.addMouseListener(new ButtonsResponsivityMouseAdapter(btnNewButton_2) {
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				System.out.println("Warning 2");
			}
		});
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton();
		btnNewButton_3.setIcon(Main.getImageIconInResources("warning.png", 175, Image.SCALE_SMOOTH));
		btnNewButton_3.setBounds(1206, 93, 433, 203);
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.addMouseListener(new ButtonsResponsivityMouseAdapter(btnNewButton_3) {
			@Override
			public void fixedMouseClicked(MouseEvent e) {
				System.out.println("Warning 3");
			}
		});
		add(btnNewButton_3);
	}
}
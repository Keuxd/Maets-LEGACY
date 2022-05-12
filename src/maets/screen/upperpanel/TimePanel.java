package maets.screen.upperpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimePanel extends JPanel {

	private static final long serialVersionUID = 6045759015224652453L;

	private Timer timer;
	
	public TimePanel() {
		setLayout(null);
		setBounds(1627, 0, 293, 200);
		
		JLabel timeLabel = new JLabel(getTime());
		timeLabel.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 45));
		timeLabel.setForeground(new Color(227, 229, 232));
		timeLabel.setBounds(0, 32, 200, 130);
		add(timeLabel);
		
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeLabel.setText(getTime());
			}
		});
		
		timer.start();
	}
	
	private String getTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}
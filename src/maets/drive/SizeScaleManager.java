package maets.drive;

public class SizeScaleManager {
	
	private int b;
	private int kb;
	private int mb;
	private int gb;
	
	private double percentageCalculation;
	private double eachPercentage;
	
	public SizeScaleManager(long totalSizeInB) {
		this.b = 0;
		this.kb = 0;
		this.mb = 0;
		this.gb = 0;
	
		
		eachPercentage = 100000.0 / totalSizeInB;
		percentageCalculation = eachPercentage;
	}
	
	private void updateValues() {
		int bTimes = b / 1000;
		for(int i = 0; i < bTimes; i++)
			kb++;
		b -= (bTimes * 1000);
		
		int kbTimes = kb / 1000;
		for(int i = 0; i < kbTimes; i++)
			mb++;
		kb -= (kbTimes * 1000);
		
		int mbTimes = mb / 1000;
		for(int i = 0; i < mbTimes; i++)
			gb++;
		
		mb -= (mbTimes * 1000);
	}
	
	private void updatePercentage() {
		percentageCalculation += eachPercentage;
	}
	
	protected void add(int bytesAdded) {
		b += bytesAdded;
		updateValues();
		updatePercentage();
	}
	
	public String getSize() {
		return String.format("(%.2f %%) GB: %d | MB: %d | KB: %d | B: %d", percentageCalculation, gb, mb, kb, b);
	}
}

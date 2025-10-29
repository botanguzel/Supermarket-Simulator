package snabbköp.kunder;

public class Kund {
	static int lastID = 0;
	private int ID;
	public Kund() {
		this.ID = lastID;
		lastID++;
	}
	public int getKundID() {
		return this.ID;
	}
}

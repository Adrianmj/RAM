import java.util.ArrayList;

public class Cinta {
	ArrayList<Integer> cinta;
	private int cabeza = 0;

	public Cinta() {
		cinta = new ArrayList<>();
	}

	public int getCabeza() {
		return cabeza;
	}

	public void setCabeza(int cabeza) {
		this.cabeza = cabeza;
	}

	public void print() {
		for (int i = 0; i < this.cinta.size(); i++) {
			if (i == this.getCabeza()) {
				System.out.print(" |" + this.cinta.get(i) + "| ");
			} else
				System.out.print(" " + this.cinta.get(i) + " ");
		}
		System.out.println();
	}
}

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CintaSalida extends Cinta {
	public void setCinta() {
		for (int i = 0; i <= 10; i++) {
			this.cinta.add(0);

		}
	}

	public void setValue(int valor) {
		this.cinta.add(valor);
		this.setCabeza(this.getCabeza() + 1);
	}
	public void write(){
		String out = "";
		for (int i = 0; i < this.cinta.size(); i++) {
			out = out+this.cinta.get(i)+ " ";
		}
		File salida = new File("salida");
		FileWriter fw;
		try {
			fw = new FileWriter(salida.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(out);
			bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

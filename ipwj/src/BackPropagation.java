
/**
 * @author Tama Asrory Ridhana
 * @serial Jaringan Syaraf Tiruan, metode Back Propagation Neural Network (BPNN)
 * @since ١٤ (ﺭﺑﻴﻊ ﺍﻷﻭﻝ) ١٤٣٨
 * @version 1.0
 * @copyright 2016 tama asrory ridhana
 */
public class BackPropagation {

    public double weightInputToHidden[][];
    public double uWeightInputToHidden[][];
    public double weightHiddenToOutput[][];
    public double uWeightHiddenToOutput[][];
    public double[] x;
    public int z;
    public double z_net[];
    public double fz_net[];
    public int y;
    public double y_net[];
    public double fy_net[];
    public double faktor_δ[];
    public double δ_net[];
    public double fδ_net[];
    public double t[];
    public double learningRate;
    public double maxEpoch;
    public double maxErr;

    /**
     * <b>Langkah 0. </b><br/> Inisialisasi bobot-bobot, konstanta laju pelatihan (α), toleransi error atau
     * nilai bobot (bila menggunakan nilai bobot sebagai kondisi berhenti) atau set maksimal epoch (jika
     * menggunakan banyaknya epoch sebagai kondisi berhenti).
     */
    public void init() {
	try {
	    this.weightInputToHidden = new double[this.x.length + 1][this.z];
	    this.weightHiddenToOutput = new double[this.z + 1][this.y];
	    this.uWeightInputToHidden = new double[this.x.length + 1][this.z];
	    this.uWeightHiddenToOutput = new double[this.z + 1][this.y];

//	    for (int i = 0; i < this.weightInputToHidden.length; i++) {
//		for (int j = 0; j < this.weightInputToHidden[0].length; j++) {
//		    this.weightInputToHidden[i][j] = (i % 2 == 0) ? Math.random() : -Math.random();
//		}
//	    }
//
//	    for (int i = 0; i < this.weightHiddenToOutput.length; i++) {
//		for (int j = 0; j < this.weightHiddenToOutput[0].length; j++) {
//		    this.weightHiddenToOutput[i][j] = (i % 2 == 0) ? Math.random() : -Math.random();
//		}
//	    }
	    this.weightInputToHidden = new double[][]{
		{0.2, 0.3, -0.1},
		{0.3, 0.1, -0.1},
		{-0.3, 0.3, 0.3}// <-- bias selalu di akhir
	    };
	    this.weightHiddenToOutput = new double[][]{
		{0.5},
		{-0.3},
		{-0.4},
		{-0.1} // <-- bias selalu di akhir
	    };
	    this.pelatihan();
	} catch (Exception e) {
	    e.getLocalizedMessage();
	}

    }

    public void pelatihan() {
	this.feedForward();
	this.backWard();
	this.updateWeight(this.uWeightHiddenToOutput, this.weightHiddenToOutput);
	this.updateWeight(this.uWeightInputToHidden, this.weightInputToHidden);
    }

    public void feedForward() {
	// Hitung keluaran unit tersembunyi z
	this.z_net = new double[this.z];
	this.fz_net = new double[this.z];
	for (int i = 0; i < this.z_net.length; i++) {
	    this.z_net[i] = this.weightInputToHidden[this.weightInputToHidden.length - 1][i];
	    for (int j = 0; j < this.x.length; j++) {
		this.z_net[i] += this.x[j] * this.weightInputToHidden[j][i];
	    }
	    System.out.println("z_net " + (i + 1) + " = " + this.z_net[i]);
	    this.fz_net[i] = 1 / (1 + Math.exp(-1 * this.z_net[i]));
	    System.out.println("fz_net " + (i + 1) + " = " + this.fz_net[i]);
	}

	// Hitung keluaran unit y
	this.y_net = new double[this.y];
	this.fy_net = new double[this.y];
	for (int i = 0; i < this.y_net.length; i++) {
	    this.y_net[i] = this.weightHiddenToOutput[this.weightHiddenToOutput.length - 1][i];
	    System.out.print("y_net " + (i + 1) + " = "
		    + this.weightHiddenToOutput[this.weightHiddenToOutput.length - 1][i]);
	    for (int j = 0; j < this.fz_net.length; j++) {
		System.out.print(" + (" + this.fz_net[j] + " * " + this.weightHiddenToOutput[j][i] + ")");
		this.y_net[i] += this.fz_net[j] * this.weightHiddenToOutput[j][i];

	    }
	    System.out.println(" = " + this.y_net[i]);
	    this.fy_net[i] = 1 / (1 + Math.exp(-1 * this.y_net[i]));
	    System.out.println("fy_net " + (i + 1) + " = " + this.fy_net[i]);
	}
    }

    public void backWard() {
	// Hitung faktor δ di unit keluaran Yk
	this.faktor_δ = new double[this.y];
	for (int i = 0; i < this.faktor_δ.length; i++) {
//	    δ k = ( t k − y k ) f ' ( y _ net k ) = ( t k − y k ) y k ( 1 − y k )
	    this.faktor_δ[i] = (this.t[i] - this.fy_net[i]) * (this.fy_net[i]) * (1 - this.fy_net[i]);
	    // print
	    System.out.println("faktor_δ = (" + this.t[i] + " - " + this.fy_net[i] + ") * (" + this.fy_net[i]
		    + ") * (" + 1 + "-" + this.fy_net[i] + ") = " + this.faktor_δ[i]);

	    for (int j = 0; j < this.weightHiddenToOutput.length; j++) {
		this.uWeightHiddenToOutput[j][i] = this.learningRate * this.faktor_δ[i]
			* ((j < this.fz_net.length) ? this.fz_net[j] : 1);
		// print
		System.out.println("W" + j + i + " = " + this.learningRate + " * " + this.faktor_δ[i] + " * "
			+ ((j < this.fz_net.length) ? this.fz_net[j] : 1) + " = "
			+ this.uWeightHiddenToOutput[j][i]);
	    }
	}
	// Hitung penjumlahan kesalahan dari unit tersembunyi δ
	this.δ_net = new double[this.weightHiddenToOutput.length - 1];
	this.fδ_net = new double[this.δ_net.length];
	for (int i = 0; i < this.δ_net.length; i++) {
	    for (int j = 0; j < this.faktor_δ.length; j++) {
		this.δ_net[i] += this.faktor_δ[j] * this.weightHiddenToOutput[i][j];
	    }
	    // print
	    System.out.println("δ_net " + (i + 1) + " = " + this.δ_net[i]);
	    // Faktor kesalahan δ di unit tersembunyi :
	    this.fδ_net[i] = this.δ_net[i] * this.fz_net[i] * (1 - this.fz_net[i]);
	    // print
	    System.out.println("fδ_net " + (i + 1) + " = " + this.δ_net[i] + " * "
		    + this.fz_net[i] + " * " + "(1 - " + this.fz_net[i] + ") = " + this.fδ_net[i]);
	}
	// Suku perubahan bobot ke unit tersembunyi :
	for (int i = 0; i < this.weightInputToHidden.length; i++) {
	    for (int j = 0; j < this.weightInputToHidden[0].length; j++) {
		// Δv ji = α δj xi
		this.uWeightInputToHidden[i][j] = this.learningRate * this.fδ_net[j] * ((i < this.x.length)
			? this.x[i] : 1);
		System.out.println("V " + i + j + " = " + this.learningRate + " * " + this.fδ_net[j] + " * "
			+ ((i < this.x.length) ? this.x[i] : 1) + " = " + this.uWeightInputToHidden[i][j]);
	    }
	}
    }

    /**
     * <b>Langkah 8 Tahap III : Pengupdatean Bobot dan Bias.</b><br/>Masing-masing unit output/keluaran
     * (y<sub>j</sub>, k=1,2,3,...,m) dilakukan pengupdatean bias dan bobotnya (j = 0,1,2,...,p) sehingga
     * menghasilkan bobot dan bias baru :
     * <blockquote>W<sub>kj</sub>(baru) = W<sub>kj</sub>(lama) + ∆W<sub>kj</sub></blockquote>
     * Demikian juga untuk setiap unit tersembunyi mulai dari unit ke-1 sampai dengan unit ke-p dilakukan
     * pengupdatean bobot dan bias :
     * <blockquote>V<sub>ji</sub>(baru) = V<sub>ji</sub>(lama) + ∆V<sub>ji</sub></blockquote>
     */
    public void updateWeight(double dbBaru[][], double dbLama[][]) {
	for (int i = 0; i < dbBaru.length; i++) {
	    for (int j = 0; j < dbBaru[0].length; j++) {
		System.out.print("W " + i + j + " = " + dbLama[i][j] + " + " + dbBaru[i][j] + " = ");
		dbLama[i][j] = dbLama[i][j] + dbBaru[i][j];
		System.out.println(dbLama[i][j]);
	    }
	}
    }

    public static void main(String[] args) {
	BackPropagation bp = new BackPropagation();
	bp.x = new double[]{1.0, 1.0};
	bp.z = 3;
	bp.y = 1;
	bp.learningRate = 0.2;
	bp.t = new double[]{0};
	bp.init();
    }
}

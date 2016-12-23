
/**
 * @author Tama Asrory Ridhana
 * @version 1.0
 * @serial Jaringan Syaraf Tiruan, metode Back Propagation Neural Network (BPNN)
 * @copyright 2016 tama asrory ridhana
 * @since ١٤ (ﺭﺑﻴﻊ ﺍﻷﻭﻝ) ١٤٣٨
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
        } catch (Exception Err) {
            Err.getLocalizedMessage();
        }
    }

    public void pelatihan() {
        this.feedForward();
        this.backWard();
        this.updateWeight(this.uWeightHiddenToOutput, this.weightHiddenToOutput);
        this.updateWeight(this.uWeightInputToHidden, this.weightInputToHidden);
    }

    public void feedForward() {
        /**
         * Saat umpan maju (feedforward), setiap unit input (Xi) akan menerima sinyal input
         * dan akan menyebarkan sinyal tersebut pada tiap hidden unit (Zj).
         */
        // Hitung keluaran unit tersembunyi z
        this.z_net = new double[this.z];
        this.fz_net = new double[this.z];
        // loop :
        for (int i = 0; i < this.z_net.length; i++) {
            // formula :
            this.z_net[i] = this.weightInputToHidden[this.weightInputToHidden.length - 1][i];
            // loop :
            for (int j = 0; j < this.x.length; j++) {
                // formula :
                this.z_net[i] += this.x[j] * this.weightInputToHidden[j][i];
            }
            //print
            System.out.println("z_net " + (i + 1) + " = " + this.z_net[i]);
            /**
             * Setiap hidden unit kemudian akan menghitung aktivasinya
             * dan mengirim sinyal (Zj) ke tiap unit output.
             */
            // formula :
            this.fz_net[i] = 1 / (1 + Math.exp(-1 * this.z_net[i]));
            // print
            System.out.println("fz_net " + (i + 1) + " = " + this.fz_net[i]);
        }

        // Hitung keluaran unit y
        this.y_net = new double[this.y];
        this.fy_net = new double[this.y];
        // loop :
        for (int i = 0; i < this.y_net.length; i++) {
            // formula :
            this.y_net[i] = this.weightHiddenToOutput[this.weightHiddenToOutput.length - 1][i];
            //print
            System.out.print("y_net " + (i + 1) + " = "
                    + this.weightHiddenToOutput[this.weightHiddenToOutput.length - 1][i]);
            // loop :
            for (int j = 0; j < this.fz_net.length; j++) {
                //print
                System.out.print(" + (" + this.fz_net[j] + " * " + this.weightHiddenToOutput[j][i] + ")");
                // formula :
                this.y_net[i] += this.fz_net[j] * this.weightHiddenToOutput[j][i];

            }
            // print
            System.out.println(" = " + this.y_net[i]);
            /**
             * Kemudian setiap unit output (Y) juga akan menghitung aktivasinya (Y)
             * untuk menghasilkan respons terhadap input yang diberikan jaringan.
             */
            // formula :
            this.fy_net[i] = 1 / (1 + Math.exp(-1 * this.y_net[i]));
            // print
            System.out.println("fy_net " + (i + 1) + " = " + this.fy_net[i]);
        }
    }

    public void backWard() {
        // Hitung faktor δ di unit keluaran Yk
        this.faktor_δ = new double[this.y];
        // loop :
        for (int i = 0; i < this.faktor_δ.length; i++) {
            // formula : δ k = ( t k − y k ) f ' ( y _ net k ) = ( t k − y k ) y k ( 1 − y k )
            this.faktor_δ[i] = (this.t[i] - this.fy_net[i]) * (this.fy_net[i]) * (1 - this.fy_net[i]);
            // print
            System.out.println("faktor_δ = (" + this.t[i] + " - " + this.fy_net[i] + ") * (" + this.fy_net[i]
                    + ") * (" + 1 + "-" + this.fy_net[i] + ") = " + this.faktor_δ[i]);
            // loop :
            for (int j = 0; j < this.weightHiddenToOutput.length; j++) {
                // formula :
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
        // loop :
        for (int i = 0; i < this.δ_net.length; i++) {
            // loop :
            for (int j = 0; j < this.faktor_δ.length; j++) {
                // formula :
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
        // loop :
        for (int i = 0; i < this.weightInputToHidden.length; i++) {
            // loop :
            for (int j = 0; j < this.weightInputToHidden[0].length; j++) {
                // formula : Δv ji = α δj xi
                this.uWeightInputToHidden[i][j] = this.learningRate * this.fδ_net[j] * ((i < this.x.length)
                        ? this.x[i] : 1);
                // print
                System.out.println("V " + i + j + " = " + this.learningRate + " * " + this.fδ_net[j] + " * "
                        + ((i < this.x.length) ? this.x[i] : 1) + " = " + this.uWeightInputToHidden[i][j]);
            }
        }
    }

    /**
     *
     */
    public void updateWeight(double dbBaru[][], double dbLama[][]) {
        // loop :
        for (int i = 0; i < dbBaru.length; i++) {
            // loop :
            for (int j = 0; j < dbBaru[0].length; j++) {
                // print
                System.out.print("W " + i + j + " = " + dbLama[i][j] + " + " + dbBaru[i][j] + " = ");
                // formula :
                dbLama[i][j] = dbLama[i][j] + dbBaru[i][j];
                // print
                System.out.println(dbLama[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        // instance class :
        BackPropagation bp = new BackPropagation();
        // neuron input
        bp.x = new double[]{1.0, 1.0};
        // neuron hidden
        bp.z = 3;
        // neuron output
        bp.y = 1;
        // learning rate
        bp.learningRate = 0.2;
        // target
        bp.t = new double[]{0};
        // init
        bp.init();
    }
}

/**
 * @author Tama Asrory Ridhana
 * @serial Jaringan Syaraf Tiruan, metode Perceptron
 * @version 1.0
 * @since 9/11/1438 Hijriyah
 */

public class Perceptron {
    static int counter=1;
    /**
     * @param rows
     * @param cols
     * @param init
     * @return
     */
    static public double[][] initWeight(int rows, int cols, int init) {
	double initWeight[][] = new double[rows][cols];
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < cols; j++) {
		initWeight[i][j] = init;
		System.out.print("W"+(counter++)+"=" + init + "\t");
		if (j % 5 == 0) {
		    System.out.println("");
		}
	    }
	}
	counter=1;
	System.out.println("");
	return initWeight;
    }

    /**
     * @param Wi
     * @param pattern
     * @param bias
     * @return
     */
    static public double y_in(double Wi[][], int pattern[][], double bias) {
	double XiWi[][] = new double[pattern.length][pattern[0].length];
	double Sigma_XiWi = 0;
	for (int i = 0; i < XiWi.length; i++) {
	    for (int j = 0; j < XiWi[0].length; j++) {
		XiWi[i][j] = pattern[i][j] * Wi[i][j];
		System.out.print("(" + pattern[i][j] + "x" + Wi[i][j] + ")+");
		if (j % 5 == 0) {
		    System.out.println("");
		}
		Sigma_XiWi += XiWi[i][j];
	    }
	}
	// y_in = SIGMA Xi*Wi + bias
	System.out.println(bias + " = " + (Sigma_XiWi + bias) + "\n");
	return (Sigma_XiWi + bias);
    }

    /**
     *
     * @param Wi
     * @param pattern
     * @param t
     * @return
     */
    static public double[][] setWeight(double Wi[][], int pattern[][], int t, double alfa) {
	for (int i = 0; i < Wi.length; i++) {
	    for (int j = 0; j < Wi[0].length; j++) {
		// W_i(baru)=W_i(lama)+alfa*X_i*t
		System.out.print("W"+(counter++)+"(baru) = " + Wi[i][j] + "+" + alfa + "*" + pattern[i][j] + "*" + t + " = ");
		Wi[i][j] = Wi[i][j] + alfa * pattern[i][j] * t;
		System.out.println(Wi[i][j]);
	    }
	}
	counter=1;
	return Wi;
    }

    /**
     *
     * @param y_in
     * @param target
     * @return
     */
    static public boolean cekTarget(double y_in, int target, double teta) {

	int y = 0;

	if (y_in > teta) {
	    y = 1;
	} else if (y_in >= teta || y_in <= teta) {
	    y = 0;
	} else if (y_in < (-1 * teta)) {
	    y = -1;
	}
	System.out.print("y = " + y + " ==> y" + ((y == target) ? " sama dengan Target" : " Tidak sama dengan Target ") + "(t=" + target + ")" + "\n\n");
	return (y == target);

    }

    public Perceptron(int epoch, double teta, double alfa, double bias, double Wi[][], int pattern[][][], int t[]) {
	System.out.println("bias = " + bias);
	for (int i = 0; i < epoch; i++) {
	    System.out.println("-------------------------epoch ke-" + (i + 1) + "--------------------------");
	    for (int j = 0; j < pattern.length; j++) {
		System.out.println("......................DATA KE-" + (j + 1) + ".....................");
		if (!cekTarget(y_in(Wi, pattern[j], bias), t[j], teta)) {
		    System.out.println("/\\/\\/\\/ melakukan perubahan bobot /\\/\\/\\/");
		    Wi = setWeight(Wi, pattern[j], t[j], alfa);
		    System.out.print("b(baru) = " + bias + "+" + alfa + "*" + t[j] + " = ");
		    bias = bias + alfa * t[j];
		    System.out.println(bias + "\n");
		}
	    }
	}
    }

    public static void main(String[] args) {

	double w[][] = initWeight(5, 5, 0);

	int t[] = {1, -1, -1};

	int pola[][][] = {
	    {
		{-1, 1, 1, 1, -1},
		{1, 1, -1, 1, 1},
		{1, -1, -1, -1, 1},
		{1, 1, 1, 1, 1},
		{1, -1, -1, -1, 1}
	    },
	    {
		{1, 1, 1, 1, 1},
		{1, -1, -1, -1, -1},
		{1, 1, 1, 1, -1},
		{1, -1, -1, -1, -1},
		{1, 1, 1, 1, 1}
	    },
	    {
		{1, 1, 1, 1, 1},
		{1, -1, -1, -1, -1},
		{1, 1, 1, 1, -1},
		{1, -1, -1, -1, -1},
		{1, -1, -1, -1, -1}
	    }
	};

	Perceptron perceptron = new Perceptron(3, 0.5, 1, 0, w, pola, t);
    }
}

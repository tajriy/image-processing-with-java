/**
 * 
 * @author Tama Asrory Ridhana
 * @serial Jaringan Syaraf Tiruan, metode Perceptron
 * @version 1.0
 * @since  هِجْرَة : ٩ سؤر ١٤٣٨  
 * 
 * @url ###https://github.com/tamaasrory/image-processing-with-java/blob/master/ipwj/src/Perceptron.java
 * 
 * @see Perceptron#Perceptron
 * @see Perceptron#initWeight
 * @see Perceptron#y_in
 * @see Perceptron#setWeight
 * @see Perceptron#main
 */

public class Perceptron {
    
    /**
     * Deskripsi : <p>Digunakan sebagai penghitung</p>
     * @variable COUNTER
     */
    static int COUNTER = 1;

    /**
     * Deskripsi : @Method initWeight() <p>digunakan untuk mengatur nilai awal bobot</p>
     * 
     * @param ROWS
     * @param COLS
     * @param INIT
     * @return INIT_WEIGHT
     */
    static public double[][] initWeight(int ROWS, int COLS, double INIT) {
	double INIT_WEIGHT[][] = new double[ROWS][COLS];
	for (int i = 0; i < ROWS; i++) {
	    for (int j = 0; j < COLS; j++) {
		INIT_WEIGHT[i][j] = INIT;
		System.out.print("W" + (COUNTER++) + "=" + INIT + "\t");
		if (j % 5 == 0) {
		    System.out.println("");
		}
	    }
	}
	COUNTER = 1;
	System.out.println("");
	return INIT_WEIGHT;
    }

    /**
     * Deskripsi : @Method y_in() <p>digunakan untuk menghitung nilai Y_in </p>
     * 
     * @param WEIGHT
     * @param PATTERN
     * @param BIAS
     * @return
     */
    static public double y_in(double WEIGHT[][], int PATTERN[][], double BIAS) {
	double XiWEIGHT[][] = new double[PATTERN.length][PATTERN[0].length];
	double Sigma_XiWEIGHT = 0;
	for (int i = 0; i < XiWEIGHT.length; i++) {
	    for (int j = 0; j < XiWEIGHT[0].length; j++) {
		// RUMUS : Xi*Wi
		XiWEIGHT[i][j] = PATTERN[i][j] * WEIGHT[i][j];
		// print
		System.out.print("(" + PATTERN[i][j] + "x" + WEIGHT[i][j] + ")+");
		if (j % 5 == 0) {
		    System.out.println("");
		}
		// RUMUS : SIGMA Xi*Wi
		Sigma_XiWEIGHT += XiWEIGHT[i][j];
	    }
	}
	// print
	System.out.println(BIAS + " = " + (Sigma_XiWEIGHT + BIAS) + "\n");
	/**
	 * RUMUS :
	 * y_in = SIGMA Xi*WEIGHT + BIAS
	 */
	return (Sigma_XiWEIGHT + BIAS);
    }

    /**
     * Deskripsi : @Method setWeight() <p>digunakan untuk melakukan perubahan bobot</p>
     * 
     * @param WEIGHT
     * @param PATTERN
     * @param TARGET
     * @return
     */
    static public double[][] setWeight(double WEIGHT[][], int PATTERN[][], int TARGET, double ALPHA) {
	for (int i = 0; i < WEIGHT.length; i++) {
	    for (int j = 0; j < WEIGHT[0].length; j++) {
		System.out.print("W" + (COUNTER++) + "(baru) = " + WEIGHT[i][j] 
			+ "+" + ALPHA + "*" + PATTERN[i][j] + "*" + TARGET + " = ");
		/**
		 * RUMUS :
		 * W_i(baru)=W_i(lama)+ALPHA*X_i*t
		 */
		WEIGHT[i][j] = WEIGHT[i][j] + ALPHA * PATTERN[i][j] * TARGET;
		
		System.out.println(WEIGHT[i][j]);
	    }
	}
	/**
	 * Reset COUNTER = 1
	 */
	COUNTER = 1;
	
	// return weight
	return WEIGHT;
    }

    /**
     * Deskripsi : @Method cekTarget() <p>digunakan untuk menentukan nilai Y dan 
     * melakukan pemeriksaan apakah sesuai dengan target atau tidak</p>
     * 
     * @param Y_in
     * @param TARGET
     * @return
     */
    static public boolean cekTarget(double Y_in, int TARGET, double THRESHOLD) {

	int Y = 0;

	if (Y_in > THRESHOLD) {
	    Y = 1;
	} else if (Y_in >= THRESHOLD && Y_in <= THRESHOLD) {
	    Y = 0;
	} else if (Y_in < (-1 * THRESHOLD)) {
	    Y = -1;
	}
	System.out.print("y = " + Y + " ==> y" + ((Y == TARGET) ? " sama dengan Target" : " Tidak sama dengan Target ") + "(t=" + TARGET + ")" + "\n\n");
	return (Y == TARGET);

    }

    /**
     * Deskripsi : @Method constructor Perceptron() <p>digunakan untuk perhitung perceptron</p>
     * 
     * @param EPOCH
     * @param THRESHOLD
     * @param ALPHA
     * @param BIAS
     * @param WEIGHT
     * @param PATTERN
     * @param TARGET
     */
    public Perceptron(int EPOCH, double THRESHOLD, double ALPHA, double BIAS, 
	    double WEIGHT, int PATTERN[][][], int TARGET[]) {
	System.out.print("b = " + BIAS+" ");
	double WEIGHTS[][]=initWeight(PATTERN[0].length, PATTERN[0][0].length, WEIGHT);
	for (int i = 0; i < EPOCH; i++) {
	    System.out.println("-------------------------EPOCH ke-" + (i + 1) + "--------------------------");
	    for (int j = 0; j < PATTERN.length; j++) {
		System.out.println("......................DATA KE-" + (j + 1) + ".....................");
		/**
		 * Memeriksa apakah inputan sesuai dengan target
		 */
		if (!cekTarget(y_in(WEIGHTS, PATTERN[j], BIAS), TARGET[j], THRESHOLD)) {
		    
		    System.out.println("/\\/\\/\\/ melakukan perubahan bobot /\\/\\/\\/");
		    /**
		     * Mengatur bobot 
		     */
		    WEIGHTS = setWeight(WEIGHTS, PATTERN[j], TARGET[j], ALPHA);
		    System.out.print("b(baru) = " + BIAS + "+" + ALPHA + "*" + TARGET[j] + " = ");
		    /**
		     * Mengatur bias
		     */
		    BIAS = BIAS + ALPHA * TARGET[j];
		    System.out.println(BIAS + "\n");
		}else{
		    /**
		     * Memeriksa apakah target telah tercapai semua
		     */
		    if (COUNTER == TARGET.length) {
			System.exit(0);
		    }else{
			COUNTER++;
		    }
		}
	    }
	    /**
	     * Reset Nilai COUNTER = 1;
	     */
	    COUNTER=1;
	}
    }

    public static void main(String[] args) {
	
	// target
	int TARGET[] = {-1, 1, -1}; 
	// pola
	int PATTERN[][][] = { 
	    {
		{-1, 1, 1, 1,-1},
		{ 1, 1,-1, 1, 1},
		{ 1,-1,-1,-1, 1},
		{ 1, 1, 1, 1, 1},
		{ 1,-1,-1,-1, 1}
	    },
	    {
		{1, 1, 1, 1, 1},
		{1,-1,-1,-1,-1},
		{1, 1, 1, 1,-1},
		{1,-1,-1,-1,-1},
		{1, 1, 1, 1, 1}
	    },
	    {
		{1, 1, 1, 1, 1},
		{1,-1,-1,-1,-1},
		{1, 1, 1, 1,-1},
		{1,-1,-1,-1,-1},
		{1,-1,-1,-1,-1}
	    }
	};
	
	/**
	 * Contructor Perceptron(EPOCH, THRESHOLD, ALPHA, BIAS, BOBOT, PATTERN, TARGET);
	 */
	new Perceptron(4, 0, 1, 0, 0, PATTERN, TARGET);
    }
}
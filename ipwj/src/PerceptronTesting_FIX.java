
public class PerceptronTesting_FIX {
    public static void main(String[] args) {
	/**
	 * CONTOH SOAL :
	 * Buatlah perceptron untuk mengenali fungsi logika "AND" dengan masukan dan keluaran 
	 * bipolar, Untuk inisialisasi, gunakan :
	 * @bobot dan 
	 * @bias awal = 0, 
	 * @alpha = 1 dan,
	 * @threshold = 0
	 * 
	 *     TABEL INPUTAN
	 * |-------------------
	 * | X1 | X2 | 1 | t  |
	 * |-------------------
	 * | 1	  1    1   1  |
	 * | 1	 -1    1  -1  |
	 * |-1	  1    1  -1  |
	 * |-1	 -1    1  -1  |
	 * --------------------
	 * 
	 */
	int p[][][]={
	    {
		{1,1}
	    },
	    {
		{1,-1}
	    },
	    {
		{-1,1}
	    },
	    {
		{-1,-1}
	    }
	};
	int t[]={1,-1,-1,-1};
	double w[][]=Perceptron.initWeight(1,2, 0);
	Perceptron run=new Perceptron(1, 0, 1, 0, w, p, t);
    }
}

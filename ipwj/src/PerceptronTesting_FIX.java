
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
	int INPUTAN[][][]={
	    {
		{1,1}
	    },
	    {
		{1,0}
	    },
	    {
		{0,1}
	    },
	    {
		{0,0}
	    }
	};
	
	int TARGET[]={1,-1,-1,-1};
	
	Perceptron run=new Perceptron(1, 0.2, 1, 0, 0, INPUTAN, TARGET);
    }
}

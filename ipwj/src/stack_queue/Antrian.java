package stack_queue;

class Antrian {

    private mahasiswa[] data = (mahasiswa[]) new mahasiswa[10];
    private int ekor = 0;
    private int kepala = 0;
    private int jumlah = 0;
    private mahasiswa temp;

    public void enqueue(mahasiswa isi) {
	data[ekor] = isi;
	jumlah++;
	if (ekor == data.length) { ekor = 0;} else { ekor++;}
    }

    public mahasiswa dequeue() {
	temp = data[kepala]; jumlah--;
	if (kepala == data.length - 1) {kepala = 0;} else {kepala++;}
	return temp;
    }

    public mahasiswa peek() {return data[kepala];}

    public boolean isEmpty() {return (jumlah == 0);}

    public boolean isFull() {return (jumlah == data.length); }

    public void clear() {
	kepala = 0;
	ekor = 0;
	jumlah = 0;
    }
}

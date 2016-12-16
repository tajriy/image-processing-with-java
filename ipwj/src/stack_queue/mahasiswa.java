package stack_queue;

public class mahasiswa {

    private String nama;
    private int tahun_lahir;

    public mahasiswa() {
	nama = "";
	tahun_lahir = 0;
    }

    public String getNAMA() {
	return nama;
    }

    public double getIPK() {
	return tahun_lahir;
    }


    public void setNAMA(String nama) {
	this.nama = nama;
    }

    public void setIPK(int tahun_lahir) {
	this.tahun_lahir = tahun_lahir;
    }
}

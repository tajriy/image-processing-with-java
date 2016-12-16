/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack_queue;

import java.util.*;

public class mainAntrianMahasiswa {

   public static void main (String[] args) {
        String nama;
        int ipk;
        
        Antrian antrian = new Antrian();
        mahasiswa data;
        
        boolean kondisi=true;
        
        while (kondisi){
            System.out.println (" ");
            System.out.println ("Pilih Menu di bawah ini");
            System.out.println ("1. Input data");
            System.out.println ("2. Ambil data");
            System.out.println ("3. Lihat peek");
            System.out.println ("4. Cek kosong");
            System.out.println ("5. Cek Penuh");
            System.out.println ("6. Clear");
            System.out.println ("7. Waktu");
            System.out.println ("8. Keluar");
            System.out.print ("Masukkan pilihan Anda : ");
            Scanner op = new Scanner(System.in);
            int Menu = op.nextInt();

            if(Menu==1){
                Scanner inputString = new Scanner(System.in);
                System.out.print ("Masukkan Nama : ");
                nama = inputString.nextLine();
                Scanner input = new Scanner(System.in);
                System.out.print ("Masukkan IPK : ");
                ipk = input.nextInt();

                data=new mahasiswa();
                data.setNAMA(nama);
                data.setIPK(ipk);
                antrian.enqueue(data);
            }

            else if(Menu==2){
                if(antrian.isEmpty()){
                    System.out.println ("Kosong");
                }
                else{
                    data=antrian.dequeue();
                    System.out.println (data.getNAMA()+"|"+ data.getIPK());
                }
            }

            else if(Menu==3){
                if(antrian.isEmpty()){
                    System.out.println ("Kosong");
                }
                else{
                    data=antrian.peek();
                    System.out.println (data.getNAMA()+"|"+ "|" + data.getIPK());
                }
            }

            else if(Menu==4){
                if(antrian.isEmpty()){
                    System.out.println ("Kosong");
                }
                else{
                    System.out.println ("Ada isinya");
                }
            }

            else if(Menu==5){
                if(antrian.isFull()){
                    System.out.println ("Penuh");
                }
                else{
                System.out.println ("Belum Penuh");
                }
            }

            else if(Menu==6){
                antrian.clear();
                System.out.println ("Antrian dikosongkan");
            }

            else if(Menu==7){
                Date dt = new Date();
                int detik = dt.getSeconds();
                int menit = dt.getMinutes();
                int jam = dt.getHours();
                int har = dt.getDay();
                int tanggal = dt.getDate();
                int month = dt.getMonth();
                int tahun  =dt.getYear();

                //yang menentukan am pm
                String wkt = "AM";
                if(jam>12){
                    wkt = "PM";
                }
                System.out.print("\n===================================\n");
                String[] ha = {"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jum,at", "Sabtu"};
                String[] bulan = {"January", "February", "Maret", "April", "Mei", "Juni",
"Juli", "Agustus", "September", "Oktober", "November", "Desember"};
                System.out.print("Sekarang : "+jam+":"+menit+" "+wkt+"\npada "+ha[har]+
" Tgl : "+tanggal+", Bln : "+bulan[month]+" 2013");
                System.out.print("\n===================================\n");
            }

            else if(Menu==8){
                kondisi = false;
            }

            else if(Menu>=9){
                System.out.println("Input menu harus diantara 1 sampai 7");
            }

            else if(Menu<=0){
                System.out.println("Input menu harus diantara 1 sampai 7");
            }
        
        }
    }
}

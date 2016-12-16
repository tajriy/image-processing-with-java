/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack_queue;

public class Tumpukan {

    private String[] data = (String[]) new Object[10];
    private int posisi = 0;

    public void push(String isi) {
	data[posisi++] = isi;
    }

    public String pop() {
	return data[--posisi];
    }

    public String peek() {
	return data[posisi - 1];
    }

    public boolean isEmpty() {
	return (posisi == 0);
    }

    public boolean isFull() {
	return (posisi == data.length);
    }

    public void clear() {
	posisi = 0;
    }
}

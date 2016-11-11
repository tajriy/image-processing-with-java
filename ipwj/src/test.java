/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asrory
 */
public class test {
    public static void main(String[] args) {
		
	int pola[][][] = {
	    {
		{-1, 1, 1},
		{1, -1, -1},
		{1, 1, 1, 1},
		{1, -1, -1},
		{1, -1, -1}
	    },
	    {
		{-1, 1, 1},
		{1, -1, -1},
		{1, 1, 1, 1},
		{1, -1, -1},
		{1, -1, -1}
	    },
	    {
		{-1, 1, 1},
		{1, -1, -1},
		{1, 1, 1, 1},
		{1, -1, -1},
		{1, -1, -1}
	    },
	    {
		{-1, 1, 1},
		{1, -1, -1},
		{1, 1, 1, 1},
		{1, -1, -1},
		{1, -1, -1}
	    }
	};
//	System.out.println("baris : "+pola.length+"\nbaris : "+pola[0].length+"\nkolom : "+pola[0][0].length);
	for (int i = 0; i < pola.length; i++) {
	    System.out.println(i+"\n");
	    System.out.println(pola[i].toString());
	}
    }
}

package br.com.joshua.desafio.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainTestes {

	
	public static void main(String[] args) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Calendar calendario1 = Calendar.getInstance();
		Calendar calendario2 = Calendar.getInstance();
		Calendar calendario3 = Calendar.getInstance();
		calendario1.set(calendario3.get(Calendar.YEAR), calendario3.get(Calendar.MONTH), calendario3.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
		System.out.println(dateFormat.format(calendario1.getTime()));
		calendario2.set(calendario3.get(Calendar.YEAR), calendario3.get(Calendar.MONTH), calendario3.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		System.out.println(dateFormat.format(calendario2.getTime()));
		
		BigDecimal b = BigDecimal.ZERO;
		
		BigDecimal c = new BigDecimal(-2);
		
		System.out.println(b.compareTo(BigDecimal.ZERO));
		
		System.out.println(b.add(c));
		
	}
	
}

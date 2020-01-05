package com.neteye.xinzhizhu.test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

public class TestCompress {

	public static void main(String[] args) {
		BigDecimal orderTotalPrice = new BigDecimal("0.00");
        if(orderTotalPrice.compareTo(BigDecimal.ZERO) == 0) {
        	System.out.print("ZEROZEROZEROZERO");
        }
	}

}

package com.sharingcard.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sharingcard.common.utils.DateUtils;

public class TestDate {

	public  static Object getkey() {
		return 3+"";
	}
	
	public static void main(String[] args) throws ParseException {
		
		System.out.println(Integer.valueOf("09"));
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d= sdf.parse("2050-12-31");
		System.out.println(d);
		System.out.println(d.toString());
		System.out.println(d.toString().substring(0, 19));
		System.out.println(d.toString().substring(1, 19));
		System.out.println("8986011584170200636J".startsWith("8986011584170200636", 0));
		int houre = Integer.valueOf(DateUtils.formatsimplehoure(new  Date()));
		if(6 > houre ||  22 < houre)
		{
			System.out.println("抢单很积极，休息更重要，请在6点之后，23点之前抢单");
		}
		
		System.out.println("您抢单频率太快，请"+Math.round((20001l-20000l)/(1000*60)+0.5)+"分钟后重试");
		
		String ratevalue = TestDate.getkey()+"";
		System.out.println("redis rate key:"+ratevalue+", value:"+ratevalue);
		if(ratevalue != null && !ratevalue.equals("null")  && !ratevalue.equals(""))
		{
			System.out.println("您抢单频率太快，请"+Integer.valueOf(ratevalue)+"分钟后重试");
		}

		System.out.println("您抢单频率太快，请"+Math.round((20001l-20000l)/(1000*60)+0.5)+"分钟后重试");
	}

}

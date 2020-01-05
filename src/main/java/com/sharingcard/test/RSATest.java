package com.sharingcard.test;

import com.alibaba.fastjson.JSONObject;
import com.sharingcard.common.utils.RSAApi;
import com.sharingcard.common.utils.RSASignature;

public class RSATest {

	public static void main(String[] args) throws Exception {
		int timemax = 20;
		int canorder = 30;
		int times = timemax>canorder?canorder:timemax;
		System.out.println("<<<-times->>>" + times);
		//1.获取 RSA 私钥
		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI9e/KpckfW3pM2Gn8IPbHshWoPWH0SBYYP5KAkYL7izULwkW8UzpsVBL46fcibOCusvrvoQcfrsgN1JZu2GtOmuCJDpzwW9nJG1an9EpcGYfM0ycHDYKQ7a8PFSgHK9CgNvx3iiR/9osuT7JIPBkbbiV0cjZ90hm4cW35IiddHbAgMBAAECgYBV8hQ3ME0TZuZ1iomEtOhMG999/sM5tvG1Qox8fzJEX8I7NvSIkAEeaIhQ0EaG+qdS+3/CSk95o4NQpGr+Yu1Z7kecnm7m2WF/7+gJ4rqwvQF8v+qQmyER2655RJMfsjCmg7xkiV0lVtDo8GHGsrN5R4vLLdCLTVmwFosYQ1fEIQJBAMnTUMbtrxFMkg3MlQ0a/9nyJiEaajUc/SuxbTX/LE381uPDoRX/IdUvEoU2rlyCoPgyiPUwTx/EofDiXj7sXbECQQC12uee3bu5RVYOZxvic0uuHpPL/TbCuxR2ScyDEQ8Jt2+51lGBfvbsnyVbcwKYHKH/qngYanBGUVZVGYKWiQ9LAkEAkDTwM6NjAp5OgZObMIkJJMYTnnWMS7UR5GVbHkjcdEDjnkyKzffFSdjfh9WhUjJ4J7+cAm1pK87I0yA7rQQgkQJAXFVAAuZ8GO5+aiOqAckeZNK7off35NlN411HDEkOBJXxkT6i9Yf8YVnQguUbLRZknkX0Yhkw1lMX7W9dYYdHjwJBAI0cQLQrCqG7pOWfd3RhSf9Nbgq9lw7Cm7ONc4i+QvCm0br5hU/Ow4fPQOs+gQ4C3aK+bz0jel4V066xEqTy/1I=";
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPXvyqXJH1t6TNhp/CD2x7IVqD1h9EgWGD+SgJGC+4s1C8JFvFM6bFQS+On3ImzgrrL676EHH67IDdSWbthrTprgiQ6c8FvZyRtWp/RKXBmHzNMnBw2CkO2vDxUoByvQoDb8d4okf/aLLk+ySDwZG24ldHI2fdIZuHFt+SInXR2wIDAQAB";
		System.out.println("<<<-RSA私钥->>>" + privateKey);
		System.out.println("<<<-RSA公钥->>>" + publicKey);
		//各平台参数数据 SvcCont 数据
		String svcCont = "{'parameters': {'serialNumber': '17002602740','flag': '1','cycleId': '201408'}}";
		System.out.println("<<<-SvcCont加密前数据->>>" + svcCont);
		//2.SvcCont 数据签名
		String sign = RSASignature.sign(svcCont, privateKey);
		System.out.println("<<<-SvcCont签名->>>" + sign);
		//3.SvcCont 数据加密
		String encrypt = RSAApi.encryptInBase64(privateKey, svcCont);
		System.out.println("<<<-SvcCont加密数据->>>" + encrypt);
		//各平台报文头数据 TcpCont 数据
		JSONObject tcpCont = new JSONObject();
		tcpCont.put("channelCode", "S");//渠道编码
		tcpCont.put("messageID", "1239217398217");//需自动生成流水号,各平台

		tcpCont.put("reqTime", "YYYYMMDDHHMMSS");//请求时间格式
		tcpCont.put("serviceKey", "microQyrHistoricalBill");//微服务编码
		tcpCont.put("signature", sign);//签名
		tcpCont.put("staffId", "");//操作员工号
		tcpCont.put("version", "MICRO-1.0.1");//微服务版本号
		//4.组装报文传输
		JSONObject param = new JSONObject();
		param.put("tcpCont", tcpCont);//报文头
		param.put("svcCont", encrypt);//报文体(加密后)
		System.out.println("<<<-组装后请求报文->>>" + param);
		//5.SvcCont 数据解密
		String decrypt = RSAApi.decryptInBase64(publicKey, encrypt);
		System.out.println("<<<-解密后请求报文->>>" + decrypt);
	}

}

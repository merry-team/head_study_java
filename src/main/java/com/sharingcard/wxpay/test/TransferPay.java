package com.sharingcard.wxpay.test;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sharingcard.wxpay.model.JsonResult;
import com.sharingcard.wxpay.utils.CollectionUtil;
import com.sharingcard.wxpay.utils.ConfigUtil;
import com.sharingcard.wxpay.utils.HttpUtils;
import com.sharingcard.wxpay.utils.PayUtil;
import com.sharingcard.wxpay.utils.SerializerFeatureUtil;
import com.sharingcard.wxpay.utils.WebUtil;
import com.sharingcard.wxpay.utils.XmlUtil;

public class TransferPay {
	private static final String TRANSFERS_PAY = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers"; // 企业付款

	private static final String TRANSFERS_PAY_QUERY = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo"; // 企业付款查询

	private static final String APP_ID = ConfigUtil.getProperty("wx.appid");

	private static final String MCH_ID = ConfigUtil.getProperty("wx.mchid");

	private static final String API_SECRET = ConfigUtil.getProperty("wx.api.secret");
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(TransferPay.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> restmap = null;
		try {
			Map<String, String> parm = new HashMap<String, String>();
			parm.put("mch_appid", APP_ID); //公众账号appid
			parm.put("mchid", MCH_ID); //商户号
			parm.put("nonce_str", PayUtil.getNonceStr()); //随机字符串
			parm.put("partner_trade_no", PayUtil.getTransferNo()); //商户订单号
			//parm.put("nonce_str", "K5JlibZ4lgMlKdUA88xdpqb6eVkQQ4G6"); //随机字符串
			//parm.put("partner_trade_no", "TNO2019031414505114800000001"); //商户订单号
			//parm.put("openid", "o_I1G0-YnPC8HwOoJfrwvVQgK_Vw"); //用户openid	 尹湘军
			parm.put("openid", "o3GiAw2j-MtiHb9b47HP5RPSIRls"); // 尹湘军的unionid
			parm.put("check_name", "NO_CHECK"); //校验用户姓名选项 OPTION_CHECK
			//parm.put("re_user_name", "安迪"); //check_name设置为FORCE_CHECK或OPTION_CHECK，则必填
			parm.put("amount", "100"); //转账金额
			parm.put("desc", "测试转账到个人"); //企业付款描述信息
			parm.put("spbill_create_ip", "10.2.3.10"); //Ip地址
			parm.put("sign", PayUtil.getSign(parm, API_SECRET));
String xmlstring ="<xml>\r\n" + 
		"<mch_appid>wxe062425f740c30d8</mch_appid>\r\n" + 
		"<mchid>10000098</mchid>\r\n" + 
		"<nonce_str>3PG2J4ILTKCH16CQ2502SI8ZNMTM67VS</nonce_str>\r\n" + 
		"<partner_trade_no>100000982014120919616</partner_trade_no>\r\n" + 
		"<openid>ohO4Gt7wVPxIT1A9GjFaMYMiZY1s</openid>\r\n" + 
		"<check_name>FORCE_CHECK</check_name>\r\n" + 
		"<re_user_name>张三</re_user_name>\r\n" + 
		"<amount>100</amount>\r\n" + 
		"<desc>节日快乐!</desc>\r\n" + 
		"<spbill_create_ip>10.2.3.10</spbill_create_ip>\r\n" + 
		"<sign>C97BDBACF37622775366F38B629F45E3</sign>\r\n" + 
		"</xml>";
			String restxml = HttpUtils.posts(TRANSFERS_PAY, XmlUtil.xmlFormat(parm, false));
			//String restxml = HttpUtils.posts(TRANSFERS_PAY, xmlstring);
			restmap = XmlUtil.xmlParse(restxml);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		if (CollectionUtil.isNotEmpty(restmap) && "SUCCESS".equals(restmap.get("result_code"))) {
			LOGGER.info("转账成功：" + restmap.get("err_code") + ":" + restmap.get("err_code_des")+":"+restmap.get("partner_trade_no")+":"+ restmap.get("payment_no"));
			Map<String, String> transferMap = new HashMap<>();
			transferMap.put("partner_trade_no", restmap.get("partner_trade_no"));//商户转账订单号
			transferMap.put("payment_no", restmap.get("payment_no")); //微信订单号
			transferMap.put("payment_time", restmap.get("payment_time")); //微信支付成功时间
			
//			WebUtil.response(response,
//					WebUtil.packJsonp(callback,
//							JSON.toJSONString(new JsonResult(1, "转账成功", new ResponseData(null, transferMap)),
//									SerializerFeatureUtil.FEATURES)));
		}else {
			if (CollectionUtil.isNotEmpty(restmap)) {
				LOGGER.info("转账失败：" + restmap.get("err_code") + ":" + restmap.get("err_code_des"));
			}
//			WebUtil.response(response, WebUtil.packJsonp(callback, JSON
//					.toJSONString(new JsonResult(-1, "转账失败", new ResponseData()), SerializerFeatureUtil.FEATURES)));
		}
	}

}

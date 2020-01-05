package com.neteye.xinzhizhu.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.neteye.xinzhizhu.cache.J2CacheUtils;
//import com.platform.dao.ApiAddressMapper;
//import com.platform.dao.ApiCartMapper;
//import com.platform.dao.ApiCouponMapper;
import com.neteye.xinzhizhu.dao.ApiOrderGoodsMapper;
import com.neteye.xinzhizhu.dao.ApiOrderMapper;
import com.neteye.xinzhizhu.dao.CourseDao;
import com.neteye.xinzhizhu.domain.CourseDO;
//import com.platform.entity.AddressVo;
//import com.platform.entity.BuyGoodsVo;
//import com.platform.entity.CartVo;
//import com.platform.entity.CouponVo;
import com.neteye.xinzhizhu.entity.OrderGoodsVo;
import com.neteye.xinzhizhu.entity.OrderVo;
//import com.platform.entity.ProductVo;
import com.neteye.xinzhizhu.entity.UserVo;
import com.neteye.xinzhizhu.utils.CommonUtil;


@Service
public class ApiOrderService {
    @Autowired
    private ApiOrderMapper orderDao;
    
    @Autowired
    private  ApiOrderGoodsMapper orderGoods;
    
    @Autowired
    private CourseDao courseDao;

    public OrderVo queryObjectByOrderSn(String orderSn) {
        return orderDao.queryObjectByOrderSn(orderSn);
    }

    public OrderVo queryObject(Integer id) {
        return orderDao.queryObject(id);
    }


    public List<OrderVo> queryList(Map<String, Object> map) {
        return orderDao.queryList(map);
    }


    public int queryTotal(Map<String, Object> map) {
        return orderDao.queryTotal(map);
    }


    public void save(OrderVo order) {
        orderDao.save(order);
    }


    public int update(OrderVo order) {
        return orderDao.update(order);
    }


    public void delete(Integer id) {
        orderDao.delete(id);
    }


    public void deleteBatch(Integer[] ids) {
        orderDao.deleteBatch(ids);
    }

    @Transactional
    public Map<String, Object> submit(JSONObject jsonParam, UserVo loginUser) {
        Map<String, Object> resultObj = new HashMap<String, Object>();

        Integer courseId = jsonParam.getInteger("id");
        // * 获取要购买的商品
        CourseDO course = courseDao.get(courseId);
	    if (null == course) {
	      resultObj.put("errno", 400);
	      resultObj.put("errmsg", "请选择课程");
	      return resultObj;
	    }
	    if(!course.getStatus().equals(1)) {
		      resultObj.put("errno", 401);
		      resultObj.put("errmsg", "课程已经下架");
		      return resultObj;  	
	    }
	    
	    //查询有效的订单
	    Map map = new HashMap();
	    map.put("user_id", loginUser.getUserId());
	    map.put("pay_status", 2);
	    map.put("parent_id", courseId);
	    List<OrderVo> listOrder = orderDao.queryList(map);
	    if(listOrder!=null && listOrder.size()>0) {
		      resultObj.put("errno", 402);
		      resultObj.put("errmsg", "课程已经购买过，请勿重复购买");
		      return resultObj;  
	    }
	    
	    //
        Integer freightPrice = 0; //运费价格
        
        BigDecimal goodsTotalPrice = new BigDecimal(0.00);
        goodsTotalPrice = BigDecimal.valueOf(course.getCoursePrice());
        //获取订单使用的优惠券
        BigDecimal couponPrice = new BigDecimal(0.00);
        //订单价格计算
        BigDecimal orderTotalPrice = goodsTotalPrice.add(new BigDecimal(freightPrice)); //订单的总价

        BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice);  //减去其它支付的金额后，要实际支付的金额

        Long currentTime = System.currentTimeMillis() / 1000;

        //
        OrderVo orderInfo = new OrderVo();
        orderInfo.setOrder_sn(CommonUtil.generateOrderNumber()); //订单编号
        orderInfo.setUser_id(loginUser.getUserId());
        orderInfo.setParent_id(course.getCourseId());
        
        orderInfo.setFreight_price(freightPrice);
        //留言 变为商品的名称
        orderInfo.setPostscript(course.getCourseName());
        //使用的优惠券
        //orderInfo.setCoupon_id(couponId);
        orderInfo.setCoupon_price(couponPrice);  //优惠价格
        orderInfo.setAdd_time(new Date());
        orderInfo.setGoods_price(goodsTotalPrice); //商品价格
        orderInfo.setOrder_price(orderTotalPrice);//商品加运费
        orderInfo.setActual_price(actualPrice); //商品加运费减去优惠
        // 待付款
        orderInfo.setOrder_status(0);
        orderInfo.setShipping_status(0);
        orderInfo.setPay_status(0);
        orderInfo.setShipping_id(0);
        orderInfo.setShipping_fee(new BigDecimal(0));
        orderInfo.setIntegral(0);
        orderInfo.setIntegral_money(new BigDecimal(0));
        orderInfo.setOrder_type("4");
        
        //判断支付金额，如果金额小于等于0订单直接改为已经支付和发货
        if(orderTotalPrice.compareTo(BigDecimal.ZERO) == 0) 
        {
//	        orderInfo.setOrder_status(0);
//	        orderInfo.setShipping_status(0);
//	        orderInfo.setPay_status(0);
            orderInfo.setPay_status(2);
            orderInfo.setOrder_status(301);
            orderInfo.setShipping_status(2);
        }
        
        //开启事务，插入订单信息和订单商品
        orderDao.save(orderInfo);
        if (null == orderInfo.getId()) {
            resultObj.put("errno", 1);
            resultObj.put("errmsg", "订单提交失败");
            return resultObj;
        }
        //统计商品明细
		  OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
		  orderGoodsVo.setOrder_id(orderInfo.getId());
		  orderGoodsVo.setGoods_id(course.getCourseId());
		  orderGoodsVo.setGoods_sn(course.getCourseId()+"");
		  orderGoodsVo.setProduct_id(course.getCourseId());
		  orderGoodsVo.setGoods_name(course.getCourseName());
		  orderGoodsVo.setList_pic_url(course.getImageUrl());
		  orderGoodsVo.setMarket_price(new BigDecimal(course.getCoursePrice()));
		  orderGoodsVo.setRetail_price(new BigDecimal(course.getCoursePrice()));
		  orderGoodsVo.setNumber(1);
		  orderGoodsVo.setGoods_specifition_name_value(course.getCourseName());
		  orderGoodsVo.setGoods_specifition_ids(course.getCourseName());
		  orderGoods.save(orderGoodsVo);
        
        if(orderTotalPrice.compareTo(BigDecimal.ZERO) == 0) 
        {
	        resultObj.put("errno",999);
	        resultObj.put("errmsg", "订单提交成功");
        }
        else
        {
	        resultObj.put("errno", 0);
	        resultObj.put("errmsg", "订单提交成功");
        }
        //
        Map<String, OrderVo> orderInfoMap = new HashMap<String, OrderVo>();
        orderInfoMap.put("orderInfo", orderInfo);
        //
        resultObj.put("data", orderInfoMap);
        return resultObj;
    }


    @Transactional
    public Map<String, Object> submitdddddddd(JSONObject jsonParam, UserVo loginUser) {
        Map<String, Object> resultObj = new HashMap<String, Object>();

        Integer couponId = jsonParam.getInteger("couponId");
        String type = jsonParam.getString("type");
        String postscript = jsonParam.getString("postscript");
//        AddressVo addressVo = jsonParam.getObject("checkedAddress",AddressVo.class);
//        AddressVo addressVo = apiAddressMapper.queryObject(jsonParam.getInteger("addressId"));


        Integer freightPrice = 0; 
        BigDecimal goodsTotalPrice = new BigDecimal(0.00);
        // * 获取要购买的商品
//        List<CartVo> checkedGoodsList = new ArrayList<>();
//        BigDecimal goodsTotalPrice;
//        if (type.equals("cart")) {
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("user_id", loginUser.getUserId());
//            param.put("session_id", 1);
//            param.put("checked", 1);
//            checkedGoodsList = apiCartMapper.queryList(param);
//            if (null == checkedGoodsList) {
//                resultObj.put("errno", 400);
//                resultObj.put("errmsg", "请选择商品");
//                return resultObj;
//            }
//            //统计商品总价
//            goodsTotalPrice = new BigDecimal(0.00);
//            for (CartVo cartItem : checkedGoodsList) {
//                goodsTotalPrice = goodsTotalPrice.add(cartItem.getRetail_price().multiply(new BigDecimal(cartItem.getNumber())));
//            }
//        } else {
//            BuyGoodsVo goodsVo = (BuyGoodsVo) J2CacheUtils.get(J2CacheUtils.SHOP_CACHE_NAME, "goods" + loginUser.getUserId());
//            ProductVo productInfo = productService.queryObject(goodsVo.getProductId());
//            //计算订单的费用
//            //商品总价
//            goodsTotalPrice = productInfo.getRetail_price().multiply(new BigDecimal(goodsVo.getNumber()));
//
//            CartVo cartVo = new CartVo();
//            BeanUtils.copyProperties(productInfo, cartVo);
//            cartVo.setNumber(goodsVo.getNumber());
//            cartVo.setProduct_id(goodsVo.getProductId());
//            checkedGoodsList.add(cartVo);
//        }


        //获取订单使用的优惠券
        BigDecimal couponPrice = new BigDecimal(0.00);
//        CouponVo couponVo = null;
//        if (couponId != null && couponId != 0) {
//            couponVo = apiCouponMapper.getUserCoupon(couponId);
//            if (couponVo != null && couponVo.getCoupon_status() == 1) {
//                couponPrice = couponVo.getType_money();
//            }
//        }

        //订单价格计算
        BigDecimal orderTotalPrice = goodsTotalPrice.add(new BigDecimal(freightPrice)); //订单的总价

        BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice);  //减去其它支付的金额后，要实际支付的金额

        Long currentTime = System.currentTimeMillis() / 1000;

        //
        OrderVo orderInfo = new OrderVo();
        orderInfo.setOrder_sn(CommonUtil.generateOrderNumber());
        orderInfo.setUser_id(loginUser.getUserId());
        //收货地址和运费
//        orderInfo.setConsignee(addressVo.getUserName());
//        orderInfo.setMobile(addressVo.getTelNumber());
//        orderInfo.setCountry(addressVo.getNationalCode());
//        orderInfo.setProvince(addressVo.getProvinceName());
//        orderInfo.setCity(addressVo.getCityName());
//        orderInfo.setDistrict(addressVo.getCountyName());
//        orderInfo.setAddress(addressVo.getDetailInfo());
        //
        orderInfo.setFreight_price(freightPrice);
        //留言
        orderInfo.setPostscript(postscript);
        //使用的优惠券
        orderInfo.setCoupon_id(couponId);
        orderInfo.setCoupon_price(couponPrice);
        orderInfo.setAdd_time(new Date());
        orderInfo.setGoods_price(goodsTotalPrice);
        orderInfo.setOrder_price(orderTotalPrice);
        orderInfo.setActual_price(actualPrice);
        // 待付款
        orderInfo.setOrder_status(0);
        orderInfo.setShipping_status(0);
        orderInfo.setPay_status(0);
        orderInfo.setShipping_id(0);
        orderInfo.setShipping_fee(new BigDecimal(0));
        orderInfo.setIntegral(0);
        orderInfo.setIntegral_money(new BigDecimal(0));
        if (type.equals("cart")) {
            orderInfo.setOrder_type("1");
        } else {
            orderInfo.setOrder_type("4");
        }

        //开启事务，插入订单信息和订单商品
        orderDao.save(orderInfo);
        if (null == orderInfo.getId()) {
            resultObj.put("errno", 1);
            resultObj.put("errmsg", "订单提交失败");
            return resultObj;
        }
        //统计商品总价
        List<OrderGoodsVo> orderGoodsData = new ArrayList<OrderGoodsVo>();
//        for (CartVo goodsItem : checkedGoodsList) {
//            OrderGoodsVo orderGoodsVo = new OrderGoodsVo();
//            orderGoodsVo.setOrder_id(orderInfo.getId());
//            orderGoodsVo.setGoods_id(goodsItem.getGoods_id());
//            orderGoodsVo.setGoods_sn(goodsItem.getGoods_sn());
//            orderGoodsVo.setProduct_id(goodsItem.getProduct_id());
//            orderGoodsVo.setGoods_name(goodsItem.getGoods_name());
//            orderGoodsVo.setList_pic_url(goodsItem.getList_pic_url());
//            orderGoodsVo.setMarket_price(goodsItem.getMarket_price());
//            orderGoodsVo.setRetail_price(goodsItem.getRetail_price());
//            orderGoodsVo.setNumber(goodsItem.getNumber());
//            orderGoodsVo.setGoods_specifition_name_value(goodsItem.getGoods_specifition_name_value());
//            orderGoodsVo.setGoods_specifition_ids(goodsItem.getGoods_specifition_ids());
//            orderGoodsData.add(orderGoodsVo);
//            apiOrderGoodsMapper.save(orderGoodsVo);
//        }

        //清空已购买的商品
//        apiCartMapper.deleteByCart(loginUser.getUserId(), 1, 1);
        resultObj.put("errno", 0);
        resultObj.put("errmsg", "订单提交成功");
        //
        Map<String, OrderVo> orderInfoMap = new HashMap<String, OrderVo>();
        orderInfoMap.put("orderInfo", orderInfo);
        //
        resultObj.put("data", orderInfoMap);
        // 优惠券标记已用
//        if (couponVo != null && couponVo.getCoupon_status() == 1) {
//            couponVo.setCoupon_status(2);
//            apiCouponMapper.updateUserCoupon(couponVo);
//        }

        return resultObj;
    }

}

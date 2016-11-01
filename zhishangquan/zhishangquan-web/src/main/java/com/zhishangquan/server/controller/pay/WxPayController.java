package com.zhishangquan.server.controller.pay;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhishangquan.server.config.WeixinConfig;
import com.zhishangquan.server.controller.BaseController;
//import com.zhishangquan.server.domain.Order;
import com.zhishangquan.server.pay.PayParamBean;
import com.zhishangquan.server.pay.PayStatusBean;
import com.zhishangquan.server.response.pay.WxPayNotifyResponseBean;
import com.zhishangquan.server.service.pay.WxPayService;
import com.zhishangquan.server.util.ResponseEntity;
import com.zhishangquan.server.util.ResponseEntityUtil;
import com.zhishangquan.server.util.Signature;
import com.zhishangquan.server.util.XmlUtil;
import com.zhishangquan.server.util.acs.ACS;

/**
 * 微信支付管理
 * sdf：以下接口只是对app，还需要退款申请接口，以及对网页支付接口
 * 
 */
@Api(value = "支付管理", produces = "application/json")
@Transactional(readOnly = false)
@RestController
@RequestMapping("/wxpay")
public class WxPayController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private WeixinConfig weixinConfig;
    @Resource
    private WxPayService wxPayService;

    @ApiOperation(value = "微信支付开始(获取预支付交易会话标识)", notes = "微信支付开始(获取预支付交易会话标识)", consumes = "application/json")
    @ACS(allowAnonymous = false)
    @RequestMapping(value = "/initWxpayForApp", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> wxPayStart(@PathVariable("orderNo") String orderNo, HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("order_no", orderNo);
        PayParamBean paramBean = new PayParamBean();
        paramBean.setOrder_no(orderNo);
//        Order order = orderService.payStartProcess(orderNo);
//        paramBean.setAmount((int) (order.getTotalprice() * 100));//将元化为分
        Map<String, Object> payinfoMap = null;
        try {
            payinfoMap = wxPayService.genPrepayMap(paramBean);
            responseMap.put("exp", payinfoMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntityUtil.success(responseMap);
    }

    @ApiOperation(value = "微信支付结果查询", notes = "微信支付结果查询(order_status值大于1表示已支付  订单状态(1:未支付,2:已支付,3:未发货,4:已发货,5:已完成))", consumes = "application/json")
    @ACS(allowAnonymous = false)
    @RequestMapping(value = "/wxPayResultQuery/{orderNo}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> wxPayResultQuery(@PathVariable("orderNo") String orderNo,
            HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("order_no", orderNo);
        PayStatusBean bean;
		try {
			bean = this.wxPayService.wxPayQuery(orderNo);
			responseMap.put("order_status", bean.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}

        return ResponseEntityUtil.success(responseMap);
    }

    /**
     * 微信支付回调接口
     * @Date   2016年3月19日,上午2:15:08
     * @author YYF
     * @param bean
     * @param request
     * @return
     * @return ResponseEntity<ProductDetailResponseBean>
     * @throws
     */
    @ApiOperation(value = "微信支付回调接口", notes = "微信支付回调接口(由微信调用，app端无需调用)", consumes = "application/json")
    @ACS(allowAnonymous = true)
    @RequestMapping(value = "/wxPayNotify", method = RequestMethod.POST)
    public void wxPayNotify(HttpServletRequest request, HttpServletResponse response) {
        logger.info("微信支付回调通知开始");
        try {
            //获取头部所有信息
//            Enumeration<?> headerNames = request.getHeaderNames();
//            while (headerNames.hasMoreElements()) {
//                String key = (String) headerNames.nextElement();
//                String value = request.getHeader(key);
//                logger.info(key + " " + value);
//            }
            //获得 http body 内容
            BufferedReader reader = request.getReader();
            StringBuffer buffer = new StringBuffer();
            String string;
            while ((string = reader.readLine()) != null) {
                buffer.append(string);
            }
            reader.close();
            logger.info("微信支付回调内容：" + buffer.toString());

            //解析成回调对象
            WxPayNotifyResponseBean responseBean = XmlUtil.toObject(buffer.toString(), WxPayNotifyResponseBean.class);
            String wxNotifyReturn = null;
            //验证回调内容
            if (responseBean.getReturn_code().equals("SUCCESS")) {
                String sign = responseBean.getSign();
                logger.info("微信回调返回的签名：" + sign);
                responseBean.setSign(""); //sign不参与运算 先取出
                String mySign = Signature.getSign(responseBean, "key=" + weixinConfig.getApi_key()).toUpperCase();
                logger.info("微信回调接口本地验证的签名：" + mySign);
                if (sign.equals(mySign)) {
                    //处理业务流程
//                    String orderNo = responseBean.getOut_trade_no();//订单号
//                    String resultCode = responseBean.getResult_code();//业务结果 SUCCESS/FAIL

//                    Map<String, String> map = new HashMap<>();
                    //TODO 处理结果
//                    orderService.paySuccessProcess(orderNo, resultCode, PayType.WEIXIN, map);

                    //返回成功
                    wxNotifyReturn = "<xml><return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg></xml>";
                } else {
                    wxNotifyReturn = "<xml><return_code><![CDATA[FAIL]]></return_code>"
                            + "<return_msg><![CDATA[sign error]]></return_msg></xml>";
                }
            }
            logger.info("微信支付回调返回内容：" + wxNotifyReturn);
            PrintWriter out = response.getWriter();
            out.println(wxNotifyReturn);
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("微信支付回调通知异常:" + ex.toString());
        }

    }
}

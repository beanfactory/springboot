package com.zhishangquan.server.controller.pay;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
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

import com.zhishangquan.server.controller.BaseController;
//import com.zhishangquan.server.domain.Order;
import com.zhishangquan.server.pay.PayParamBean;
import com.zhishangquan.server.pay.PayStatusBean;
import com.zhishangquan.server.service.pay.AlipayService;
import com.zhishangquan.server.util.ResponseEntity;
import com.zhishangquan.server.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 支付宝支付管理
 * sdf：以下接口只是对app，还需要退款申请接口，以及对网页扫码支付接口
 * 
 * @Date 2016年3月12日,上午12:46:52
 * @version v1.0
 * @author YYF
 */
@Api(value = "支付管理", produces = "application/json")
@Transactional(readOnly = false)
@RestController
@RequestMapping("/alipay")
public class AliPayController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Resource
//    private OrderService orderService;

    @Resource
    private AlipayService alipayService;

    @ApiOperation(value = "支付宝支付开始(获取预支付交易会话标识)", notes = "支付宝支付开始(获取预支付交易会话标识)", consumes = "application/json")
    @RequestMapping(value = "/initAlipayForApp", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> aliPayStart(@PathVariable("orderNo") String orderNo,
            HttpServletRequest request) {

        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("order_no", orderNo);

//        Order order = orderService.payStartProcess(orderNo);
        PayParamBean paramBean = new PayParamBean();
        paramBean.setOrder_no(orderNo);
//        paramBean.setTotal_fee(order.getTotalprice());//单位:元
        paramBean.setSubject(orderNo);
        paramBean.setBody(orderNo);
        try {
            String payUrlString = alipayService.getPayUrlString(paramBean);
            responseMap.put("exp", payUrlString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntityUtil.success(responseMap);
    }

    @ApiOperation(value = "支付宝支付结果查询", notes = "支付宝支付结果查询(order_status值大于1表示已支付  订单状态(1:未支付,2:已支付))", consumes = "application/json")
    @RequestMapping(value = "/aliPayResultQuery", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> aliPayResultQuery(@PathVariable("orderNo") String orderNo,
            HttpServletRequest request){

        Map<String, Object> responseMap = new HashMap<String, Object>();
        responseMap.put("order_no", orderNo);
        PayStatusBean bean;
		try {
			bean = this.alipayService.aliPayQuery(orderNo);
			responseMap.put("order_status", bean.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}

        return ResponseEntityUtil.success(responseMap);
    }

    
    /**
     * 支付宝回调
     * @Date   2016年4月6日,上午1:43:53
     * @author YYF
     * @param request
     * @param response
     * @return void
     * @throws
     */
    @ApiOperation(value = "支付宝支付回调接口", notes = "支付宝支付回调接口(由支付宝调用，app端无需调用)", consumes = "application/json")
    @RequestMapping(value = "/aliPayNotify", method = RequestMethod.POST)
    public void aliPayNotify(HttpServletRequest request, HttpServletResponse response) {
        logger.info("支付宝回调通知开始＝＝＝＝＝＝＝》");
        //获取支付宝POST过来反馈信息
        try {
            Map<String, String> params = new HashMap<String, String>();
            Map<String, String[]> requestParams = request.getParameterMap();
            //处理结果
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }

            //商户订单号
//            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            PrintWriter out = response.getWriter();
            //两个验证成功 处理业务逻辑
            if (alipayService.verify(params)) { //验证成功
                logger.info("支付宝回调验证成功");
                if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                    //处理业务流程
                    Map<String, String> map = new HashMap<>();
                    map.put("trade_no", trade_no);
                    //这里调用业务逻辑  更新订单支付状态  TODO...
//                    orderService.paySuccessProcess(out_trade_no, "SUCCESS", PayType.ALI, map);
                }
                out.println("success");

            } else {
                logger.info("支付宝回调返回失败");
                out.println("fail");
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("支付宝支付回调通知异常:" + ex.toString());
        }

    }

}

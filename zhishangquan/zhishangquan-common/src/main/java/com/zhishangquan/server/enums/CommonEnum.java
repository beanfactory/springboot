package com.zhishangquan.server.enums;

/**
 *  公共枚举
 * @Date 2016年3月1日,上午1:51:38
 * @version v1.0
 * @author YYF
 */
public class CommonEnum {

    /**
     * 公用状态:1:正常 2:冻结
     * @Date 2016年3月12日,上午12:07:56
     * @version v1.0
     * @author YYF
     */
    public enum CommonStatus {
        NORMAL(1, "正常"), FROZEN(2, "冻结");
        public int code;
        public String label;

        private CommonStatus(int code, String label) {
            this.code = code;
            this.label = label;
        }

        public static boolean isNormal(Integer status) {
            if (status != null && status.intValue() == NORMAL.code) {
                return true;
            }
            return false;
        }
    }

  
    /**
     * 订单状态(1:未支付,2:已支付,3:未发货,4:已发货,5:已完成)
     * @Date 2016年3月14日,上午3:11:32
     * @version v1.0
     * @author YYF
     */
    public enum OrderStatus {
        WZF(1, "未支付"), YZF(2, "已支付"), YWC(3, "已完成");
        public int code;
        public String label;

        private OrderStatus(int code, String label) {
            this.code = code;
            this.label = label;
        }
    }

      
    
    public enum PayType {
        WEIXIN(1, "微信"), ALI(2, "阿里");
        public int code;
        public String label;

        private PayType(int code, String label) {
            this.code = code;
            this.label = label;
        }
    }
    

}

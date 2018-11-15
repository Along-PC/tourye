package com.tourye.zhong.beans;

/**
 * Created by longlongren on 2018/11/5.
 * <p>
 * introduce:用户账户实体
 */

public class UserAccountBean {

    /**
     * status : 0
     * timestamp : 1541395714
     * data : {"coin":0,"fund":0,"coupon":0}
     */

    private int status;
    private int timestamp;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * coin : 0
         * fund : 0
         * coupon : 0
         */

        private int coin;
        private int fund;
        private int coupon;

        public int getCoin() {
            return coin;
        }

        public void setCoin(int coin) {
            this.coin = coin;
        }

        public int getFund() {
            return fund;
        }

        public void setFund(int fund) {
            this.fund = fund;
        }

        public int getCoupon() {
            return coupon;
        }

        public void setCoupon(int coupon) {
            this.coupon = coupon;
        }
    }
}

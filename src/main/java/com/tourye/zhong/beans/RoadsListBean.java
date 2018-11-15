package com.tourye.zhong.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by longlongren on 2018/10/29.
 * <p>
 * introduce:路线列表实体
 */

public class RoadsListBean implements Serializable{

    /**
     * status : 0
     * timestamp : 1540790276
     * data : [{"id":1,"name":"丝路108","logo":null,"image":"https://ro-test.oss-cn-beijing.aliyuncs.com/image_upload/2018-09-03-10-41-26/Qh59OB-1","abstract":"去戈壁，发现更强大的自己  敦煌戈壁·丝绸之路","content":"<p>行走在敦煌，丝绸之路<\/p>\r\n<p>敦煌，一方神秘圣土。有&ldquo;一轮圆日半边天&rdquo;的静谧,有&ldquo;大漠孤烟直&rdquo;的空荡，多少人一生必去清单上的一项。穿越戈壁、湿地、河床、红柳林、盐碱地、黑戈壁、雅丹魔鬼城、荒漠、绿洲、玉门关、沙漠多种地形，探寻千年古城的神秘，感受历史文化的厚重。<\/p>\r\n<p>来到戈壁，从放下现代社会文明的习惯、开始适应大自然，到在路上的坚持，到面对坎坷困难的突破和对审视自己与自然关系后的自我突破，再到实现自我潜能的发掘与更强大自己的发现。用一路的实践，实现古丝绸商路开拓精神与商界精神的高效契合。<\/p>\r\n<p>&nbsp;<\/p>","activity_count":79,"applicant_count":194},{"id":2,"name":"茶马108","logo":null,"image":"https://ro-test.oss-cn-beijing.aliyuncs.com/image_upload/2018-09-03-10-43-05/aBsL2A-1","abstract":"走好每一步  丽江·茶马古道","content":"<p>行走在丽江，茶马古道<\/p>\r\n<p>&ldquo;古道悠悠韵尚留，马帮铃响过芳丘&rdquo;，青石板上被马蹄践踏出的深浅痕迹犹在，那些置生死于不顾的非凡经历和无畏精神，仍在吸引我们去探寻、发现和感知。跨越湿地、丛林、雪山、古道、高山草甸、纳西族古村落、玉湖水库，平均海拔2300&mdash;2800米，顺着古道，从脚下的路走向心里的路。<\/p>\r\n<p>山高路险，然而沿途壮丽的自然景观却可以激发人潜在的勇气、力量和忍耐，使人的灵魂得到淬炼，使企业核心团队获取精神图腾，传承古道普济的价值精神，追求卓越，勇往直前。这是熔炼协作、超越自我，激发潜能的&mdash;&mdash;茶马古道。<\/p>\r\n<p>&nbsp;<\/p>","activity_count":11,"applicant_count":4},{"id":3,"name":"沙漠108","logo":null,"image":"https://ro-test.oss-cn-beijing.aliyuncs.com/image_upload/2018-09-03-10-50-43/D8VgpA-1","abstract":"若同行，必不负 库布其·沙漠","content":"<p><span style=\"font-family: '微软雅黑','sans-serif';\">行走在库布其，金黄沙漠<\/span><\/p>\r\n<p><span style=\"font-family: '微软雅黑','sans-serif';\">库布其&mdash;&mdash;&ldquo;弓上的弦&rdquo;，这里有高低起伏的的浩瀚流沙，这里有绿洲再现、河流潺潺，这里有抬头仰望的星瀚苍穹，这里还有不一样的体验和无可估量的收获。<\/span><\/p>\r\n<p><span style=\"font-size: 14px; font-family: '微软雅黑','sans-serif';\">从无数道沙子涌起的褶皱如凝固的浪涛中行走，途经沙漠、沙丘、绿洲，深入沙漠无人区，不仅有坚韧不拔的毅力，还能镇定自若，沉着冷静，这种极致的精神正是人们需要历练沙漠精神。<\/span><\/p>\r\n<p>&nbsp;<\/p>","activity_count":9,"applicant_count":0},{"id":6,"name":"草原108","logo":null,"image":"https://ro-test.oss-cn-beijing.aliyuncs.com/image_upload/2018-09-03-10-51-02/wV4R7i-1","abstract":"走天地辽阔，壮雄心自在  呼伦贝尔·大草原","content":"<p class=\"MsoNormal\"><span style=\"font-family: '微软雅黑','sans-serif'; mso-bidi-font-family: 微软雅黑;\">走天地辽阔，壮雄心自在，望断草尽处。呼伦贝尔大草原缘起蒙古人民的精神特征是由草原生活的点点滴滴积淀而成的，主要表现为&ldquo;天之骄子&rdquo;勇敢、热情、爽直、豪迈的性格，途经草原、绿洲，领略游牧民族的别样风情，在草原路上挑战自我，超越极限！<\/span><\/p>\r\n<p class=\"MsoNormal\"><span style=\"font-family: '微软雅黑','sans-serif'; mso-bidi-font-family: 微软雅黑;\">行走在呼伦贝尔，仿佛将爱放飞在碧草蓝天之间。一望无际的草原孕育着蒙古人民独特精神：勇敢、热情、爽直，辉映出&ldquo;天之骄子&rdquo;的豪迈。呼伦贝尔草原千年的文化印记随我们一并前行，秉承初心，相互扶持。<\/span><\/p>","activity_count":5,"applicant_count":0}]
     */

    private int status;
    private int timestamp;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * name : 丝路108
         * logo : null
         * image : https://ro-test.oss-cn-beijing.aliyuncs.com/image_upload/2018-09-03-10-41-26/Qh59OB-1
         * abstract : 去戈壁，发现更强大的自己  敦煌戈壁·丝绸之路
         * content : <p>行走在敦煌，丝绸之路</p>
         <p>敦煌，一方神秘圣土。有&ldquo;一轮圆日半边天&rdquo;的静谧,有&ldquo;大漠孤烟直&rdquo;的空荡，多少人一生必去清单上的一项。穿越戈壁、湿地、河床、红柳林、盐碱地、黑戈壁、雅丹魔鬼城、荒漠、绿洲、玉门关、沙漠多种地形，探寻千年古城的神秘，感受历史文化的厚重。</p>
         <p>来到戈壁，从放下现代社会文明的习惯、开始适应大自然，到在路上的坚持，到面对坎坷困难的突破和对审视自己与自然关系后的自我突破，再到实现自我潜能的发掘与更强大自己的发现。用一路的实践，实现古丝绸商路开拓精神与商界精神的高效契合。</p>
         <p>&nbsp;</p>
         * activity_count : 79
         * applicant_count : 194
         */

        private int id;
        private String name;
        private Object logo;
        private String image;
        @SerializedName("abstract")
        private String abstractX;
        private String content;
        private int activity_count;
        private int applicant_count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getLogo() {
            return logo;
        }

        public void setLogo(Object logo) {
            this.logo = logo;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getActivity_count() {
            return activity_count;
        }

        public void setActivity_count(int activity_count) {
            this.activity_count = activity_count;
        }

        public int getApplicant_count() {
            return applicant_count;
        }

        public void setApplicant_count(int applicant_count) {
            this.applicant_count = applicant_count;
        }
    }
}

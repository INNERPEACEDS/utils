package com.wgb.utils.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author INNERPEACE
 * @date 2019/1/7 9:48
 **/
@Slf4j
public class A {
    public A a;

    public String value;

    @Override
    public boolean equals(Object object) {
        if (object instanceof A) {
            if (((A) object).value.equals(this.value)) {
                return true;
            }
        }
        return false;
    }

    A() {
//        a = new A();
    }
    public static void main(String[] args) {
       /* A a1 = new A();
        a1.value = "2";
        A a2 = new A();
        a2.value = "2";
        log.info("{},{}比较结果：{}",  a1.hashCode(), a2.hashCode(), a1.equals(a2));*/
        String amtRule = "{'id':3,'amt':1,'rule':'lt','persent':80}";
        JSONObject json = JSONObject.parseObject(amtRule);
        int id = json.getIntValue("id");
        int amt = json.getIntValue("amt");
        int persent = json.getIntValue("persent");
        String rule = json.getString("rule");
       log.info("id:{},amt:{},persent:{},rule:{}", id, amt, persent, rule);

       String json1 = "{}";
        if (json != null) {
            String[] split = json1.split("\\|");
//            split
            for (int i = 0; i < split.length; i++) {
//                System.out.print(split[i]);
                jsonToObject(split[i]);
            }

        }
    }

    public static String jsonToObject(String amountRuleJson) {
        String amountRuleStr = amountRuleJson.substring(1, amountRuleJson.length() - 1);
        log.info("amountRuleStr: {}", amountRuleStr);
        return null;
    }
}

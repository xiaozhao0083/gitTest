package com.yc.springTest2.user;

import com.yc.springTest2.system.ObjFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-26 09:01
 */
@Component("bmiFilter")
public class StudentBmiFilter implements ObjFilter {
    private Logger log = LoggerFactory.getLogger(StudentBmiFilter.class);

    @Override
    public boolean doFilter(Object obj) {
        if (obj == null) {
            log.error("待测数据异常" + obj.toString());
            return false;
        }
        if (obj instanceof Student == false) {
            log.error("待测数据异常" + obj.toString());
            return false;
        }
        Student s = (Student) obj;
        if (s.getName() == null || "".equalsIgnoreCase(s.getName())) {
            log.error("名字异常" + obj.toString());
            return false;
        }
        if (s.getHeight() < 100 || s.getHeight() > 240) {
            log.error("身高异常" + obj.toString());
            return false;
        }
        if (s.getWeight() < 30 || s.getWeight() > 500) {
            log.error("体重异常" + obj.toString());
            return false;
        }
        return true;
    }
}

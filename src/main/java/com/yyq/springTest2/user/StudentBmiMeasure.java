package com.yyq.springTest2.user;

import com.yyq.springTest2.system.Measure;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-26 10:29
 */
@Component("bmiMeasure")
public class StudentBmiMeasure implements Measure {
    private Logger log = Logger.getLogger(StudentBmiMeasure.class);

    @Override
    public double doMeasure(Object obj) {
        if (obj == null) {
            log.error("待测数据异常" + obj.toString());
           throw new RuntimeException("待测数据异常");
        }
        if (obj instanceof Student == false) {
            log.error("待测数据异常" + obj.toString());
            throw new RuntimeException("待测数据异常");
        }
        Student s = (Student) obj;
        return s.getWeight()/(Math.pow(s.getHeight(), 2))*10000;
    }
}

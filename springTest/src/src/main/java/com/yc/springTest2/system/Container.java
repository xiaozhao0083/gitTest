package com.yc.springTest2.system;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-26 08:51
 */
@Component
public class Container<T> {
    private List<T> objs = new ArrayList<T>();

    @Resource(name = "bmiMeasure")
    private Measure measure;
    @Resource(name = "bmiFilter")
    private ObjFilter filter;

    private T max;
    private T min;
    private double avg;
    private double sum;

    public void add(T t) {
        if (filter != null) {
            if (!filter.doFilter(t)) {
                return;
            }
        }
        objs.add(t);
        if (objs.size() == 1) {
            max = t;
            min = t;
        } else {
            double val = measure.doMeasure(t);
            System.out.println(val);
            double maxval = measure.doMeasure(max);
            double minval = measure.doMeasure(min);
            if (val > maxval) {
                max = t;
            }
            if (val < minval) {
                min = t;
            }
        }
        sum+=measure.doMeasure(t);
        avg=sum/objs.size();
    }

    public void clearAll() {
        objs = new ArrayList<T>();
        measure = null;
        filter = null;
        max = null;
        min = null;
        avg = 0;
        sum=0;
    }

    public T getMax() {
        return max;
    }

    public T getMin() {
        return min;
    }

    public double getAvg() {
        return avg;
    }

    public int size() {
        int size = objs.size();
        return size;
    }
}

package com.yc;


import com.yc.biz.AccountBiz;
import com.yc.biz.AccountBizImpl;
import com.yc.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App1 {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		AccountBiz ab = context.getBean(AccountBizImpl.class);
		//功能 => 用例
//		 ab.addAccount(1, 99);
	}
}

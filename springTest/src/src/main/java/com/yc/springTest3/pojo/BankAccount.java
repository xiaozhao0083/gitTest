package com.yc.springTest3.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: servlet
 * description:
 * @author:yyq
 * @create: 2023-05-05 19:00
 */
@Data
public class BankAccount implements Serializable {
    private  int id;
    private  double money;
}

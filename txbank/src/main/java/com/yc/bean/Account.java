package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-08-03 09:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account implements Serializable {
    private int accountid;
    private double money;
}

package com.yc.springTest3.dataSource;

import lombok.Data;

import java.sql.Connection;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-26 21:07
 */
@Data
public class MyConnection {
    public  boolean status;
    public  Connection con;
}

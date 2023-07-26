package com.yyq.springTest2.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-26 09:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private double height;
    private double weight;
}

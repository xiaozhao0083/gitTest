package com.yc.dao;

import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.config.SpringConfig;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@Log4j2
public class OpRecordDaoJdbcTemplateImplTest extends TestCase {

    @Autowired
    private OpRecordDao opRecordDao;

    @Test
    public void insertOpRecord() {
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(1);
        opRecord.setOpmoney(10);
        opRecord.setOptime("2023-08-03 11:47:09");
        opRecord.setOptype(OpType.DEPOSITE);
        opRecordDao.insertOpRecord(opRecord);
    }

    @Test
    public void findOpRecord() {
        List<OpRecord> list = opRecordDao.findOpRecord(1);
        log.info(list);
        for (OpRecord opRecord : list) {
            System.out.println(opRecord.getId() + "\t" + opRecord.getAccountid() + "\t" + opRecord.getOpmoney());
        }
    }

    @Test
    public void testFindOpRecord() {
        List<OpRecord> list = opRecordDao.findOpRecord(1, "deposit");
        log.info(list);
        for (OpRecord opRecord : list) {
            System.out.println(opRecord.getId() + "\t" + opRecord.getAccountid() + "\t" + opRecord.getOpmoney());
        }
    }

    @Test
    public void testFindOpRecord1() {
    }
}
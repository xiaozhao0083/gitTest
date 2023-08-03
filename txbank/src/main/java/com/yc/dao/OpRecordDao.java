package com.yc.dao;

import com.yc.bean.OpRecord;

import java.util.List;

public interface OpRecordDao {

    public void insertOpRecord(OpRecord opRecord);

    /**
     * 查询一个用户所有日志，根据时间排序
     * @param accountid
     * @return
     */
    public List<OpRecord> findOpRecord(int accountid);

    /**
     * 查询一个用户,opType类型的操作日志，根据时间排序
     * @param accountid
     * @param opType
     * @return
     */
    public List<OpRecord> findOpRecord(int accountid, String opType);

    /**
     * 查询一个用户所有日志，根据时间排序
     * @param opRecord
     * @return
     */
    public List<OpRecord> findOpRecord(OpRecord opRecord);

}

package com.mhy.crawl.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by mhy on 2017/5/2.
 */
public interface DDLDao {

    public void truncateTable(@Param("tableName") String tableName);

}

package com.chinaredstar.serivce.dao.impl;

import com.chinaredstar.common.Pagination;
import com.chinaredstar.common.Search;
import com.chinaredstar.perseus.db.DynamicSqlSessionTemplate;
import com.chinaredstar.po.TestUserPo;
import com.chinaredstar.serivce.common.Constant;
import com.chinaredstar.serivce.dao.TestUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CC on 16/3/29.
 */
@Repository("testUserDao")
public class TestUserDaoImpl implements TestUserDao {


    private static Logger logger = LoggerFactory.getLogger(TestUserDaoImpl.class);

    @Autowired
    private DynamicSqlSessionTemplate sqlSessionTemplate;

    public TestUserPo getTestUser(int id) {
        return sqlSessionTemplate.selectOne("selectTestUserByID", id);
    }

    public Pagination<TestUserPo> listTestUser(Search search) {
        search.setPageSize(5);
        Pagination pagination = new Pagination();
        try {
            int count = sqlSessionTemplate.selectOne("listTestUserCount", search);
            pagination.setCount(count);
            pagination.setPageNo(search.getPage());
            pagination.setPageSize(search.getPageSize());
            List<TestUserPo> list = sqlSessionTemplate.selectList("listTestUser", search);
            pagination.setRecords(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pagination;
    }

    public boolean addTestUser(TestUserPo testUserPo) {
        try {
            sqlSessionTemplate.insert("insertTestUser", testUserPo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delTestUser(int id) {
        try {
            sqlSessionTemplate.delete("deleteTestUser", id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editTestUser(TestUserPo testUserPo) {
        try {
            sqlSessionTemplate.update("updateTestUser", testUserPo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

package com.chinaredstar.serivce.dao;

import com.chinaredstar.common.Pagination;
import com.chinaredstar.common.Search;
import com.chinaredstar.po.TestUserPo;

import java.util.Map;

/**
 * Created by CC on 16/3/29.
 */

public interface TestUserDao {

    TestUserPo getTestUser(int id);

    Pagination<TestUserPo> listTestUser(Search search);

    boolean addTestUser(TestUserPo testUserPo);

    boolean delTestUser(int id);

    boolean editTestUser(TestUserPo testUserPo);
}

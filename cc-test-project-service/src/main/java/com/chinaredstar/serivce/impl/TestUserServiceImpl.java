package com.chinaredstar.serivce.impl;

import com.chinaredstar.api.TestUserService;
import com.chinaredstar.common.Pagination;
import com.chinaredstar.common.Search;
import com.chinaredstar.dto.TestUserDto;
import com.chinaredstar.po.TestUserPo;
import com.chinaredstar.serivce.dao.TestUserDao;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by CC on 16/3/24.
 */
public class TestUserServiceImpl implements TestUserService {

    @Resource
    TestUserDao testUserDao;

    public TestUserDto getTestUser(int id) {
        TestUserDto testUserDto = new TestUserDto();
        TestUserPo _testUserPo = testUserDao.getTestUser(id);
        testUserDto.setId(_testUserPo.getId());
        testUserDto.setName(_testUserPo.getName());
        testUserDto.setMobile(_testUserPo.getMobile());
        return testUserDto;
    }

    public Pagination<TestUserPo> listTestUser(Search search) {
        try {
            Pagination<TestUserPo> pagination = testUserDao.listTestUser(search);
            return pagination;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addTestUser(TestUserPo testUserPo) {
        return testUserDao.addTestUser(testUserPo);
    }

    public boolean delTestUser(int id) {
        return testUserDao.delTestUser(id);
    }

    public boolean editTestUser(TestUserPo testUserPo) {
        return testUserDao.editTestUser(testUserPo);
    }
}

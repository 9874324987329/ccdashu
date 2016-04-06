package com.chinaredstar.api;

import com.chinaredstar.common.Pagination;
import com.chinaredstar.common.Search;
import com.chinaredstar.dto.TestUserDto;
import com.chinaredstar.po.TestUserPo;

import java.util.List;
import java.util.Map;

/**
 * Created by ccdashu on 16/3/24.
 */
public interface TestUserService {

    TestUserDto getTestUser(int id);

    Pagination<TestUserPo> listTestUser(Search search);

    boolean addTestUser(TestUserPo testUserPo);

    boolean delTestUser(int id);

    boolean editTestUser(TestUserPo testUserPo);


}

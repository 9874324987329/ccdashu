package com.chinaredstar.controller;

import com.chinaredstar.api.TestUserService;
import com.chinaredstar.common.Pagination;
import com.chinaredstar.common.Search;
import com.chinaredstar.dto.TestUserDto;
import com.chinaredstar.entity.Result;
import com.chinaredstar.entity.ResultCode;
import com.chinaredstar.po.TestUserPo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by ccdashu on 16/3/25.
 */
@Controller
@RequestMapping("/api")
@Api(value = "api", description = "用户相关的接口")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    TestUserService testUserService;

    @ApiOperation(value = "获取用户分页", notes = "用户信息相关")
    @RequestMapping(value = "listTestUser", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result listTestUser(@RequestParam("name") String name, @RequestParam("page") Integer page) {
        Result result = new Result();
        try {
            Search search = new Search();
            search.setPage(page);
            search.setOrderBy("1");
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("name", name);
            search.setCondition(param);
            Pagination<TestUserPo> pagination = testUserService.listTestUser(search);
            result.setValue(pagination);
            result.setCode(ResultCode.C200.code);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }


    @ApiOperation(value = "获取单用户", notes = "用户信息相关")
    @RequestMapping(value = "getTestUser/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result getTestUser(@PathVariable("id") int id) {
        Result result = new Result();
        try {
            TestUserDto testUserDto = testUserService.getTestUser(id);
            result.setValue(testUserDto);
            result.setCode(ResultCode.C200.code);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "新增用户", notes = "用户信息相关")
    @RequestMapping(value = "addTestUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result addTestUser(@RequestBody TestUserPo testUserPo) {
        Result result = new Result();
        try {
            boolean res = testUserService.addTestUser(testUserPo);
            result.setValue(res);
            result.setCode(ResultCode.C200.code);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "删除单用户", notes = "用户信息相关")
    @RequestMapping(value = "delTestUser/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result delTestUser(@PathVariable("id") int id) {
        Result result = new Result();
        try {
            boolean res = testUserService.delTestUser(id);
            result.setValue(res);
            result.setCode(ResultCode.C200.code);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "编辑单用户", notes = "用户信息相关")
    @RequestMapping(value = "editTestUser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result editTestUser(@Valid @RequestBody TestUserPo testUserPo,
                               BindingResult bindingResult) {
        Result result = new Result();
        if (bindingResult.hasErrors()) {
            System.out.println("错误...." + bindingResult.getFieldError().getField());
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();

            result.setValue(errorMsg);
            result.setCode(ResultCode.C422.code);
            return result;
        }
        try {
            boolean res = testUserService.editTestUser(testUserPo);
            result.setValue(res);
            result.setCode(ResultCode.C200.code);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return result;
    }


}

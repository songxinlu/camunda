package com.example.camunda.controller;

import com.example.camunda.entity.ZzUser;
import com.example.camunda.facade.base.ZzUserFacade;
import com.example.camunda.vo.ZzUserVO;
import io.swagger.annotations.Api;
import org.camunda.bpm.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author zhoupeng
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理API", tags = {"用户管理API"})
public class UserController {


    @Autowired
    private ZzUserFacade zzUserFacade;

    @Autowired
    private IdentityService identityService;


    @PostMapping(value = "/add")
    public Boolean add(@RequestBody ZzUser user) {
        return zzUserFacade.save(user);
    }


    @PostMapping("/save")
    public Boolean save(String userId, String username, String psd, String roleId, String departmentId) {
        ZzUser user = new ZzUser();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPsd(psd);
        user.setDepartmentId(departmentId);
        user.setRoleId(roleId);
        return zzUserFacade.save(user);
    }


    @PostMapping("/list")
    public List<ZzUserVO> list() {
        return zzUserFacade.getBaseMapper().list();
    }

    @PostMapping("/delete")
    public Boolean delete(@RequestParam String userId) {
        return zzUserFacade.removeById(userId);
    }


}

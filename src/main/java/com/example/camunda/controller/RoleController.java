package com.example.camunda.controller;

import com.example.camunda.entity.ZzRole;
import com.example.camunda.facade.base.ZzRoleFacade;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhoupeng
 */
@RestController
@Api(value = "角色管理API", tags = {"角色管理API"})
@RequestMapping("/role")
public class RoleController {


    @Autowired
    private ZzRoleFacade zzRoleFacade;

    @PostMapping(value = "/add")
    public Boolean add(ZzRole role) {
        return zzRoleFacade.save(role);
    }

    @PostMapping("/save")
    public Boolean save(String roleId, String roleName) {
        ZzRole role = new ZzRole();
        role.setRoleId(roleId);
        role.setRoleName(roleName);
        zzRoleFacade.save(role);
        return zzRoleFacade.save(role);
    }


    @PostMapping("/list")
    public List<ZzRole> list() {
        return zzRoleFacade.list();
    }

    @PostMapping("/delete")
    public Boolean delete(@RequestParam String roleId) {
        return zzRoleFacade.removeById(roleId);
    }

}

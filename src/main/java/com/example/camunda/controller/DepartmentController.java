package com.example.camunda.controller;

import com.example.camunda.entity.ZzDepartment;
import com.example.camunda.facade.base.ZzDepartmentFacade;
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
@RequestMapping("/department")
@Api(value = "部门管理API", tags = {"部门管理API"})
public class DepartmentController {

    @Autowired
    private ZzDepartmentFacade zzDepartmentFacade;


    @PostMapping(value = "/add")
    public Boolean add(ZzDepartment department) {
        return zzDepartmentFacade.save(department);
    }


    @PostMapping("/save")
    public Boolean save(String departmentId, String departmentName) {
        ZzDepartment department = new ZzDepartment();
        department.setDepartmentId(departmentId);
        department.setDepartmentName(departmentName);
        return zzDepartmentFacade.save(department);
    }


    @PostMapping("/list")
    public List<ZzDepartment> list() {
        return zzDepartmentFacade.list();
    }

    @PostMapping("/delete")
    public Boolean delete(@RequestParam String departmentId) {
        return zzDepartmentFacade.removeById(departmentId);
    }
}

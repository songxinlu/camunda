package com.example.camunda.controller;

import com.example.camunda.entity.Paiche;
import com.example.camunda.entity.ZzUser;
import com.example.camunda.facade.base.PaicheFacade;
import com.example.camunda.facade.base.ZzUserFacade;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhoupeng
 */
@Controller
@RequestMapping("/paiche")
@Api(value = "派车管理API", tags = {"派车管理API"})
@Slf4j
public class PaicheController {


    @Autowired
    private ZzUserFacade zzUserFacade;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;


    @Autowired
    private PaicheFacade paicheFacade;


    private final String processDefinitionKey = "paiche";

    @PostMapping("/start/save")
    public String saveStartForm(@RequestBody Paiche record) {
        String userId = record.getUserId() == null ? null : record.getUserId();
        if (userId == null) {
            return "paiche_login";
        }
        Map<String, Object> map = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        String businessKey = uuid.toString().replace("-", "");
        ZzUser currentUser = zzUserFacade.getById(userId);

        try {

            //流程变量，职务，分支使用
            map.put("role", currentUser.getRoleId());
            if ("002".equals(currentUser.getRoleId())) {
                //如果是职员，则领导审批为 该职员所在部门的领导
                map.put("leader_department", currentUser.getDepartmentId() + ":001");
                map.put("approval_2","1");
                map.put("startUserId",userId);
            } else {
                //如果是部门领导，直接设置为车管部门领导
                map.put("leader_department", "003:001");
            }

            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionKey(processDefinitionKey).latestVersion().singleResult();

            String processDefinitionId = processDefinition.getId();
            identityService.setAuthenticatedUserId(userId);

            //以businessKey传参方式：启动流程
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionId, businessKey, map);

            record.setId(businessKey);
            //每个流程相关业务 需要回填记录对应的流程实例ID
            record.setPid(processInstance.getId());
            if (!paicheFacade.save(record)) {
                throw new RuntimeException("insert paiche record error");
            }

        } catch (Exception ex) {
            System.out.println("error : " + ex.toString());
        } finally {
            identityService.setAuthenticatedUserId(null);
        }
        return "redirect:/paiche/list";
    }


    @GetMapping("/index")
    public String index() {
        return "paiche_login";
    }

    @PostMapping("/login")
    public String login(String userId, String psd, HttpSession session, Model model) {
        ZzUser zzUser = zzUserFacade.getById(userId);
        if (zzUser == null) {
            model.addAttribute("msg", "账号有误");
            return "common_back_msg";
        }
        if (zzUser.getPsd().equals(psd)) {
            log.info("user login success: {}", userId);
            session.setAttribute("userId", userId);
            return "paiche_add";
        }
        model.addAttribute("msg", "密码错误");
        return "common_back_msg";
    }
}

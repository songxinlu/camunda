package com.example.camunda;

/**
 * @ClassName: CodeAutoGenerator
 * @Description:
 * @Author songxl
 * @Date 2021/6/28
 * @Version 1.0
 */
public class CodeAutoGenerator {

    /**
     * 初次生成：entity.java、mapper.java、mapper.xml、baseMapper.xml、service.java、serviceImpl.java、facade.java
     * 再次生成：entity.java、baseMapper.xml、service.java、serviceImpl.java
     *
     * @param args
     */
    public static void main(String[] args) {
        tableInit();
    }

    /**
     * security-center模块
     */
    private static void tableInit() {
        //模块名称
        String moduleName_ = "";
        //父包名路径(文件输出路径,也是导包的路径)
        String parentPackagePath_ = "/com/example/camunda";
        //生成文件父类包package
        String packageName_ = "com.example.camunda";

        //数据库
        String username = "sxl";
        String password = "sxl123456";
        String url = "jdbc:mysql://localhost:3306/camunda?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";

        //表名
        String[] TABLES = {
                "z_log",
                "z_paiche",
                "zz_department",
                "zz_role",
                "zz_user"
        };
        //生成的实体类忽略表前缀: 不需要则置空
        String[] ENTITY_IGNORE_PREFIX = { "z_" };

        GeneratorUtil.create(moduleName_, parentPackagePath_, packageName_, username, password, url, TABLES, ENTITY_IGNORE_PREFIX);
    }

}
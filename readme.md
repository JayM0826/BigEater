- https://blog.csdn.net/gfd54gd5f46/article/details/75022305 Spring Boot (教程八： 过滤器、监听器、拦截器)
- http://blog.didispace.com/springbootswagger2/ Spring Boot 中使用 Swagger2 构建强大的 RESTful API 文档
- http://www.cnblogs.com/DeepLearing/p/5663178.html logback 节点配置详解
- http://localhost:8080/swagger-ui.html swagger本地测试
- https://blog.csdn.net/itguangit/article/details/78701110 json注解对于返回数据的使用
- http://localhost:8080/druid druid连接池的状态
- https://www.imooc.com/article/75232 SpringBoot 使用 Mybatis-Generator
- https://my.oschina.net/gef/blog/712383 【真巨坑】使用连接 mysql 的 jdbc 驱动最新版引发的问题
- https://www.oschina.net/question/202626_181237 spring 注解方式 idea 报 could not autowire，eclipse 却没有问题
    File-Project Structure 页面 Facets 下删掉 Spring(直接右键 Delete)
    这个解答是对的。并不会降低安全性！！因为创建项目的时候，都是先创建空项目再创建 web moduele（你想直接创建 web project 也可以）, 一般不会使用 spring 组件。都是自己配置的。这时候你要是不小心手滑（手滑原因：因为 idea 对你 spring 的配置文件会在上方报警告，然后你一 fix，就容易出事），那就会报错无法 Autowired。所以你只要删掉你手滑添加的就可以
- https://www.jianshu.com/p/c86a9bdc2a92 @RequestBody 异常：Required request body is missing 
- http://javatech.wang/index.php/archives/106/ Required request body is missing 错误解决
- https://www.jianshu.com/p/dbeeac29ff27 MyBatis Generator 踩坑与自救
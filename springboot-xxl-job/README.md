# xxl-job docker 安装
1、初始化“调度数据库” SQL脚本地址:https://github.com/xuxueli/xxl-job/blob/2.3.0/doc/db/tables_xxl_job.sql  
2、``` docker run -e PARAMS="--spring.datasource.url=jdbc:mysql://127.0.0.1:3306/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai --spring.datasource.username=root  --spring.datasource.password=root" -p 8080:8080 -v /tmp:/data/applogs --name xxl-job-admin  -d xuxueli/xxl-job-admin:{指定版本}```  
3、访问调度中心访问地址：http://localhost:8080/xxl-job-admin (该地址执行器将会使用到，作为回调地址)默认登录账号 “admin/123456”,
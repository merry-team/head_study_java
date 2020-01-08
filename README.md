# 心之助后台管理系统
## springboot docker-compose start
1. 拉取项目到本地
2. 准备环境 docker mvn jdk1.8
3. 项目下执行
    1. 构建 
        - mvn clean package
        - docker-compose build
        - 启动 docker-compose up
        - 后台启动 docker-compose up -d
        - 查看web运行状态日志 docker-compose logs -f app
    2. 关闭 docker-compose down
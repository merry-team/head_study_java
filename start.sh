#!/bin/zsh
docker run -p 6379:6379 -d --name myredis -v `pwd`/config/redis/data:/data redis redis-server --appendonly yes
docker run --name mysql -v `pwd`/config/sql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw  -p 3306:3306 -d mysql

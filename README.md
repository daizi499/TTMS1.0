# TTMS 剧院票务管理系统

Spring Boot 3 + MyBatis-Plus + Vue 3 + MySQL 8.0

## 快速开始

### 1. 克隆

```bash
git clone git@github.com:daizi499/TTMS1.0.git
cd TTMS1.0
```

### 2. 导入数据库

```bash
mysql -u root -p < src/main/resources/ttmsnew.sql
```

> 如果报编码错误（SQL文件是GBK），先转UTF-8再导入：
> ```bash
> iconv -f GBK -t UTF-8 src/main/resources/ttmsnew.sql > /tmp/ttmsnew_utf8.sql
> mysql -u root -p < /tmp/ttmsnew_utf8.sql
> ```

### 3. 配置数据库密码

```bash
cp src/main/resources/application-template.yml src/main/resources/application.yml
```

编辑 `src/main/resources/application.yml`，把 `password` 改成你自己的数据库密码。

### 4. 编译运行

```bash
mvn package -DskipTests
java -jar target/TTMS.jar
```

> 需要 JDK 17+ 和 Maven 3.5+

### 5. 打开浏览器

| 页面 | 地址 |
|------|------|
| 演出厅管理 | http://localhost:8080/admin/studio/index-vue.html |
| 剧目管理 | http://localhost:8080/admin/play/index-vue.html |
| 演出计划 | http://localhost:8080/admin/schedule/index-vue.html |
| 前台首页 | http://localhost:8080/ |

## 技术栈

| 层面 | 技术 |
|------|------|
| 后端框架 | Spring Boot 3.4.5 |
| ORM | MyBatis-Plus 3.5.9 |
| 数据库 | MySQL 8.0 |
| 前端 | Vue 3 (CDN) + Axios |
| 构建 | Maven，JAR 打包，内嵌 Tomcat |

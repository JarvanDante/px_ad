# px_ad

`px_ad` 是一个基于 Spring Boot 的广告服务最小骨架项目，当前包含：

- 健康检查接口：`GET /api/health`
- 广告接口：
  - `GET /api/ads`：广告列表
  - `GET /api/ads/{id}`：广告详情
  - `POST /api/ads`：新建广告

## 环境要求

- JDK 21
- MySQL 8+
- Maven 3.9+（或使用项目自带 `./mvnw`）

## 配置

默认配置在 `src/main/resources/application.yaml`，可通过环境变量覆盖：

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `REDIS_HOST`
- `REDIS_PORT`

默认端口：`8090`

## 启动

```bash
./mvnw spring-boot:run
```

## 测试

```bash
./mvnw test
```

测试使用 H2 内存数据库配置（`src/test/resources/application.yaml`）。

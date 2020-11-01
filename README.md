### **POC-R2DBC**
**spring boot webflux + r2dbc + postgresql**

#### 1. Run rabbitmq
/docker
```bash
docker-compose up
```

#### 2. Rabbitmq management
```bash
http://localhost:15672
username: admin
password: test
```

#### 2. Build service
```bash
mvn clean install
```


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

#### 3. Build service
```bash
mvn clean install
```

#### 4. Queue test
run single instance service-client and multiple instances service-server
```bash
curl --request POST 'localhost:8080/queue?message=example'
```

#### 5. Fanout test
run single instance service-client and multiple instances service-server
```bash
curl --request POST 'localhost:8080/exchange/fanout?message=example'
```

#### 5. Direct test
run single instance service-client and multiple instances service-server
```bash
curl --request POST 'localhost:8080/exchange/direct?routing=test&message=example'
```
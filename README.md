### **POC-RABBITMQ-EXTEND**
**spring boot web + rabbitmq**

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
run single instance service-publisher and multiple instances service-consumer
```bash
curl --request POST 'localhost:8080/queue?message=example'
```

#### 5. Fanout test
run single instance service-publisher and multiple instances service-consumer
```bash
curl --request POST 'localhost:8080/exchange/fanout?message=example'
```

#### 5. Direct test
run single instance service-publisher and multiple instances service-consumer
```bash
curl --request POST 'localhost:8080/exchange/direct?routing=test&message=example'
```

#### 5. Topic test
run single instance service-publisher and multiple instances service-consumer
```bash
curl --request POST 'localhost:8080/exchange/topic?routing=test.1&message=example'
curl --request POST 'localhost:8080/exchange/topic?routing=test.2&message=example'
```

#### 6. Header test
run single instance service-publisher and multiple instances service-consumer
```bash
curl --request POST 'localhost:8080/exchange/header?userType=any&message=example'
curl --request POST 'localhost:8080/exchange/header?userType=admin&message=example'
curl --request POST 'localhost:8080/exchange/header?userType=simple&message=example'
```

#### 7. Ack test
run single instance service-publisher and multiple instances service-consumer
```bash
curl --request POST 'localhost:8080/queue/ack?message=example'
```

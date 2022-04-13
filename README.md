# 마이크로서비스 아키텍처로 변경한 백엔드
## 사용 기술 : Spring cloud, Maria DB, AWS EC2, Rabbit MQ, Kafka

서비스 구성
- service-discovery : 서비스 디스커버리로, 마이크로서비스들을 관리 
- apigateway-service : API 게이트웨이 마이크로서비스 
- user-service : 유저 마이크로서비스 

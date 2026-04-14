# Mutual Fund Transaction System

A mini mutual fund transaction system built with **Java Spring Boot** and **PostgreSQL**.  
Supports **buy, sell, and portfolio operations** with financial correctness and basic authentication.

---

##  Features
- **Buy mutual fund units** based on NAV  
- **Sell mutual fund units** with financial validation  
- **View user portfolio holdings**  
- **Idempotency key** to prevent duplicate transactions  
- **Basic authentication** with Spring Security  
- **PostgreSQL persistence** with JPA/Hibernate  
- **Transaction management** for consistency  

---

##  Tech Stack
- **Backend**: Java 17, Spring Boot  
- **Database**: PostgreSQL  
- **Security**: Spring Security (Basic Auth)  
- **API Docs**: Swagger / Postman Collection  

---

##  Setup Instructions

### 1. Clone Repository
```bash
	git clone https://github.com/gadekarrahul1924/mutualfund-system.git
	cd mutualfund-system


2. Configure Database
Update src/main/resources/application.properties:

	spring.datasource.url=jdbc:postgresql://localhost:5432/mutualfund
	spring.datasource.username=postgres
	spring.datasource.password=yourpassword
	spring.jpa.hibernate.ddl-auto=update
	spring.jpa.show-sql=true
	spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect


3. Load Sample Data
	Run data.sql in your PostgreSQL database:
	psql -U postgres -d mutualfund -f src/main/resources/data.sql


4. Build & Run
	mvn clean install
	mvn spring-boot:run


API Endpoints

1. Buy Mutual Fund
Endpoint:  
POST /buy

	Request Body:

	json
	{
  		"fundId": 1,
  	"amount": 1000,
  	"idempotencyKey": "txn-001"
	}
Responses:

	200 OK → Purchase successful
	400 Bad Request → Invalid input (e.g., missing fields)
	409 Conflict → Duplicate transaction (idempotency key already used)
	404 Not Found → Fund or user not found

2. Sell Mutual Fund
	Endpoint:  
	POST /sell

	Request Body:

	json
	{
		"fundId": 1,
  		"units": 5,
  		"idempotencyKey": "txn-002"
	}

Responses:

200 OK → Redemption successful

400 Bad Request → Invalid input

404 Not Found → No holdings for this fund

409 Conflict → Duplicate transaction

422 Unprocessable Entity → Insufficient units to sell


3. Get Portfolio

Endpoint:  
GET /portfolio

Responses:

	200 OK → Returns portfolio list
Example:

json
[
  {
    "fund": "Equity Growth Fund",
    "units": 8.30,
    "investedAmount": 1000.00
  },
  {
    "fund": "Balanced Fund",
    "units": 15.20,
    "investedAmount": 1455.00
  }
]

404 Not Found → User not found
401 Unauthorized → Invalid credentials



Sample Data (data.sql)
sql
-- Insert sample users (passwords should be BCrypt encoded in real setup)
INSERT INTO users (username, password) VALUES ('alice', '$2a$10$7QjYwZk9FZzQ7kzQxYwQ9uYwQ9uYwQ9uYwQ9uYwQ9uYwQ9u'); -- password: alice123
INSERT INTO users (username, password) VALUES ('bob', '$2a$10$7QjYwZk9FZzQ7kzQxYwQ9uYwQ9uYwQ9uYwQ9uYwQ9uYwQ9u'); -- password: bob123

-- Insert sample funds
INSERT INTO funds (name, nav) VALUES ('Equity Growth Fund', 120.50);
INSERT INTO funds (name, nav) VALUES ('Balanced Fund', 95.75);
INSERT INTO funds (name, nav) VALUES ('Debt Fund', 102.30);

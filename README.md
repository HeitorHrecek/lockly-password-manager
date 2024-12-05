# Gerenciador-Society

## #️⃣ SOBRE
Este projeto surgiu de um trabalho proposto pela faculdade, cujo tema era desenvolver uma aplicação completa (frontend e backend) que abordasse a temática de cibersegurança. A partir desse tema, nasceu a ideia do Lockly, um sistema web que gerencia suas senhas de forma segura e prática, permitindo que você as guarde e organize como preferir, utilizando pastas. As senhas são criptografadas até o momento de seu acesso.

## ⚒️ FERRAMENTAS
As tecnologias empregadas neste projeto foram:

### Backend

- Java 21  
- Spring Boot 3.1.2  
- MySQL 8  
- Lombok  
- Maven  
- Spring Security  
- Criptografia AES em modo CBC com PKCS5Padding  

### Frontend

- HTML5  
- CSS3  
- Tailwind  
- Angular  

## 🔵 COMO UTILIZAR:

### Clonando o repositório

Abra o CMD e digite o seguinte comando:

```bash
git clone https://github.com/HeitorHrecek/lockly-password-manager
```
---

### Backend

⚠️ **Pré-requisitos:**
- É necessário ter o banco de dados MySQL instalado e em execução na sua máquina ou um container configurado.  
- O Maven deve estar instalado e configurado corretamente.

### Configuração do banco de dados

1. Configure a aplicação para se conectar ao banco de dados. Após clonar o repositório, acesse a pasta criada com o nome do repositório:  
   **lockly-password-manager**.

2. Em seguida, entre na pasta **back-end**:  
   `lockly-password-manager/back-end`.

3. Crie um arquivo chamado **.env** e adicione o seguinte conteúdo:

```bash
DB_URL=jdbc:mysql://localhost/lockly?createDatabaseIfNotExist=true
DB_USERNAME=seu_username
DB_PASSWORD=sua_senha
```
Substitua `seu_username` e `sua_senha` pelas credenciais do seu banco de dados.

---

Após configurar o banco de dados, na mesma pasta **back-end**, execute o comando:

```bash
mvn clean install
```

Depois, acesse a pasta **target** (`back-end/target`) e execute o seguinte comando:

```bash
java -jar target/lockly-0.0.1-SNAPSHOT.jar
```
---

### Frontend

⚠️ **Pré-requisito:**  
- É necessário ter o Node.js instalado na sua máquina.

1. Na raiz do repositório, acesse a pasta **front-end**:  
   `lockly-password-manager/front-end`.

2. Execute o comando para instalar as dependências:  
```bash
npm install
```

3. Em seguida, inicie o servidor com o comando:  
```bash
ng serve
```

Agora, o projeto estará rodando na sua máquina! Basta acessar:  
[http://localhost:3000/](http://localhost:3000/).

---

## ⚠️ LEMBRETE
É necessário estar conectado à internet para utilizar o projeto, garantindo que os gerenciadores de dependências (Maven e NPM) consigam baixar os pacotes necessários corretamente.

# Anexos 🖇️

Caso tenha interesse, foram feitos alguns diagramas do projeto: 

## Diagrama de caso de uso

Clique <a href="https://drive.google.com/file/d/1RsYnli5K3OL2dsZE7Tm12cfXZQU-IXf3/view?usp=sharing">aqui</a> para acessar o diagrama.

## Diagrama Entidade-Relacionamento

Clique <a href="https://drive.google.com/drive/folders/1nX4dhh6Lw_Zxcrj_qwA4y8-6x4zfMP4l?usp=sharing">aqui</a> para acessar o diagrama.

# Participantes 👨‍💻

- <a href="https://www.linkedin.com/in/vitor-hugo-vieira-de-lima/">Vitor Hugo Vieira</a>
- <a href="https://www.linkedin.com/in/heitor-hrecek-630109339/">Heitor Gomes Hrecek</a>
- <a href="https://www.linkedin.com/in/gustavo-nakamura/">Gustavo Nakamura Cardoso</a>
# 🎓 EduSystem - Sistema de Gerenciamento de Cursos

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3.0-purple)
![Status](https://img.shields.io/badge/Status-Ativo-success)

## 📋 Sobre o Projeto

O **EduSystem** é uma plataforma completa para gerenciamento de cursos online, desenvolvida em Java com Spring Boot. O sistema permite o cadastro e gerenciamento de **Professores**, **Categorias** e **Cursos** de forma moderna e eficiente.

### ✨ Funcionalidades Principais

- 👨‍🏫 **Gestão de Professores** - Cadastro completo com upload de imagem
- 📚 **Gestão de Categorias** - Organização dos cursos por áreas de conhecimento
- 🎯 **Gestão de Cursos** - Criação e administração de conteúdo educacional
- 🌐 **Interface Pública** - Navegação por categorias e visualização de cursos
- 📱 **Design Responsivo** - Interface adaptável para todos os dispositivos

## 🎬 Demonstração

### 📹 Vídeo de Funcionamento

<video width="800" controls>
  <source src="demo.mp4" type="video/mp4">
  Seu navegador não suporta a tag de vídeo.
  <br>
  <a href="demo.mp4">📥 Baixar vídeo de demonstração</a>
</video>

> **Como adicionar o vídeo:**
> 1. Converta seu vídeo MKV para MP4
> 2. Salve o arquivo como `demo.mp4` na raiz do projeto (mesma pasta do README.md)  
> 3. O vídeo será reproduzido automaticamente no README com máxima compatibilidade

**📝 Roteiro sugerido para o vídeo (3-5 minutos):**
- ✅ Navegação pela página inicial  
- ✅ Gestão de Categorias (listar, inserir, editar)
- ✅ Gestão de Professores (incluindo upload de imagem)
- ✅ Gestão de Cursos (CRUD completo)
- ✅ Interface pública - navegação por categorias
- ✅ Visualização de detalhes do curso

**✅ Formato MP4 escolhido:**
- 🌐 **Compatibilidade universal** - funciona em todos os navegadores
- 📱 **Mobile friendly** - reproduz perfeitamente em dispositivos móveis  
- 🚀 **GitHub otimizado** - melhor suporte nativo na plataforma

### 📸 Screenshots

<details>
<summary>Clique para ver as capturas de tela</summary>

#### Página Inicial
![Página Inicial](./screenshots/home.png)

#### Gestão de Professores  
![Professores](./screenshots/professores.png)

#### Gestão de Cursos
![Cursos](./screenshots/cursos.png)

#### Detalhes do Curso
![Detalhes](./screenshots/detalhes.png)

</details>

## 🚀 Tecnologias Utilizadas

### Backend
- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.4** - Framework principal
- **Spring MVC** - Arquitetura web
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM
- **MySQL** - Banco de dados
- **Maven** - Gerenciamento de dependências

### Frontend
- **Thymeleaf** - Template engine
- **Bootstrap 5.3.0** - Framework CSS
- **FontAwesome 6.4.0** - Ícones
- **JavaScript** - Interatividade
- **HTML5/CSS3** - Estrutura e estilo

### Arquitetura
- **MVC (Model-View-Controller)** - Padrão arquitetural
- **DTO (Data Transfer Object)** - Transferência de dados
- **Repository Pattern** - Acesso a dados
- **Bean Validation** - Validação de dados

## 📦 Instalação e Execução

### Pré-requisitos

- ☕ Java 21 ou superior
- 🗄️ MySQL 8.0 ou superior
- 📦 Maven 3.6+ (ou usar o wrapper incluído)

### Configuração do Banco de Dados

1. Crie um banco de dados MySQL:
```sql
CREATE DATABASE spring2 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Configure as credenciais em `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/spring2
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Executando o Projeto

1. **Clone o repositório:**
```bash
git clone https://github.com/WilliamUcha/2025web2spring-main.git
cd 2025web2spring-main/web2/web2
```

2. **Execute com Maven:**
```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

3. **Acesse a aplicação:**
```
http://localhost:8080
```

## 🗂️ Estrutura do Projeto

```
web2/
├── src/
│   ├── main/
│   │   ├── java/com/web2/
│   │   │   ├── controller/          # Controladores MVC
│   │   │   ├── model/              # Entidades JPA
│   │   │   ├── repository/         # Repositories
│   │   │   ├── dto/               # Data Transfer Objects
│   │   │   └── Web2Application.java
│   │   └── resources/
│   │       ├── templates/          # Templates Thymeleaf
│   │       │   ├── categoria/     # CRUD Categorias
│   │       │   ├── curso/         # CRUD Cursos  
│   │       │   ├── professor/     # CRUD Professores
│   │       │   ├── index.html     # Página inicial
│   │       │   └── layout.html    # Layout base
│   │       ├── static/
│   │       │   └── img/          # Imagens dos professores
│   │       └── application.properties
│   └── test/                      # Testes
├── target/                        # Build artifacts
├── pom.xml                       # Dependências Maven
└── README.md                     # Este arquivo
```

## 🎯 Funcionalidades Detalhadas

### 👨‍🏫 Gestão de Professores
- ✅ Cadastro com campos: nome, email, telefone, especialização, currículo
- ✅ Upload de foto do professor
- ✅ Listagem com busca e paginação
- ✅ Edição e exclusão com validações
- ✅ Prevenção de exclusão com cursos vinculados

### 📚 Gestão de Categorias  
- ✅ Cadastro com nome e descrição
- ✅ Listagem organizada
- ✅ Edição e exclusão
- ✅ Prevenção de exclusão com cursos vinculados

### 🎯 Gestão de Cursos
- ✅ Cadastro completo: nome, descrição, carga horária, preço, datas
- ✅ Vinculação com professor e categoria
- ✅ Listagem administrativa
- ✅ Edição e exclusão
- ✅ Ícones padronizados (sem imagens)

### 🌐 Interface Pública
- ✅ Página inicial com cards de navegação
- ✅ Listagem de cursos por categoria
- ✅ Página de detalhes do curso
- ✅ Informações do professor responsável
- ✅ Design responsivo e moderno

## 🔧 Configurações Importantes

### Upload de Arquivos
```properties
# Configuração para upload de imagens
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB
```

### Banco de Dados
```properties
# Configurações JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## 🤝 Como Contribuir

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**William Ucha**
- GitHub: [@WilliamUcha](https://github.com/WilliamUcha)
- LinkedIn: [William Ucha](https://linkedin.com/in/williamucha)

## 🙏 Agradecimentos

- Spring Framework Team
- Bootstrap Team  
- FontAwesome
- Comunidade Java/Spring Boot

---

⭐ Se este projeto te ajudou, considere dar uma estrela no repositório!

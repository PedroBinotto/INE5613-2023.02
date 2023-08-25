# INE5613-2023.02

Projeto final da disciplina de Bancos de Dados I

**[INE5613]**

_INE | CTC | UFSC_

### Executando o projeto localmente

---

#### Configuração do ambiente de execução

##### Instalando o [SDK](https://sdkman.io/)

O SDK é usado para gerenciar instalações de ferramentas como a JDK e o Maven

> Install Software Development Kits for the JVM such as Java, Scala, Kotlin and
> Groovy. Ant, Gradle, Grails, Maven, SBT, Spark, Spring Boot, Vert.x and many
> others also supported.

```bash
curl -s "https://get.sdkman.io" | bash
source ~/.sdkman/bin/sdkman-init.sh
```

---

##### Instalando o [Java](https://www.java.com/en/download/)

Utilizando o SDK, encontre a versão desejada (recomendada `17.0.x`) e instale o
pacote na sua máquina

```bash
sdk list java
sdk install java 17.0.6-zulu
```

---

##### Instalando o [Docker](https://www.docker.com/)

Utilizando o gerenciador de pacotes da sua distribuição, instale o pacote
`docker`

```bash
# ex.: Debian, Ubuntu, Mint, etc....
sudo apt-get install apparmor lxc cgroup-lite
sudo apt-get install docker docker.io

# ex.: Arch e Arch-based
sudo pacman -S docker
```

---

##### Instalando o [Docker Compose](https://docs.docker.com/compose/)

Utilizando o gerenciador de pacotes da sua distribuição, instale o pacote
`docker-compose`

```bash
# ex.: Debian, Ubuntu, Mint, etc....
sudo apt-get install docker-compose

# ex.: Arch e Arch-based
sudo pacman -S docker-compose
```

---

##### Instalando [Python](https://www.python.org/) e ferramentas

- Utilizando o gerenciador de pacotes de sua distribuição, instalar o intérprete
  `python` (`3.x`) (consultar
  [lista de versões disponíveis](https://www.python.org/downloads/source/));
  recomenda-se versão estável mais recente;

- Instale o gerenciador de pacotes do Python (`pip`) (normalmente disponível
  através do pacote `python3-pip` no gerenciador de pacotes de sua ditribuição)
  e ferramenta de ambientes virtuais do python (`venv`);

- [Documentação](https://docs.python.org/3/tutorial/venv.html?highlight=pip).

---

#### Compilando e executando

- Na raiz do projeto, execute os seguintes comandos:

```bash
# Para subir o container do banco de dados:
docker-compose up -d postgres

# Para compilar o código-fonte
mvn clean install -DskipTests

# Para gerar o schema do banco:
cd database
mvn spring-boot:run

# Para executar o backend:
cd backend
mvn spring-boot:run

# Para executar o fronted:
cd frontend
source ./.venv/bin/activate
ine5613 start
```

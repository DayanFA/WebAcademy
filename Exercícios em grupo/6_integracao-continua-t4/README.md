# integracao-continua-t4

Repositório da disciplina Integração Contínua (Turma 4)

## Atualizando seu repositório local

O código produzido em sala de aula, e compartilhado neste repositório, pode ser atualizado em seu repositório local com o comando:

```console
git pull
```

No entando, se você fez alterações no seu repositório local, o comando acima pode gerar conflitos. Para evitar lidar com isso, você pode forçar uma atualização com o repositório remoto por meio dos comandos:

```console
git fetch origin
git reset --hard origin/main
```

O primeiro comando recebe as atualizações mais recentes do repositório remoto, e o segundo descarta todas as alterações locais e atualiza com o histórico mais recente do repositório remoto (branch main).

## Como inciar a aplicação

### Back-end

```console
cd sgcmapi
mvn package
java -jar target\sgcmapi.jar
```

Ou

```console
cd sgcmapi
mvn spring-boot:run
```

A aplicação vai iniciar no endereço <https://localhost:9000>, com acesso local a base de dados MySQL, por meio da porta padrão 3306, além de usuário e senha "root".

### Front-end

Para iniciar a aplicação, é necessário também instalar as dependências do projeto.

```console
cd sgcmapp
npm install
ng serve --ssl
```

A aplicação vai iniciar no endereço <https://localhost:4200>.

## Sites de referência

- Software Delivery Guide (Martin Fowler): <https://martinfowler.com/delivery.html>
- GitHub Docs - GitHub Actions: <https://docs.github.com/pt/actions>
- Docker Docs: <https://docs.docker.com/get-started/overview/>

## Ferramentas

- **Render**
  - Plataforma que será utilizada para deploy da aplicação back-end.
  - Criar uma conta: <https://render.com/>
  - [Tutorial de configuração do Render para deploy](https://github.com/webacademyufac/tutoriais/blob/main/render/render.md)
- **Aiven**
  - Serviço de hospedagem de banco de dados MySQL.
  - Criar uma conta: <https://aiven.io/>
- **Visual Studio Code**
  - <https://code.visualstudio.com/Download>
- **Extension Pack for Java (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack>
- **Spring Boot Extension Pack (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=pivotal.vscode-boot-dev-pack>
- **XML (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=redhat.vscode-xml>
- **Angular Language Service (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=Angular.ng-template>
- **Git**
  - <https://git-scm.com/downloads>
- **JDK 17**
  - Para verificar se o JDK está corretamente instalado e configurado, digite no prompt de comandos:
    - ```javac -version```
  - Se necessário, realizar a instalação e configuração:
    - Link para download: <https://download.oracle.com/java/17/archive/jdk-17.0.8_windows-x64_bin.msi>
    - Criar a variável de ambiente JAVA_HOME configurada para o diretório de instalação do JDK. Exemplo: “C:\Program Files\Java\jdk-17”.
    - Adicionar “%JAVA_HOME% bin” na variável de ambiente PATH.
    - Tutorial de configuração: <https://mkyong.com/java/how-to-set-java_home-on-windows-10/>
- **Maven**
  - Para verificar se o Maven está corretamente instalado e configurado, digite no prompt de comandos:
    - ```mvn -version```
  - Se necessário, realizar a instalação e configuração:
    - Link para download: <https://dlcdn.apache.org/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.zip>
    - Adicionar o diretório de instalação do Maven na variável de ambiente PATH. Exemplo: “C:\apache-maven\bin”.
    - Tutorial de instalação: <https://mkyong.com/maven/how-to-install-maven-in-windows/>
- **MySQL**
  - Verificar se o MySQL está funcionando:
    - Para tentar conectar no MySQL, no prompt de comandos digite:
      - ```mysql -u root -p```
    - Tentar acessar com senha em branco ou senha igual ao nome de usuário (root).
    - Tutorial para resetar a senha de root, caso necessário: <https://dev.mysql.com/doc/mysql-windows-excerpt/8.0/en/resetting-permissions-windows.html>
  - Remova o banco de dados ```sgcm```, se existir:
    - No prompt de comandos digite:
      - ```mysql -u root -p```
    - Ao conectar no MySQL, execute a seguinte instrução SQL:
      - ```DROP DATABASE sgcm;```
  - Se necessário, realizar a instalação:
    - Link para download: <https://dev.mysql.com/downloads/file/?id=516927>
    - [Tutorial de instalação](https://github.com/webacademyufac/tutoriais/blob/main/mysql/mysql.md)
- **Node.js (e npm)**
  - Versão 18 (LTS).
  - Para verificar a versão do Node.js, no prompt de comandos digite:
    - ```node --version```
  - Link para download: <https://nodejs.org/en/download/>
- **Angular CLI**
  - Versão 16.
  - Para verificar a versão do Angular CLI, no prompt de comandos digite:
    - ```ng version```
  - Tutorial de instalação: <https://angular.io/guide/setup-local>
  - Para instalar o Angular CLI, no prompt de comandos digite:
    - ```npm i -g @angular/cli@16```

## SGCM - Sistema de Gerenciamento de Clínica Médica

A demonstração de uso das ferramentas e tecnologias abordadas na capacitação é baseada em um projeto de exemplo, o SGCM. A documentação básica deste projeto está disponível [em outro repositório](https://github.com/webacademyufac/sgcmdocs) e aborda os seguintes tópicos:

- [Principais funcionalidades](https://github.com/webacademyufac/sgcmdocs#principais-funcionalides)
- [Histórias de usuário](https://github.com/webacademyufac/sgcmdocs#histórias-de-usuário)
- [Diagrama de Classes](https://github.com/webacademyufac/sgcmdocs#diagrama-de-classes)
- [Diagrama Entidade Relacionamento](https://github.com/webacademyufac/sgcmdocs#diagrama-entidade-relacionamento)

## Atividades práticas

1. [INDIVIDUAL] Modificar workflow para que o _**job**_ que compila o projeto do back-end e realiza os testes, seja dividido em dois _**jobs**_, sendo um para copilar o projeto e o outro para testes. (Entrega: 31/10/2023)

2. [GRUPO] Criar workflow de implantação contínua para o projeto front-end utilizando o GitHub Actions. (Entrega: 01/11/2023)

    - A implantação pode ser feita em qualquer plataforma. Exemplos:
      - Render (com Docker, a exemplo do que foi feito para aplicação back-end)
      - Netlify (não tem suporte para Docker): <https://www.netlify.com/>
      - Vercel (não tem suporte para Docker): <https://vercel.com/>
    - Antes do _**job**_ de _**deploy**_, deve ser executado o workflow de integração contínua do front-end.
    - Deve ser criado também um job para executar os testes do end-to-end.
      - Comando para executar os testes: ```ng run sgcmapp:cypress-run```
    - **ATENÇÃO**:
      - Configurar a constante ```API_URL``` no arquivo ```environment.ts``` do projeto front-end.
      - Modificar as configurações de CORS no back-end para adicionar o host da aplicação front-end em produção.
      - A implantação deve ser feita obrigatoriamente por meio do GitHub Actions.

> **IMPORTANTE:**
>
> - _**Todos os membros dos grupos devem participar das atividades**_, registrando esta participação por meio da identificação dos commits com seus respectivos usuários no GitHub.
> - As atividades devem ser desenvolvidas utilizando o respectivo repositório do grupo no GitHub, e _**organizadas por disciplina**_.
> - Quando se tratar de atividades inividuais, _**cada aluno deve criar uma subpasta com seu nome**_.

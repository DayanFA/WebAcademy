# programacao-avancada-frontend-t4

Repositório da disciplina Programação Avançada Front-end (Turma 4)

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

- Sass Documentation: <https://sass-lang.com/documentation/>
- Angular Docs: <https://angular.io/docs>
- TypeScript Documentation: <https://www.typescriptlang.org/docs/>
- Spring Boot Reference Documentation: <https://docs.spring.io/spring-boot/docs/current/reference/html/index.html>
- MDN Web Docs: Aprendendo desenvolvimento web: <https://developer.mozilla.org/pt-BR/docs/Learn>
- Swagger Documentation: <https://swagger.io/docs/>

## Ferramentas

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

1. [INDIVIDUAL] Reescrever regras CSS no arquivo ```estilo.scss```, ```app.component.scss``` e ```alerta.component.scss```, utilizando recursos do SASS/SCSS. (Entrega: ~~07/11/2023~~ 08/11/2023)

    **Solução:** <https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/ec03a16>

2. [GRUPO] Definir um problema de UX no SGCM, utilzando a Técnica dos 5 Ws, e implementar uma solução.

    - Primeira parte da atividade: produzir um slide com a definição do problema, utilizando a Técnica dos 5 Ws. (Entrega: 09/11/2023)
    - Segunda parte da atividade: implementar no projeto SGCM a solução efetiva para o problema definido. (Entrega: 13/11/2023)

3. [GRUPO] Melhorar o recurso de paginação e ordenação, para que seja utilizado também em conjunto com a funcionalidade de busca. (Entrega: 22/11/2023)

    - Quando o usuário mudar a página ou a ordenação, a busca deve ser realizada novamente, com o mesmo termo de busca.

    **Solução:** <https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/7348ce8>

4. [GRUPO] Adicionar ao componente de paginação uma funcionalidade que permita ao usuário selecionar a quantidade de itens por página. (Entrega: 22/11/2023)

    **Solução:** <https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/d1cd53b>

5. [GRUPO] Implementar as funcionalidades de paginação de resultados e ordenação para as demais entidades do SGCM, a exemplo do que foi feito para o agendamento. (Entrega: 22/11/2023)

    **Solução:** <https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/1326e1e>

6. [GRUPO] Criar um componente para a funcionalidade de seleção de tema para substituir a implementação atual. (Entrega: 22/11/2023)

    **Solução:** <https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/9e0abfe>

> **IMPORTANTE:**
>
> - _**Todos os membros dos grupos devem participar das atividades**_, registrando esta participação por meio da identificação dos commits com seus respectivos usuários no GitHub.
> - As atividades devem ser desenvolvidas utilizando o respectivo repositório do grupo no GitHub, e _**organizadas por disciplina**_.
> - Quando se tratar de atividades inividuais, _**cada aluno deve criar uma subpasta com seu nome**_.

## Soluções para problemas do SGCM

1. Alguns campos nos formulários de agendamento e do cadastro de profisisonal são preenchidos com o primeiro conjunto de resultados das respostas paginadas, que por padrão tem até 20 itens. Exemplo: ao listar os pacientes no formulário de agendamento, serão exibidos apenas os 20 primeiros pacientes.

    **Solução:** <https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/ee04365>
    - No back-end, foi criado o endpoint `/all`, que utiliza o método `Pageable.unpaged()`, o que permite retornar todos os registros em uma única página.
    - No front-end, foi criado o método `getAll()` para fazer requisições para o endpoint `/all`, mas o méotodo que retorna os registro paginados foi mantido.

2. Quando os status de um atendimento é alterado nas telas que listam os agendamentos e atendimentos, a paginção falha pois o filtro de status em cada tela é feito no front-end, mas a paginação ocorre no back-end. Exemplo: ao cancelar um agendamento, o registro continua sendo considerado no cálculo de total de itens paginados.

    **Solução:** <https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/ffbba3c>
    - A solução consiste em criar endpoints para recuperar os registros já filtrados por status no back-end, e utilizar estes endpoints nas telas que listam os agendamentos e atendimentos.

## Testes

### Back-end

- **[58bbea1](https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/58bbea1): Corrige testes de integração de classes da camada repository**
  - A solução consiste em alterar os testes para suportar o recurso de paginação. No entanto, o recurso em si não foi testado, apenas foi modificada a forma como o retorno é tratado.
  - Nesta solução há um problema com o uso do `Mockito.any(Pageable.class)`, como parâmetro para o método `busca()`, em conjunto com um valor real. Isso pode ser verificado por exemplo na [linha 32](https://github.com/webacademyufac/programacao-avancada-frontend-t4/blob/58bbea1/sgcmapi/src/test/java/br/ufac/sgcmapi/repository/ConvenioRepositoryIntegrationTest.java#L32) da classe `ConvenioRepositoryIntegrationTest`. Em tese, não há nenhum impedimento para que isso funcione, mas misturar valores reais e mocks como parâmetros de métodos pode gerar resultados inesperados, sobretudo quando há compartilhamento de estado entre os testes em diferentes classes, e a execução não é ordenada. Se as classes forem executadas individualmente em diferentes momentos, o teste pode passar, mas se forem executadas em conjunto, o teste pode falhar. Para evitar esse tipo de problema, o ideal é utilizar apenas mocks como parâmetros de métodos, ou apenas valores reais, mas não misturar os dois. O commit abaixo corrige esse problema.
    - [8b0a595](https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/8b0a595): Corrige erro com mocks (substitui mocks por objetos)
- **[c545f6e](https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/c545f6e): Corrige testes de unidade de classes da camada service**
  - Aqui também foram aplicadas apenas modificações para suportar o recurso de paginação.
- **[146fc70](https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/146fc70): Corrige testes de integração de classes da camada controller**
  - No caso dos testes de integração da camada controller, além do suporte a paginação, foi utilizada a anotação `@WithMockUser` para simular um usuário autenticado.
- **[35bb6f3](https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/35bb6f3): Corrige testes de unidade de classes da camada controller**
  - Neste caso, como se trata de testes de unidade, foi utilizado a anotação `@AutoConfigureMockMvc(addFilters = false)` para evitar a exigência de usuário autenticado ou uso de token JWT.

### Front-end

- **[e5383b0](https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/e5383b0): Corrige teste de unidade**
  - A maioria das modificações são relacionadas dependências ausentes.
  - Também foram realizadas modificações para corrigir o formato da respostas das requisições.
- **[ca5779f](https://github.com/webacademyufac/programacao-avancada-frontend-t4/commit/ca5779f): Corrige testes E2E**
  - No caso dos teste E2E, também foram feitas modificações para corrigir o formato da respostas das requisições.
  - Além disso, foi necessário lidar com a exigência de autenticação, o que foi feito por meio da criação de mock para representar um usuário autenticado.
    - Para utilizar o memso código em todos os teste, foi criado um comando customizado em `cypress/support/commands.ts`.

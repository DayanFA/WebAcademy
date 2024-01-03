# frameworks-back-end

Repositório da disciplina Frameworks Back-end

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

## Sites de referência

- Spring Boot Reference Documentation: <https://docs.spring.io/spring-boot/docs/current/reference/html/index.html>
- Spring Getting Started Guides: <https://spring.io/guides#getting-started-guides>
- Spring Boot in Visual Studio Code: <https://code.visualstudio.com/docs/java/java-spring-boot>
- Uma visão geral do HTTP: <https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview>
- Apostila Java e Orientação a Objetos (Caelum/Alura): <https://www.alura.com.br/apostila-java-orientacao-objetos>
- Baeldung: <https://www.baeldung.com/>

## Ferramentas

- **Visual Studio Code**
  - <https://code.visualstudio.com/Download>
- **Extension Pack for Java (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack>
- **Spring Boot Extension Pack (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=pivotal.vscode-boot-dev-pack>
- **XML (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=redhat.vscode-xml>
- **Git**
  - <https://git-scm.com/downloads>
- **Postman**
  - <https://www.postman.com/downloads/>
  - Link para download da coleção compartilhada: <https://api.postman.com/collections/19704449-e147c76f-5808-48bd-9808-8f7315414ed9?access_key=PMAT-01HBEZH1WVE959024Z1V9S5BYS>
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

## SGCM - Sistema de Gerenciamento de Clínica Médica

A demonstração de uso das ferramentas e tecnologias abordadas na capacitação é baseada em um projeto de exemplo, o SGCM. A documentação básica deste projeto está disponível [em outro repositório](https://github.com/webacademyufac/sgcmdocs) e aborda os seguintes tópicos:

- [Principais funcionalidades](https://github.com/webacademyufac/sgcmdocs#principais-funcionalides)
- [Histórias de usuário](https://github.com/webacademyufac/sgcmdocs#histórias-de-usuário)
- [Diagrama de Classes](https://github.com/webacademyufac/sgcmdocs#diagrama-de-classes)
- [Diagrama Entidade Relacionamento](https://github.com/webacademyufac/sgcmdocs#diagrama-entidade-relacionamento)

## Atividades práticas

1. [INDIVIDUAL] Modifique a classe `Unidade` utilizando as anotações necessárias para aplicar a técnica de ORM, a exemplo do que foi feito com a classe `Especialidade`. (Entrega: 03/10/2023)

    **Solução:** <https://github.com/webacademyufac/frameworks-back-end/commit/bbfee10>

2. [INDIVIDUAL] Criar um endpoint que forneça os horários agendados de um profissional para uma determinada data. (Entrega: 09/10/2023)

    - O conteúdo retornado deve ser uma ```List<String>```.
    - Exemplo: ["16:00:00", "16:30:00", "17:00:00"]
    - O endpoint deve ser criado na classe ```AtendimentoController```.
    - A tarefa inclui também a criação dos métodos necessários em ```AtendimentoService``` e ```AtendimentoRepository```.

    **Solução:** <https://github.com/webacademyufac/frameworks-back-end/commit/bb0c18c>

3. [GRUPO] Criar as classes para as demais entidades do sistema, em todas as camadas, implementando os métodos necessários para as operações CRUD, de forma semelhante ao ```Atendimento```, e baseado na [documentação do SGCM](https://github.com/webacademyufac/sgcmdocs). (Entrega: 11/10/2023)

    - As URLs dos endpoints para Usuario, Especialidade e Unidade, devem iniciar com ```/config```.
    - Exemplo: ```@RequestMapping("/config/especialidade")```

> **IMPORTANTE:**
>
> - _**Todos os membros dos grupos devem participar das atividades**_, registrando esta participação por meio da identificação dos commits com seus respectivos usuários no GitHub.
> - As atividades devem ser desenvolvidas utilizando o respectivo repositório do grupo no GitHub, e _**organizadas por disciplina**_.
> - Quando se tratar de atividades inividuais, _**cada aluno deve criar uma subpasta com seu nome**_.

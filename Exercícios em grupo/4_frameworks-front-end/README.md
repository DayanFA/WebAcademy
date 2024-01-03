# frameworks-front-end

Repositório da disciplina Frameworks Front-end

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

## Dependências do projeto

As dependências do projeto não são compartilhadas no repositório. Para instalar as dependências, a partir da raiz do projeto, no prompt de comandos, digite: ```npm install```.

## Sites de referência

- Angular Docs: <https://angular.io/docs>
- TypeScript Documentation: <https://www.typescriptlang.org/docs/>
- MDN Web Docs - Aprendendo desenvolvimento web: <https://developer.mozilla.org/pt-BR/docs/Learn>
- Using Angular in Visual Studio Code: <https://code.visualstudio.com/docs/nodejs/angular-tutorial>

## Ferramentas

- **Visual Studio Cod**
  - <https://code.visualstudio.com/Download>
- **Angular Language Service (Extensão do VS Code)**
  - <https://marketplace.visualstudio.com/items?itemName=Angular.ng-template>
- **Git**
  - <https://git-scm.com/downloads>
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

1. [GRUPO] Criar componentes para as demais entidades do sistema, implementando os métodos necessários para as operações CRUD, de forma semelhante ao ```AgendaFormComponent```, ```AgendaListComponent``` e ```AtendimentoService```, e baseado na [documentação do SGCM](https://github.com/webacademyufac/sgcmdocs). (Entrega: 23/10/2023)

    - As rotas para ```Usuario```, ```Especialidade``` e ```Unidade```, devem iniciar com ```/config```.
    - Exemplo: ```/config/usuario```

2. [INDIVIDUAL] Filtrar os convênios exibidos no formulário de agendamento, permitindo que apenas convênios ativos sejam listados. (Entrega: 23/10/2023)

3. [INDIVIDUAL] Implementar uma funcionalidade no formulário de agendamento para impedir que o usuário selecione horários já ocupados para um profissional em uma determinada data. (Entrega: 23/10/2023)

    - Sempre que o usuário alterar a data ou o profissional no formulário, as opções de horário devem ser atualizadas.
    - O back-end já possui um endpoint que retorna os horários ocupados para um profissional por data.

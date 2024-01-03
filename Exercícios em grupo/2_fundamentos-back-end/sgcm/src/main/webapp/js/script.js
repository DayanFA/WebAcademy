// Altera o tema o arquivo CSS de acordo com o tema selecionado
const mudarTema = (temaSelecionado) => {
    let url = "./css/estilo-tema-" + temaSelecionado + ".css";
    let linkTema = document.querySelector("#link-tema");
    linkTema.href = url;
}

// Altera o tema quando mudar a opção selecionada
let selectTema = document.querySelector("select#tema");
selectTema.addEventListener("change", evento => {
    let temaSelecionado = evento.target.value;
    if (temaSelecionado) {
        mudarTema(temaSelecionado);
        // Salva a opção de tema escolhida pelo usuário no localStorage
        localStorage.setItem("tema", temaSelecionado);
    }
});

// Recupera a opção de tema escolhida pelo usuário e
// altera o tema se houver uma opção salva no localStorage
let tema = localStorage.getItem("tema");
if (tema) {
    mudarTema(tema);
    // Seleciona a opção de tema escolhida pelo usuário
    selectTema.value = tema;
}

const adicionarEventoExcluir = () => {
    let botoesExcluir = document.querySelectorAll('a.botao.excluir');
    for (const botao of botoesExcluir) {
        botao.addEventListener('click', (evento) => {
            if (!confirm('Confirma exclusão?')) {
                evento.preventDefault();
            }
        });
    }
}

adicionarEventoExcluir();

let botaoCancelar = document.querySelector("input[data-url]");
if (botaoCancelar) {
    botaoCancelar.addEventListener("click", () => {
        let url = botaoCancelar.getAttribute("data-url");
        window.location.href = url;
    });
}

// Limpa a tabela removendo todas as linhas existentes
const limparTabela = () => {
    const tabela = document.querySelector('table');
    const corpoTabela = tabela.tBodies[0];
    while (corpoTabela.firstChild) {
        corpoTabela.removeChild(corpoTabela.firstChild);
    }
}

// Insere dados de um profissional na tabela
const inserirProfissional = (item) => {
    // Cria os elementos HTML
    let tabela = document.querySelector('table');
    let linha = document.createElement('tr');
    let id = document.createElement('td');
    let nome = document.createElement('td');
    let registro = document.createElement('td');
    let especialidade = document.createElement('td');
    let unidade = document.createElement('td');
    let telefone = document.createElement('td');
    let email = document.createElement('td');
    let acoes = document.createElement('td');

    // Adiciona os dados do profissional nos elementos criados
    id.classList.add('fit');
    id.textContent = item.id;
    nome.textContent = item.nome;
    registro.textContent = item.registroConselho;
    especialidade.textContent = item.especialidade.nome;
    unidade.textContent = item.unidade.nome;
    telefone.textContent = item.telefone;
    email.textContent = item.email;
    acoes.innerHTML = `
            <a href="profissionaisForm.jsp?id=${item.id}"
                class="botao">Editar</a>
            <a href="profissionais.jsp?excluir=${item.id}"
                class="botao excluir">Excluir</a>
        `;

    console.log("ID:", item.id);
    console.log("Nome:", item.nome);
    console.log("Registro Conselho:", item.registroConselho);
    console.log("Especialidade:", item.especialidade.nome);
    console.log("Unidade:", item.unidade.nome);
    console.log("Telefone:", item.telefone);
    console.log("Email:", item.email);
    
    // Adiciona os elementos criados na linha da tabela
    linha.appendChild(id);
    linha.appendChild(nome);
    linha.appendChild(registro);
    linha.appendChild(especialidade);
    linha.appendChild(unidade);
    linha.appendChild(telefone);
    linha.appendChild(email);
    linha.appendChild(acoes);

    // Adiciona a linha na tabela
    tabela.tBodies[0].appendChild(linha);

}

let busca = document.querySelector('input[type="search"]');

busca.addEventListener('input', (evento) => {
    //console.log("oi");
    let valorBusca = evento.target.value;
    let urlAtual = "./buscaprofissionaisservlet";
    let novaUrl = urlAtual + "?busca=" + encodeURIComponent(valorBusca);
    //window.location.href = novaUrl;
    buscarProfissional(novaUrl);
})

const buscarProfissional = (url) => {
    fetch(url, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        // Aqui você pode atualizar sua tabela com os dados obtidos em 'data'
        console.log(data); // Para verificar os dados no console
        limparTabela();
        // Atualize a tabela com os dados em 'data'
        for (const dados of data) {
            inserirProfissional(dados);
        }
    })
    .catch(error => {
        console.error('Erro na requisição Fetch:', error);
    });
}


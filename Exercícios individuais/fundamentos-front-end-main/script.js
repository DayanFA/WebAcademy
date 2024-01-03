//ATIVIDADE 2:
let botaoadd = document.querySelector('a#add'); //alterei aquele outro botão, n mudei muito
let formulario = document.querySelector('form'); //Selecionar o formulario
let botaocancelar = document.querySelector('#cancel'); //No Html adicionei um id #cancel no botão cancelar

//Mostra o forms com Adicionar
botaoadd.addEventListener('click', () => {
    formulario.style.display = 'block'; 
});

//Esconder o forms com Cancelar
botaocancelar.addEventListener('click', () => {
    formulario.style.display = 'none';
    formulario.reset(); // Limpa os campos do formulário
});


//ATIVIDADE 3:
let botaosalvar = document.querySelector('#save'); //No Html adicionei um id #save no botão salvar
let tabela = document.querySelector('table tbody'); //Selecionar a tabela

//Adicionar conteudo ao forms com Salvar
botaosalvar.addEventListener('click', (event) => {
    let nome = document.querySelector('#nome').value;
    let registroConselho = document.querySelector('#registroConselho').value;
    let telefone = document.querySelector('#telefone').value;
    let email = document.querySelector('#email').value;
    let especialidade = document.getElementById('especialidade').value;
    let unidade = document.getElementById('unidade').value;

    //Evitar que algo vazio seja enviado
    if (nome === '' || registroConselho === '' || telefone === '' || email === '' || especialidade === '' || unidade === '') {
        alert('Preencha todos os campos obrigatórios!');
        event.preventDefault();
        return;
    }

    //Evitar enviar um email no formato errado
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert('O e-mail não está no formato correto!');
            event.preventDefault(); 
            return;
        }

    //Criando novas linhas
    let novalinha = tabela.insertRow();
    novalinha.innerHTML = `
        <td>${tabela.rows.length}</td>
        <td>${nome}</td>
        <td>${registroConselho}</td>
        <td>${especialidade}</td>
        <td>${unidade}</td>
        <td>${telefone}</td>
        <td>${email}</td>
        <td>
            <a href="javascript:void(0)" class="botao">Editar</a>
            <a href="javascript:void(0)" class="botao excluir">Excluir</a>
        </td>
    `;

    //Limpa depois que envia
    document.querySelector('form').reset();
    //Mantem o Excluir do botão
    adicionarEventoExcluir();
});




const adicionarEventoExcluir = () => {
    let botoesExcluir = document.querySelectorAll('a.botao.excluir');
    for (const botao of botoesExcluir) {
        botao.addEventListener('click', () => {
            botao.parentNode.parentNode.remove();
        });
    }
}

const carregarDadosProfissionais = () => {
    let tabela = document.querySelector('table');
    let url = 'http://my-json-server.typicode.com/danielnsilva/json/profissionais';
    let xhr = new XMLHttpRequest();
    xhr.open('GET', url);
    xhr.addEventListener('readystatechange', () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let dados = JSON.parse(xhr.responseText);
            for (const item of dados) {
                let linha = document.createElement('tr');
                let id = document.createElement('td');
                let nome = document.createElement('td');
                let registro = document.createElement('td');
                let especialidade = document.createElement('td');
                let unidade = document.createElement('td');
                let telefone = document.createElement('td');
                let email = document.createElement('td');
                let acoes = document.createElement('td');
                id.classList.add('fit');
                id.textContent = item.id;
                nome.textContent = item.nome;
                registro.textContent = item.registro;
                especialidade.textContent = item.especialidade;
                unidade.textContent = item.unidade;
                telefone.textContent = item.telefone;
                email.textContent = item.email;
                acoes.innerHTML = `
                <a href="javascript:void(0)"
                   class="botao">Editar</a>
                <a href="javascript:void(0)"
                   class="botao excluir">Excluir</a>
                `;
                linha.appendChild(id);
                linha.appendChild(nome);
                linha.appendChild(registro);
                linha.appendChild(especialidade);
                linha.appendChild(unidade);
                linha.appendChild(telefone);
                linha.appendChild(email);
                linha.appendChild(acoes);
                tabela.tBodies[0].appendChild(linha);
            }
            adicionarEventoExcluir();
        }
    });
    xhr.send();
}

carregarDadosProfissionais();

const adicionarEventoExcluir = () => {
    let botoesExcluir = document.querySelectorAll('a.botao.excluir');
    for (const botao of botoesExcluir) {
        botao.addEventListener('click', () => {
            botao.parentNode.parentNode.remove();
        });
    }
}

const adicionarEventoEditar = () => {
    let botoesEditar = document.querySelectorAll('a.botao.editar');
    botoesEditar.forEach(button => {
       button.addEventListener('click', () => {
            let linha = button.closest('tr');
            let id = linha.querySelector('.fit').textContent;
            let ativo = linha.cells[1].textContent;
            let cnpj = linha.cells[2].textContent;
            let email = linha.cells[3].textContent;
            let nome = linha.cells[4].textContent;
            let razaoSocial = linha.cells[5].textContent;
            let representante = linha.cells[6].textContent;
            let telefone = linha.cells[7].textContent;
            //let acoes = linha.cells[8].textContent;

            form.classList.remove('inativo');

            //form.id.value = id;
            idAtualizar = id;
            form.ativo.value = ativo;
            form.cnpj.value = cnpj;
            form.email.value = email;
            form.nome.value = nome;
            form.razaoSocial.value = razaoSocial;
            form.representante.value = representante;
            form.telefone.value = telefone;

            //console.log (linha.querySelector('.fit').textContent)
        }) 
    });
}

let form = document.querySelector('form');
let botaoAdd = document.querySelector('a#add');
let botaoCancelar = document.querySelector('input[value="Cancelar"]');
let btnSalvar = document.querySelector('input[type="submit"]');
let tabela = document.querySelector('table');
let idAtualizar = null;

// Adiciona o evento de click ao botão de adicionar
botaoAdd.addEventListener('click', () => {
    form.classList.remove('inativo');
    
});

// Adiciona o evento de click ao botão de cancelar
botaoCancelar.addEventListener('click', () => {
    form.classList.add('inativo');
    // Limpa os campos do formulário
    form.reset();
});

// Adiciona o evento de submit ao formulário
form.addEventListener('submit', (evento) => {

    // Evita que a página seja recarregada ao submeter o formulário
    evento.preventDefault();

    // Cria um objeto com os dados do formulário

        let idExiste = null;
        let linhas = tabela.querySelectorAll('tbody tr');

        linhas.forEach(linha => {
            let linhaId = linha.querySelector('.fit').textContent;
            if (linhaId === idAtualizar) {
                idExiste = linha;
            }
        });

        if (idExiste) {
            idExiste.cells[1].textContent = form.ativo.value;
            idExiste.cells[2].textContent = form.cnpj.value;
            idExiste.cells[3].textContent = form.email.value;
            idExiste.cells[4].textContent = form.nome.value;
            idExiste.cells[5].textContent = form.razaoSocial.value;
            idExiste.cells[6].textContent = form.representante.value;
            idExiste.cells[7].textContent = form.telefone.value;

            console.log (idExiste)

            idExiste.classList.add('destacado');
            console.log('Classe "destacada" adicionada.');

            setTimeout(() => {
                idExiste.classList.remove('destacado');
                console.log('Classe "destacada" removida.');
            }, 2000); 

            form.reset();
            form.classList.add('inativo');
        } else {
            let convenios = {
                id: tabela.tBodies[0].rows.length + 1,
                ativo: true,
                cnpj: form.cnpj.value,
                email: form.email.value,
                nome: form.nome.value,
                razao_social: form.razaoSocial.value,
                representante: form.representante.value,
                telefone: form.telefone.value
            };

            // Insere o unidades na tabela
            inserirConvenios(convenios);

            // Limpa os campos do formulário
            form.reset();

            // Esconde o formulário
            form.classList.add('inativo');

            // Adiciona o evento de excluir ao botão criado ao inserir nova linha na tabela
            adicionarEventoExcluir();
            adicionarEventoEditar();
        }
});

// Insere dados de um convenios na tabela
const inserirConvenios = (item) => {

    // Cria os elementos HTML
    let linha = document.createElement('tr');
    let id = document.createElement('td');
    let ativo = document.createElement('td');
    let cnpj = document.createElement('td');
    let email = document.createElement('td');
    let nome = document.createElement('td');
    let razaoSocial = document.createElement('td');
    let representante = document.createElement('td');
    let telefone = document.createElement('td');
    let acoes = document.createElement('td');

    // Adiciona os dados do unidades nos elementos criados
    id.classList.add('fit');
    id.textContent = item.id;
    ativo.textContent = item.ativo;
    cnpj.textContent = item.cnpj;
    email.textContent = item.email;
    nome.textContent = item.nome;
    razaoSocial.textContent = item.razao_social;
    representante.textContent = item.representante;
    telefone.textContent = item.telefone;
    acoes.innerHTML = `
        <a href="javascript:void(0)"
            class="botao editar">Editar</a>
        <a href="javascript:void(0)"
            class="botao excluir">Excluir</a>
        `;
    
    // Adiciona os elementos criados na linha da tabela
    linha.appendChild(id);
    linha.appendChild(ativo);
    linha.appendChild(cnpj);
    linha.appendChild(email);
    linha.appendChild(nome);
    linha.appendChild(razaoSocial);
    linha.appendChild(representante);
    linha.appendChild(telefone);
    linha.appendChild(acoes);

    // Adiciona a linha na tabela
    tabela.tBodies[0].appendChild(linha);

}

// Carrega os dados dos convenios na tabela
const carregarDadosConvenios = () => {
    let url = './json/convenios.json';
    fetch(url).then(resposta => {
        return resposta.json();
    }).then(dados => {
        for (const item of dados) {
            inserirConvenios(item);
        }
        adicionarEventoExcluir();
        adicionarEventoEditar();
    }).catch(erro => {
        console.error(erro);
    });
}

carregarDadosConvenios();

let link = document.querySelector('head link');

const customBlack = () => {
    console.log('Color Black');
    link['href'] = './css/estilo1.css'
}

const customWhite = () => {
    console.log('Color White');
    link['href'] = './css/estilo.css'
    console.log (link['href'])
}

(function () {
    let buttonTema = document.querySelector('nav ul li div.dropdown_menu select#selectTema');
    let selectedValue;

    const storedValue = localStorage.getItem('selectedValue');

    if (storedValue) {
        selectedValue = storedValue;
        buttonTema.value = selectedValue;

        if (selectedValue === 'black') {
            customBlack();
        } else if (selectedValue === 'white') {
            customWhite();
        }
    }

    buttonTema.addEventListener('change', () => {
        selectedValue = buttonTema.value;

        localStorage.setItem('selectedValue', selectedValue);

        if (selectedValue === 'black') {
            customBlack();
        } else if (selectedValue === 'white') {
            customWhite();
        }
    });
})();


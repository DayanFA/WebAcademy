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
            let nome = linha.cells[1].textContent;
            //let acoes = linha.cells[8].textContent;

            form.classList.remove('inativo');

            //form.id.value = id;
            idAtualizar = id;
            form.nome.value = nome;

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

        let idExiste = null;
        let linhas = tabela.querySelectorAll('tbody tr');

        linhas.forEach(linha => {
            let linhaId = linha.querySelector('.fit').textContent;
            if (linhaId === idAtualizar) {
                idExiste = linha;
            }
        });
    // Cria um objeto com os dados do formulário
        if (idExiste) {
            idExiste.cells[1].textContent = form.nome.value;

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
            let especialidade = {
                id: tabela.tBodies[0].rows.length + 1,
                nome: form.nome.value
            };

            // Insere a especialidade na tabela
            inserirEspecialidade(especialidade);

            // Limpa os campos do formulário
            form.reset();

            // Esconde o formulário
            form.classList.add('inativo');

            // Adiciona o evento de excluir ao botão criado ao inserir nova linha na tabela
            adicionarEventoExcluir();
            adicionarEventoEditar();
        }

});

// Insere dados de uma especialidade na tabela
const inserirEspecialidade = (item) => {

    // Cria os elementos HTML
    let linha = document.createElement('tr');
    let id = document.createElement('td');
    let nome = document.createElement('td');
    let acoes = document.createElement('td');

    // Adiciona os dados da especialidade nos elementos criados
    id.classList.add('fit');
    id.textContent = item.id;
    nome.textContent = item.nome;
    acoes.innerHTML = `
        <a href="javascript:void(0)"
            class="botao editar">Editar</a>
        <a href="javascript:void(0)"
            class="botao excluir">Excluir</a>
        `;
    
    // Adiciona os elementos criados na linha da tabela
    linha.appendChild(id);
    linha.appendChild(nome);
    linha.appendChild(acoes);

    // Adiciona a linha na tabela
    tabela.tBodies[0].appendChild(linha);

}

// Carrega os dados das especialidades na tabela
const carregarDadosEspecialidades = () => {
    let url = './json/esppecialidades.json';
    fetch(url).then(resposta => {
        return resposta.json();
    }).then(dados => {
        for (const item of dados) {
            inserirEspecialidade(item);
        }
        adicionarEventoExcluir();
        adicionarEventoEditar();
    }).catch(erro => {
        console.error(erro);
    });
}

carregarDadosEspecialidades();

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


const adicionarEventoExcluir = () => {
    let botoesExcluir = document.querySelectorAll('a.botao.excluir');
    for (const botao of botoesExcluir) {
        botao.addEventListener('click', () => {
            botao.parentNode.parentNode.remove();
        });
    }
}

let idAtualizar = null;

const adicionarEventoEditar = () => {
    let botoesEditar = document.querySelectorAll('a.botao.editar');
    botoesEditar.forEach(button => {
        button.addEventListener('click', () => {
             let linha = button.closest('tr');
             let id = linha.querySelector('.fit').textContent;
             let endereco = linha.cells[1].textContent;
             let nome = linha.cells[2].textContent;
             //let acoes = linha.cells[8].textContent;
 
             form.classList.remove('inativo');
 
             //form.id.value = id;
             idAtualizar = id;
             form.nome.value = nome;
             form.endereco.value = endereco;
 
             //console.log (linha.querySelector('.fit').textContent)
         }) 
     });
}

let form = document.querySelector('form');
let botaoAdd = document.querySelector('a#add');
let botaoCancelar = document.querySelector('input[value="Cancelar"]');
let btnSalvar = document.querySelector('input[type="submit"]');
let tabela = document.querySelector('table');

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

    console.log (idAtualizar);
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
            idExiste.cells[1].textContent = form.endereco.value;
            idExiste.cells[2].textContent = form.nome.value;

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
            let paciente = {
                id: tabela.tBodies[0].rows.length + 1,
                nome: form.nome.value,
                endereco: form.endereco.value,
            };
            

            // Insere o paciente na tabela
            inserirUnidades(paciente);

            // Limpa os campos do formulário
            form.reset();

            // Esconde o formulário
            form.classList.add('inativo');

            // Adiciona o evento de excluir ao botão criado ao inserir nova linha na tabela
            adicionarEventoExcluir();
            adicionarEventoEditar();
        }
});

// Insere dados de um paciente na tabela
const inserirUnidades = (item) => {

    // Cria os elementos HTML
    let linha = document.createElement('tr');
    let id = document.createElement('td');
    let nome = document.createElement('td');
    let endereco = document.createElement('td');
    let acoes = document.createElement('td');

    // Adiciona os dados do paciente nos elementos criados
    id.classList.add('fit');
    id.textContent = item.id;
    nome.textContent = item.nome;
    endereco.textContent = item.endereco;
    acoes.innerHTML = `
        <a href="javascript:void(0)"
            class="botao editar">Editar</a>
        <a href="javascript:void(0)"
            class="botao excluir">Excluir</a>
        `;
    
    // Adiciona os elementos criados na linha da tabela
    linha.appendChild(id);
    linha.appendChild(endereco);
    linha.appendChild(nome);
    linha.appendChild(acoes);

    // Adiciona a linha na tabela
    tabela.tBodies[0].appendChild(linha);
}

// Carrega os dados dos pacientes na tabela
const carregarDadosUnidades = () => {
    let url = './json/uniddades.json';
    fetch(url).then(resposta => {
        return resposta.json();
    }).then(dados => {
        for (const item of dados) {
            inserirUnidades(item);
        }
        adicionarEventoExcluir();
        adicionarEventoEditar();
    }).catch(erro => {
        console.error(erro);
    });
}

carregarDadosUnidades();

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


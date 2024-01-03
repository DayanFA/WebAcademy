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
             let registro = linha.cells[2].textContent;
             let especialidade = linha.cells[3].textContent;
             let unidade = linha.cells[4].textContent;
             let telefone = linha.cells[5].textContent;
             let email = linha.cells[6].textContent;
             //let acoes = linha.cells[8].textContent;

             console.log('ID:', id);
             console.log('Nome:', nome);
             console.log('Registro:', registro);
             console.log('Especialidade:', especialidade);
             console.log('Unidade:', unidade);
             console.log('Telefone:', telefone);
             console.log('Email:', email);
 
 
             form.classList.remove('inativo');
 
             //form.id.value = id;
             idAtualizar = id;
             form.nome.value = nome;
             form.registroConselho.value = registro;
             form.especialidade.value = especialidade;
             form.unidade.value = unidade;
             form.telefone.value = telefone;
             form.email.value = email;

             console.log('ID:', idAtualizar);
             console.log('Nome:', form.nome.value);
             console.log('Registro:', form.registroConselho.value);
             console.log('Especialidade:', form.especialidade.value);
             console.log('Unidade:', form.unidade.value);
             console.log('Telefone:', form.telefone.value);
             console.log('Email:', form.email.value); 

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

    console.log (idAtualizar);

        let idExiste = null;
        let linhas = tabela.querySelectorAll('tbody tr');

        linhas.forEach(linha => {
            let linhaId = linha.querySelector('.fit').textContent;
            if (linhaId === idAtualizar) {
                idExiste = linha;
            }
        });

        console.log (idExiste)

        if (idExiste) {
            idExiste.cells[1].textContent = form.nome.value;
            idExiste.cells[2].textContent = form.registroConselho.value;
            idExiste.cells[3].textContent = form.especialidade.options[form.especialidade.selectedIndex].label;
            idExiste.cells[4].textContent = form.unidade.options[form.unidade.selectedIndex].label;
            idExiste.cells[5].textContent = form.telefone.value;
            idExiste.cells[6].textContent = form.email.value;
            
            idExiste.classList.add('destacado');
            console.log('Classe "destacada" adicionada.');

            setTimeout(() => {
                idExiste.classList.remove('destacado');
                console.log('Classe "destacada" removida.');
            }, 2000); 

            form.reset();
            form.classList.add('inativo');
        } else {
            // Cria um objeto com os dados do formulário
            let profissional = {
                id: tabela.tBodies[0].rows.length + 1,
                nome: form.nome.value,
                registro: form.registroConselho.value,
                especialidade: form.especialidade.options[form.especialidade.selectedIndex].label,
                unidade: form.unidade.options[form.unidade.selectedIndex].label,
                telefone: form.telefone.value,
                email: form.email.value
            };

            // Insere o profissional na tabela
            inserirProfissional(profissional);

            // Limpa os campos do formulário
            form.reset();

            // Esconde o formulário
            form.classList.add('inativo');

            // Adiciona o evento de excluir ao botão criado ao inserir nova linha na tabela
            adicionarEventoExcluir();
            adicionarEventoEditar();
        }
});

// Insere dados de um profissional na tabela
const inserirProfissional = (item) => {
    // Cria os elementos HTML
    let linha = document.createElement('tr');
    let id = document.createElement('td');
    let nome = document.createElement('td');
    let registro = document.createElement('td');
    let especialidade = document.createElement('td');
    let unidade = document.createElement('td');
    let telefone = document.createElement('td');
    let email = document.createElement('td');
    let acoes = document.createElement('td');
    let optionsEsp = document.createElement('option');
    let optionsUni = document.createElement('option');

    let optEsp = item.especialidade;

    optEsp.forEach((opt) => {
        optionsEsp.value = opt;
        form.especialidade.appendChild(optionsEsp);
    })

    for (let i = 0; i < item.length; i++) {
        optionsEsp.value = item.especialidade;
        optionsUni.value = item.unidade;

        form.especialidade.label.appendChild(optionsEsp);
        form.unidade.label.appendChild(optionsUni);
    }

    // Adiciona os dados do profissional nos elementos criados
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
            class="botao editar">Editar</a>
        <a href="javascript:void(0)"
            class="botao excluir">Excluir</a>
        `;
    
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

// Carrega os dados dos profissionais na tabela
const carregarDadosProfissionais = () => {
    let url = './json/proffissionais.json';
    fetch(url).then(resposta => {
        return resposta.json();
    }).then(dados => {
        for (const item of dados) {
            inserirProfissional(item);
        }
        adicionarEventoExcluir();
        adicionarEventoEditar();
    }).catch(erro => {
        console.error(erro);
    });
}

carregarDadosProfissionais();

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
    carregarDadosProfissionais();
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


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
             let nome_completo = linha.cells[2].textContent;
             let nome_usuario = linha.cells[3].textContent;
             let papel = linha.cells[4].textContent;
             let senha = linha.cells[5].textContent;
             //let acoes = linha.cells[8].textContent;
 
             form.classList.remove('inativo');
 
             //form.id.value = id;
             idAtualizar = id;
             form.nome_completo.value = nome_completo;
             form.senha.value = senha;
             form.nome_usuario.value = nome_usuario;
             form.papel.value = papel;
 
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
    
    if (idExiste) {
        idExiste.cells[1].textContent = true;
        idExiste.cells[2].textContent = form.nome_completo.value;
        idExiste.cells[3].textContent = form.nome_usuario.value;
        idExiste.cells[4].textContent = form.papel.value;
        idExiste.cells[5].textContent = form.senha.value;

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
        let usuarios = {
            id: tabela.tBodies[0].rows.length + 1,
            ativo: "true",
            nome_completo: form.nome_completo.value,
            nome_usuario: form.nome_usuario.value,
            papel: form.papel.value,
            senha: form.senha.value
        };

        // Insere o usuarios na tabela
        inserirUsuarios(usuarios);

        // Limpa os campos do formulário
        form.reset();

        // Esconde o formulário
        form.classList.add('inativo');

        // Adiciona o evento de excluir ao botão criado ao inserir nova linha na tabela
        adicionarEventoExcluir();
        adicionarEventoEditar();
    }

});

// Insere dados de um Usuarios na tabela
const inserirUsuarios = (item) => {

    // Cria os elementos HTML
    let linha = document.createElement('tr');
    let id = document.createElement('td');
    let ativo = document.createElement('td');
    let nome_completo = document.createElement('td');
    let nome_usuario = document.createElement('td');
    let papel = document.createElement('td');
    let senha = document.createElement('td');
    let acoes = document.createElement('td');

    // Adiciona os dados do Usuarios nos elementos criados
    id.classList.add('fit');
    id.textContent = item.id;
    ativo.textContent = item.ativo;
    nome_completo.textContent = item.nome_completo;
    nome_usuario.textContent = item.nome_usuario;
    papel.textContent = item.papel;
    senha.textContent = item.senha;
    acoes.innerHTML = `
        <a href="javascript:void(0)"
            class="botao editar">Editar</a>
        <a href="javascript:void(0)"
            class="botao excluir">Excluir</a>
        `;
    
    // Adiciona os elementos criados na linha da tabela
    linha.appendChild(id);
    linha.appendChild(ativo);
    linha.appendChild(nome_completo);
    linha.appendChild(nome_usuario);
    linha.appendChild(papel);
    linha.appendChild(senha);
    linha.appendChild(acoes);

    // Adiciona a linha na tabela
    tabela.tBodies[0].appendChild(linha);

}

// Carrega os dados dos usuarios na tabela
const carregarDadosUsuarios = () => {
    let url = './json/ussuarios.json';
    fetch(url).then(resposta => {
        return resposta.json();
    }).then(dados => {
        for (const item of dados) {
            inserirUsuarios(item);
        }
        adicionarEventoExcluir();
        adicionarEventoEditar();
    }).catch(erro => {
        console.error(erro);
    });
}

carregarDadosUsuarios();
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
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
             let data_nascimento = linha.cells[2].textContent;
             let endereco = linha.cells[3].textContent;
             let cidade = linha.cells[4].textContent;
             let estado = linha.cells[5].textContent;
             let grupo_sanguineo = linha.cells[6].textContent;
             let sexo = linha.cells[7].textContent;
             let telefone = linha.cells[8].textContent;
             let email = linha.cells[9].textContent;
             //let acoes = linha.cells[8].textContent;
 
             form.classList.remove('inativo');
 
             //form.id.value = id;
             idAtualizar = id;
             form.nome.value = nome;
             form.data_nascimento.value = data_nascimento;
             form.endereco.value = endereco;
             form.cidade.value = cidade;
             form.estado.value = estado;
             form.grupo_sanguineo.value = grupo_sanguineo;
             form.sexo.value = sexo;
             form.telefone.value = telefone;
             form.email.value = email;
 
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
            idExiste.cells[1].textContent = form.nome.value;
            idExiste.cells[2].textContent = form.data_nascimento.value;
            idExiste.cells[3].textContent = form.endereco.value;
            idExiste.cells[4].textContent = form.cidade.value;
            idExiste.cells[5].textContent = form.estado.value;
            idExiste.cells[6].textContent = form.grupo_sanguineo.value;
            idExiste.cells[7].textContent = form.sexo.value;
            idExiste.cells[8].textContent = form.telefone.value;
            idExiste.cells[9].textContent = form.email.value;

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
                telefone: form.telefone.value,
                data_nascimento: form.data_nascimento.value,
                endereco: form.endereco.value,
                cidade: form.cidade.value,
                estado: form.estado.value,
                cep: form.cep.value,
                grupo_sanguineo: form.grupo_sanguineo.value,
                email: form.email.value,
                sexo: form.sexo.options[form.sexo.selectedIndex].label
            };

            // Insere o paciente na tabela
            inserirPaciente(paciente);

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
const inserirPaciente = (item) => {

    // Cria os elementos HTML
    let linha = document.createElement('tr');
    let id = document.createElement('td');
    let nome = document.createElement('td');
    let data_nascimento = document.createElement('td');
    let endereco = document.createElement('td');
    let cidade = document.createElement('td');
    let estado = document.createElement('td');
    let grupo_sanguineo = document.createElement('td');
    let sexo = document.createElement('td');
    let telefone = document.createElement('td');
    let email = document.createElement('td');
    let acoes = document.createElement('td');

    // Adiciona os dados do paciente nos elementos criados
    id.classList.add('fit');
    id.textContent = item.id;
    nome.textContent = item.nome;
    data_nascimento.textContent = item.data_nascimento;
    endereco.textContent = item.endereco;
    cidade.textContent = item.cidade;
    estado.textContent = item.estado;
    grupo_sanguineo.textContent = item.grupo_sanguineo;
    sexo.textContent = item.sexo;
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
    linha.appendChild(data_nascimento);
    linha.appendChild(endereco);
    linha.appendChild(cidade);
    linha.appendChild(estado);
    linha.appendChild(grupo_sanguineo);
    linha.appendChild(sexo);
    linha.appendChild(telefone);
    linha.appendChild(email);
    linha.appendChild(acoes);

    // Adiciona a linha na tabela
    tabela.tBodies[0].appendChild(linha);

}

// Carrega os dados dos pacientes na tabela
const carregarDadospacientes = () => {
    let url = './json/pacientes.json';
    fetch(url).then(resposta => {
        return resposta.json();
    }).then(dados => {
        for (const item of dados) {
            inserirPaciente(item);
        }
        adicionarEventoExcluir();
        adicionarEventoEditar();
    }).catch(erro => {
        console.error(erro);
    });
}

carregarDadospacientes();

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


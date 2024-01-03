// Altera o tema o arquivo CSS de acordo com o tema selecionado
/*const mudarTema = (temaSelecionado) => {
    let url = "/assets/css/estilo-tema-" + temaSelecionado + ".css";
    let linkTema = document.querySelector("#link-tema");
    linkTema.href = url;
}

window.onload = () => {
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
    
/*let corFundoLink = window.getComputedStyle(document.querySelector('a#add.botao')).backgroundColor;
let corFundoRodape = window.getComputedStyle(document.querySelector('footer')).backgroundColor;
let linksNav = document.querySelectorAll('nav ul li a');
let linkConfiguracoes = document.querySelector('#dropdown > a'); 
let linksDropdown = document.querySelectorAll('#dropdown_menu a'); 

linksNav.forEach(link => {
    link.addEventListener('mouseover', function() {
        if (!this.style.backgroundColor) 
            this.style.backgroundColor = corFundoRodape;
    });

    link.addEventListener('mouseout', function() {
        if (!this.classList.contains('clicked')) {
            this.style.backgroundColor = ""; // substitua "" pela cor de fundo original do link
        }
    });

    link.addEventListener('click', function() {
        // Remove a classe 'clicked' de todos os outros links
        linksNav.forEach(otherLink => {
            if (otherLink !== this) {
                otherLink.classList.remove('clicked');
                otherLink.style.backgroundColor = ""; // substitua "" pela cor de fundo original do link
            }
        });

        this.style.backgroundColor = corFundoLink;
        this.classList.add('clicked');
    });
});

linksDropdown.forEach(link => {
    link.addEventListener('click', function() {
        linkConfiguracoes.style.backgroundColor = corFundoLink;
        linkConfiguracoes.classList.add('clicked');
    });
});
}*/

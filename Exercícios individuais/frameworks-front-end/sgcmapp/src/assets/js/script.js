// Altera o tema o arquivo CSS de acordo com o tema selecionado
const mudarTema = (temaSelecionado) => {
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
}

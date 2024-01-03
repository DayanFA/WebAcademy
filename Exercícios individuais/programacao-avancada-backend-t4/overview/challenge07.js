// Desafio 07: utilize a classe nativa Date para, dentro de uma função, exibir via console uma string com o dia, mês e ano atual. Em outras palavras: crie uma função sem parâmetros, crie um objeto do tipo Date dentro dela e utilize os métodos getHours, getMinutes e getSeconds para montar uma string com a hora, minuto e segundo. Após a função, acrescente o código setTimeout(nomeDaFuncao, 5000) três vezes. Teste com o comando node ./nomedoarquivo.js via terminal. Perguntas retóricas: o que acontece? Qual o motivo disso acontecer? Agora, utilize o método setInterval apenas uma vez no lugar de setTimeout e responda às mesmas perguntas.

function hour(){
    const date = new Date()
    const hour = date.getHours()
    const min = date.getMinutes()
    const sec = date.getSeconds()
    console.log(`${hour}:${min}:${sec}`)
}

setTimeout(hour,5000)
setTimeout(hour,5000)
setTimeout(hour,5000)

setInterval(hour,5000)

console.log('qlqr coisa')
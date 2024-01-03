const ul = document.querySelector('ul')
const input = document.querySelector('input')
const form = document.querySelector('form')

// Função que carrega o conteúdo da API.
async function load() {
    // fetch está como await para evitar que entre num esquema de promisse e só devolva o conteúdo após a iteração qua acontece em seguida.
    const res = await fetch('http://127.0.0.1:3000/')
        .then(data => data.json())
    // Iterando no vetor com o conteúdo (JSON) que está vindo da API e adicionando-os no frontend.
    res.urls.map(({name, url}) => addElement({name, url}))
}

load()

async function addElementAndSendToApi({ name, url }){
    // addElement joga para o front-end
    addElement( { name, url } )

    const response = await fetch(`http://127.0.0.1:3000?name=${name}&url=${url}`)

    if(!response)
            console.error(`Erro ao inserir: ${response.statusText}`)

}

function addElement({ name, url }) {
    
    const li = document.createElement('li')
    const a = document.createElement('a')
    const trash = document.createElement('span')

    a.href = url
    a.innerHTML = name
    a.target = "_blank"

    trash.innerHTML = "x"
    trash.onclick = () => removeElement(trash, { name, url })
    
    ul.append(li)
    li.append(a)
    li.append(trash)

}

async function removeElement(element, { name, url }) {
    if(confirm('Tem certeza que deseja deletar?')){
        element.parentNode.remove()

        const response = await fetch(`http://127.0.0.1:3000?name=${name}&url=${url}&del=1`)

        if(!response)
            console.error(`Erro ao deletar: ${response.statusText}`)
    }
}

form.addEventListener('submit', (event) => {
    
    event.preventDefault();

    let { value } = input

    if (!value)
        return alert('Preencha o campo!')

    const [name, url] = value.split(',')

    if (!url)
        return alert('O texto não está formatado da maneira correta.')

    if (!/^http/.test(url))
        return alert('Digite a url da maneira correta.')

    addElementAndSendToApi({ name, url })

    input.value = ''

})
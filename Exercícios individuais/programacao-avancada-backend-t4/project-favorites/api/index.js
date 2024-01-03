const express = require('express')
const mongoose = require('mongoose')
const app = express()

app.use(express.json())
app.use(express.urlencoded({ extended: true }))

mongoose.connect('mongodb://localhost:27017/myapp')
    .then(() => console.log('MongoDB connected...'))
    .catch(err => console.log(err))

const LinkSchema = new mongoose.Schema({
    name: String,
    url: String
})

const Link = mongoose.model('Link', LinkSchema)

app.get('/links', async (req, res) => {
    const links = await Link.find()
    res.send(links)
})

app.post('/links', async (req, res) => {
    const { name, url } = req.body
    const link = new Link({ name, url })
    await link.save()
    res.send('Link adicionado com sucesso.')
})

app.delete('/links', async (req, res) => {
    const { url } = req.body
    await Link.findOneAndDelete({ url })
    res.send('Link deletado com sucesso.')
})

app.listen(3000, () => console.log('Servidor rodando na porta 3000'))
// const http = require('http')
// const data = require('./urls.json')
// const URL = require('url')
// const fs = require('fs')
// const path = require('path')

// function writeFile(cb) {
//     fs.writeFile(
//         path.join(__dirname, 'urls.json'),
//         JSON.stringify(data, null, 2),
//         err => {
//             if (err) throw err
//             cb('Operação realizada com sucesso!')
//         }
//     )
// }

// http.createServer((req, res) => {
    
//     const { name, url, del } = URL.parse(req.url, true).query

//     res.writeHead(200, {
//         'Access-Control-Allow-Origin': '*'
//     })

//     if (!name || !url)
//         return res.end(JSON.stringify(data))

//     if (del) {
//         data.urls = data.urls.filter( item => item.url != url )
//         return writeFile( message => res.end(message+' Deletou.') )
//     }

//     data.urls.push( { name, url } )
//     return writeFile( message => res.end(message+' Inserção.') )

// }).listen(3000, () => console.log('Api rodando!'))
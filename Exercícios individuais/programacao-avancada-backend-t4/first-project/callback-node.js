const fs = require('fs')

fs.readFile('./text.txt', (err, content)=>{
    if(err) return console.error(err)
    console.log(String(content))
})

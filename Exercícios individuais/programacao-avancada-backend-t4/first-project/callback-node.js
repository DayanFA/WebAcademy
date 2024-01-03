const fs = require('fs');

fs.readFile('./text.txt',(err, content) => {
    if(err) return console.log(err)
    console.log(String(content))
})
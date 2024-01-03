// Promise é uma classe
// then e catch
/*
const promise = new Promise((resolve, reject)=>{
    const result = 1 + 1
    if(result == 2) resolve()
    else reject()
})

console.log('teste 01')
promise.then(()=>{
    console.log('Sucesso! O resultado é 2.')
}).catch(()=>{
    console.log('Falha! O resultado não é 2.')
})
console.log('teste 02')
*/

//---------------

const bestDev = 'Victor'

const promise = new Promise((resolve,reject)=>{
    if(bestDev == 'Dayan'){
        callback({
            name: bestDev,
            message: ' humildemente é o melhor!'
        })
    }else{
        callbackError({
            message01: 'Tá errado... ',
            message02: bestDev + '? Sério?'
        })
    }
})

promise.then((result)=>{
    console.log(result.name + result.message)
}).catch((result)=>{
    console.log(result.message01 + result.message02)
})


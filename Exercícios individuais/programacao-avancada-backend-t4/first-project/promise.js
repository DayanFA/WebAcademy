/*
const promise = new Promise((resolve, reject) => {
    const result = 1 + 1;
    if (result == 2) resolve();
    else reject();
});

console.log('teste 01');
promise.then((message) => {
    console.log('Sucesso! O resultado é 2.');
}).catch(() => {
    console.log('Falha! O resultado não é 2.');
});
console.log('teste 02');
*/
// ------------
const bestDev = 'Dayan';

const promise =  new Promise((resolve, reject) => {
    if (bestDev == 'Dayan') {
        resolve({
            name: bestDev,
            message: ' humildemente o melhor!'
        });
    } else {
        reject({
            message01: 'Tá errado...',
            message02: bestDev + '? Sério?'
        });
    }
});

promise.then((result) => {
    console.log(result.name + result.message)
}).catch(() => {
    console.log(result.message01 + result.message02)
});
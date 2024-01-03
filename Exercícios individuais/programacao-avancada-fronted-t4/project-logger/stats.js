const os = require('os')
const log = require('./logger')

setInterval(()=>{

    console.clear()

    const { freemem , totalmem } = os

    const mem = parseInt(freemem() / 1024 / 1024)
    const total = parseInt(totalmem() / 1024 / 1024)
    const percents = parseInt((mem / total) * 100)

    const stats = {
        freeMem: `${mem} MB`,
        totalMem: `${total} MB`,
        freePercents: `${percents}%`
    }

    console.log('******** Memory Stats ********')
    console.table(stats)

    log(`${JSON.stringify(stats)}\n`)

}, 1000)


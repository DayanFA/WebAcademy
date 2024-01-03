const EventEmitter = require('events')

const oi = new EventEmitter()

oi.on('oi', ()=>{console.log('Alguém disse oi...')})

oi.emit('oi')

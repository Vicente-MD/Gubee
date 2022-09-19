const PROXY_CONFIG = [
    {
        context: ['/heroes'],
        target: 'http://localhost:8080/api/v1/',
        secure: false,
        logLevel: 'debug'
    }
];

module.exports = PROXY_CONFIG;

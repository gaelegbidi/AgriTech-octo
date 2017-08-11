var  config  =() =>{
    return{
        apiUrlBase: 'http://localhost:8080/api',
        apiTokenUrl: 'http://localhost:8080/oauth/token',
        clientId: 'agritech-client',
        clientSecret: 'secret'
    }
}
export default new config;
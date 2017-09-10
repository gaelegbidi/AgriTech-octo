import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Redirect, Route, Switch} from 'react-router-dom'
import {createBrowserHistory} from 'history';
import request from 'axios'
// Containers
import Full from './containers/Full/'
// Views
import Login from './views/Pages/Login/'
import Register from './views/Register/'
import Page404 from './views/Pages/Page404/'
import Page500 from './views/Pages/Page500/'

export const gHistory = createBrowserHistory();

export const authRequest = request.create({
    baseURL: 'http://localhost:8080/',
    auth: {
        username: 'agritech-client',
        password: 'Pa123456'
    }
});

export const apiRequest = request.create({
    baseURL: 'http://localhost:8080/api/'
});

export const apiStorageRequest = request.create({
    baseURL: 'http://localhost:8080/api/',
    headers: {
        'content-type': 'multipart/form-data'
    }
});

var interceptorSuccess = (config) => {
    let access_token = localStorage.getItem('access_token');

    console.log("tocken: ", access_token);

    if (!access_token && window.location.pathname !== '/login') {
        gHistory.push('/login');
    }

    config.headers.common['authorization'] = 'Bearer ' + access_token;

    return config;

};

var interceptorError = (error)=>{
    // Do something with request error
    console.log(error);
    return Promise.reject(error);
}

// Add a request interceptor
apiRequest.interceptors.request.use(interceptorSuccess, interceptorError);
apiStorageRequest.interceptors.request.use(interceptorSuccess, interceptorError);

// // Add a response interceptor
// apiRequest.interceptors.response.use(function (response) {
//     // Do something with response data
//     return response;
// }, function (error) {
//     // Do something with response error
//     if (error.response.data.error === "unauthorized") {
//         localStorage.clear();
//     }
//     return Promise.reject(error);
// });

const checkAuth = () => !!localStorage.getItem('access_token');

const checkAuthAdmin = () => (!!localStorage.getItem('access_token')) && (localStorage.getItem('roles').includes('ADMIN') );

const AuthRoute = ({component: Component, ...rest}) => (
    <Route {...rest} render={props => (
        checkAuth() ? (<Component {...props}/>) : (<Redirect to={{pathname: '/login'}}/>)
    )}/>
);

const GuestRoute = ({component: Component, ...rest}) => (
    <Route {...rest} render={props => (
        !checkAuth() ? (<Component {...props}/>) : (<Redirect to={{pathname: '/'}}/>)
    )}/>
);

export const AdminRoute = ({component: Component, ...rest}) => (
    <Route {...rest} render={props => (
        checkAuthAdmin() ? (<Component {...props}/>) : (<Redirect to={{pathname: '/'}}/>)
    )}/>
);

ReactDOM.render((
    <BrowserRouter history={gHistory}>
        <Switch>
            <Route exact path="/login" name="Login Page" component={Login}/>
            {/*<AdminRoute exact path="/register" name="Register Page" component={Register}/>*/}
            <Route exact path="/404" name="Page 404" component={Page404}/>
            <Route exact path="/500" name="Page 500" component={Page500}/>
            <AuthRoute path="/" name="Home" component={Full}/>
        </Switch>
    </BrowserRouter>
), document.getElementById('root'));



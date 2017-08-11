import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Route, Switch, Redirect} from 'react-router-dom'
import {createBrowserHistory} from 'history';
import decode from 'jwt-decode';
import request from 'axios';
import config from './config'

// Containers
import Full from './containers/Full/'

// Views
import Login from './views/Pages/Login/'
import Register from './views/Pages/Register/'
import Page404 from './views/Pages/Page404/'
import Page500 from './views/Pages/Page500/'

export const gHistory = createBrowserHistory();
//
// // Handle API request errors
// request.interceptors.response.use(response => {
//     return response;
// }, error => {
//     return new Promise((resolve, reject) => {
//         if (error.status === 401 && error.data.error_description === 'The access token provided has expired.') {
//             // AuthActions.refreshToken({initialRequest: error.config, resolve: resolve, reject: reject});
//         } else if (error.status === 401 && error.statusText === 'Unauthorized') {
//             // AuthActions.logout();
//         } else {
//             reject(error);
//         }
//     });
// });

export var apiRequest = request.create({
    baseURL: config.apiUrlBase,
    timeout: 1000
});



// headers: {'X-Custom-Header': 'foobar'}

// const fakeAuth = {
//     isAuthenticated: false,
//     authenticate(cb) {
//         this.isAuthenticated = true
//         setTimeout(cb, 100) // fake async
//     },
//     signout(cb) {
//         this.isAuthenticated = false
//         setTimeout(cb, 100)
//     }
// }

const checkIfExist =() =>{

}

const checkAuth = () => {
    const token = localStorage.getItem('token');
    if (!token) {
        return false;
    }

    try {
        // { exp: 12903819203 }

        const { exp,sub,roles } = decode(token);
        localStorage.setItem('username',sub);
        localStorage.setItem('roles',roles);

        if (exp < new Date().getTime() / 1000) {
            return false;
        }

    } catch (e) {
        return false;
    }

    return true;
}
const checkAuthAdmin = () => {
    let userRole='user';
    const token = localStorage.getItem('token');
    if (!token) {
        return false;
    }

    try {
        // { exp: 12903819203 }

        const { exp,sub,roles } = decode(token);
        localStorage.setItem('username',sub);
        localStorage.setItem('roles',roles);
        userRole=roles;

        if (exp < new Date().getTime() / 1000) {
            return false;
        }

    } catch (e) {
        return false;
    }


    return userRole==='Admin';
}

const AuthRoute = ({component: Component, ...rest}) => (
    <Route {...rest} render={props => (
        checkAuth() ? (<Component {...props}/>) : (<Redirect to={{pathname: '/login'}}/>)
    )}/>
)

const GuestRoute = ({component: Component, ...rest}) => (
    <Route {...rest} render={props => (
        !checkAuth() ? (<Component {...props}/>) : (<Redirect to={{pathname: '/'}}/>)
    )}/>
)
const AdminRoute = ({component: Component, ...rest}) => (
    <Route {...rest} render={props => (
        checkAuthAdmin() ? (<Component {...props}/>) : (<Redirect to={{pathname: '/'}}/>)
    )}/>
)

ReactDOM.render((
    <BrowserRouter history={gHistory}>
        <Switch>
            {/*<Route exact path="/login" name="Login Page" component={Login}/>*/}
            <Route exact path="/login" render={props => <Login {...props} />} />
            <AdminRoute exact path="/register" name="Register Page" component={Register}/>
            <Route exact path="/404" name="Page 404" component={Page404}/>
            <Route exact path="/500" name="Page 500" component={Page500}/>
            <AuthRoute path="/" name="Home" component={Full}/>
        </Switch>
    </BrowserRouter>
), document.getElementById('root'))



import React, {lazy, Suspense} from './js/react.js';
import ReactDOM from './js/react-dom.js';
import {Button, CircularProgress} from './js/material-ui.js';
import {BrowserRouter, Link, Route} from './js/react-router-dom.js';
import html from './js/htm.js';

const Dashboard = lazy(() => import('./pages/Dashboard.js'));

ReactDOM.render(html`
    <${React.StrictMode}>
        <${BrowserRouter}>
            <${Button} to='/' component=${Link}>Beranda<//>
            <${Button} to='/dashboard' component=${Link}>Dasbor<//>
            <${Suspense} fallback=${html`<${CircularProgress} />`}>
                <${Route} exact path='/'>
                    <h1>Ini halaman Beranda.</h1>
                <//>
                <${Route} path='/dashboard'>
                    <h1>Ini halaman Dasbor.</h1>
                    <${Dashboard}/>
                <//>
            <//>
        <//>
    <//>
    `, document.body);
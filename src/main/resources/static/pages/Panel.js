import React, {lazy, Suspense, useContext} from '../modules/react.js';
import {CircularProgress, Divider, Stack} from '../modules/material-ui.js';
import {Redirect, Route, useRouteMatch} from '../modules/react-router-dom.js';
import html from '../modules/htm.js';
import NavDrawer from "../fragments/NavDrawer.js"
import {AuthContext} from "../App.js"
import Footer from "../fragments/Footer.js"

const Dashboard = lazy(() => import('./panel/Dashboard.js'));
const Employee = lazy(() => import('./panel/Employee.js'));

function Panel() {

    const { path, url } = useRouteMatch();
    const { user, setUser } = useContext(AuthContext);

    if (user) {
        return html`
            <${Stack} direction="row">
                <${NavDrawer} url=${url}/>
                <${Stack} minWidth=${0} flexGrow=${1}>
                    <${Suspense} fallback=${html`<${CircularProgress} />`}>
                        <${Route} exact path="${path}/">
                            <${Dashboard}/>
                        <//>
                        <${Route} path="${path}/calendar">
                            <h1>Ini halaman Kalender.</h1>
                        <//>
                        <${Route} path="${path}/my_leaves">
                            <h1>Ini halaman Cuti Saya.</h1>
                        <//>
                        <${Route} path="${path}/approvals">
                            <h1>Ini halaman Persetujuan.</h1>
                        <//>
                        <${Route} path="${path}/employees">
                            <${Employee}/>
                        <//>
                    <//>
                    <${Divider}/>
                    <${Footer}/>
                <//>
            <//>
    `} else {
        return html`<${Redirect} to="/"/>`;
    }
}

export default Panel;
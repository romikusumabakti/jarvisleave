import React, {lazy, Suspense} from '../modules/react.js';
import {CircularProgress, Divider, Stack, Typography} from '../modules/material-ui.js';
import {Route, useRouteMatch} from '../modules/react-router-dom.js';
import html from '../modules/htm.js';
import JarvisLogo from "../components/JarvisLogo.js"
import NavDrawer from "../fragments/NavDrawer.js"

const Dashboard = lazy(() => import('./panel/Dashboard.js'));
const Employee = lazy(() => import('./panel/Employee.js'));

function Panel() {

    const { path, url } = useRouteMatch();

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
                <${Divider} />
                <${Stack} p=${3} spacing=${1} alignItems="center">
                    <${JarvisLogo} size=${24}/>
                    <${Typography} variant="subtitle2">
                        © 2021 PUB Angkatan 18 (Jarvis)
                    <//>
                <//>
            <//>
        <//>
`}

export default Panel;
import React, {lazy, Suspense, useContext} from '../modules/react.js';
import {CircularProgress, Divider, Stack} from '../modules/material-ui.js';
import {Redirect, Route, useRouteMatch} from '../modules/react-router-dom.js';
import html from '../modules/htm.js';
import NavDrawer from "../fragments/NavDrawer.js"
import {AuthContext} from "../App.js"
import Footer from "../fragments/Footer.js"

const Dashboard = lazy(() => import('./panel/Dashboard.js'));
const Calendar = lazy(() => import('./panel/Calendar.js'));
const MyLeaves = lazy(() => import('./panel/MyLeaves.js'));

const Approvals = lazy(() => import('./panel/hrd/Approvals.js'));
const Holidays = lazy(() => import('./panel/hrd/Holidays.js'));
const Employees = lazy(() => import('./panel/hrd/Employees.js'));
const Divisions = lazy(() => import('./panel/hrd/Divisions.js'));

export const pages = [
    {
        icon: 'home',
        title: 'Dasbor',
        path: '',
        component: Dashboard,
    },
    {
        icon: 'calendar_today',
        title: 'Kalender',
        path: '/calendar',
        component: Calendar,
    },
    {
        icon: 'event',
        title: 'Cuti saya',
        path: '/my_leaves',
        component: MyLeaves,
    },
];

export const adminPages = [
    {
        icon: 'rule',
        title: 'Persetujuan',
        path: '/approvals',
        component: Approvals,
    },
    {
        icon: 'date_range',
        title: 'Hari libur',
        path: '/holidays',
        component: Holidays,
    },
    {
        icon: 'people',
        title: 'Karyawan',
        path: '/employees',
        component: Employees,
    },
    {
        icon: 'groups',
        title: 'Divisi',
        path: '/divisions',
        component: Divisions,
    },
];

function Panel() {

    const {path, url} = useRouteMatch();
    const {user} = useContext(AuthContext);

    if (user) {
        return html`
            <${Stack} direction="row">
                <${NavDrawer} url=${url}/>
                    <${Stack} minWidth=${0} flexGrow=${1}>
                        <${Suspense} fallback=${html`<${CircularProgress} />`}>
                            ${[...pages, ...adminPages].map(page => html`
                                <${Route} exact path="${path + page.path}">
                                    <${page.component}/>
                                <//>
                            `)}
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
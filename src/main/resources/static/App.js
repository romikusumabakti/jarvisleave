import {html} from './js/standalone.module.js';
import {Suspense, lazy} from './js/compat.module.js';
import Router from "./js/preact-router.es.min.js";
import Header from "./components/Header.js";

const Dashboard = lazy(() => import('./pages/Dashboard.js'));

function App() {
    return html`
        <${Header}/>
        <${Suspense} fallback=${html`<div>Memuat...</div>`}>
            <${Router}>
                <${Dashboard} path="/dashboard"/>
            <//>
        <//>
    `;
}

export default App;
import {html} from './js/htm.js';
import {Suspense, lazy, useEffect} from './js/compat.module.js';
import Router from "./js/preact-router.es.min.js";
import Header from "./fragments/Header.js";
import Button from "./components/Button.js";
import ProgressSpinner from "./components/ProgressSpinner.js";

const Dashboard = lazy(() => import('./pages/Dashboard.js'));

function App() {

    return html`
        <${Header}/>
        <main>
            <${Button}>Tombol<//>
            <${Suspense} fallback=${html`<${ProgressSpinner} />`}>
                <${Router}>
                    <${Dashboard} path="/dashboard"/>
                    <div path="*">Halaman tidak ditemukan.</div>
                <//>
            <//>
        </main>
    `;
}

export default App;
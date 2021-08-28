import {html} from './js/standalone.module.js';
import {Suspense, lazy} from './js/compat.module.js';
import Router from "./js/preact-router.es.min.js";
import Header from "./fragments/Header.js";
import Button from "./components/Button.js";

const Dashboard = lazy(() => import('./pages/Dashboard.js'));

function App() {
    return html`
        <${Header}/>
        <${Button}>Tombol<//>
        <${Suspense} fallback=${html`<div>Memuat...</div>`}>
            <${Router}>
                <${Dashboard} path="/dashboard"/>
                <div path="*">Halaman tidak ditemukan.</div>
            <//>
        <//>
    `;
}

export default App;
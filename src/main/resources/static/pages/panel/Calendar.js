import {Stack, Typography} from '../../modules/material-ui.js';
import html from '../../modules/htm.js';
import MaterialIcon from "../../components/MaterialIcon.js"

function Dashboard() {

    return html`
        <${Stack} p=${2} spacing=${2}>
            <${Stack} direction="row" justifyContent="space-between">
                <${Stack} direction="row" alignItems="center" gap=${1}>
                    <${MaterialIcon}>calendar_today<//>
                    <${Typography} variant="h5">Kalender<//>
                <//>
            <//>
            <${Typography} variant="h4">Maaf, halaman ini belum tersedia :(<//>
        <//>
    `;
}

export default Dashboard;
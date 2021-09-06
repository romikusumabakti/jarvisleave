import html from '../modules/htm.js';
import {Stack, Typography} from "../modules/material-ui.js"
import JarvisLogo from "../components/JarvisLogo.js"

function Header(props) {

    return html`
        <${Stack} p=${3} spacing=${1} alignItems="center">
            <${JarvisLogo} size=${24}/>
            <${Typography} variant="subtitle2">
                © 2021 PUB Angkatan 18 (Jarvis)
            <//>
        <//>
    `;
}

export default Header;
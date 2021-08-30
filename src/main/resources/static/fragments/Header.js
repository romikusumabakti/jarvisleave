import {html} from '../js/htm.js';
import {Link} from "../js/preact-router.es.min.js";

import AppBar from "../components/AppBar.js";
import AppBarSection from "../components/AppBarSection.js";
import NavDrawerButton from "../components/NavDrawerButton.js";
import Button from "../components/Button.js";

function Header() {
    return html`
        <${AppBar} style="background: white">
            <${AppBarSection}>
                <${NavDrawerButton} style="color: gray" />
                <span class="mdc-top-app-bar__title" style="color: gray">
                    JarvisLeave
                </span>
            <//>
            <${AppBarSection}>
                <${Link} variant="outlined" href="/login" component=${Button}>Login<//>
            <//>
        <//>
    `;
}

export default Header;
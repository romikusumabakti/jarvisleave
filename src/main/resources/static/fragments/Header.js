import html from '../modules/htm.js';
import {AppBar, Button, IconButton, Stack, Toolbar, Tooltip, useTheme} from "../modules/material-ui.js"
import MaterialIcon from "../components/MaterialIcon.js"
import {Link, NavLink} from "../modules/react-router-dom.js"
import JarvisLogo from "../components/JarvisLogo.js"
import {useContext} from "../modules/react.js"
import {AuthContext} from "../App.js"
import AccountMenu from "./AccountMenu.js"

function Header(props) {

    const { user, setUser } = useContext(AuthContext);

    const userPages = user ? [
        {
            title: 'Panel',
            path: '/panel',
            exact: false,
        },
    ] : [];

    const pages = [
        {
            title: 'Beranda',
            path: '',
            exact: true,
        },
            ...userPages,
        {
            title: 'Tentang',
            path: '/about',
            exact: true,
        },
    ];

    return html`
        <${AppBar} position="sticky" sx=${props.sx}>
            <${Toolbar}>
                <${IconButton}
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                >
                    <${MaterialIcon}>menu<//>
                <//>
                <${Button} component=${Link} to="/">
                    <${JarvisLogo} size=${20}/>
                <//>
                <${Stack} direction="row" flexGrow=${1}>
                    ${pages.map(tab => html`
                        <${Button} component=${NavLink} exact=${tab.exact} to=${tab.path} activeClassName='active'>${tab.title}<//>
                    `)}
                <//>
                <${Stack} direction="row" spacing=${1} alignItems="center">
                    ${useTheme().palette.mode === 'light' ? html`
                        <${Tooltip} title="Ubah ke tema gelap">
                            <${IconButton} onClick=${props.handleMode}>
                                <${MaterialIcon}>dark_mode<//>
                            <//>
                        <//>
                    ` : html`
                        <${Tooltip} title="Ubah ke tema terang">
                            <${IconButton} onClick=${props.handleMode}>
                                <${MaterialIcon}>light_mode<//>
                            <//>
                        <//>
                    `}
                    ${user ? html`
                        <${AccountMenu}/>
                    ` : html`
                        <${Button} variant="outlined" onClick=${props.loginButtonOnClick}>Login<//>
                    `}
                <//>
            <//>
        <//>
    `;
}

export default Header;
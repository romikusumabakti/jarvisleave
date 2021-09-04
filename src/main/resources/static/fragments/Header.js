import html from '../modules/htm.js';
import {AppBar, Button, IconButton, Stack, Toolbar, Tooltip, useTheme} from "../modules/material-ui.js"
import MaterialIcon from "../components/MaterialIcon.js"
import {Link, NavLink, useLocation, useRouteMatch} from "../modules/react-router-dom.js"
import JarvisLogo from "../components/JarvisLogo.js"
import {useContext} from "../modules/react.js"
import {AuthContext} from "../App.js"

function Header(props) {

    const { user, setUser } = useContext(AuthContext);

    const tabs = [
        {
            title: 'Beranda',
            path: '',
            exact: true,
        },
        {
            title: 'Panel',
            path: '/panel',
            exact: false,
        },
        {
            title: 'Tentang',
            path: '/about',
            exact: true,
        },
    ];

    const logout = () => {
        localStorage.removeItem('token');
        setUser(null);
    }

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
                    ${tabs.map(tab => html`
                        <${Button} component=${NavLink} exact=${tab.exact} to=${tab.path} activeClassName='active'>${tab.title}<//>
                    `)}
                <//>
                <${Stack} direction="row" spacing=${1} alignItems="center">
                    <${IconButton} onClick=${props.handleMode}>
                        ${useTheme().palette.mode === 'light' ? html`
                            <${Tooltip} title="Ubah ke tema gelap">
                                <${MaterialIcon}>dark_mode<//>
                            <//>
                        `
                        : html`
                            <${Tooltip} title="Ubah ke tema terang">
                                <${MaterialIcon}>light_mode<//>
                            <//>
                        `}
                    <//>
                    ${user ? html`
                        <${Button} variant="outlined" onClick=${logout}>Logout<//>
                    ` : html`
                        <${Button} variant="outlined" onClick=${props.loginButtonOnClick}>Login<//>
                    `}
                <//>
            <//>
        <//>
    `;
}

export default Header;
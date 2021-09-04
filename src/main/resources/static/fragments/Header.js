import html from '../modules/htm.js';
import {
    AppBar,
    Button,
    IconButton,
    Stack,
    Tab,
    Tabs,
    Toolbar,
    Tooltip,
    Typography,
    useTheme
} from "../modules/material-ui.js"
import MaterialIcon from "../components/MaterialIcon.js"
import {Link, useLocation} from "../modules/react-router-dom.js"
import JarvisLogo from "../components/JarvisLogo.js"

function Header(props) {

    const tabs = [
        {
            title: 'Dasbor',
            path: '/'
        },
        {
            title: 'Monitoring',
            path: '/monitoring'
        },
        {
            title: 'Pengajuan',
            path: '/submission'
        },
        {
            title: 'Karyawan',
            path: '/employee'
        },
    ]

    return html`
        <${AppBar} position="sticky">
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
                        <${Button} component=${Link} to=${tab.path} className="${tab.path === useLocation().pathname ? 'active' : ''}">${tab.title}<//>
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
                    <${Button} variant="outlined" onClick=${props.loginButtonOnClick}>Login<//>
                <//>
            <//>
        <//>
    `;
}

export default Header;
import {useContext} from "../modules/react.js"
import {AuthContext} from "../App.js"
import html from "../modules/htm.js"
import MaterialIcon from "../components/MaterialIcon.js"
import {
    Avatar,
    Box,
    Button,
    Divider,
    IconButton,
    ListItemIcon,
    Menu,
    MenuItem,
    Stack,
    Tooltip,
    Typography
} from "../modules/material-ui.js"

function AccountMenu() {

    const { user, setUser } = useContext(AuthContext);

    const logout = () => {
        localStorage.removeItem('token');
        setUser(null);
    }

    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return html`
        <${Box} sx=${{ display: 'flex', alignItems: 'center', textAlign: 'center' }}>
            <${Tooltip} title=${'Akun JarvisLeave (' + user.name + ')'}>
                <${IconButton} onClick=${handleClick} size="small">
                    <${Avatar} alt=${user.nama}/>
                    <//>
                <//>
            <//>
            <${Menu}
                    anchorEl=${anchorEl}
                    open=${open}
                    onClose=${handleClose}
                    onClick=${handleClose}
                    transformOrigin=${{ horizontal: 'right', vertical: 'top' }}
                    anchorOrigin=${{ horizontal: 'right', vertical: 'bottom' }}
            >
                <${Stack} px=${4} py=${2} spacing=${1} alignItems="center">
                    <${Avatar} alt=${user.name} sx=${{width: 64, height: 64}}/>
                        <${Typography} variant="subtitle1">${user.name}<//>
                        <${Button} variant="outlined">
                            Kelola Akun JarvisLeave Anda
                        <//>
                    <//>
                    <${Divider} />
                    <${MenuItem}>
                        <${ListItemIcon}>
                            <${MaterialIcon} fontSize="small">settings<//>
                        <//>
                        Setelan
                    <//>
                    <${MenuItem} onClick=${logout}>
                        <${ListItemIcon}>
                            <${MaterialIcon} fontSize="small">logout<//>
                        <//>
                        Logout
                    <//>
                <//>
    `;
}

export default AccountMenu;
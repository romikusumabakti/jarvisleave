import {useContext} from "../modules/react.js"
import {AuthContext} from "../App.js"
import html from "../modules/htm.js"
import MaterialIcon from "../components/MaterialIcon.js"
import {Avatar, Box, Divider, IconButton, ListItemIcon, Menu, MenuItem, Tooltip} from "../modules/material-ui.js"
import {useHistory} from "../modules/react-router-dom.js"

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
            PaperProps=${{
                sx: {
                    '& .MuiAvatar-root': {
                        width: 32,
                        height: 32,
                        ml: -0.5,
                        mr: 1,
                    },
                },
            }}
            transformOrigin=${{ horizontal: 'right', vertical: 'top' }}
            anchorOrigin=${{ horizontal: 'right', vertical: 'bottom' }}
        >
            <${MenuItem}>
                <${Avatar} alt=${user.name}/> ${user.name}
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
                Keluar
            <//>
        <//>
    `;
}

export default AccountMenu;
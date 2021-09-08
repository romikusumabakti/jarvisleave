import {useContext, useEffect, useState} from '../../modules/react.js';
import {
    Button,
    Card,
    CardActionArea,
    Dialog, DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    Stack,
    TextField,
    Typography
} from '../../modules/material-ui.js';
import html from '../../modules/htm.js';
import MaterialIcon from "../../components/MaterialIcon.js"
import {AuthContext} from "../../App.js"
import {api} from "../../utils/api.js"

function Dashboard() {

    const {user} = useContext(AuthContext);
    const [leaveAllowances, setLeaveAllowances] = useState();
    const [submitted, setSubmitted] = useState(null);
    const [editErrors, setEditErrors] = useState({});

    useEffect(() => {
        api('/user/leave_allowances')
            .then(response => response.json())
            .then(leaveAllowances => {
                setLeaveAllowances(leaveAllowances);
            });
    }, []);

    const handleChange = () => {

    };

    const save = () => {

    };

    const cancelEdit = () => {

    };

    return html`
        <${Stack} p=${2} spacing=${2}>
            <${Stack} direction="row" justifyContent="space-between">
                <${Stack} direction="row" alignItems="center" gap=${1}>
                    <${MaterialIcon}>home<//>
                    <${Typography} variant="h5">Dasbor<//>
                <//>
                <${Button} variant="contained" startIcon=${html`<${MaterialIcon}>add<//>`}>
                    Ajukan cuti
                <//>
            <//>
            <${Typography} variant="h4">Hai, ${user.name}!<//>
            <${Stack} direction="row" spacing=${2}>
                ${leaveAllowances ? leaveAllowances.map(leaveAllowance => html`
                    <${Card}>
                        <${CardActionArea} sx=${{p: 4}}>
                            <${Typography} variant="h6">${leaveAllowance.type.name}<//>
                            <${Typography} variant="h4">${leaveAllowance.allowance}<//>
                        <//>
                    <//>
                `) : null}
            <//>
        <//>
        <${Dialog}
                open=${submitted !== null}
                onClose=${cancelEdit}
                scroll="paper"
                fullWidth
                component="form"
                onSubmit=${save}
        >
            <${DialogTitle}>${submitted?.id ? 'Edit' : 'Buat'} karyawan<//>
            <${DialogContent} dividers>
                <input type="hidden" name="id" value=${submitted?.id}/>
                <${Stack} spacing=${3}>
                    <${TextField}
                            label="NIP"
                            fullWidth
                            variant="outlined"
                            name="nip"
                            value=${submitted?.nip}
                            onChange=${handleChange}
                            required
                            autoFocus=${!submitted?.id}
                            error=${editErrors.nip}
                            helperText=${editErrors.nip}
                    />
                    <${TextField}
                            label="Nama"
                            fullWidth
                            variant="outlined"
                            name="name"
                            value=${submitted?.name}
                            onChange=${handleChange}
                            required
                    />
                    <${TextField}
                            label="Email"
                            type="email"
                            fullWidth
                            variant="outlined"
                            name="email"
                            value=${submitted?.email}
                            onChange=${handleChange}
                            required
                            error=${editErrors.email}
                            helperText=${editErrors.email}
                    />
                    <${TextField}
                            label="Nama pengguna"
                            fullWidth
                            variant="outlined"
                            name="username"
                            value=${submitted?.username}
                            onChange=${handleChange}
                            required
                            error=${editErrors.username}
                            helperText=${editErrors.username}
                    />
                    <${TextField}
                            label="Kata sandi${submitted?.id ? ' ' + (submitted?.password ? '(diubah)' : '(tidak diubah)') : ''}"
                            type="password"
                            fullWidth
                            variant="outlined"
                            name="password"
                            value=${submitted?.password}
                            onChange=${handleChange}
                            required=${!submitted?.id}
                    />
                <//>
            <//>
            <${DialogActions}>
                <${Button} variant="outlined" type="reset" onClick=${cancelEdit}>Batal<//>
                <${Button} variant="contained" type="submit">Simpan<//>
            <//>
        <//>
    `;
}

export default Dashboard;
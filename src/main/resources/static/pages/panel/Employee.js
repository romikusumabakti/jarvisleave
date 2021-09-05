import {useEffect, useState} from '../../modules/react.js';
import {
    Button,
    CircularProgress, Dialog, DialogActions, DialogContent, DialogTitle, FormControl,
    IconButton, InputLabel, MenuItem,
    Paper, Select,
    Snackbar,
    Stack,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow, TextField,
    Typography
} from '../../modules/material-ui.js';
import html from '../../modules/htm.js';
import MaterialIcon from "../../components/MaterialIcon.js"
import api from "../../utils/api.js"

function Employee() {

    const [employees, setEmployees] = useState();
    const [divisions, setDivisions] = useState();

    const [deleteOpen, setDeleteOpen] = useState(false);
    const [deleted, setDeleted] = useState();
    const [deletedIndex, setDeletedIndex] = useState();

    const [editOpen, setEditOpen] = useState(false);
    const [employee, setEmployee] = useState({});

    useEffect(() => {
        api('/employee')
            .then(response => response.json())
            .then(employees => {
                setEmployees(employees);
            });

        api('/division')
            .then(response => response.json())
            .then(divisions => {
                setDivisions(divisions);
            });
    }, []);

    const handleChange = event => {
      employee[event.target.name] = event.target.value;
      console.log(employee);
    };

    const edit = id => {
        api('/employee/' + id)
            .then(response => response.json())
            .then(employee => {
                setEmployee(employee);
                setEditOpen(true);
            });
    }

    const del = id => {
        api('/employee/' + id, 'DELETE')
        .then(response => {
            if (response.ok) {
                setDeleted(employees.find(employee => employee.id === id));
                setDeletedIndex(employees.findIndex(employee => employee.id === id));
                setEmployees(employees.filter(employee => employee.id !== id));
                setDeleteOpen(true);
            }
        });
    };

    const cancelDelete = () => {
        api('/employee/' + deleted.id, 'POST')
        .then(response => {
            if (response.ok) {
                setEmployees([...employees.slice(0, deletedIndex), deleted, ...employees.slice(deletedIndex)]);
                setDeleted(null);
                setDeletedIndex(null);
                setDeleteOpen(false);
            }
        });
    };

    return html`
        <${Stack} p=${2} spacing=${2}>
            <${Stack} direction="row" justifyContent="space-between">
                <${Stack} direction="row" alignItems="center" gap=${1}>
                    <${MaterialIcon}>people<//>
                    <${Typography} variant="h5">Karyawan<//>
                <//>
                <${Button} variant="contained" startIcon=${html`<${MaterialIcon}>add<//>`} onClick=${() => setEditOpen(true)}>
                    Buat
                <//>
            <//>
            <${TableContainer} component=${Paper}>
                <${Table}>
                    <${TableHead}>
                        <${TableRow}>
                            <${TableCell}>NIP<//>
                            <${TableCell}>Nama<//>
                            <${TableCell}>Divisi<//>
<!--                            <${TableCell}>Email<//>-->
                            <${TableCell}>Nama pengguna<//>
                            <${TableCell}>Role<//>
                            <${TableCell}><//>
                        <//>
                    <//>
                    <${TableBody}>
                    ${employees ? employees.map(employee => html`
                        <${TableRow}>
                            <${TableCell}>${employee.nip}<//>
                            <${TableCell}>${employee.name}<//>
                            <${TableCell}>${employee.division.name}<//>
<!--                            <${TableCell}>${employee.email}<//>-->
                            <${TableCell}>${employee.username}<//>
                            <${TableCell}>${employee.role.name}<//>
                            <${TableCell}>
                                <${Stack} direction="row" spacing=${2}>
                                    <${IconButton} onClick=${() => edit(employee.id)}>
                                        <${MaterialIcon}>edit<//>
                                    <//>
                                    <${IconButton} onClick=${() => del(employee.id)}>
                                        <${MaterialIcon}>delete<//>
                                    <//>
                                <//>
                            <//>
                        <//>
                    `) : html`<${CircularProgress} />`}
                    <//>
                <//>
            <//>
        <//>
        <${Snackbar}
                open=${deleteOpen}
                autoHideDuration=${10000}
                onClose=${() => setDeleteOpen(false)}
                message="1 karyawan dihapus."
                action=${html`
                    <${Button} size="small" onClick=${cancelDelete}>Batal<//>
                    <${IconButton}
                            size="small"
                            aria-label="close"
                            color="inherit"
                            onClick=${() => setDeleteOpen(false)}
                    >
                        <${MaterialIcon} size="small" >close<//>
                    <//>
                `}
        />
        <${Dialog}
                open=${editOpen}
                onClose=${() => setEditOpen(false)}
                scroll="paper"
                fullWidth
        >
            <${DialogTitle}>${employee ? 'Edit' : 'Buat'} karyawan<//>
            <${DialogContent} dividers>
                <${Stack} spacing=${3}>
                    <${TextField}
                            label="NIP"
                            fullWidth
                            variant="outlined"
                            name="nip"
                            value=${employee.nip}
                            onChange=${handleChange}
                    />
                    <${TextField}
                            label="Nama"
                            fullWidth
                            variant="outlined"
                            name="name"
                            value=${employee.name}
                            onChange=${handleChange}
                    />
                    <${FormControl} fullWidth>
                        <${InputLabel}>Divisi<//>
                        <${Select}
                                label="Divisi"
                                name="division"
                                value=${employee.division?.id}
                                onChange=${handleChange}
                        >
                            ${divisions ? divisions.map(division => html`
                                <${MenuItem} value=${division.id} key=${division.id}>${division.name}</MenuItem>
                            `) : null}
                        <//>
                    <//>
                    <${TextField}
                            label="Email"
                            type="email"
                            fullWidth
                            variant="outlined"
                            name="email"
                            value=${employee.email}
                            onChange=${handleChange}
                    />
                    <${TextField}
                            label="Nama pengguna"
                            fullWidth
                            variant="outlined"
                            name="username"
                            value=${employee.username}
                            onChange=${handleChange}
                    />
                <//>
            <//>
            <${DialogActions}>
                <${Button} variant="outlined" onClick=${() => setEditOpen(false)}>Batal<//>
                <${Button} variant="contained" onClick=${() => setEditOpen(false)}>Simpan<//>
            <//>
        <//>
    `;
}

export default Employee;
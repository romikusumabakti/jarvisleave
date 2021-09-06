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
    const [roles, setRoles] = useState();
    const [divisions, setDivisions] = useState();

    const [editOpen, setEditOpen] = useState(false);
    const [editErrors, setEditErrors] = useState({});
    const [employee, setEmployee] = useState({});
    
    const [savedOpen, setSavedOpen] = useState(false);

    const [deletedOpen, setDeletedOpen] = useState(false);
    const [deleted, setDeleted] = useState();
    const [deletedIndex, setDeletedIndex] = useState();

    useEffect(() => {
        api('/employee')
            .then(response => response.json())
            .then(employees => {
                setEmployees(employees);
            });

        api('/role')
            .then(response => response.json())
            .then(roles => {
                setRoles(roles);
            });

        api('/division')
            .then(response => response.json())
            .then(divisions => {
                setDivisions(divisions);
            });
    }, []);

    const handleChange = event => {
        const key = event.target.name;
        const value = event.target.value;
        if (value !== '') {
            setEmployee({...employee, [key]: value});
        } else {
            setEmployee({...employee, [key]: null});
        }
    };

    const edit = id => {
        api('/employee/' + id)
            .then(response => response.json())
            .then(employee => {
                setEmployee({
                    ...employee,
                    role: employee.role.id,
                    division: employee.division.id,
                });
                setEditOpen(true);
            });
    };

    const cancelEdit = () => {
      setEmployee({});
      setEditErrors({});
      setEditOpen(false);
    };

    const save = async event => {
        event.preventDefault();
        const response = await api('/employee', 'POST', employee)
        if (response.ok) {
            const employee = await response.json();
            setEmployees(employees.map(e => (e.id === employee.id) ? employee : e));
            setEmployee({});
            setEditOpen(false);
            setSavedOpen(true);
        } else {
            const errors = await response.json();
            setEditErrors(errors);
        }
    };

    const del = id => {
        api('/employee/' + id, 'DELETE')
        .then(response => {
            if (response.ok) {
                setDeleted(employees.find(employee => employee.id === id));
                setDeletedIndex(employees.findIndex(employee => employee.id === id));
                setEmployees(employees.filter(employee => employee.id !== id));
                setDeletedOpen(true);
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
                setDeletedOpen(false);
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
                <${Stack} direction="row" gap=${2}>
                    <${Button} variant="outlined" startIcon=${html`<${MaterialIcon}>description<//>`} onClick=${() => setEditOpen(true)}>
                        Ekspor ke Excel
                    <//>
                    <${Button} variant="contained" startIcon=${html`<${MaterialIcon}>add<//>`} onClick=${() => setEditOpen(true)}>
                        Buat karyawan
                    <//>
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
                            <${TableCell}>${employee.role.name}<//>
                            <${TableCell}>${employee.division.name}<//>
<!--                            <${TableCell}>${employee.email}<//>-->
                            <${TableCell}>${employee.username}<//>
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
        <${Dialog}
                open=${editOpen}
                onClose=${cancelEdit}
                scroll="paper"
                fullWidth
                component="form"
                onSubmit=${save}
        >
            <${DialogTitle}>${employee.id ? 'Edit' : 'Buat'} karyawan<//>
            <${DialogContent} dividers>
                <input type="hidden" name="id" value=${employee.id}/>
                <${Stack} spacing=${3}>
                    <${TextField}
                            label="NIP"
                            fullWidth
                            variant="outlined"
                            name="nip"
                            value=${employee.nip}
                            onChange=${handleChange}
                            required
                            autoFocus=${!employee.id}
                            error=${editErrors.nip}
                            helperText=${editErrors.nip}
                    />
                    <${TextField}
                            label="Nama"
                            fullWidth
                            variant="outlined"
                            name="name"
                            value=${employee.name}
                            onChange=${handleChange}
                            required
                    />
                    <${FormControl} fullWidth>
                        <${InputLabel}>Role<//>
                        <${Select}
                                label="Role"
                                name="role"
                                value=${employee.role}
                                onChange=${handleChange}
                                required
                        >
                            ${roles ? roles.map(role => html`
                                <${MenuItem} value=${role.id} key=${role.id}>${role.name}<//>
                            `) : null}
                        <//>
                    <//>
                    <${FormControl} fullWidth>
                        <${InputLabel}>Divisi<//>
                        <${Select}
                                label="Divisi"
                                name="division"
                                value=${employee.division}
                                onChange=${handleChange}
                                required
                        >
                            ${divisions ? divisions.map(division => html`
                                <${MenuItem} value=${division.id} key=${division.id}>${division.name}<//>
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
                            required
                            error=${editErrors.email}
                            helperText=${editErrors.email}
                    />
                    <${TextField}
                            label="Nama pengguna"
                            fullWidth
                            variant="outlined"
                            name="username"
                            value=${employee.username}
                            onChange=${handleChange}
                            required
                            error=${editErrors.username}
                            helperText=${editErrors.username}
                    />
                    <${TextField}
                            label="Kata sandi${employee.id ? ' ' + (employee.password ? '(diubah)' : '(tidak diubah)') : ''}"
                            type="password"
                            fullWidth
                            variant="outlined"
                            name="password"
                            value=${employee.password}
                            onChange=${handleChange}
                            required=${!employee.id}
                    />
                <//>
            <//>
            <${DialogActions}>
                <${Button} variant="outlined" type="reset" onClick=${cancelEdit}>Batal<//>
                <${Button} variant="contained" type="submit">Simpan<//>
            <//>
        <//>
        <${Snackbar}
                open=${savedOpen}
                autoHideDuration=${2750}
                onClose=${() => setSavedOpen(false)}
                message="Karyawan disimpan."
                action=${html`
                    <${IconButton}
                            size="small"
                            aria-label="close"
                            color="inherit"
                            onClick=${() => setSavedOpen(false)}
                    >
                        <${MaterialIcon} size="small" >close<//>
                    <//>
                `}
        />
        <${Snackbar}
                open=${deletedOpen}
                autoHideDuration=${6000}
                onClose=${() => setDeletedOpen(false)}
                message="1 karyawan dihapus."
                action=${html`
                    <${Button} size="small" color="inherit" onClick=${cancelDelete}>
                        Batal
                    <//>
                    <${IconButton}
                            size="small"
                            aria-label="close"
                            color="inherit"
                            onClick=${() => setDeletedOpen(false)}
                    >
                        <${MaterialIcon} size="small" >close<//>
                    <//>
                `}
        />
    `;
}

export default Employee;
import {useEffect, useState} from '../../../modules/react.js';
import {
    Button,
    CircularProgress,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    IconButton,
    InputLabel,
    MenuItem,
    Paper,
    Select,
    Snackbar,
    Stack,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    TextField,
    Tooltip,
    Typography
} from '../../../modules/material-ui.js';
import html from '../../../modules/htm.js';
import MaterialIcon from "../../../components/MaterialIcon.js"
import {api, jsonApi} from "../../../utils/api.js"

function Employees() {

    const [employees, setEmployees] = useState();
    const [roles, setRoles] = useState();
    const [divisions, setDivisions] = useState();

    const [notification, setNotification] = useState();

    const [edited, setEdited] = useState(null);
    const [editErrors, setEditErrors] = useState({});

    const [deleted, setDeleted] = useState();
    const [deletedIndex, setDeletedIndex] = useState();

    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        api('/employees')
            .then(response => response.json())
            .then(employees => {
                setEmployees(employees);
            });

        api('/roles')
            .then(response => response.json())
            .then(roles => {
                setRoles(roles);
            });

        api('/divisions')
            .then(response => response.json())
            .then(divisions => {
                setDivisions(divisions);
            });
    }, []);

    const handleChange = event => {
        const key = event.target.name;
        const value = event.target.value;
        if (value !== '') {
            setEdited({...edited, [key]: value});
        } else {
            setEdited({...edited, [key]: null});
        }
    };

    const edit = id => {
        api('/employees/' + id)
            .then(response => response.json())
            .then(employee => {
                setEdited({
                    ...employee,
                    role: employee.role.id,
                    division: employee.division.id,
                });
            });
    };

    const cancelEdit = () => {
        setEdited(null);
        setEditErrors({});
    };

    const save = async event => {
        event.preventDefault();
        setIsLoading(true);
        const response = await jsonApi('/employees', edited.id ? 'PUT' : 'POST', edited)
        if (response.ok) {
            const employee = await response.json();
            if (edited.id) {
                setEmployees(employees.map(e => (e.id === employee.id) ? employee : e));
            } else {
                setEmployees([...employees, employee]);
                setIsLoading(false);
            }
            setEdited(null);
            if (!edited.id) {
                if (!edited.password) {
                    setNotification("Karyawan direkrut. Kata sandi dikirim ke email karyawan.");
                } else {
                    setNotification("Karyawan direkrut.");
                }
            } else {
                setNotification("Karyawan disimpan.");
            }
        } else {
            const errors = await response.json();
            setEditErrors(errors);
            setIsLoading(false);
        }
    };

    const del = id => {
        api('/employees/' + id, 'DELETE')
            .then(response => {
                if (response.ok) {
                    setDeleted(employees.find(employee => employee.id === id));
                    setDeletedIndex(employees.findIndex(employee => employee.id === id));
                    setEmployees(employees.filter(employee => employee.id !== id));
                }
            });
    };

    const cancelDelete = () => {
        api('/employees/' + deleted.id, 'POST')
            .then(response => {
                if (response.ok) {
                    setEmployees([...employees.slice(0, deletedIndex), deleted, ...employees.slice(deletedIndex)]);
                    setDeleted(null);
                    setDeletedIndex(null);
                }
            });
    };

    const importFromExcel = async () => {
        const input = document.createElement('input');
        input.type = 'file';
        input.accept = '.xlsx';
        input.click();
        input.onchange = () => {
            const formData = new FormData();
            formData.append('file', input.files[0]);
            api('/employees/excel', 'POST', formData)
                .then(response => response.json())
                .then(employees => {
                    setNotification('Daftar karyawan diimpor dari Excel.');
                    setEmployees(employees);
                });
        };
    };

    const exportToExcel = async () => {
        const response = await api('/employees/excel');
        const filename = response.headers.get('Content-Disposition').split('filename=')[1];
        response.blob().then(excel => {
            const a = document.createElement('a');
            a.href = window.URL.createObjectURL(excel);
            a.download = filename;
            a.click();
            setNotification('Daftar karyawan diekspor ke Excel.');
        })
    };

    return html`
        <${Stack} p=${2} spacing=${2}>
            <${Stack} direction="row" justifyContent="space-between">
                <${Stack} direction="row" alignItems="center" gap=${1}>
                    <${MaterialIcon}>people<//>
                    <${Typography} variant="h5">Karyawan<//>
                <//>
                <${Stack} direction="row" gap=${2}>
                    <${Button} variant="contained" startIcon=${html`<${MaterialIcon}>add<//>`} onClick=${() => setEdited({})}>
                        Rekrut karyawan
                    <//>
                    <${Button} variant="outlined" startIcon=${html`<${MaterialIcon}>file_upload<//>`} onClick=${importFromExcel}>
                        Impor dari Excel
                    <//>
                    <${Button} variant="outlined" startIcon=${html`<${MaterialIcon}>file_download<//>`} onClick=${exportToExcel}>
                        Ekspor ke Excel
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
                                    <${Stack} direction="row" spacing=${2} justifyContent="flex-end">
                                        <${Tooltip} title="Edit karyawan">
                                            <${IconButton} onClick=${() => edit(employee.id)}>
                                                <${MaterialIcon}>edit<//>
                                            <//>
                                        <//>
                                        <${Tooltip} title="Hapus karyawan">
                                            <${IconButton} onClick=${() => del(employee.id)}>
                                                <${MaterialIcon}>delete<//>
                                            <//>
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
                open=${edited !== null}
                onClose=${cancelEdit}
                scroll="paper"
                fullWidth
                component="form"
                onSubmit=${save}
        >
            <${DialogTitle}>${edited?.id ? 'Edit' : 'Rekrut'} karyawan<//>
            <${DialogContent} dividers>
                <input type="hidden" name="id" value=${edited?.id}/>
                <${Stack} spacing=${3}>
                    <${TextField}
                            label="NIP"
                            fullWidth
                            variant="outlined"
                            name="nip"
                            value=${edited?.nip}
                            onChange=${handleChange}
                            required
                            autoFocus=${!edited?.id}
                            error=${editErrors.nip}
                            helperText=${editErrors.nip}
                    />
                    <${TextField}
                            label="Nama"
                            fullWidth
                            variant="outlined"
                            name="name"
                            value=${edited?.name}
                            onChange=${handleChange}
                            required
                    />
                    <${FormControl} fullWidth>
                        <${InputLabel}>Role<//>
                        <${Select}
                                label="Role"
                                name="role"
                                value=${edited?.role}
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
                                value=${edited?.division}
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
                            value=${edited?.email}
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
                            value=${edited?.username}
                            onChange=${handleChange}
                            required
                            error=${editErrors.username}
                            helperText=${editErrors.username}
                    />
                    <${TextField}
                            label="Kata sandi ${edited?.id ? (edited?.password ? '(diubah)' : '(tidak diubah)') : (edited?.password ? '(manual)' : '(acak dan dikirim melalui email)')}"
                            type="password"
                            fullWidth
                            variant="outlined"
                            name="password"
                            value=${edited?.password}
                            onChange=${handleChange}
                    />
                <//>
            <//>
            <${DialogActions}>
                <${Button} variant="outlined" type="reset" onClick=${cancelEdit} disabled=${isLoading}>Batal<//>
                <${Button} variant="contained" type="submit" disabled=${isLoading}>Simpan<//>
            <//>
        <//>
        <${Snackbar}
                open=${notification}
                autoHideDuration=${2750}
                onClose=${() => setNotification(null)}
                message=${notification}
                action=${html`
                    <${IconButton}
                            size="small"
                            aria-label="close"
                            color="inherit"
                            onClick=${() => setNotification(null)}
                    >
                        <${MaterialIcon} size="small" >close<//>
                    <//>
                `}
        />
        <${Snackbar}
                open=${deleted}
                autoHideDuration=${6000}
                onClose=${() => setDeleted(null)}
                message="1 karyawan dihapus."
                action=${html`
                    <${Button} size="small" color="inherit" onClick=${cancelDelete}>
                        Batal
                    <//>
                    <${IconButton}
                            size="small"
                            aria-label="close"
                            color="inherit"
                            onClick=${() => setDeleted(null)}
                    >
                        <${MaterialIcon} size="small" >close<//>
                    <//>
                `}
        />
    `;
}

export default Employees;
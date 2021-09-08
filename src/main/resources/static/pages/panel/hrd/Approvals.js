import {useEffect, useState} from '../../../modules/react.js';
import {
    Button,
    CircularProgress,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    IconButton,
    Paper,
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

function Approvals() {

    const dateFormat = new Intl.DateTimeFormat('id-ID', { weekday: 'long', day: 'numeric', month: 'long', year: 'numeric' });

    const [leaveSubmissions, setLeaveSubmissions] = useState();

    const [notification, setNotification] = useState();

    const [edited, setEdited] = useState(null);

    const [deleted, setDeleted] = useState();
    const [deletedIndex, setDeletedIndex] = useState();

    useEffect(() => {
        api('/leave_submissions')
            .then(response => response.json())
            .then(leaveSubmissions => {
                setLeaveSubmissions(leaveSubmissions);
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
        api('/leave_submissions/' + id)
            .then(response => response.json())
            .then(leaveSubmission => {
                setEdited({...leaveSubmission});
            });
    };

    const cancelEdit = () => {
        setEdited(null);
    };

    const save = async event => {
        event.preventDefault();
        jsonApi('/leave_submissions', edited.id ? 'PUT' : 'POST', edited)
            .then(response => response.json())
            .then(leaveSubmission => {
                if (edited.id) {
                    setLeaveSubmissions(leaveSubmissions.map(e => (e.id === leaveSubmission.id) ? leaveSubmission : e));
                } else {
                    setLeaveSubmissions([...leaveSubmissions, leaveSubmission]);
                }
                setEdited(null);
                setNotification("Persetujuan disimpan.");
            });
    };

    const del = id => {
        api('/leave_submissions/' + id, 'DELETE')
            .then(response => {
                if (response.ok) {
                    setDeleted(leaveSubmissions.find(leaveSubmission => leaveSubmission.id === id));
                    setDeletedIndex(leaveSubmissions.findIndex(leaveSubmission => leaveSubmission.id === id));
                    setLeaveSubmissions(leaveSubmissions.filter(leaveSubmission => leaveSubmission.id !== id));
                }
            });
    };

    const cancelDelete = () => {
        api('/leave_submissions/' + deleted.id, 'POST')
            .then(response => {
                if (response.ok) {
                    setLeaveSubmissions([...leaveSubmissions.slice(0, deletedIndex), deleted, ...leaveSubmissions.slice(deletedIndex)]);
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
            api('/leave_submissions/excel', 'POST', formData)
                .then(setNotification('Daftar persetujuan diimpor dari Excel.'));
        };
    };

    const exportToExcel = async () => {
        const response = await api('/leave_submissions/excel');
        const filename = response.headers.get('Content-Disposition').split('filename=')[1];
        response.blob().then(excel => {
            const a = document.createElement('a');
            a.href = window.URL.createObjectURL(excel);
            a.download = filename;
            a.click();
            setNotification('Daftar persetujuan diekspor ke Excel.');
        })
    };

    return html`
        <${Stack} p=${2} spacing=${2}>
            <${Stack} direction="row" justifyContent="space-between">
                <${Stack} direction="row" alignItems="center" gap=${1}>
                    <${MaterialIcon}>rule<//>
                    <${Typography} variant="h5">Persetujuan<//>
                <//>
                <${Stack} direction="row" gap=${2}>
                    <${Button} variant="outlined" startIcon=${html`<${MaterialIcon}>file_download<//>`} onClick=${exportToExcel}>
                        Ekspor ke Excel
                    <//>
                <//>
            <//>
            <${TableContainer} component=${Paper}>
                <${Table}>
                    <${TableHead}>
                        <${TableRow}>
                            <${TableCell}>Karyawan<//>
                            <${TableCell}>Pengganti<//>
                            <${TableCell}>Durasi<//>
                            <${TableCell}>Deskripsi<//>
                            <${TableCell}><//>
                        <//>
                    <//>
                    <${TableBody}>
                        ${leaveSubmissions ? leaveSubmissions.map(leaveSubmission => html`
                            <${TableRow}>
                                <${TableCell}>${leaveSubmission.employee.name}<//>
                                <${TableCell}>${leaveSubmission.replacement.name}<//>
                                <${TableCell}>${leaveSubmission.duration} hari<//>
                                <${TableCell}>${leaveSubmission.description}<//>
                                <${TableCell}>
                                    <${Stack} direction="row" spacing=${2} justifyContent="flex-end">
                                        <${Tooltip} title="Setujui pengajuan">
                                            <${IconButton} onClick=${() => edit(leaveSubmission.id)}>
                                                <${MaterialIcon}>done<//>
                                            <//>
                                        <//>
                                        <${Tooltip} title="Tolak pengajuan">
                                            <${IconButton} onClick=${() => del(leaveSubmission.id)}>
                                                <${MaterialIcon}>close<//>
                                            <//>
                                        <//>
                                        <${Tooltip} title="Detail pengajuan">
                                            <${IconButton} onClick=${() => edit(leaveSubmission.id)}>
                                                <${MaterialIcon}>info<//>
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
            <${DialogTitle}>${edited?.id ? 'Edit' : 'Buat'} persetujuan<//>
            <${DialogContent} dividers>
                <input type="hidden" name="id" value=${edited?.id}/>
                <${Stack} spacing=${3}>
                    <${TextField}
                            label="Nama"
                            fullWidth
                            variant="outlined"
                            name="name"
                            value=${edited?.name}
                            onChange=${handleChange}
                            required
                            autoFocus=${!edited?.id}
                    />
                    <${TextField}
                            label="Deskripsi"
                            fullWidth
                            variant="outlined"
                            name="description"
                            value=${edited?.description}
                            onChange=${handleChange}
                            required
                    />
                    <${TextField}
                            label="Tanggal"
                            fullWidth
                            variant="outlined"
                            name="date"
                            value=${edited?.date}
                            onChange=${handleChange}
                            required
                    />
                <//>
            <//>
            <${DialogActions}>
                <${Button} variant="outlined" type="reset" onClick=${cancelEdit}>Batal<//>
                <${Button} variant="contained" type="submit">Simpan<//>
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
                message="1 persetujuan dihapus."
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

export default Approvals;
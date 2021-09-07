import {useEffect, useState} from '../../modules/react.js';
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
} from '../../modules/material-ui.js';
import html from '../../modules/htm.js';
import MaterialIcon from "../../components/MaterialIcon.js"
import {api, jsonApi} from "../../utils/api.js"

function Divisions() {

    const [divisions, setDivisions] = useState();

    const [notification, setNotification] = useState();

    const [edited, setEdited] = useState(null);

    const [deleted, setDeleted] = useState();
    const [deletedIndex, setDeletedIndex] = useState();

    useEffect(() => {
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
        api('/divisions/' + id)
            .then(response => response.json())
            .then(division => {
                setEdited({...division});
            });
    };

    const cancelEdit = () => {
        setEdited(null);
    };

    const save = async event => {
        event.preventDefault();
        jsonApi('/divisions', edited.id ? 'PUT' : 'POST', edited)
            .then(response => response.json())
            .then(division => {
                if (edited.id) {
                    setDivisions(divisions.map(e => (e.id === division.id) ? division : e));
                } else {
                    setDivisions([...divisions, division]);
                }
                setEdited(null);
                setNotification("Divisi disimpan.");
            });
    };

    const del = id => {
        api('/divisions/' + id, 'DELETE')
            .then(response => {
                if (response.ok) {
                    setDeleted(divisions.find(division => division.id === id));
                    setDeletedIndex(divisions.findIndex(division => division.id === id));
                    setDivisions(divisions.filter(division => division.id !== id));
                }
            });
    };

    const cancelDelete = () => {
        api('/divisions/' + deleted.id, 'POST')
            .then(response => {
                if (response.ok) {
                    setDivisions([...divisions.slice(0, deletedIndex), deleted, ...divisions.slice(deletedIndex)]);
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
            api('/divisions/excel', 'POST', formData)
                .then(setNotification('Daftar divisi diimpor dari Excel.'));
        };
    };

    const exportToExcel = async () => {
        const response = await api('/divisions/excel');
        const filename = response.headers.get('Content-Disposition').split('filename=')[1];
        response.blob().then(excel => {
            const a = document.createElement('a');
            a.href = window.URL.createObjectURL(excel);
            a.download = filename;
            a.click();
            setNotification('Daftar divisi diekspor ke Excel.');
        })
    };

    return html`
        <${Stack} p=${2} spacing=${2}>
            <${Stack} direction="row" justifyContent="space-between">
                <${Stack} direction="row" alignItems="center" gap=${1}>
                    <${MaterialIcon}>groups<//>
                    <${Typography} variant="h5">Divisi<//>
                <//>
                <${Stack} direction="row" gap=${2}>
                    <${Button} variant="contained" startIcon=${html`<${MaterialIcon}>add<//>`} onClick=${() => setEdited({})}>
                        Buat divisi
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
                            <${TableCell}>Nama<//>
                            <${TableCell}><//>
                        <//>
                    <//>
                    <${TableBody}>
                        ${divisions ? divisions.map(division => html`
                            <${TableRow}>
                                <${TableCell}>${division.name}<//>
                                <${TableCell}>
                                    <${Stack} direction="row" spacing=${2} justifyContent="flex-end">
                                        <${Tooltip} title="Edit divisi">
                                            <${IconButton} onClick=${() => edit(division.id)}>
                                                <${MaterialIcon}>edit<//>
                                            <//>
                                        <//>
                                        <${Tooltip} title="Hapus divisi">
                                            <${IconButton} onClick=${() => del(division.id)}>
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
            <${DialogTitle}>${edited?.id ? 'Edit' : 'Buat'} divisi<//>
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
                message="1 divisi dihapus."
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

export default Divisions;
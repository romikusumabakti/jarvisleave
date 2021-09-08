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

function Holidays() {

    const dateFormat = new Intl.DateTimeFormat('id-ID', { weekday: 'long', day: 'numeric', month: 'long', year: 'numeric' });

    const [holidays, setHolidays] = useState();

    const [notification, setNotification] = useState();

    const [edited, setEdited] = useState(null);

    const [deleted, setDeleted] = useState();
    const [deletedIndex, setDeletedIndex] = useState();

    useEffect(() => {
        api('/holidays')
            .then(response => response.json())
            .then(holidays => {
                setHolidays(holidays);
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
        api('/holidays/' + id)
            .then(response => response.json())
            .then(holiday => {
                setEdited({...holiday});
            });
    };

    const cancelEdit = () => {
        setEdited(null);
    };

    const save = async event => {
        event.preventDefault();
        jsonApi('/holidays', edited.id ? 'PUT' : 'POST', edited)
            .then(response => response.json())
            .then(holiday => {
                if (edited.id) {
                    setHolidays(holidays.map(e => (e.id === holiday.id) ? holiday : e));
                } else {
                    setHolidays([...holidays, holiday]);
                }
                setEdited(null);
                setNotification("Hari libur disimpan.");
            });
    };

    const del = id => {
        api('/holidays/' + id, 'DELETE')
            .then(response => {
                if (response.ok) {
                    setDeleted(holidays.find(holiday => holiday.id === id));
                    setDeletedIndex(holidays.findIndex(holiday => holiday.id === id));
                    setHolidays(holidays.filter(holiday => holiday.id !== id));
                }
            });
    };

    const cancelDelete = () => {
        api('/holidays/' + deleted.id, 'POST')
            .then(response => {
                if (response.ok) {
                    setHolidays([...holidays.slice(0, deletedIndex), deleted, ...holidays.slice(deletedIndex)]);
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
            api('/holidays/excel', 'POST', formData)
                .then(setNotification('Daftar hari libur diimpor dari Excel.'));
        };
    };

    const exportToExcel = async () => {
        const response = await api('/holidays/excel');
        const filename = response.headers.get('Content-Disposition').split('filename=')[1];
        response.blob().then(excel => {
            const a = document.createElement('a');
            a.href = window.URL.createObjectURL(excel);
            a.download = filename;
            a.click();
            setNotification('Daftar hari libur diekspor ke Excel.');
        })
    };

    return html`
        <${Stack} p=${2} spacing=${2}>
            <${Stack} direction="row" justifyContent="space-between">
                <${Stack} direction="row" alignItems="center" gap=${1}>
                    <${MaterialIcon}>date_range<//>
                    <${Typography} variant="h5">Hari libur<//>
                <//>
                <${Stack} direction="row" gap=${2}>
                    <${Button} variant="contained" startIcon=${html`<${MaterialIcon}>add<//>`} onClick=${() => setEdited({})}>
                        Buat hari libur
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
                            <${TableCell}>Deskripsi<//>
                            <${TableCell}>Tanggal<//>
                            <${TableCell}><//>
                        <//>
                    <//>
                    <${TableBody}>
                        ${holidays ? holidays.map(holiday => html`
                            <${TableRow}>
                                <${TableCell}>${holiday.name}<//>
                                <${TableCell}>${holiday.description}<//>
                                <${TableCell}>${dateFormat.format(new Date(holiday.date))}<//>
                                <${TableCell}>
                                    <${Stack} direction="row" spacing=${2} justifyContent="flex-end">
                                        <${Tooltip} title="Edit hari libur">
                                            <${IconButton} onClick=${() => edit(holiday.id)}>
                                                <${MaterialIcon}>edit<//>
                                            <//>
                                        <//>
                                        <${Tooltip} title="Hapus hari libur">
                                            <${IconButton} onClick=${() => del(holiday.id)}>
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
            <${DialogTitle}>${edited?.id ? 'Edit' : 'Buat'} hari libur<//>
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
                    <input
                            type="date"
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
                message="1 hari libur dihapus."
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

export default Holidays;
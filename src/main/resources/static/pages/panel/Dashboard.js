import {useContext, useEffect, useState} from '../../modules/react.js';
import {
    Button, Card, CardActionArea, CardActions, CardContent,
    CircularProgress, Grid,
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
    Typography
} from '../../modules/material-ui.js';
import html from '../../modules/htm.js';
import MaterialIcon from "../../components/MaterialIcon.js"
import api from "../../utils/api.js"
import {AuthContext} from "../../App.js"

function Dashboard() {

    const { user } = useContext(AuthContext);

    const [employees, setEmployees] = useState();
    const [deleteOpen, setDeleteOpen] = useState();
    const [deleted, setDeleted] = useState();
    const [deletedIndex, setDeletedIndex] = useState();

    useEffect(() => {
        api('/employee')
            .then(response => response.json())
            .then(employees => {
                setEmployees(employees);
            });
    }, []);

    const del = (id) => {
        api('/employee/' + id, 'DELETE')
            .then(() => {
                setDeleted(employees.find(employee => employee.id === id));
                setDeletedIndex(employees.findIndex(employee => employee.id === id));
                setEmployees(employees.filter(employee => employee.id !== id));
                setDeleteOpen(true);
            });
    };

    const cancelDelete = () => {
        api('/employee/' + deleted.id, 'POST')
            .then(() => {
                setEmployees([...employees.slice(0, deletedIndex), deleted, ...employees.slice(deletedIndex)]);
                setDeleted(null);
                setDeletedIndex(null);
                setDeleteOpen(false);
            });
    };

    return html`
        <${Stack} p=${2} spacing=${2}>
            <${Stack} direction="row" justifyContent="space-between">
                <${Stack} direction="row" alignItems="center" gap=${1}>
                    <${MaterialIcon}>home<//>
                    <${Typography} variant="h5">Dasbor<//>
                <//>
                <${Button} variant="contained" startIcon=${html`<${MaterialIcon}>add<//>`}>
                    Buat
                <//>
            <//>
            <${Typography} variant="h4">Hai, ${user.name}!<//>
            <${Grid} container spacing=${2} sx=${{ paddingLeft: '-24px' }}>
                <${Grid} item>
                    <${Card}>
                        <${CardActionArea} sx=${{ p: 4 }}>
                            <${Typography} variant="h5">Karyawan<//>
                        <//>
                    <//>
                <//>
                <${Grid} item>
                    <${Card}>
                        <${CardActionArea} sx=${{ p: 4 }}>
                            <${Typography} variant="h5">Karyawan<//>
                        <//>
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
    `;
}

export default Dashboard;
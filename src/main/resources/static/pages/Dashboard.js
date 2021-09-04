import {useEffect, useState} from '../js/react.js';
import {
    Button,
    CircularProgress,
    IconButton,
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow
} from '../js/material-ui.js';
import html from '../js/htm.js';
import MaterialIcon from "../components/MaterialIcon.js"

function Dashboard() {
    const [employees, setEmployees] = useState();

    useEffect(() => {
        fetch('/api/employee')
            .then(response => response.json())
            .then(employees => {
                setEmployees(employees);
            });
    }, []);

    return html`
        <main>
            <h1>Dasbor</h1>
            <${Table}>
                <${TableHead}>
                    <${TableRow}>
                        <${TableCell}>NIP<//>
                        <${TableCell}>Nama lengkap<//>
                        <${TableCell}>Divisi<//>
                        <${TableCell}>Email<//>
                        <${TableCell}>Nama pengguna<//>
                        <${TableCell}>Role<//>
                        <${TableCell}><//>
                    <//>
                <//>
                <${TableBody}>
                ${employees ? employees.map(employee => html`
                    <${TableRow}>
                        <${TableCell}>${employee.nip}<//>
                        <${TableCell}>${employee.namaLengkap}<//>
                        <${TableCell}>${employee.divisi}<//>
                        <${TableCell}>${employee.email}<//>
                        <${TableCell}>${employee.username}<//>
                        <${TableCell}>${employee.role.nama}<//>
                        <${TableCell}>
                            <${IconButton}>
                                <${MaterialIcon}>edit<//>
                            <//>
                            <${IconButton}>
                                <${MaterialIcon}>delete<//>
                            <//>
                        <//>
                    <//>
                `) : html`<${CircularProgress} />`}
                <//>
            <//>
        </main>
    `;
}

export default Dashboard;
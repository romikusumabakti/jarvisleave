import {html} from '../js/standalone.module.js';
import {useEffect, useState} from "../js/hooks.module.js";

import Button from "../components/Button.js";
import Table from "../components/Table.js";
import TableHeader from "../components/TableHeader.js";
import TableHeaderCell from "../components/TableHeaderCell.js";
import TableContent from "../components/TableContent.js";
import TableRow from "../components/TableRow.js";
import TableCell from "../components/TableCell.js";

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
            <${Table} label="Employee">
                <${TableHeader}>
                    <${TableHeaderCell}>NIP<//>
                    <${TableHeaderCell}>Nama lengkap<//>
                    <${TableHeaderCell}>Divisi<//>
                    <${TableHeaderCell}>Email<//>
                    <${TableHeaderCell}>Nama pengguna<//>
                    <${TableHeaderCell}>Role<//>
                    <${TableHeaderCell}><//>
                <//>
                <${TableContent}>
                ${employees ? employees.map(employee => html`
                    <${TableRow}>
                        <${TableCell}>${employee.nip}<//>
                        <${TableCell}>${employee.namaLengkap}<//>
                        <${TableCell}>${employee.divisi}<//>
                        <${TableCell}>${employee.email}<//>
                        <${TableCell}>${employee.username}<//>
                        <${TableCell}>${employee.role.nama}<//>
                        <${TableCell}>
                            <${Button} variant="contained">Edit<//>
                            <${Button} variant="outlined">Hapus<//>
                        <//>
                    <//>
                `) : 'Memuat...'}
                <//>
            <//>
        </main>
    `;
}

export default Dashboard;
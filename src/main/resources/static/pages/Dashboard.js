import {html} from '../js/standalone.module.js';
import {useEffect, useState} from "../js/hooks.module.js";

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
            <table>
                <thead>
                <tr>
                    <td>NIP</td>
                    <td>Nama lengkap</td>
                    <td>Divisi</td>
                    <td>Email</td>
                    <td>Nama pengguna</td>
                    <td>Role</td>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                ${employees ? employees.map(employee => html`
                    <tr>
                        <td>${employee.nip}</td>
                        <td>${employee.namaLengkap}</td>
                        <td>${employee.divisi}</td>
                        <td>${employee.email}</td>
                        <td>${employee.username}</td>
                        <td>${employee.role.nama}</td>
                        <td>
                            <button>Edit</button>
                            <button>Hapus</button>
                        </td>
                    </tr>
                `) : null}
                </tbody>
            </table>
        </main>
    `;
}

export default Dashboard;
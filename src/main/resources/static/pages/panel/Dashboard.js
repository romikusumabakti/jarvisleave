import {useContext, useEffect, useState} from '../../modules/react.js';
import {Button, Card, CardActionArea, Stack, Typography} from '../../modules/material-ui.js';
import html from '../../modules/htm.js';
import MaterialIcon from "../../components/MaterialIcon.js"
import {AuthContext} from "../../App.js"
import {api} from "../../utils/api.js"

function Dashboard() {

    const {user} = useContext(AuthContext);
    const [leaveAllowances, setLeaveAllowances] = useState();

    useEffect(() => {
        api('/user/leave_allowances')
            .then(response => response.json())
            .then(leaveAllowances => {
                setLeaveAllowances(leaveAllowances);
            });
    }, []);

    return html`
        <${Stack} p=${2} spacing=${2}>
            <${Stack} direction="row" justifyContent="space-between">
                <${Stack} direction="row" alignItems="center" gap=${1}>
                    <${MaterialIcon}>home<//>
                    <${Typography} variant="h5">Dasbor<//>
                <//>
                <${Button} variant="contained" startIcon=${html`<${MaterialIcon}>add<//>`}>
                    Ajukan cuti
                <//>
            <//>
            <${Typography} variant="h4">Hai, ${user.name}!<//>
            <${Stack} direction="row" spacing=${2}>
                ${leaveAllowances ? leaveAllowances.map(leaveAllowance => html`
                    <${Card}>
                        <${CardActionArea} sx=${{p: 4}}>
                            <${Typography} variant="h6">${leaveAllowance.type.name}<//>
                            <${Typography} variant="h4">${leaveAllowance.allowance}<//>
                        <//>
                    <//>
                `) : null}
            <//>
        <//>
    `;
}

export default Dashboard;
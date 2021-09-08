import html from '../modules/htm.js';
import {Button, Dialog, Stack, TextField, Typography} from "../modules/material-ui.js"
import {useContext, useState} from "../modules/react.js"
import JarvisIcon from "../components/JarvisIcon.js"
import {api, jsonApi} from "../utils/api.js"
import {AuthContext} from "../App.js"
import {useHistory} from "../modules/react-router-dom.js"

function Login(props) {

    const { setUser } = useContext(AuthContext);
    const history = useHistory();

    const [checked, setChecked] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [usernameError, setUsernameError] = useState(null);
    const [passwordError, setPasswordError] = useState(null);

    const check = async (event) => {
        event.preventDefault();
        setIsLoading(true);
        const response = await api(`/auth/check?username=${event.target.username.value}`);
        if (response.ok) {
            const checked = await response.json();
            setChecked(checked);
            setUsernameError(null);
        } else {
            const error = await response.text();
            setUsernameError(error);
            event.target.username.focus();
        }
        setIsLoading(false);
    };

    const login = async (event) => {
        event.preventDefault();
        setIsLoading(true);
        const response = await jsonApi('/auth/login', 'POST', {
            username: checked.username,
            password: event.target.password.value,
        });
        if (response.ok) {
            const token = await response.text();
            localStorage.setItem('token', token);
            setUser(checked);
            onClose();
            if (history.location.pathname === '/') {
                history.push('/panel');
            }
        } else {
            const error = await response.text();
            setPasswordError(error);
            event.target.password.focus();
        }
        setIsLoading(false);
    };

    const onClose = () => {
        if (!isLoading) {
            setChecked(null);
            props.onClose();
        }
    };

    return html`
        <${Dialog} open=${props.open} onClose=${onClose} fullWidth maxWidth="xs">
            <${Stack} p=${3} spacing=${3} component="form" onSubmit=${check} hidden=${checked !== null}>
                ${!checked ? html`
                    <${Stack} spacing=${1} alignItems="center">
                        <${JarvisIcon} size=${64} padding=${8}/>
                        <${Typography} variant="h5">
                            Login
                        <//>
                        <${Typography} variant="subtitle1">
                            menggunakan Akun JarvisLeave
                        <//>
                    <//>
                    <${TextField}
                            autoFocus
                            label="NIP, nama pengguna, atau email"
                            fullWidth
                            required
                            spellcheck="false"
                            name="username"
                            error=${usernameError !== null}
                            helperText=${usernameError}
                    />
                    <${Stack} direction="row" justifyContent="space-between">
                        <${Button} type="reset" onClick=${onClose} disabled=${isLoading}>Batal<//>
                        <${Button} type="submit" variant="contained" disabled=${isLoading}>Berikutnya<//>
                    <//>
                ` : null}
            <//>
            <${Stack} p=${3} spacing=${3} component="form" onSubmit=${login} hidden=${checked === null}>
                ${checked ? html`
                    <${Stack} spacing=${1} alignItems="center">
                        <${JarvisIcon} size=${64} padding=${8}/>
                        <${Typography} variant="h5">
                            Selamat datang
                        <//>
                        <${Stack} alignItems="center">
                            <${Typography} variant="h6">
                                ${checked.name}
                            <//>
                            <${Typography} variant="subtitle1">
                                Divisi ${checked.division.name}
                            <//>
                        <//>
                    <//>
                    <${TextField}
                            autoFocus
                            label="Kata sandi"
                            type="password"
                            fullWidth
                            required
                            name="password"
                            error=${passwordError !== null}
                            helperText=${passwordError}
                    />
                    <${Stack} direction="row" justifyContent="space-between">
                        <${Button} type="reset" onClick=${() => setChecked(null)} disabled=${isLoading}>Kembali<//>
                        <${Button} type="submit" variant="contained" disabled=${isLoading}>Login<//>
                    <//>
                ` : null}
            <//>
        <//>
    `;
}

export default Login;
import html from '../modules/htm.js';
import {
    Button,
    Dialog,
    DialogContent,
    Stack,
    TextField, Typography
} from "../modules/material-ui.js"
import {useContext, useState} from "../modules/react.js"
import JarvisIcon from "../components/JarvisIcon.js"
import api from "../utils/api.js"
import {AuthContext} from "../App.js"

function Login(props) {

    const { setUser } = useContext(AuthContext);
    const [checked, setChecked] = useState(null);
    const [loading, setLoading] = useState(false);
    const [idError, setIdError] = useState(null);
    const [passwordError, setPasswordError] = useState(null);

    const check = async (event) => {
        event.preventDefault();
        setLoading(true);
        const response = await api(`/auth/check?id=${event.target.id.value}`);
        if (response.ok) {
            const checked = await response.json();
            setChecked(checked);
            setIdError(null);
        } else {
            const error = await response.text();
            setIdError(error);
            event.target.id.focus();
        }
        setLoading(false);
    };

    const login = async (event) => {
        event.preventDefault();
        setLoading(true);
        const response = await api('/auth/login', 'POST', JSON.stringify({
            username: checked.username,
            password: event.target.password.value,
        }));
        if (response.ok) {
            const token = await response.text();
            localStorage.setItem('token', token);
            setUser(checked);
            onClose();
        } else {
            const error = await response.text();
            setPasswordError(error);
            event.target.password.focus();
        }
        setLoading(false);
    };

    const onClose = () => {
        if (!loading) {
            setChecked(null);
            props.onClose();
        }
    };

    return html`
        <${Dialog} open=${props.open} onClose=${onClose} fullWidth maxWidth="xs">
            <${DialogContent}>
                <${Stack} spacing=${3} component="form" onSubmit=${check} hidden=${checked !== null}>
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
                                id="id"
                                label="NIP, nama pengguna, atau email"
                                fullWidth
                                required
                                spellcheck="false"
                                InputLabelProps=${{required: false}}
                                name="id"
                                error=${idError !== null}
                                helperText=${idError}
                        />
                        <${Stack} direction="row" justifyContent="space-between">
                            <${Button} type="reset" onClick=${onClose} disabled=${loading}>Batal<//>
                            <${Button} type="submit" variant="contained" disabled=${loading}>Berikutnya<//>
                        <//>
                    ` : null}
                <//>
                <${Stack} spacing=${3} component="form" onSubmit=${login} hidden=${checked === null}>
                    ${checked ? html`
                        <${Stack} spacing=${1} alignItems="center">
                            <${JarvisIcon} size=${64} padding=${8}/>
                            <${Typography} variant="h5">
                                Selamat datang
                            <//>
                            <${Stack} alignItems="center">
                                <${Typography} variant="h6">
                                    ${checked.nama}
                                <//>
                                <${Typography} variant="subtitle1">
                                    Divisi ${checked.divisi.nama}
                                <//>
                            <//>
                        <//>
                        <${TextField}
                                autoFocus
                                id="password"
                                label="Kata sandi"
                                type="password"
                                fullWidth
                                required
                                InputLabelProps=${{required: false}}
                                name="password"
                                error=${passwordError !== null}
                                helperText=${passwordError}
                        />
                        <${Stack} direction="row" justifyContent="space-between">
                            <${Button} type="reset" onClick=${() => setChecked(null)} disabled=${loading}>Kembali<//>
                            <${Button} type="submit" variant="contained" disabled=${loading}>Login<//>
                        <//>
                    ` : null}
                <//>
            <//>
        <//>
    `;
}

export default Login;
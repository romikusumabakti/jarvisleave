import html from '../modules/htm.js';
import {
    Button,
    Dialog,
    DialogContent,
    Stack,
    TextField, Typography
} from "../modules/material-ui.js"
import {useState} from "../modules/react.js"
import JarvisIcon from "../components/JarvisIcon.js"

function Login(props) {

    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(false);
    const [idError, setIdError] = useState(null);

    const getUser = async (event) => {
        event.preventDefault();
        setLoading(true);
        fetch(`/api/auth/check?id=${event.target.id.value}`)
            .then(async response => {
                if (response.ok) {
                    const user = await response.json();
                    setUser(user);
                    setIdError(null);
                } else {
                    const error = await response.text();
                    setIdError(error);
                    event.target.id.focus();
                }
                setLoading(false);
            });
    };

    const onClose = () => {
        if (!loading) {
            setUser(null);
            props.onClose();
        }
    };

    return html`
        <${Dialog} open=${props.open} onClose=${onClose} fullWidth maxWidth="xs">
            <${DialogContent}>
                <${Stack} spacing=${3} component="form" onSubmit=${getUser} hidden=${user !== null}>
                    ${!user ? html`
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
                <${Stack} spacing=${3} component="form" hidden=${user === null}>
                    ${user ? html`
                        <${Stack} spacing=${1} alignItems="center">
                            <${JarvisIcon} size=${64} padding=${8}/>
                            <${Typography} variant="h5">
                                Selamat datang
                            <//>
                            <${Stack} alignItems="center">
                                <${Typography} variant="h6">
                                    ${user?.nama}
                                <//>
                                <${Typography} variant="subtitle1">
                                    Divisi ${user?.divisi.nama}
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
                        />
                        <${Stack} direction="row" justifyContent="space-between">
                            <${Button} type="reset" onClick=${() => setUser(null)} disabled=${loading}>Kembali<//>
                            <${Button} variant="contained" onClick=${getUser} disabled=${loading}>Login<//>
                        <//>
                    ` : null}
                <//>
            <//>
        <//>
    `;
}

export default Login;
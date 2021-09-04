import React, {lazy, Suspense, useState} from './modules/react.js';
import {
    CircularProgress,
    Container,
    createTheme,
    CssBaseline, Divider, Stack,
    ThemeProvider, Typography,
    useMediaQuery
} from './modules/material-ui.js';
import {BrowserRouter, Route} from './modules/react-router-dom.js';
import html from './modules/htm.js';
import Header from "./fragments/Header.js"
import Employee from "./pages/Employee.js"
import Login from "./fragments/Login.js"
import JarvisLogo from "./components/JarvisLogo.js"

const Dashboard = lazy(() => import('./pages/Employee.js'));

function App() {


    const [mode, setMode] = useState(window.localStorage.getItem('mode'));

    const handleMode = () => {
        if (!mode) {
            if (dark) {
                window.localStorage.setItem('mode', 'light');
            } else {
                window.localStorage.setItem('mode', 'dark');
            }
        } else {
            window.localStorage.removeItem('mode');
        }
        setMode(window.localStorage.getItem('mode'));
    };

    let dark = useMediaQuery('(prefers-color-scheme: dark)');

    if (mode === 'dark') {
        dark = true;
    } else if (mode === 'light') {
        dark = false;
    }

    const baseTheme = {
        palette: {
            mode: dark ? 'dark' : 'light',
            background: {
                paper: dark ? '#333' : 'white',
                default: dark ? '#1b1b1b' : '#eee',
            },
        },
    };

    let theme = createTheme(baseTheme);

    theme = createTheme({
        palette: {
            ...baseTheme.palette,
        },
        typography: {
            fontFamily: '"Google Sans", sans-serif',
        },
        shape: {
            borderRadius: 32,
        },
        components: {
            MuiAppBar: {
                styleOverrides: {
                    root: {
                        background: theme.palette.background.paper,
                        color: theme.palette.text.primary,
                    },
                },
            },
            MuiPaper: {
                styleOverrides: {
                    root: {
                        background: theme.palette.background.paper,
                    },
                },
            },
            MuiTableCell: {
                styleOverrides: {
                    root: {
                        paddingInline: 32,
                    },
                },
            },
            MuiOutlinedInput: {
                styleOverrides: {
                    root: {
                        borderRadius: 4,
                    },
                },
            },
            MuiButton: {
                styleOverrides: {
                    root: {
                        borderRadius: 18,
                        paddingInline: 18,
                        textTransform: 'unset',
                    },
                },
                defaultProps: {
                    disableElevation: true,
                },
            },
            MuiSnackbarContent: {
                styleOverrides: {
                    root: {
                        background: theme.palette.background.paper,
                        color: theme.palette.text.primary,
                    },
                },
                defaultProps: {
                    elevation: 1,
                },
            },
        },
        shadows: [
            'none',
            '0 0 4px #0001',
            '0 0 8px #0001',
            '0 0 12px #0001',
            '0 0 16px #0001',
        ]
    });

    const [loginOpen, setLoginOpen] = useState(false);

    return html`
    <${BrowserRouter}>
        <${ThemeProvider} theme=${theme}>
            <${CssBaseline} />
            <${Header} loginButtonOnClick=${() => setLoginOpen(true)} mode=${mode} handleMode=${handleMode}/>
            <${Container} maxWidth="xl">
                <${Suspense} fallback=${html`<${CircularProgress} />`}>
                    <${Route} exact path="/">
                        <h1>Ini halaman Dasbor.</h1>
                    <//>
                    <${Route} path="/monitoring">
                        <h1>Ini halaman Monitoring.</h1>
                    <//>
                    <${Route} path="/submission">
                        <h1>Ini halaman Pengajuan.</h1>
                    <//>
                    <${Route} path="/employee">
                        <${Employee}/>
                    <//>
                <//>
            <//>
            <${Divider} />
            <${Stack} p=${3} spacing=${1} alignItems="center">
                <${JarvisLogo} size=${32}/>
                <${Typography} variant="subtitle2">
                    © 2021 PUB Angkatan 18 (Jarvis)
                <//>
            <//>
            <${Login} open=${loginOpen} onClose=${() => setLoginOpen(false)}/>
        <//>
    <//>
`}

export default App;
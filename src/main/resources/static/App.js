import React, {createContext, Suspense, useEffect, useState} from './modules/react.js';
import {
    CircularProgress,
    createTheme,
    CssBaseline,
    Stack,
    ThemeProvider,
    useMediaQuery
} from './modules/material-ui.js';
import {BrowserRouter, Route} from './modules/react-router-dom.js';
import html from './modules/htm.js';
import Header from "./fragments/Header.js"
import Login from "./fragments/Login.js"
import NavDrawer from "./fragments/NavDrawer.js"
import Panel from "./pages/Panel.js"
import api from "./utils/api.js"

export const AuthContext = createContext();

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
            MuiList: {
                styleOverrides: {
                    root: {
                        paddingRight: 8,
                    },
                },
            },
            MuiListItemButton: {
                styleOverrides: {
                    root: {
                        borderTopRightRadius: 24,
                        borderBottomRightRadius: 24,
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
    const [user, setUser] = useState(null);

    useEffect(async () => {
        if (localStorage.getItem('token')) {
            const response = await api('/auth/me');
            if (response.ok) {
                const user = await response.json();
                setUser(user);
                console.log(user);
            } else {
                const error = await response.text();
                console.error(error);
            }
        }
    }, []);

    return html`
        <${AuthContext.Provider} value=${{ user, setUser }}>
            <${BrowserRouter}>
                <${ThemeProvider} theme=${theme}>
                    <${CssBaseline} />
                    <${Header} loginButtonOnClick=${() => setLoginOpen(true)} mode=${mode} handleMode=${handleMode} sx=${{ zIndex: (theme) => theme.zIndex.drawer + 1 }}/>
                    <${Suspense} fallback=${html`<${CircularProgress} />`}>
                        <${Route} exact path="/">
                            <h1>Ini halaman Beranda.</h1>
                        <//>
                        ${user ? html`
                            <${Route} path="/panel">
                                <${Panel}/>
                            <//>
                        ` : null}
                        <${Route} exact path="/about">
                            <h1>Ini halaman Tentang.</h1>
                        <//>
                    <//>
                    <${Login} open=${loginOpen} onClose=${() => setLoginOpen(false)}/>
                <//>
            <//>
        <//>
`}

export default App;